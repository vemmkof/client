package com.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App {
	private static final Logger LOGGER = Logger.getLogger(App.class.getName());

	public static void main(String[] args) {
		String key = Thread.currentThread().getName();
		try {
			Scanner scn = new Scanner(System.in);
			InetAddress ip = InetAddress.getByName("localhost");
			Socket s = new Socket(ip, 8080);
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			while (true) {
				LOGGER.log(Level.INFO, "MESSAGE: ");
				String tosend = scn.nextLine();
				LOGGER.log(Level.INFO, "Thread[" + key + "] set: " + tosend);
				dos.writeUTF(tosend);
				String received = dis.readUTF();
				LOGGER.log(Level.INFO, "Thread[" + key + "] get: " + received);
				if (tosend.equals("Exit")) {
					System.out.println("Closing this connection : " + s);
					s.close();
					System.out.println("Connection closed");
					break;
				}
			}
			scn.close();
			dis.close();
			dos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
