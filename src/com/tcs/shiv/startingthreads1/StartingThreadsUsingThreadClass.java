package com.tcs.shiv.startingthreads1;

class Runner extends Thread{

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


public class StartingThreadsUsingThreadClass {
	public static void main(String[] args) {
		
		Runner runner1 = new Runner();
		runner1.start();
		
		Runner runner2 = new Runner();
		runner2.start();
				
	}

}


////////////////  Interview Question  ///////////////////////

/* Since start() method calls run() method indirectly, programmer often ask why not directly call run method? 
well difference between calling run from start() and run() is that, start first creates a new thread and then call run() method on new thread 
while if you call run() method directly than it will be called on same thread and no new thread will be created  */