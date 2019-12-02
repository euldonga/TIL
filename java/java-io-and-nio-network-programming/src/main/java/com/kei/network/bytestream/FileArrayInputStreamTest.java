package com.kei.network.bytestream;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileArrayInputStreamTest {

	public static void print(InputStream in) {
		byte[] buffer = new byte[512];
		int readcount = 0;

		try {
			while ((readcount = in.read(buffer)) != -1) {
				System.out.write(buffer, 0, readcount);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if (args[0].length() != 1) {
			System.out.println("사용법: java FileArrayInputStreamTest file/array");
			System.exit(0);
		}

		if (args[0].equals("file")) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream("/Users/kei/Desktop/FileArrayInputStreamTest");
				print(fis);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} else if (args[0].equals("array")) {
			byte[] abc = new byte[26];
			for (int i = 0; i < 26; i++) {
				abc[i] = (byte) ('a' + i);
			}
			ByteArrayInputStream bais = null;
			bais = new ByteArrayInputStream(abc);
			print(bais);
			try {
				bais.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("사용법: java FileArrayInputStreamTest file/array");
			System.exit(0);
		}
	}
}
