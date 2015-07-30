package com.tcs.shiv.ThreadSynchronization2;

import java.util.Scanner;

class Processor extends Thread{

private volatile boolean running = true;
	
	@Override
	public void run() {
		while(running){
			System.out.println("Hello");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void shutdown(){
		running =false;
	}
}


public class ThreadSyncUsingVolatile {
	public static void main(String[] args) {
		Processor proc1 = new Processor();
		proc1.start();
		
		//Code to stop the running thread
		System.out.println("Press return key to stop...!!");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
		proc1.shutdown();
		scanner.close();
	}
}


//////////////////    Explanation of volatile keyword     ////////////////

/*  volatile variable in Java is a special variable which is used to signal threads, compiler that this particular variables values is going to be updated 
 *  by multiple thread inside Java application. By making a variable volatile using volatile keyword in Java, application programmer ensures that its value should 
 *  always been read from main memory and thread should not used cached value of that variable from there own stack. With the introduction of Java memory model
 *  from Java 5 onwards along with introduction of CountDownLatch, CyclicBarrier, Semaphore and ConcurrentHashMap, volatile variable also guarantees 
 *  "happens-before" relationship, which means not only other thread has visibility of latest value of volatile variable but also all the variable seen by the thread 
 *  which has updated value of volatile variable before this threads sees it.
 *  
 *  Link:-  http://java67.blogspot.in/2012/08/what-is-volatile-variable-in-java-when.html

*/

