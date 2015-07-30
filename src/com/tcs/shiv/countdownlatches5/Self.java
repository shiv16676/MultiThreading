package com.tcs.shiv.countdownlatches5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Machine implements Runnable{
	private CountDownLatch latch;
	
	public Machine(CountDownLatch latch){
		this.latch =latch;
	}
	
	@Override
	public void run() {
		System.out.println("Started..!!");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		latch.countDown();
	}
	
}

public class Self {
	public static void main(String[] args) {
		
		CountDownLatch latch = new CountDownLatch(3);
		ExecutorService executor = Executors.newFixedThreadPool(10);
		
		for(int i=0;i<10;i++){
			executor.submit(new Machine(latch));
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}

}
