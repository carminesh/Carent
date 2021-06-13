package carent.model;

import java.io.Serializable;

public class CarBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String targa;
	private String marca;
	private String modello;
	private int chilometraggio;
	private int annoImmatricolazione;
	private String alimentazione;
	private int potenza;
	public String getTarga() {
		return targa;
	}
	public void setTarga(String targa) {
		this.targa = targa;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModello() {
		return modello;
	}
	public void setModello(String modello) {
		this.modello = modello;
	}
	public int getChilometraggio() {
		return chilometraggio;
	}
	public void setChilometraggio(int chilometraggio) {
		this.chilometraggio = chilometraggio;
	}
	public int getAnnoImmatricolazione() {
		return annoImmatricolazione;
	}
	public void setAnnoImmatricolazione(int annoImmatricolazione) {
		this.annoImmatricolazione = annoImmatricolazione;
	}
	public String getAlimentazione() {
		return alimentazione;
	}
	public void setAlimentazione(String alimentazione) {
		this.alimentazione = alimentazione;
	}
	public int getPotenza() {
		return potenza;
	}
	public void setPotenza(int potenza) {
		this.potenza = potenza;
	}
	
	public String toString () {
		return targa +" "+marca+" "+modello+ "(Anno: "+annoImmatricolazione+", Potenza: "+potenza+" kW, Alimentazione: "+alimentazione+")";
	}
	
	public boolean equals (Object otherObject) {
		return this.targa.equals(((CarBean)otherObject).targa);
	}
}
