package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Model.Medicine;
import Model.MedicineForm;

public class MedicineFormDAO {
	Connection conn;

	public MedicineFormDAO() {
		conn = DBConnection.getConn();
	}
	public Vector<MedicineForm> findMedicineForm(String name, int startIndex) {
		conn = DBConnection.getConn();
		StringBuffer query = new StringBuffer("select * from tblmedicineform as a inner join tblmedicine as b on a.medicineid = b.id ");
		
		
		if (name.length() > 0) {
			query.append("where b.name like '%" + name + "%' or ");
			query.append("description like '%" + name + "%' or ");
			query.append("patientid like '%" + name + "%' or ");
			query.append("id like '%" + name + "%' or ");
			query.append("count like '%" + name + "%' ");
		}
		query.append(" limit "+startIndex+",20");
		System.out.println(query);

		java.sql.Statement st;
		ResultSet rs = null;
		Vector<MedicineForm> result = new Vector<>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query.toString());

			while (rs.next()) {
				MedicineForm r = new MedicineForm();
				r.setCount(rs.getInt("a.count"));
				r.setDescription(rs.getString("a.description"));
				r.setId(rs.getInt("a.id"));
				
				Medicine me = new Medicine();
				me.setName(rs.getString("b.name"));
				me.setId(rs.getInt("b.id"));
				me.setDescription(rs.getString("b.description"));
				
				r.setMedicine(me);
				r.setPatientid(rs.getString("a.patientid"));
				r.setQuantity(rs.getInt("a.quantity"));
				r.setUnit(rs.getString("a.unit"));
				
				result.add(r);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public Vector<MedicineForm> getMedicineForms(String patientid,int count) {
		conn = DBConnection.getConn();
		StringBuffer query = new StringBuffer("select * from tblmedicineform as a inner join tblmedicine as b on a.medicineid = b.id"
				+ " where a.patientid = "+patientid+" and a.count = "+count);
		
	
		System.out.println(query);

		java.sql.Statement st;
		ResultSet rs = null;
		Vector<MedicineForm> result = new Vector<>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query.toString());

			while (rs.next()) {
				MedicineForm r = new MedicineForm();
				r.setCount(rs.getInt("a.count"));
				r.setDescription(rs.getString("a.description"));
				r.setId(rs.getInt("a.id"));
				
				Medicine me = new Medicine();
				me.setName(rs.getString("b.name"));
				me.setId(rs.getInt("b.id"));
				me.setDescription(rs.getString("b.description"));
				
				r.setMedicine(me);
				r.setPatientid(rs.getString("a.patientid"));
				r.setQuantity(rs.getInt("a.quantity"));
				r.setUnit(rs.getString("a.unit"));
				
				result.add(r);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	
	
	public int getCountMedicineForm(){
		String query = "select count(*) as c from tbllaboratorform";
		PreparedStatement pr;
		int count = 0;
		try {
			pr = conn.prepareStatement(query);
			ResultSet rs = pr.executeQuery();
			if(rs.next()){
				count = rs.getInt("c");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
}
