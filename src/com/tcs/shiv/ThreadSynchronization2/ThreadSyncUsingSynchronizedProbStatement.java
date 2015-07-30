package com.tcs.shiv.ThreadSynchronization2;

public class ThreadSyncUsingSynchronizedProbStatement {

	private int count = 0;

	public static void main(String[] args) {

		ThreadSyncUsingSynchronizedSolution thread1 = new ThreadSyncUsingSynchronizedSolution();
		thread1.doWork();
	}

	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					count++;
				}

			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					count++;
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


// This Program has few problems like count values should be 20000 , but it's not because of concurrent access of count var by both
//  the threads(may be when one thread trying to access count var other is incrementing the value at that time)