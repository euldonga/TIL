package com.kei.network.bytestream;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataInputStreamTest {

	public static void main(String[] args) {
		FileInputStream fis = null;
		DataInputStream dis = null;

		try {
			fis = new FileInputStream("/Users/kei/Desktop/DataStreamTest");
			dis = new DataInputStream(fis);
			boolean b = dis.readBoolean();
			byte b2 = dis.readByte();
			int i = dis.readInt();
			double d = dis.readDouble();
			String s = dis.readUTF();
			System.out.println("boolean: " + b);
			System.out.println("byte: " + b2);
			System.out.println("int: " + i);
			System.out.println("double: " + d);
			System.out.println("String: " + s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
