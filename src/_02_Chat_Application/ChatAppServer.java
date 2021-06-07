package _02_Chat_Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;



/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatAppServer {
	private int port;

	private ServerSocket server;
	private Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public ChatAppServer(int port) {
		this.port = port;
	}

	public void start(){
		try {
			server = new ServerSocket(port, 100);

			connection = server.accept();

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();

			while (connection.isConnected()) {
				try {
					Object msg=(String)is.readObject();
					JOptionPane.showMessageDialog(null, msg);
					System.out.println(is.readObject());
				}catch(EOFException e) {
					JOptionPane.showMessageDialog(null, "Connection Lost");
					System.exit(0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR!!!!!";
		}
	}

	public int getPort() {
		return port;
	}

	public void sendClick() {
		try {
			if (os != null) {
				os.writeObject("CLICK SENT FROM SERVER");
				os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

