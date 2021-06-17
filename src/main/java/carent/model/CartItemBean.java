package carent.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CartItemBean {
    private CarBean auto;
    private String daData;
    private String aData;
    private String luogo;
    private long prezzoTotale;

    public CarBean getAuto() {
        return auto;
    }

    public String getDaData() {
        return daData;
    }

    public String getaData() {
        return aData;
    }

    public String getLuogo() {
        return luogo;
    }

    public long getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setAuto(CarBean auto) {
        this.auto = auto;
    }

    public void setDaData(String daData) {
        this.daData = daData;
    }

    public void setaData(String aData) {
        this.aData = aData;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public void setPrezzoTotale(long prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public long calcolaPeriodo () {
        Date sqlStart = Date.valueOf(this.getDaData());
        Date sqlFinish = Date.valueOf(this.getaData());
        LocalDate localStart = sqlStart.toLocalDate();
        LocalDate localFinish = sqlFinish.toLocalDate();
        return ChronoUnit.DAYS.between(localStart,localFinish);
    }
}
