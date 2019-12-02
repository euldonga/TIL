package com.kei.network.bytestream;

import java.io.File;
import java.io.IOException;

public class TempFile {

	public static void main(String[] args) {
		try {
			File f = File.createTempFile("tmp_", ".dat");
			System.out.println(f.getAbsolutePath());
			System.out.println("60초 동안 멈춰있습니다.");
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			f.deleteOnExit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
