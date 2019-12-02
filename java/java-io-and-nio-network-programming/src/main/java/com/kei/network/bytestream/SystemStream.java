package com.kei.network.bytestream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

class ReadThread extends Thread implements Runnable {
	InputStream is = null;
	OutputStream os = null;

	public ReadThread(InputStream is, OutputStream os) {
		this.is = is;
		this.os = os;
	}

	@Override
	public void run() {
		byte[] buffer = new byte[512];
		int bytes_read;

		try {
			for (;;) {
				bytes_read = is.read(buffer);
				if (bytes_read == -1) {
					return;
				}
				os.write(buffer, 0, bytes_read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}
}

public class SystemStream {

	public static void main(String[] args) {
		try {
			PipedInputStream pis = new PipedInputStream();
			PipedOutputStream pos = new PipedOutputStream(pis);

			ReadThread rt = new ReadThread(System.in, pos);
			ReadThread wt = new ReadThread(pis, System.out);

			rt.start();
			wt.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
