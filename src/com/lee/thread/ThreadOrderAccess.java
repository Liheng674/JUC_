package com.lee.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
	// 标记, 1-->打印A, 2-->打印B, 3-->打印C
	private int flag = 1;
	private Lock lock = new ReentrantLock();
	Condition c1 = lock.newCondition();
	Condition c2 = lock.newCondition();
	Condition c3 = lock.newCondition();
	
	public void printAA(Integer loop) {
		lock.lock();
		try {
			// 判断
			if(flag != 1) {
				c1.await();
			}
			// 干活
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + loop);
			}
			// 轮到B打印
			flag = 2;
			// 通知
			c2.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	public void printBB(Integer loop) {
		lock.lock();
		try {
			// 判断
			if(flag != 2) {
				c2.await();
			}
			// 干活
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + loop);
			}
			// 轮到C打印
			flag = 3;
			// 通知
			c3.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	public void printCC(Integer loop) {
		lock.lock();
		try {
			// 判断
			if(flag != 3) {
				c3.await();
			}
			// 干活
			for (int i = 0; i < 15; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + loop);
			}
			// 轮到A打印
			flag = 1;
			// 通知
			c1.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

/**
 * @description 
 * 多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下：
 * 
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * ......来10轮
 * @author Lee
 * @date 2018年3月17日
 */
public class ThreadOrderAccess {
	public static void main(String[] args) {
		ShareData sd = new ShareData();
		
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				sd.printAA(i);
			}
		}, "AA").start();
		
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				sd.printBB(i);
			}
		}, "BB").start();
		
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				sd.printCC(i);
			}
		}, "CC").start();
	}
}
