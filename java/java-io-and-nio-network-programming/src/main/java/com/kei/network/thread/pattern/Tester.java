package com.kei.network.thread.pattern;

public class Tester {

	public static void main(String[] args) throws InterruptedException {
		// 큐 생성.
		Queue queue = JobQueue.getInstance();

		// 소비자를 생성하고 시작.
		Thread consumer1 = new Thread(new Consumer(queue, "0"));
		Thread consumer2 = new Thread(new Consumer(queue, "1"));
		Thread consumer3 = new Thread(new Consumer(queue, "2"));
		consumer1.start();
		consumer2.start();
		consumer3.start();

		// 생산자를 생성하고 시작.
		Thread producer = new Thread(new Producer(queue));
		producer.start();

//		Thread.sleep(500);
//		// 생산자 종료.
//		producer.interrupt();
//
//		Thread.sleep(500);
//		// 소비자 종료.
//		consumer1.interrupt();
//		consumer2.interrupt();
//		consumer3.interrupt();

	}

}
