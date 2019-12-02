package com.kei.network.thread.pattern;

/**
 * 큐의 첫번째 데이터를 출력 및 삭제.
 * 
 * @author kei
 *
 */
public class Consumer implements Runnable {
	private Queue queue = null;
	private String name = null;

	public Consumer(Queue queue, String index) {
		this.queue = queue;
		name = "Consumer-" + index;
	}

	@Override
	public void run() {
		System.out.println("[ Start " + name + ".. ]");
		try {
			while (!Thread.currentThread().isInterrupted()) {
				System.out.println(name + ": " + queue.pop().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("[ End " + name + ".. ]");
		}
	}

}
