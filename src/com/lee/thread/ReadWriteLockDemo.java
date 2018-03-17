package com.lee.thread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyQueue{
	private Object obj;
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
	public void write(Object obj) {
		// 写锁
		lock.writeLock().lock();;
		try {
			this.obj = obj;
			System.out.println(Thread.currentThread().getName() + "-------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	public void read() {
		// 读锁
		lock.readLock().lock();;
		try {
			System.out.println(Thread.currentThread().getName() + "\t" + obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.readLock().unlock();
		}
	}
}

/**
 * @description 一个线程写入,100个线程读取
 * @author Lee
 * @date 2018年3月17日
 */
public class ReadWriteLockDemo {

	public static void main(String[] args) {
		MyQueue mq = new MyQueue();
		
		new Thread(() -> {
			mq.write("read");
		}, "Write").start();
		
		for (int i = 1; i <= 100; i++) {
			new Thread(() -> {
				mq.read();
			}, String.valueOf(i)).start();
		}
	}

}
