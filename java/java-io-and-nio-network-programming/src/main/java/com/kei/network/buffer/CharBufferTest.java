package com.kei.network.buffer;

import java.nio.CharBuffer;

public class CharBufferTest {

	public static void main(String[] args) {
		CharBuffer c = CharBuffer.allocate(10);
		System.out.println("Position : " + c.position());
		
		c.put("Hello 일두!");
		System.out.println("Position : " + c.position());
	}

}
