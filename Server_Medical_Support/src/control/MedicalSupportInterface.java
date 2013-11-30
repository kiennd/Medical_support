package control;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import Model.DetectedResult;
import Model.Laborator;
import Model.User;

public interface MedicalSupportInterface extends Remote {
	public boolean checkLogin(User u) throws RemoteException;
	public DetectedResult detectDisease(Vector<Laborator> laborators) throws RemoteException;
}
