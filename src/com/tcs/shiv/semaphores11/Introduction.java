package com.tcs.shiv.semaphores11;

import java.util.concurrent.Semaphore;

public class Introduction {
	public static void main(String[] args) throws InterruptedException {
		
		//semaphore maintains a set of permits. and semaphore with 1 permit is like a lock
		Semaphore sem = new Semaphore(1);
		sem.release();
		sem.acquire();
		System.out.println("Available Permits:" + sem.availablePermits());
	}

}
