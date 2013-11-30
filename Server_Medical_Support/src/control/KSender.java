package control;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.Vector;

import Model.ClientInfo;
import Model.Message;

public class KSender extends Thread {
	private ServerDispatcher aServerDispatcher;
	private Vector<Message> messQueue = new Vector<>();
	private ObjectOutputStream oos;
	private ClientInfo aClient;

	public KSender(ClientInfo clientInfo, ServerDispatcher mServerDispatcher)
			throws IOException {

		aServerDispatcher = mServerDispatcher;
		aClient = clientInfo;
		oos = new ObjectOutputStream(aClient.getaClient().getOutputStream());
	}

	synchronized Message getNextMessageFromQueue() throws InterruptedException {
		while (messQueue.size() == 0)
			wait();

		Message mes = messQueue.elementAt(0);
		messQueue.removeElementAt(0);
		return mes;
	}

	public synchronized void sendMessage(Message mes) {
		messQueue.add(mes);
		notify();
	}

	public synchronized void sendMessageToClient(Message mes)
			throws IOException {
		oos.writeObject(mes);
	}

	public void run() {
		try {
			while (!isInterrupted()) {
				Message mess;
				mess = getNextMessageFromQueue();
				sendMessageToClient(mess);
			}
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}

		aClient.getSender().interrupt();
		try {
			aServerDispatcher.deleteClient(aClient);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
