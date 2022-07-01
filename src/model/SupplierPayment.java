package model;

import java.util.Date;

public class SupplierPayment {

    private String sPayId;
    private String suplOrId;
    private Double sPayAmount;
    private Date sPdate;
    private String sPtype;

    public SupplierPayment() {
    }

    public SupplierPayment(String sPayId, String suplOrId, Double sPayAmount, Date sPdate, String sPtype) {
        this.sPayId = sPayId;
        this.suplOrId = suplOrId;
        this.sPayAmount = sPayAmount;
        this.sPdate = sPdate;
        this.sPtype = sPtype;
    }

    public String getsPayId() {
        return sPayId;
    }

    public void setsPayId(String sPayId) {
        this.sPayId = sPayId;
    }

    public String getSuplOrId() {
        return suplOrId;
    }

    public void setSuplOrId(String suplOrId) {
        this.suplOrId = suplOrId;
    }

    public Double getsPayAmount() {
        return sPayAmount;
    }

    public void setsPayAmount(Double sPayAmount) {
        this.sPayAmount = sPayAmount;
    }

    public Date getsPdate() {
        return sPdate;
    }

    public void setsPdate(Date sPdate) {
        this.sPdate = sPdate;
    }

    public String getsPtype() {
        return sPtype;
    }

    public void setsPtype(String sPtype) {
        this.sPtype = sPtype;
    }

    @Override
    public String toString() {
        return "SupplierPayment{" +
                "sPayId='" + sPayId + '\'' +
                ", suplOrId='" + suplOrId + '\'' +
                ", sPayAmount=" + sPayAmount +
                ", sPdate=" + sPdate +
                ", sPtype='" + sPtype + '\'' +
                '}';
    }
}
