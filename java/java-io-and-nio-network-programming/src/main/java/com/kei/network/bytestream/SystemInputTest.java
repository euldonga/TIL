package com.kei.network.bytestream;

import java.io.IOException;

public class SystemInputTest {

	public static void main(String[] args) {
		int i = 0;
		try {
			while ((i = System.in.read()) != -1) {
				System.out.write(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
