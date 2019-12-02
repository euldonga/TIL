package com.kei.network.bytestream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ImprovementFileView {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("사용법: java ImprovementFileView 파일명");
			System.exit(0);
		}

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(args[0]);
			int readcount = 0;
			byte[] buffer = new byte[512];
			while ((readcount = fis.read(buffer)) != -1) {
				System.out.write(buffer, 0, readcount);
			}
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
		}
	}
}
