package com.kei.network.thread.multi;

import java.util.Random;

public class ThreadLocalTest {

	static volatile int counter = 0; // 카운터 변수.
	static Random random = new Random(); // 임의 클래스.

	/**
	 * ThreadLocal 상속.
	 * 
	 * @author kei
	 *
	 */
	private static class ThreadLocalObject extends ThreadLocal<Integer> {
		Random random = new Random();

		protected Integer initialValue() {
			return new Integer(random.nextInt(1000));
		}
	}

	static ThreadLocal<Integer> threadLocal = new ThreadLocalObject();

	/**
	 * 각 스레드의 value 출력.
	 */
	private static void displayValue() {
		System.out.println("Thread Name: " + Thread.currentThread().getName() + ", initialValue: " + threadLocal.get()
				+ ", counter: " + counter);
	}

	public static void main(String[] args) {
		// main 스레드 value 출력.
		displayValue();
		Runnable runner = new Runnable() {
			public void run() {
				synchronized (ThreadLocalObject.class) {
					counter++;
				}
				// value 출력.
				displayValue();
				try {
					Thread.sleep(((Integer) threadLocal.get()).intValue());
					// value 출력.
					displayValue();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		for (int i = 0; i < 3; i++) {
			Thread t = new Thread(runner);
			t.start();
		}
	}

}
