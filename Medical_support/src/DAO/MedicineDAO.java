package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Model.Medicine;

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
	
}
