package com.tcs.shiv.prodconsviawaitandnotify7;

import java.util.Scanner;

public class Processor {

	public void produce() throws InterruptedException{
		synchronized (this) {
			System.out.println("Producer thread running..");
			wait();
			System.out.println("Resumed");
		}
	}

	public void consume() throws InterruptedException{
		Scanner sc = new Scanner(System.in);
		Thread.sleep(2000);
		synchronized (this) {
			System.out.println("Waiting for the enter key");
			sc.nextLine();
			System.out.println("Enter key pressed");
			notify();
			
			//just to prove that notify() method works unlike wait() in a way that notify() will release the lock after current synchronized block
			Thread.sleep(5000);
			
		}
	}
	
}
