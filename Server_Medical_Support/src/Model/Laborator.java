package Model;

import java.io.Serializable;

public class Laborator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -242495308446435764L;
	private String name;
	private float result;
	private String normalValue;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getResult() {
		return result;
	}
	public void setResult(float result) {
		this.result = result;
	}
	public String getNormalValue() {
		return normalValue;
	}
	public void setNormalValue(String normalValue) {
		this.normalValue = normalValue;
	}
	
}
