package control;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import view.RmiMedicalView;
import DAO.UserDAO;
import Model.User;

public class MedicalRMIControl extends UnicastRemoteObject implements
		MedicalSupportInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8226593487598812757L;
	private Registry registry;
	private int rmiport = 1234;
	private String rmiservice = "SudokuRmiService";
	private RmiMedicalView view;
	private UserDAO userdao = new UserDAO();
	
	public MedicalRMIControl(RmiMedicalView view) throws RemoteException {
		this.view = view;
		view.showMessage("runing");
		registry = LocateRegistry.createRegistry(rmiport);
		registry.rebind(rmiservice, this);

	}
	
	
	@Override
	public boolean checkLogin(User u) throws RemoteException {
		return userdao.checkLogin(u);
	}



	public static void main(String[] args) {
		RmiMedicalView view = new RmiMedicalView();
		try {
			MedicalRMIControl control = new MedicalRMIControl(view);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
