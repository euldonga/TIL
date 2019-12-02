package com.kei.network.thread.pattern;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class JobQueue implements Queue {
	private static final String NAME = "JQB QUEUE";
	private static final Object monitor = new Object();
	private LinkedList<Object> jobs = new LinkedList<>();
	private static JobQueue instance = new JobQueue();

	private JobQueue() {
	}

	/**
	 * Singleton
	 * 
	 * @return
	 */
	public static JobQueue getInstance() {
		if (instance == null) {
			synchronized (JobQueue.class) {
				instance = new JobQueue();
			}
		}
		return instance;
	}

	public LinkedList<Object> getLinkedList() {
		return jobs;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void clear() {
		synchronized (monitor) {
			jobs.clear();
		}
	}

	@Override
	public void put(Object o) {
		synchronized (monitor) {
			jobs.addLast(o);
			monitor.notify(); // 대기중인 스레드 호출.
		}
	}

	@Override
	public Object pop() throws InterruptedException, NoSuchElementException {
		Object o = null;
		synchronized (monitor) {
			if (jobs.isEmpty()) {
				monitor.wait(); // 스레드 대기.
				System.out.println("Wait-" + Thread.currentThread().getName());
			}
			o = jobs.removeFirst();
		}
		if (o == null) {
			throw new NoSuchElementException();
		}
		return o;
	}

	@Override
	public int size() {
		return jobs.size();
	}

}
