package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Model.Role;

public class RoleDAO {
	Connection conn;

	public Vector<Role> findRole(String name) {
		conn = DBConnection.getConn();
		StringBuffer query = new StringBuffer("select * from tblrole ");

		if (name.length() > 0) {
			query.append("where role like '%" + name + "%' or ");
			query.append("description like '%" + name + "%'");
		}
		System.out.println(query);

		java.sql.Statement st;
		ResultSet rs = null;
		Vector<Role> result = new Vector<>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query.toString());

			while (rs.next()) {
				Role r = new Role();
				r.setRole(rs.getString("role"));
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
	public Role getRole(int id) {
		String sql = "select * from tblrole where id = ?";
		Role r = new Role();
		conn = DBConnection.getConn();
		try {
			java.sql.PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				r.setId(rs.getInt(1));
				r.setRole(rs.getString(2));
				r.setDescription(rs.getString(3));
				return r;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	public boolean newRole(Role role) {
		conn = DBConnection.getConn();
		String sql = "insert into tblrole values (?,?,?)";
		System.out.println(sql);
		try {
			java.sql.PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, role.getId());
			pre.setString(2, role.getRole());
			pre.setString(3, role.getDescription());

			if (!pre.execute()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("tung");
		}
		return false;
	}

	public boolean saveRole(Role role) {
		conn = DBConnection.getConn();
		String query = "update tblrole set role = ?, description = ?"
				+ "where id = ?";
		try {
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setInt(3, role.getId());
			pr.setString(1, role.getRole());
			pr.setString(2, role.getDescription());
			if (!pr.execute()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean deleteRole(int id) {
		conn = DBConnection.getConn();

		try {
			String query = "delete from tblUser where roleid = " + id;

			PreparedStatement pr = conn.prepareStatement(query);
			if (pr.execute()) {
				return false;
			}
			
			query = "delete from tblrole where id = " + id;
			pr = conn.prepareStatement(query);
			if (!pr.execute()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}
