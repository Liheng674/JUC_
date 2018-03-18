package com.lee.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @description 第四种获取多线程的方式
 * @author Lee
 * @date 2018年3月18日
 */
public class ExecutorsDemo {
	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
		ScheduledFuture<Integer> result = null;
		try {
			for (int i = 1; i <= 15; i++) {
				result = service.schedule(()->{
					System.out.print(Thread.currentThread().getName());
					return new Random().nextInt(10);
				}, 2, TimeUnit.SECONDS);
				System.out.println("\tresult: " +result.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			service.shutdown();
		}
	}

	public static void testExecutor() {
		// ExecutorService service = Executors.newFixedThreadPool(5);
		// ExecutorService service = Executors.newSingleThreadExecutor();
		ExecutorService service = Executors.newCachedThreadPool();
		Future<Integer> result = null;

		try {
			for (int i = 1; i <= 15; i++) {
				result = service.submit(() -> {
					System.out.print(Thread.currentThread().getName());
					return new Random().nextInt(10);
				});
				System.out.println("\tresult: " + result.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			service.shutdown();
		}
	}
}
