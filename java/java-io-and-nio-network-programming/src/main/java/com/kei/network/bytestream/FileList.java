package com.kei.network.bytestream;

import java.io.File;

public class FileList {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("사용법: java FileList 디렉토리명");
			System.exit(0);
		}

		File f = new File(args[0]);
		if (!f.exists()) {
			System.out.println("디렉토리가 존재하지 않습니다.");
			System.exit(0);
		}
		if (f.isDirectory()) {
			File[] fileList = f.listFiles();
			for (File file : fileList) {
				System.out.print(file.getName());
				System.out.print("\t");
				if (file.isDirectory()) {
					System.out.println("디렉토리");
				} else {
					System.out.print("파일");
					System.out.print("\t");
					System.out.println(file.length());
				}
			}
		} else {
			System.out.println("디렉토리가 아닙니다.");
		}
	}
}
