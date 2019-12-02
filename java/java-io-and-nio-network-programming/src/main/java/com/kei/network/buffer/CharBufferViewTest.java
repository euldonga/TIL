package com.kei.network.buffer;

import java.nio.CharBuffer;

public class CharBufferViewTest {

	public static void main(String[] args) {
		CharBuffer buf = CharBuffer.wrap("hi kid!");
		while (buf.hasRemaining()) {
			System.out.print(buf.get());
		}
	}

}
