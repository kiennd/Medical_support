package control;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Model.User;

public interface MedicalSupportInterface extends Remote {
	public boolean checkLogin(User u) throws RemoteException;
}
