package control;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.UUID;

import Model.*;

public class ServerControl {
	ServerSocket server;
	Connection con;
	ServerDispatcher aServerDispatcher = new ServerDispatcher();
	

	public ServerControl() {
		try {
			aServerDispatcher.start();
			server = new ServerSocket(9999);
			System.out.print("socket started at port 9999");
			
			while (true) {
				Socket socket = server.accept();
				ClientInfo aClientInfo = new ClientInfo();
				aClientInfo.setaClient(socket);
				KListener aKListener = new KListener(aClientInfo, aServerDispatcher);
				KSender aKSender = new KSender(aClientInfo, aServerDispatcher);
				
				aClientInfo.setListener(aKListener);
				aClientInfo.setSender(aKSender);
				String nameTmp = UUID.randomUUID().toString();
				aClientInfo.setUsername(nameTmp);
				aKListener.start();
				aKSender.start();
				aServerDispatcher.addClient(aClientInfo);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
