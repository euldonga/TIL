package com.kei.network;

import java.sql.Timestamp;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
//    	try {
//			Socket socket = new Socket("61.38.108.231", 33000);
//			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
//			BufferedWriter bw = new BufferedWriter(osw);
//			bw.write("ABCDEFGHIJKL");
//			bw.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
    	
    	long a = System.currentTimeMillis();
    	Thread.sleep(5000);
    	long b = new Timestamp(System.currentTimeMillis()).getTime();
    	System.out.println(a);
    	System.out.println(b);
    }
}
