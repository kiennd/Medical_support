package Model;

import java.io.Serializable;

public class Laborator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -242495308446435764L;
	private String name;
	private float result;
	private float normalValue;
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
	public float getNormalValue() {
		return normalValue;
	}
	public void setNormalValue(float normalValue) {
		this.normalValue = normalValue;
	}
}
