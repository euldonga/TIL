package com.kei.network.bytestream;

import java.io.File;
import java.io.IOException;

public class FileInfo {

	public static void main(String[] args) {
		 if (args.length != 1) {
			System.out.println("사용법: java FileInfo 파일명");
			System.exit(0);
		}

		//String file1 = "/Users/kei/Documents/Github/network/network/target/classes/com/kei/network/bytestream/FileInfo.class";
		//String file2 = "/Users/kei/Documents/Github/network/network/target/classes/com/kei/network/bytestream/./FileInfo.class";
		//String file3 = "./";
		File f = new File(args[0]);
		if (f.exists()) {
			System.out.println("length: " + f.length());
			System.out.println("canRead: " + f.canRead());
			System.out.println("canWrite: " + f.canWrite());
			System.out.println("getAbsolutePath: " + f.getAbsolutePath());
			try {
				System.out.println("getCanonicalPath: " + f.getCanonicalPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("getName: " + f.getName());
			System.out.println("getParent: " + f.getParent());
			System.out.println("getPath: " + f.getPath());
		} else {
			System.out.println("파일이 존재하지 않습니다.");
		}
	}

}
/**
 * ***************
 * **[file1 결과]**
 * ***************
 * length: 1982
 * canRead: true
 * canWrite: true
 * getAbsolutePath: /Users/kei/Documents/Github/network/network/target/classes/com/kei/network/bytestream/FileInfo.class
 * getCanonicalPath: /Users/kei/Documents/GitHub/network/network/target/classes/com/kei/network/bytestream/FileInfo.class
 * getName: FileInfo.class
 * getParent: /Users/kei/Documents/Github/network/network/target/classes/com/kei/network/bytestream
 * getPath: /Users/kei/Documents/Github/network/network/target/classes/com/kei/network/bytestream/FileInfo.class
 * 
 * ***************
 * **[file2 결과]**
 * ***************
 * 
 * length: 1845
 * canRead: true
 * canWrite: true
 * getAbsolutePath: /Users/kei/Documents/Github/network/network/target/classes/com/kei/network/bytestream/./FileInfo.class
 * getCanonicalPath: /Users/kei/Documents/GitHub/network/network/target/classes/com/kei/network/bytestream/FileInfo.class
 * getName: FileInfo.class
 * getParent: /Users/kei/Documents/Github/network/network/target/classes/com/kei/network/bytestream/.
 * getPath: /Users/kei/Documents/Github/network/network/target/classes/com/kei/network/bytestream/./FileInfo.class
 */
