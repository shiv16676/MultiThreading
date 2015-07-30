package com.tcs.shiv.reentrantlocks9;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	private int count =0;
	private Lock lock = new ReentrantLock();
	
	private void increment(){
		for(int i=0;i<10000;i++){
			count++;
		}
	}
	
	public void firstThread() throws InterruptedException {
		//ReentrantLock can give lock many times but it keeps count of how many times a lock was given.So you need to unlock that many times
		lock.lock();
		try{
			increment();
		}finally{
			//Always release the lock inside the finally block so that if the "increment()" throws any exception , it should not skip release of lock.
			lock.unlock();
		}
	}

	public void secondThread() throws InterruptedException {
		lock.lock();
		try{
			increment();
		}finally{
			lock.unlock();
		}
	}

	public void finished() {
		System.out.println("Count is:" + count);
	}
}
