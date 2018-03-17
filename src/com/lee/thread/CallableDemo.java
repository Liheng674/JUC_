package com.lee.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		System.out.println("------------------Start");
		TimeUnit.SECONDS.sleep(3);
		return 200;
	}
	
}

/**
 * @description 第三种获取多线程的方法: 实现Callable接口
 * 				Callable接口与Runnable接口的区别: 
 * 						①. 方法不同
 * 						②. 有无返回值
 * 						②. 有无异常
 * 						②. 有无泛型
 * 				面向接口编程, 找一个类能够实现多个不相关的接口
 * @author Lee
 * @date 2018年3月17日
 * 
 *  在主线程中需要执行比较耗时的操作时，但又不想阻塞主线程时，可以把这些作业交给Future对象在后台完成，
	当主线程将来需要时，就可以通过Future对象获得后台作业的计算结果或者执行状态。
	
	一般FutureTask多用于耗时的计算，主线程可以在完成自己的任务后，再去获取结果。
	
	仅在计算完成时才能检索结果；如果计算尚未完成，则阻塞 get 方法。一旦计算完成，
	就不能再重新开始或取消计算。get方法而获取结果只有在计算完成时获取，否则会一直阻塞直到任务转入完成状态，
	然后会返回结果或者抛出异常。 
	
	只计算一次
	get方法放到最后
 */
public class CallableDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
//		FutureTask<Integer> ft = new FutureTask<>(new MyThread());
//		new Thread(ft, "AA").start();;
//		new Thread(ft, "BB").start();;
		FutureTask<Integer> ft = new FutureTask<Integer>( ()-> {
			System.out.println("lambda---------------call()");
			TimeUnit.SECONDS.sleep(2);
			return 200;} );
		new Thread(ft, "AA").start();
		System.out.println("--------------------main方法");
		
		Integer result = ft.get();
		System.out.println("result:\t" + result);
		
	}

}
