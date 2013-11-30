package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Model.Patient;


public class PatientDAO {
	Connection conn;

	public PatientDAO() {
		conn = DBConnection.getConn();
	}

	public Vector<Patient> findPatient(String name) {
		conn = DBConnection.getConn();
		StringBuffer query = new StringBuffer("select * from tblpatient ");

		if (name.length() > 0) {
			query.append("where name like '%" + name + "%'");
		}
		System.out.println(query);

		java.sql.Statement st;
		ResultSet rs = null;
		Vector<Patient> result = new Vector<>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query.toString());

			while (rs.next()) {
				Patient r = new Patient();
				r.setName(rs.getString("name"));
				r.setId(rs.getString("id"));
				r.setBornYear(rs.getInt("bornyear"));
				r.setSex(rs.getInt("sex"));
				result.add(r);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Patient getPatient(String id) {
		String sql = "select * from tblpatient	 where id = ?";
		Patient r = new Patient();
		conn = DBConnection.getConn();
		try {
			java.sql.PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, id);
			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				r.setName(rs.getString("name"));
				r.setId(rs.getString("id"));
				r.setBornYear(rs.getInt("bornyear"));
				r.setSex(rs.getInt("sex"));
				return r;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	public boolean updatePatient(Patient patient) {
		String query = "update tblpatient set name = ?, bornyear = ?, sex=? "
				+ "where id = ?";
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, patient.getName());
			pr.setInt(2, patient.getBornYear());
			pr.setInt(3, patient.getSex());
			pr.setString(4, patient.getId());
			
			if (!pr.execute()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean newPatient(Patient patient) {
		String sql = "insert into tblpatient(id,name,bornyear,sex) values (?,?,?,?)";
		System.out.println(sql);
		try {
			java.sql.PreparedStatement pr = conn.prepareStatement(sql);
			pr.setString(1, patient.getId());
			pr.setString(2, patient.getName());
			pr.setInt(3, patient.getBornYear());
			pr.setInt(4, patient.getSex());
			
			if (!pr.execute()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("tung");
		}
		return false;
	}
	
	public boolean deletePatient(String id){
		try {
			String query = "delete from tblpatient where id = " + id;
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
