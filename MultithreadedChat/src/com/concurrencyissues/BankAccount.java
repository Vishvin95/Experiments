package com.concurrencyissues;

public class BankAccount {
	int balance;
	
	public BankAccount(int balance) {
		this.balance = balance;
	}

	int getBalance() {
		return balance;
	}

	void withdraw(int amount) {
		balance = balance - amount;
	}
}
