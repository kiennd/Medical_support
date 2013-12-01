package control;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import Model.ClientInfo;
import Model.DetectedResult;
import Model.Laborator;
import Model.Message;

public class ServerDispatcher extends Thread {

	Vector<ClientInfo> clientList;
	Vector<Message> messageQueue;
	MedicalSupportInterface rmiServer;

	public ServerDispatcher() {
		rmiServer = RMIConnector.getService();
		clientList = new Vector<>();
		messageQueue = new Vector<>();
	}

	public synchronized void addClient(ClientInfo aclient) {
		clientList.add(aclient);
	}

	public synchronized void deleteClient(ClientInfo aclient)
			throws ClassNotFoundException, SQLException {
		int ind = clientList.indexOf(aclient);
		if (ind != -1) {
			clientList.remove(ind);
		}
	}

	public synchronized void addMessToQueue(Message mes)
			throws ClassNotFoundException, SQLException, RemoteException {
		int type = mes.getType();
		switch (type) {
		case Setting.REQUEST_DETECT:
			
			Vector<Laborator>laborators = (Vector<Laborator>) mes.getObj();
			DetectedResult dr = rmiServer.detectDisease(laborators);
			addMessageToQueue(Setting.RESPONSE_DETECT, mes.getReceipent(), mes.getSender(), dr);
			break;
		
		default:
			messageQueue.add(mes);
			notify();
		}
	}

	public synchronized Message getNextMessage() throws InterruptedException {
		System.out.println("get nextMessage");
		while (messageQueue.size() <= 0) {
			wait();
		}
		Message me = messageQueue.get(0);
		messageQueue.removeElementAt(0);

		return me;
	}

	public synchronized void sendMessage(Message mes) {

		String recipient = mes.getReceipent();

		for (int i = 0; i < clientList.size(); i++) {
			ClientInfo cl = (ClientInfo) clientList.elementAt(i);
			if (recipient.equals(cl.getUsername())) {
				cl.getSender().sendMessage(mes);
			}
		}
	}

	public synchronized void addMessageToQueue(int type, String sender,
			String recipient, Object obj) {
		Message mes = new Message();
		mes.setType(type);
		mes.setReceipent(recipient);
		mes.setSender(sender);
		if (obj != null)
			mes.setObj(obj);
		messageQueue.add(mes);
		notify();
	}

	/*
	 * thread run
	 */

	public void run() {
		try {
			while (true) {
				Message mess;
				mess = getNextMessage();
				sendMessage(mess);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
