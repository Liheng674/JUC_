package com.lee.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @description ArrayList, HashSet, HashMap不安全的实例
 * 				CopyOnWriteArrayList, CopyOnWriteArraySet, ConcurrentHashMap
 * 
 * 				还可以使用Collections工具类的synchronizedList()等
 * @author Lee
 * @date 2018年3月18日
 * CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器Object[]进行Copy，
	复制出一个新的容器Object[] newElements，然后新的容器Object[] newElements里添加元素，添加完元素之后，
	再将原容器的引用指向新的容器 setArray(newElements);。这样做的好处是可以对CopyOnWrite容器进行并发的读，
	而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器
 */
class ListNotSafe {
	public static void main(String[] args) {
//		Map<String, String> map = new HashMap<>();
		Map<String, String> map = new ConcurrentHashMap<>();
		
		for (int i = 1; i < 30; i++) {
			new Thread(() -> {
				map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 4));
				System.out.println(map);
			}, String.valueOf(i)).start();
		}
	}

	public static void set() {
//		Set<String> set = new HashSet<>();
		Set<String> set = new CopyOnWriteArraySet<>();

		for (int i = 1; i <= 30; i++) {
			new Thread(() -> {
				set.add(UUID.randomUUID().toString().substring(0, 4));
				System.out.println(set);
			}, String.valueOf(i)).start();
		}
	}
	public static void list() {
		// List<String> list = new ArrayList<>();
		List<String> list = new CopyOnWriteArrayList<>();
		
		for (int i = 1; i <= 30; i++) {
			new Thread(() -> {
				list.add(UUID.randomUUID().toString().substring(0, 4));
				System.out.println(list);
			}, String.valueOf(i)).start();
		}
	}
}
