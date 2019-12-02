package com.kei.network.channel;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class SharedFileChannelInstanceTest {

	public static void main(String[] args) throws Exception {

		RandomAccessFile raf = new RandomAccessFile("/expernet/nohup.out", "rw");

		raf.seek(1000);
		FileChannel fc = raf.getChannel();
		System.out.println("File position: " + fc.position());

		raf.seek(500);
		System.out.println("File position: " + fc.position());

		fc.position(100);
		System.out.println("File position: " + raf.getFilePointer());
	}

}
