package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Model.ConstantMedical;
import Model.Laborator;
import Model.LaboratorForm;
import Model.Patient;

public class LaboratorDAO {
	
	Connection conn;
	public LaboratorDAO() {
		conn = DBConnection.getConn();
	}
	
	public Vector<LaboratorForm> findLaborator(String name, int startIndex,int endIndex){
		
		conn = DBConnection.getConn();
		StringBuffer query = new StringBuffer("select * from tblLaboratorForm ");

		if (name.length() > 0) {
			query.append("where patientid like '%" + name + "%' or ");
			query.append("result like '%" + name + "%'");
		}
		query.append(" limit "+startIndex+","+endIndex+" ");
		
		
		System.out.println(query);

		java.sql.Statement st;
		ResultSet rs = null;
		Vector<LaboratorForm> result = new Vector<>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query.toString());
			
			while (rs.next()) {
				PatientDAO pd = new PatientDAO();
				Patient p = pd.getPatient(rs.getString("patientid"));
				
				LaboratorForm r = new LaboratorForm();
				r.setId(rs.getInt("id"));
				r.setPantient(p);
				r.setResult(rs.getString("result"));
//				r.setLaborators(this.getLaborator(rs.getString("patientid"), rs.getInt("count")));
				r.setCount(rs.getInt("count"));
				
//				MedicineDAO md = new MedicineDAO();
//				r.setMedicineForms(md.getMedicineForms(rs.getString("patientid"), rs.getInt("count")));
				result.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public Vector<Laborator> getLaborators(String patientid, int count){
		
		Vector<Laborator> laborators  = new Vector<>();
		String query = "select * from tbllaborator where patientid = ? and count = ?";
		try {
			PreparedStatement pr  = conn.prepareStatement(query);
			pr.setString(1, patientid);
			pr.setInt(2, count);
			ResultSet rs = pr.executeQuery();
			if(rs.next()){
				for(int i=0;i<ConstantMedical.LABORATOR_CATEGORY.length;i++){
					float value = rs.getFloat(ConstantMedical.LABORATOR_CATEGORY[i]);
					if(rs.wasNull()){
						value = Float.NaN;
					}
					Laborator la = new Laborator();
					la.setName(ConstantMedical.LABORATOR_CATEGORY[i]);
	//					la.setNormalValue(norma);
					la.setResult(value);
					laborators.addElement(la);;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return laborators;
	}
	
	public LaboratorForm getLaboratorForm(String patientid, int count){
		String query = "select * from tbllaboratorform where patientid = ? and count = ?";
		PreparedStatement pr;
		LaboratorForm r = new LaboratorForm();
		try {
			pr = conn.prepareStatement(query);
			pr.setString(1, patientid);
			pr.setInt(2, count);
			
			ResultSet rs = pr.executeQuery();
			if(rs.next()){				
				PatientDAO pd = new PatientDAO();
				Patient p = pd.getPatient(rs.getString("patientid"));
				
				r.setId(rs.getInt("id"));
				r.setPantient(p);
				r.setResult(rs.getString("result"));
				r.setCount(rs.getInt("count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public int getCount(){
		String query = "select count(*) as c from tbllaborator";
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
	
	public boolean saveLaboratorForm(LaboratorForm laboratorform){
		String query = "update tbllaboratorform set result = '"+laboratorform.getResult()+"' where patientid="+laboratorform.getPantient().getId()+""
				+ " and count = "+ laboratorform.getCount()+"";
		try {
			Statement st = conn.createStatement();
			return !st.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean saveNewLaboratorForm(LaboratorForm laboratorform){
		
		int count=0;
		
		try {
			String query = "select count(*) as a from tbllaboratorform where patientid = "+laboratorform.getPantient().getId();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()){
				count = rs.getInt("a");
			}
			
			query = "insert into tbllaboratorform (patientid,count,result) values(?,?,?)";
			PreparedStatement pr = conn.prepareStatement(query);
			pr.setString(1, laboratorform.getPantient().getId());
			pr.setInt(2, count);
			pr.setString(3, laboratorform.getResult());
			if(!pr.execute()){
				query = "insert into tbllaborator (patientid,count) values (?,?)";
				pr = conn.prepareStatement(query);
				pr.setString(1, laboratorform.getPantient().getId());
				pr.setInt(2, count);
				pr.execute();
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	public boolean saveLaborator(String patientid,int count,String value, String laboratorName){
		String query = "update tbllaborator set `"+laboratorName+"`= "+value+" where patientid="+patientid+""
				+ " and count = "+ count+"";
		Statement st;
		try {
			st = conn.createStatement();
			return !st.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static void main(String[] args) {

	}
	
	public Laborator checkAbnormal(String name, float value){
		
		String query = "select * from tblmedicalparam where name = ?";
		PreparedStatement pr;
		try {
			pr = conn.prepareStatement(query);
			pr.setString(1, name);
			ResultSet rs = pr.executeQuery();
			if(rs.next()){
				float max =  rs.getFloat("maxvalue");
				float min = rs.getFloat("minvalue");
				if(!(value>=min && value <=max)){
					Laborator la = new Laborator();
					la.setName(name);
					la.setResult(value);
					la.setNormalValue(min+ " - " + max);
					return la;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Vector<LaboratorForm> findLaborator(String disease, Vector<Laborator> abNormals){
		
		conn = DBConnection.getConn();
		StringBuffer query = new StringBuffer("select * from tblLaboratorForm where result = '"+disease+"' limit 10");

		
		System.out.println(query);

		java.sql.Statement st;
		ResultSet rs = null;
		Vector<LaboratorForm> result = new Vector<>();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query.toString());
			
			while (rs.next()) {
				PatientDAO pd = new PatientDAO();
				Patient p = pd.getPatient(rs.getString("patientid"));
				
				LaboratorForm r = new LaboratorForm();
				r.setId(rs.getInt("id"));
				r.setPantient(p);
				r.setResult(rs.getString("result"));
				r.setCount(rs.getInt("count"));
				
				result.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
