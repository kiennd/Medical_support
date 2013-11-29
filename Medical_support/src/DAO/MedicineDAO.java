package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Model.Medicine;
import Model.MedicineForm;
public class MedicineDAO {
	Connection conn;

	public MedicineDAO() {
		conn = DBConnection.getConn();
	}

	public Vector<Medicine> findMedicine(String name) {
		conn = DBConnection.getConn();
		StringBuffer query = new StringBuffer("select * from tblmedicine ");

		if (name.length() > 0) {
			query.append("where name like '%" + name + "%' or ");
			query.append("description like '%" + name + "%'");
		}
		System.out.println(query);

		java.sql.Statement st;
		ResultSet rs = null;
		Vector<Medicine> result = new Vector<>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query.toString());

			while (rs.next()) {
				Medicine r = new Medicine();
				r.setName(rs.getString("name"));
				r.setId(rs.getInt("id"));
				r.setDescription(rs.getString("description"));
				result.add(r);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Medicine getMedicine(int id) {
		String sql = "select * from tblmedicine	 where id = ?";
		Medicine r = new Medicine();
		conn = DBConnection.getConn();
		try {
			java.sql.PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				r.setId(rs.getInt("id"));
				r.setName(rs.getString("name"));
				r.setDescription(rs.getString("description"));
				return r;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public Vector<MedicineForm> getMedicineForms(String patientid, int count){
		Vector<MedicineForm> medicineForms = new Vector<>();
		String query = "select * from tblmedicineform where patientid = ? and count = ?";
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, patientid);
			pr.setInt(2, count);
			ResultSet rs = pr.executeQuery();
			while(rs.next()){
				MedicineForm mf = new MedicineForm();
				mf.setId(rs.getInt("id"));
				mf.setPatientid(rs.getString("patientid"));
				mf.setCount(rs.getInt("count"));
				MedicineDAO md = new MedicineDAO();
				Medicine m = md.getMedicine(rs.getInt("medicineid"));
				mf.setMedicine(m);
				mf.setQuantity(rs.getInt("quantity"));
				mf.setDescription(rs.getString("description"));
				medicineForms.addElement(mf);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return medicineForms;
	}

	public boolean updateMedicine(Medicine medicine) {
		String query = "update tblmedicine set name = ?, description = ?"
				+ "where id = ?";
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setInt(3, medicine.getId());
			pr.setString(1, medicine.getName());
			pr.setString(2, medicine.getDescription());
			if (!pr.execute()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean newMedicine(Medicine medicine) {
		String sql = "insert into tblmedicine(id,name,description) values (?,?,?)";
		System.out.println(sql);
		try {
			java.sql.PreparedStatement pr = conn.prepareStatement(sql);
			pr.setInt(1, medicine.getId());
			pr.setString(2, medicine.getName());
			pr.setString(3, medicine.getDescription());

			if (!pr.execute()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("tung");
		}
		return false;
	}
	
	public boolean deleteMedicine(int id){
		try {
			String query = "delete from tblmedicine where id = " + id;
			PreparedStatement pr = conn.prepareStatement(query);
			if (!pr.execute()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}
