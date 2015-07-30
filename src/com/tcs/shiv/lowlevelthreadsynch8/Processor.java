package com.tcs.shiv.lowlevelthreadsynch8;

import java.util.LinkedList;
import java.util.Random;

public class Processor {

	private LinkedList<Integer> list = new LinkedList<Integer>();
	private int LIMIT =10;
	private Object lock = new Object();
	
	public void producer() throws InterruptedException {
		int value =0;
		
		while(true){
			synchronized (lock) {
				
				while(list.size() == LIMIT){
					lock.wait();
				}
				
				list.add(value++);
				lock.notify();
			}
		}
	}

	public void consumer() throws InterruptedException {
		Random random = new Random();
		
		while(true){
			synchronized (lock) {
				
				while(list.size() == 0){
					lock.wait();
				}
				
				System.out.print("List Size is:" + list.size());
				int value = list.removeFirst();
				System.out.println("; Value is:" + value);
				lock.notify();
			}
			Thread.sleep(random.nextInt(1000));
		}
	}
}
