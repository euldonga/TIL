package com.kei.network.buffer;

import java.nio.ByteBuffer;

public class OtherDataTest {
	public static void main(String[] args) {
		// 크기가 10인 ByteBuffer를 생성한다.
		ByteBuffer buf = ByteBuffer.allocate(10);
		print(buf);
		buf.putInt(100);
		print(buf);
		// buf.position(0);
		// while (buf.hasRemaining()) {
		// System.out.println(buf.get());
		// }
	}

	private static void print(ByteBuffer buf) {
		System.out.println("position=" + buf.position() + ", limit=" + buf.limit() + ", capacity=" + buf.capacity());
	}
}
