package com.tcs.shiv.threadpools4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable{
	private int id;
	public Processor(int id){
		this.id =id;
	}
	
	@Override
	public void run() {
		System.out.println("Starting :" + id);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Completed :" + id);
	}

}

public class Test {
	public static void main(String[] args) {
		//It's like having no of workers in a factory . here nr of workers are 2 and they are instructions like you have 5 tasks and at a time work on a single task and 
		//once that finished , carry on with the next task
		//ExecutorService have its own managerial threads and by defining threadpool here will reduce the overhead of starting threads
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		for (int i=0;i<5;i++){
			executor.submit(new Processor(i));
		}
		executor.shutdown();
		System.out.println("All tasks submitted");
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All tasks Completed");
	}
}
