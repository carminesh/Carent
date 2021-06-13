package carent.model;

import java.io.Serializable;

public class CartItemBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int codiceItem;
	private int userCode;
	private String targa;
	private String daData;
	private String aData;
	
	public CartItemBean () {
		
	}
	
	public int getCodiceItem() {
		return codiceItem;
	}
	public void setCodiceItem(int codiceItem) {
		this.codiceItem = codiceItem;
	}
	public int getUserCode() {
		return userCode;
	}
	public void setUserCode (int userCode) {
		this.userCode = userCode;
	}
	public String getTarga() {
		return targa;
	}
	public void setTarga(String targa) {
		this.targa = targa;
	}
	public String getDaData() {
		return daData;
	}
	public void setDaData(String daData) {
		this.daData = daData;
	}
	public String getaData() {
		return aData;
	}
	public void setaData(String aData) {
		this.aData = aData;
	}
	
	public String toString () {
		return userCode + " - " + targa + "( "+daData + " fino a " + aData + ")";
	}
	
}
