package carent.model;

public class RentBean {

    private static final long serialVersionUID = 1L;

    private int rentCode;
    private int userCode;
    private String targa;
    private String daData;
    private String aData;
    private String checkoutData;
    private double prezzo;

    public RentBean () {

    }

    public int getRentCode () {
        return rentCode;
    }
    public void setRentCode (int rentCode) {
        this.rentCode = rentCode;
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
    public String getCheckoutData () { return checkoutData;}
    public void setCheckoutData (String checkoutData) { this.checkoutData=checkoutData;}
    public double getPrezzo () { return prezzo;}
    public void setPrezzo (double prezzo) { this.prezzo=prezzo;}


    public String toString () {
        return userCode + " - " + targa + "( "+daData + " fino a " + aData + ")";
    }
}