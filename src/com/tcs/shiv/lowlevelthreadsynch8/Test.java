package com.tcs.shiv.lowlevelthreadsynch8;

import com.tcs.shiv.lowlevelthreadsynch8.Processor;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		final Processor proc = new Processor();

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					proc.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					proc.consumer();
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

}
