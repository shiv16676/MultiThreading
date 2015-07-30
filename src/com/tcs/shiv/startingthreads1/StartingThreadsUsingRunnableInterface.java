package com.tcs.shiv.startingthreads1;

class RunnerInterface implements Runnable{

	@Override
	public void run() {
		for (int i=0;i<10;i++){
			System.out.println("Hello" + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class StartingThreadsUsingRunnableInterface {
	public static void main(String[] args) {
		Thread t1 = new Thread(new RunnerInterface());
		Thread t2 = new Thread(new RunnerInterface());
		
		t1.start();
		t2.start();
		
	}
}
