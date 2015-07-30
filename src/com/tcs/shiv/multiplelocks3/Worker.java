package com.tcs.shiv.multiplelocks3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {

	// private instance data
	private Random random = new Random();
	
	//It's always a good practice to create separate locks objects to avoid any confusion.
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();
	
	//Instead of declaring method as synchronized, use synchronized code blocks having different locks
	public void stageOne() {
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
		}
	}
	//Instead of declaring method as synchronized, use synchronized code blocks having different locks
	public void stageTwo() {
		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list2.add(random.nextInt(100));
		}
	}

	public void process() {
		for (int i = 0; i < 1000; i++) {
			stageOne();
			stageTwo();
		}
	}
	
	//Main method
	public void main(String[] a) {
		System.out.println("Starting......");
		long start = System.currentTimeMillis();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				process();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				process();
			}
		});
		
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();

		System.out.println("Time taken" + " " + (end - start));
		System.out.println("List1:" + list1.size() + ";" + "List2:"
				+ list2.size());
	}
}
