package com.empManagement;

import java.util.ArrayList;
import java.util.List;

public class threadExample {
	public static void main(String[] args) {
		// Create a list of Runnable instances
		List<Runnable> runnables = new ArrayList<Runnable>();
		runnables.add(new Runnable() {
			public void run() {
				// code for the first thread
				for(int i = 1;i<=10;i++) {
					System.out.println("first => "+i);
				}
			}
		});
		runnables.add(new Runnable() {
			public void run() {
				// code for the second thread
				for(int i = 1;i<=10;i++) {
					System.out.println("second => "+i);
				}
			}
		});

		// Create and start a new thread for each Runnable
		List<Thread> threads = new ArrayList<Thread>();
		for (Runnable runnable : runnables) {
			Thread thread = new Thread(runnable);
			thread.start();
			threads.add(thread);
		}

		// Wait for all threads to complete
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// handle the exception
			}
		}

	}
}
