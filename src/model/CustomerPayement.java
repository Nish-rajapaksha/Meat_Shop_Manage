package model;

import java.util.Date;

public class CustomerPayement {
    private String cPayId;
    private String cusOrId;
    private Double cPayAmount;
    private Date cPdate;
    private String cPtype;

    public CustomerPayement() {
    }

    public CustomerPayement(String cPayId, String cusOrId, Double cPayAmount, Date cPdate, String cPtype) {
        this.cPayId = cPayId;
        this.cusOrId = cusOrId;
        this.cPayAmount = cPayAmount;
        this.cPdate = cPdate;
        this.cPtype = cPtype;
    }

    public String getcPayId() {
        return cPayId;
    }

    public void setcPayId(String cPayId) {
        this.cPayId = cPayId;
    }

    public String getCusOrId() {
        return cusOrId;
    }

    public void setCusOrId(String cusOrId) {
        this.cusOrId = cusOrId;
    }

    public Double getcPayAmount() {
        return cPayAmount;
    }

    public void setcPayAmount(Double cPayAmount) {
        this.cPayAmount = cPayAmount;
    }

    public Date getcPdate() {
        return cPdate;
    }

    public void setcPdate(Date cPdate) {
        this.cPdate = cPdate;
    }

    public String getcPtype() {
        return cPtype;
    }

    public void setcPtype(String cPtype) {
        this.cPtype = cPtype;
    }

    @Override
    public String toString() {
        return "CustomerPayement{" +
                "cPayId='" + cPayId + '\'' +
                ", cusOrId='" + cusOrId + '\'' +
                ", cPayAmount=" + cPayAmount +
                ", cPdate=" + cPdate +
                ", cPtype='" + cPtype + '\'' +
                '}';
    }
}
