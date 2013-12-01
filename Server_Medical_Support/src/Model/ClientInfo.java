package Model;

import java.net.Socket;

import control.KListener;
import control.KSender;

public class ClientInfo {
	private Socket aClient;
	private String username;
	private KSender sender;
	private KListener listener;
	private int [][] chessBoardStatus = new int[100][100];
	public Socket getaClient() {
		return aClient;
	}
	public void setaClient(Socket aClient) {
		this.aClient = aClient;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public KSender getSender() {
		return sender;
	}
	public void setSender(KSender sender) {
		this.sender = sender;
	}
	public KListener getListener() {
		return listener;
	}
	public void setListener(KListener listener) {
		this.listener = listener;
	}
	
	
}
