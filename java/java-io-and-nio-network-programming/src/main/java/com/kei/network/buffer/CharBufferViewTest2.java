package com.kei.network.buffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class CharBufferViewTest2 {

	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(100);
		// 뷰 버퍼 생성.
		CharBuffer cbuf = buf.asCharBuffer();
		cbuf.put("Hello World!");
		cbuf.flip();
		
		// toString 메소드로 버퍼 안의 데이터를 스트링으로 변환.
		// toString 메소드는 포지션에 영향을 주지 않음.
		String s = cbuf.toString();
		System.out.println("Data : " + s);
		System.out.println("Buffer Position : " + cbuf.position());
		System.out.println("Buffer Limit : " + cbuf.limit());
		System.out.println("Buffer Capacity : " + cbuf.capacity());
		
		int start = 6;
		int end = 12;
		// 버퍼 안의 데이터를 일부만을 charSequence로 가져옴.
		CharSequence sub = cbuf.subSequence(start, end);
		System.out.println(sub.toString());
	}

}
