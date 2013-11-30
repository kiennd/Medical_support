package Model;

import java.io.Serializable;

public class Patient implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8449432668260383250L;
	private String name,id;
	private int sex,bornYear;
	
	
	public int getBornYear() {
		return bornYear;
	}
	public void setBornYear(int bornYear) {
		this.bornYear = bornYear;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
}
