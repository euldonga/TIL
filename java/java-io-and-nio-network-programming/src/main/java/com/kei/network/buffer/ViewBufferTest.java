package com.kei.network.buffer;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class ViewBufferTest {

	public static void main(String[] args) {
		// 크기가 10인 ByteBuffer를 생성한다.
		ByteBuffer buf = ByteBuffer.allocate(10);

		// 뷰 버퍼인 IntBuffer를 생성한다.
		IntBuffer ib = buf.asIntBuffer();
		// 뷰 버퍼의 초기 값을 출력한다.
		System.out.println("position=" + ib.position());
		System.out.println("limit=" + ib.limit());
		System.out.println("capacity=" + ib.capacity());
		// 뷰 버퍼에 데이터를 쓴다.
		ib.put(1024).put(2048);
		// 뷰 버퍼의 데이터를 출력한다.
		System.out.println("index[0] = " + ib.get(0));
		System.out.println("index[1] = " + ib.get(1));

		// 원본 버퍼도 변경되었는지 확인하기 위해 출력한다.
		while (buf.hasRemaining()) {
			System.out.print(buf.get() + " ");
		}
	}

}
