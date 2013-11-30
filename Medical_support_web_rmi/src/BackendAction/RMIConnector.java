package BackendAction;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIConnector {
	private static Registry registry;
	private static MedicalSupportInterface rmiServer;
	private static int rmiport = 1234;
	public static MedicalSupportInterface getService() {
		try {
			if (rmiServer == null) {
				registry = LocateRegistry.getRegistry("localhost", rmiport);
				rmiServer = (MedicalSupportInterface) registry
						.lookup("SudokuRmiService");
			}
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		return rmiServer;
	}
}
