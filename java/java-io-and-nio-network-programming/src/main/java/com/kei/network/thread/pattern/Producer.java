package com.kei.network.thread.pattern;

/**
 * 큐에 1씩 증가연산 값 저장.
 * 
 * @author kei
 *
 */
public class Producer implements Runnable {
	private Queue queue = null;

	public Producer(Queue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		System.out.println("[ Start Producer... ]");
		try {
			int i = 0;
			while (!Thread.currentThread().isInterrupted()) {
				Thread.sleep(500);
				queue.put(Integer.toString(i++));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("[ End Producer... ]");
		}
	}

}
