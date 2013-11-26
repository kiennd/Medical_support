package DAO;

import java.sql.Connection;
import Model.Patient;

public class PatientDAO {
	Connection conn;
	public PatientDAO() {
		conn = DBConnection.getConn();
	}
	
	public void addPantient(Patient pantient){
		String query = "insert into";
	}
	
	public void removePantient(Patient pantient){
		
	}
	
	public void updatePantient(Patient pantient){
		
	}
	
	
}
