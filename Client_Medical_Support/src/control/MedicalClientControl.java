package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Model.Message;
import Model.User;
import view.DetectView;
import view.LoginView;



public class MedicalClientControl {
	private Socket client;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	private LoginView loginView;
	private DetectView detectView;
	public static final String SERVER_HOSTNAME = "localhost";
	public static final int SERVER_PORT = 9999;
	
	public void initLoginView(LoginView loginView){
		this.loginView = loginView;
		loginView.addActionLogin(new LoginListener());
		try {
			this.client = new Socket(SERVER_HOSTNAME, SERVER_PORT);
			oos = new ObjectOutputStream(this.client.getOutputStream());
			ois = new ObjectInputStream(this.client.getInputStream());
		} catch (IOException e) {
			this.loginView.showMessage(e.toString());
		}
		listenMessage();
	}
	
	public void initDetectView(DetectView detectView){
		this.detectView = detectView;
		
	}
	
	

	class LoginListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {		
			Message mes = loginView.getMessage();
			sendMessage(mes);
		}
	}
	
	
	public void sendMessage(Message mes) {
		try {
			oos.writeObject(mes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public synchronized void listenMessage() {
		try {
			while (client.isConnected()) {

				Message mes = (Message) ois.readObject();
				int type = mes.getType();

				switch (type) {

				case Setting.RESPONSE_LOGIN:
					loginView.dispose();
					String res = (String) mes.getObj();
					loginView.showMessage(res);
					if (res.equals("success!")) {
						DetectView dtv = new DetectView();
						dtv.setVisible(true);
						initDetectView(dtv);
						
					} else {

					}
					break;

				}
			}
		} catch (ClassNotFoundException | IOException e) {
			System.out.println(e.toString());
		}
	}
	public static void main(String[] args) {
		MedicalClientControl me = new MedicalClientControl();
		LoginView lgv = new LoginView();
		lgv.setVisible(true);
		me.initLoginView(lgv);
	}
}
