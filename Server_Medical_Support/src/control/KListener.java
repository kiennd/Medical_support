package control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import Model.ClientInfo;
import Model.Message;
import Model.User;

public class KListener extends Thread {

	ServerDispatcher aServerDispatcher;
	ClientInfo aClientInfo;
	ObjectInputStream ois;
	Connection con;
	MedicalSupportInterface rmiServer;

	public KListener(ClientInfo clientInfo, ServerDispatcher serverDispatcher)
			throws IOException {

		aClientInfo = clientInfo;
		aServerDispatcher = serverDispatcher;
		ois = new ObjectInputStream(aClientInfo.getaClient().getInputStream());
	}

	/*
	 * Login check
	 */



	public void loginProcess(Message mes) throws ClassNotFoundException,
			SQLException, RemoteException {
		System.out.println("Login");
		User u = (User) mes.getObj();
//		String nameTmp = UUID.randomUUID().toString();
		this.aClientInfo.setUsername(mes.getSender());
		rmiServer = RMIConnector.getService();
		if (rmiServer.checkLogin(u)) {
			this.aClientInfo.setUsername(u.getUsername());
			Message mess = new Message();
			mess.setSender("server");
			mess.setReceipent(u.getUsername());
			mess.setType(Setting.RESPONSE_LOGIN);
			mess.setObj("success!");
			aServerDispatcher.addMessToQueue(mess);

		} else {
			Message mess = new Message();
			mess.setSender("server");
			mess.setReceipent(mes.getSender());
			mess.setType(Setting.RESPONSE_LOGIN);
			mess.setObj("false!");
			aServerDispatcher.addMessToQueue(mess);
		}
	}

	public void run() {
		while (!interrupted()) {
			try {
				Message mes = (Message) ois.readObject();
				if (mes.getType()==Setting.REQUEST_LOGIN) {
					loginProcess(mes);
				} else {
					aServerDispatcher.addMessToQueue(mes);
				}
			} catch (ClassNotFoundException | IOException | SQLException e) {
				System.out.println(e.toString());
				break;
			}

		}
		aClientInfo.getListener().interrupt();
		try {
			aServerDispatcher.deleteClient(aClientInfo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
