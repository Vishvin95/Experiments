package com.concurrencyissues;

public class RyanAndMonicaJob implements Runnable {

	BankAccount account = new BankAccount(100);

	public static void main(String[] args) {
		RyanAndMonicaJob withdrawlJob = new RyanAndMonicaJob();
		Thread one = new Thread(withdrawlJob);
		Thread two = new Thread(withdrawlJob);
		one.setName("Ryan");
		two.setName("Monica");
		one.start();
		two.start();
	}

	@Override
	public void run() {
		for (int x = 0; x < 10; x++) {
			makeWithdrawl(10);
			if (account.getBalance() < 0)
				System.out.println("Overwithdrawn");
		}
	}

	private synchronized void makeWithdrawl(int amount)
	{
		if(account.getBalance() >= amount)
		{
			System.out.println(Thread.currentThread().getName() + " is about to withdraw");
			try {
				System.out.println(Thread.currentThread().getName() + " is going to sleep");
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName() + " woke up");
			account.withdraw(amount);
			System.out.println(Thread.currentThread().getName() + " compleeted withdrawl");
		}
		else
		{
			System.out.println("Sorry, not enough for " + Thread.currentThread().getName());
		}
	}
}
