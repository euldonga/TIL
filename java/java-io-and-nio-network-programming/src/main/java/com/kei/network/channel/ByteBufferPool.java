package com.kei.network.channel;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class ByteBufferPool {

	private static final int MEMORY_BLOCKSIZE = 1024;
	private static final int FILE_BLOCKSIZE = 2048;

	private final List<ByteBuffer> memoryQueue = new ArrayList<>();
	private final List<ByteBuffer> fileQueue = new ArrayList<>();

	private boolean isWait = false;

	/**
	 * 
	 * @param memorySize 물리적 메모리를 사용하기 위한 다이렉트 ByteBuffer의 크기를 지정.
	 * @param fileSize   가상 메모리를 이용하기 위해 파일 매핑을 사용하는 것인데, 이 때 사용할 파일의 크기.
	 * @param file       파일 매핑에 사용할 파일.
	 * @throws IOException
	 */
	public ByteBufferPool(int memorySize, int fileSize, File file) throws IOException {
		if (memorySize > 0) {
			initMemoryBuffer(memorySize);
		}

		if (fileSize > 0) {
			initFileBuffer(fileSize, file);
		}
	}

	/**
	 * 메모리 버퍼 한 개의 크기(MEMORY_BLOCKSIZE)를 고려해서 메모리 버퍼의 총 크기를 다시 계산한 후 해달 크기의 다이렉트
	 * ByteBuffer를 생성. 그리고 이렇게 큰 덩어리의 ByteBuffer를 실제 사용할 버퍼의 크기로 잘라서 풀(Pool)로 만들기 위해
	 * divideBuffer() 메소드 호출.
	 * 
	 * @param size
	 */
	private void initMemoryBuffer(int size) {
		int bufferCount = size / MEMORY_BLOCKSIZE;
		size = bufferCount * MEMORY_BLOCKSIZE;
		ByteBuffer directBuf = ByteBuffer.allocateDirect(size);
		divideBuffer(directBuf, MEMORY_BLOCKSIZE, memoryQueue);
	}

	/**
	 * 주어진 파일을 읽기, 쓰기가 가능한 임의접근 파일로 만든 후, 파일의 크기를 생성자에서 지정한 총 파일 크기로 설정. 그리고 임의접근 파일의
	 * 채널을 얻어온 후 map() 메소드를 이용해서 MappedByteBuffer 생성.
	 * 
	 * @param size
	 * @param f
	 * @throws IOException
	 */
	private void initFileBuffer(int size, File f) throws IOException {
		int bufferCount = size / FILE_BLOCKSIZE;
		size = bufferCount * FILE_BLOCKSIZE;
		RandomAccessFile file = new RandomAccessFile(f, "rw");
		try {
			file.setLength(size);
			ByteBuffer fileBuffer = file.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, size);
			divideBuffer(fileBuffer, FILE_BLOCKSIZE, fileQueue);
		} finally {
			file.close();
		}
	}

	/**
	 * 첫 번째 파라미터로 들어온 ByteBuffer를 두 번째 파라미터인 사이즈로 버퍼를 잘라서 세 번째 파라미터로 주어진 목록에 풀로 사용하기
	 * 위해 집어넣음.
	 * 
	 * @param buf
	 * @param blockSize
	 * @param list
	 */
	private void divideBuffer(ByteBuffer buf, int blockSize, List<ByteBuffer> list) {
		int bufferCount = buf.capacity() / blockSize;
		int position = 0;
		for (int i = 0; i < bufferCount; i++) {
			int max = position + blockSize;
			buf.limit(max);
			list.add(buf.slice());
			position = max;
			buf.position(position);
		}
	}

	public ByteBuffer getMemoryBuffer() {
		return getBuffer(memoryQueue, fileQueue);
	}

	public ByteBuffer getFileBuffer() {
		return getBuffer(fileQueue, memoryQueue);
	}

	private ByteBuffer getBuffer(List<ByteBuffer> firstQueue, List<ByteBuffer> secondQueue) {
		ByteBuffer buffer = getBuffer(firstQueue, false);
		if (buffer == null) {
			buffer = getBuffer(secondQueue, false);
			if (buffer == null) {
				if (isWait) {
					buffer = getBuffer(firstQueue, true);
				} else {
					buffer = ByteBuffer.allocate(MEMORY_BLOCKSIZE);
				}
			}
		}
		return buffer;
	}

	private ByteBuffer getBuffer(List<ByteBuffer> queue, boolean wait) {
		synchronized (queue) {
			if (queue.isEmpty()) {
				if (wait) {
					try {
						queue.wait();
					} catch (InterruptedException e) {
						return null;
					}
				} else {
					return null;
				}
			}
			return queue.remove(0);
		}
	}

	public void putBuffer(ByteBuffer buffer) {
		if (buffer.isDirect()) {
			switch (buffer.capacity()) {
				case MEMORY_BLOCKSIZE:
					putBuffer(buffer, memoryQueue);
					break;
				case FILE_BLOCKSIZE:
					putBuffer(buffer, fileQueue);
					break;
			}
		}
	}

	private void putBuffer(ByteBuffer buffer, List<ByteBuffer> queue) {
		buffer.clear();
		synchronized (queue) {
			queue.add(buffer);
			queue.notify();
		}
	}

	public synchronized void setWait(boolean wait) {
		this.isWait = wait;
	}

	public synchronized boolean isWait() {
		return isWait;
	}

	public static void main(String[] args) {
		int a = 1000 / 1024;
		System.out.println(a);
	}
}
