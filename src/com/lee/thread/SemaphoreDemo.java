package com.lee.thread;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
	public static void main(String[] args) {
		// 3个停车位
		Semaphore semaphore = new Semaphore(3);
		
		// 6辆车
		for (int i = 1; i <= 6; i++) {
			new Thread(() -> {
				try {
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName() + "\t" + "抢占了车位");
					TimeUnit.SECONDS.sleep(new Random().nextInt(10));
					System.out.println(Thread.currentThread().getName() + "\t" + "离开了车位-------------");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaphore.release();
				}
			}, String.valueOf(i)).start();
		}
	}
}
