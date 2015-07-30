package com.tcs.shiv.deadlocks10;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	private Account acc1 = new Account();
	private Account acc2 = new Account();

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	public void firstThread() throws InterruptedException {
		Random random = new Random();
		for (int i = 0; i < 10000; i++) {
			//deadlock
			lock1.lock();
			lock2.lock();
			try {
				Account.transfer(acc1, acc2, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	public void secondThread() throws InterruptedException {
		Random random = new Random();
		for (int i = 0; i < 10000; i++) {
			
			//deadlock
			lock2.lock();
			lock1.lock();
			try {
				Account.transfer(acc2, acc1, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	public void finished() {
		System.out.println("Account 1 balance:" + acc1.getBalance());
		System.out.println("Account 2 balance:" + acc2.getBalance());
		System.out.println("Total balance:"
				+ (acc1.getBalance() + acc2.getBalance()));
	}

}


////////////////////  Deadlock ///////////////////////


//Deadlock -  if firstMethod()  accquires lock in order of lock1 , lock2 and secondLock() accquires lock in order of lock2 , lock1 , then it becomes a deadlock

//To avoid this deadlock is to ensure that u always lock the locks in the same order or define separate method