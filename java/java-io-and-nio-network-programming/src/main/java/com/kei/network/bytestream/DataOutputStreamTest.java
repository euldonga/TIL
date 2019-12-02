package com.kei.network.bytestream;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOutputStreamTest {

	public static void main(String[] args) {
		FileOutputStream fos = null;
		DataOutputStream dos = null;

		try {
			fos = new FileOutputStream("/Users/kei/Desktop/DataStreamTest");
			dos = new DataOutputStream(fos);
			dos.writeBoolean(true);
			dos.writeByte((byte) 5);
			dos.writeInt(100);
			dos.writeDouble(200.5);
			dos.writeUTF("Hello world");
			System.out.println("저장했습니다.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				dos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
