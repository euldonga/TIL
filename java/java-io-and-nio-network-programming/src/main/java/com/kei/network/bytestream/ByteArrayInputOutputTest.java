package com.kei.network.bytestream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ByteArrayInputOutputTest {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("사용법: java ByteArrayInputOutputTest 파일명");
			System.exit(0);
		}

		FileInputStream fis = null;
		ByteArrayInputStream bais = null;
		ByteArrayOutputStream baos = null;
		try {
			fis = new FileInputStream(args[0]);
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[512];
			int readcount = 0;
			// 파일로부터 읽어 들인 바이트 배열을 ByteArrayOutputStream에 쓴다.
			while ((readcount = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, readcount);
			}
			
			// ByteArrayOutputStream의 내부 저장 공간에 저장된 바이트 배열에 반환한다.
			byte[] fileArray = baos.toByteArray();
			System.out.println("파일의 내용을 모두 읽어 들여 byte[]로 만들었습니다.");
			System.out.println("읽어 들인 byte의 수: " + fileArray.length);

			// Byte[]로 부터 읽어 들이는 ByteArrayInputStream을 생성한다.
			bais = new ByteArrayInputStream(fileArray);
			// ByteArrayInputStream을 통해서 읽어 들인 byte배열을 표준 출력 장치에 출력한다.
			while ((readcount = bais.read(buffer)) != -1) {
				System.out.write(buffer, 0, readcount);
			}
			System.out.println("\n\n");
			System.out.println("읽어들인 내용을 모두 출력했습니다.");
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
				bais.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
