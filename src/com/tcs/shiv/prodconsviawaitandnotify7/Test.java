package com.tcs.shiv.prodconsviawaitandnotify7;

public class Test {
	public static void main(String[] args) {
		final Processor proc = new Processor();
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					proc.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					proc.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
