package _02_Chat_Application;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

public class ChatAppGui {
	JFrame frame=new JFrame();
	JPanel panel=new JPanel();
	JTextField text=new JTextField(20);
	JLabel label=new JLabel("SERVER");
	JLabel label1=new JLabel("CLIENT");
	static ChatAppGui cag=new ChatAppGui();
	Server server;
	Client client;
	
public static void main(String[] args) {
	cag.GUI();
	cag.send();
}

public void GUI() {
	frame.add(panel);
	frame.setVisible(true);
	frame.setSize(400, 300);
	panel.add(text);
	text.setSize(100, 100);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.pack();
}

public void send() {
	int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!", JOptionPane.YES_NO_OPTION);
	if(response == JOptionPane.YES_OPTION){
	server = new Server(8080);
	JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
	text.addActionListener((e)->{
		server.sendClick();
	});
	server.start();
	
}else{
	String ipStr = ChatAppServer.getIPAddress();
	String prtStr = "8080";
	int port = Integer.parseInt(prtStr);
	client = new Client(ipStr, port);
	text.addActionListener((e)->{
		client.sendClick();
	});
	client.start();
}
}

}
