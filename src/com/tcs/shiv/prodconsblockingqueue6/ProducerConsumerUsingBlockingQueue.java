package com.tcs.shiv.prodconsblockingqueue6;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class ProducerConsumerUsingBlockingQueue {
	
	//Thread-safe - all classes inside concurrent package are thread-safe
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
	
	public static void main(String[] args) throws InterruptedException {	
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					consumer();
					} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}
	
	
	//Producer method to add integers to queue
	private static void producer() throws InterruptedException{
		Random random = new Random();
		while(true){
			queue.put(random.nextInt(100));
		}
	}
	
	//Consumer method to remove integers from queue
	private static void consumer() throws InterruptedException{
		Random random = new Random();
		while(true){
			Thread.sleep(100);
			if(random.nextInt(10) ==0){
				Integer value = queue.take();
				System.out.println("Taken Value" + value + "; Queue Size is:" + queue.size());
			}
		}
	}

}
