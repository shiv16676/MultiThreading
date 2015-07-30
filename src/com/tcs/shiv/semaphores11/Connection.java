package com.tcs.shiv.semaphores11;

import java.util.concurrent.Semaphore;

//make this class as a singleton class
public class Connection {
	private int connections = 0;
	Semaphore sem = new Semaphore(10);

	private static Connection instance = new Connection();

	// private constructor to make class as singleton
	private Connection() {

	}

	public static Connection getInstance() {
		return instance;
	}

	public void connect(){
		try {
			sem.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try{
			doConnect();
		}finally{
			sem.release();
		}
	}
	
	public void doConnect() {
		synchronized (this) {
			connections++;
			System.out.println("Current Connections:- " + connections);
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		synchronized (this) {
			connections--;
		}
	}

}
