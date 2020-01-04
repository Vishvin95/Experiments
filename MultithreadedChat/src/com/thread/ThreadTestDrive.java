package com.thread;

public class ThreadTestDrive {

	public static void main(String[] args) {
		MyRunnable job = new MyRunnable();
		Thread myThread = new Thread(job, "Test Thread");
		myThread.start();
		
		// We can never restart a thread, once its run() is completed.
		//myThread.start();
		
		System.out.println("I am main thread");
	}

}
