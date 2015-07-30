package com.tcs.shiv.ThreadSynchronization2;

public class ThreadSyncUsingSynchronizedSolution {

	private int count = 0;
	
	//this ensures the "locking" on the count variable so that when one thread is accessing the variable , no other threads will get access to it.
	public synchronized void increment() {
		count++;
	}

	public static void main(String[] args) {

		ThreadSyncUsingSynchronizedSolution thread1 = new ThreadSyncUsingSynchronizedSolution();
		thread1.doWork();
	}

	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment();
				}

			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment();
				}

			}
		});

		t1.start();
		t2.start();
		
		try {
			t1.join();
			
			/* Using join() method, we tell our thread to wait until the specified thread completes its execution. There are overloaded versions of join() method, 
			 * which allows us to specify time for which you want to wait for the specified thread to terminate.   */
			t2.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Count is :" + count);
	}
}
