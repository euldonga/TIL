package com.kei.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NSLookupLocal {
	
	public static void main(String[] args) {
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		System.out.println(address.getHostName());
		System.out.println(address.getHostAddress());

		System.out.println("byte[] 형식의 ip 주소 값의 출력.");
		byte[] ip = address.getAddress();
		for (int i = 0; i < ip.length; i++) {
			System.out.print(ip[i]);
			if (i != ip.length - 1) {
				System.out.print(".");
			}
		}
	}
}
