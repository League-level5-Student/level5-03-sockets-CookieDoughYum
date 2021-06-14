package _02_Chat_Application;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

public class ChatAppGui implements KeyListener{
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JTextField text = new JTextField(20);
	JLabel label = new JLabel("SERVER");
	JLabel label1 = new JLabel("CLIENT");
	static ChatAppGui cag = new ChatAppGui();
	ChatAppServer server;
	ChatAppClient client;
	boolean connection;
	String message;

	public static void main(String[] args) {
		cag.GUI();
	}

	public void GUI() {
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(400, 300);
		panel.add(text);
		text.setSize(100, 100);
		text.addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		cag.send();
	}

	public void send() {
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!",
				JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION) {
			connection=true;
			server = new ChatAppServer(8080, this);
			JOptionPane.showMessageDialog(null,
			"Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			server.start();
		}
		else if(response == JOptionPane.NO_OPTION) {
			connection=false;
			String ipStr = ChatAppServer.getIPAddress();
			String prtStr = "8080";
			int port = Integer.parseInt(prtStr);
			client = new ChatAppClient(ipStr, port, this);
			client.start();
		}
	}
	
	public void ict() {
		if (connection==true) {
			text.addActionListener((e) -> {
				
				server.sendClick();
			});
		} else if(connection==false){
			text.addActionListener((e) -> {
				client.sendClick();
			});
			text.setText((String)client.nsg);
			
		}

	}
	
	

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==10) {
			cag.ict();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
