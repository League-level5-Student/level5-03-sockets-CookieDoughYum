package _02_Chat_Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ChatAppClient {
	private String ip;
	private int port;

	Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;
	
	Object nsg;
	ChatAppGui cag;

	public ChatAppClient(String ip, int port, ChatAppGui cag) {
		this.ip = ip;
		this.port = port;
		this.cag = cag;
	}

	public void start(){
		try {

			connection = new Socket(ip, port);

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (connection.isConnected()) {
			try {
				nsg=(String)is.readObject();
				//JOptionPane.showMessageDialog(null, msg);
				nsg=cag.text.getText();
				cag.text.setText((String)nsg);
				System.out.println(is.readObject());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void sendClick() {
		try {
			if (os != null) {
				os.writeObject(nsg);
				os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
