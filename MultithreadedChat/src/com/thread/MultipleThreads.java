package com.thread;

public class MultipleThreads implements Runnable{

	public static void main(String[] args) {
		MultipleThreads runner = new MultipleThreads();
		Thread alpha = new Thread(runner);
		Thread beta = new Thread(runner);
		alpha.setName("Alpha");
		beta.setName("Beta");
		alpha.start();
		beta.start();
	}

	@Override
	public void run() {
		for(int i=0;i<25;i++)
		{
			System.out.println(Thread.currentThread().getName() + " is running.");
		}
	}
}
