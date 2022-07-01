package views.tm;

import java.util.Date;

public class CustomerPaymentTM {
    private String payCid;
    private String payORid;
    private Double payCORAmount;
    private Date payCdate;
    private String payCType;

    public CustomerPaymentTM() {
    }

    public CustomerPaymentTM(String payCid, String payORid, Double payCORAmount, Date payCdate, String payCType) {
        this.payCid = payCid;
        this.payORid = payORid;
        this.payCORAmount = payCORAmount;
        this.payCdate = payCdate;
        this.payCType = payCType;
    }

    public String getPayCid() {
        return payCid;
    }

    public void setPayCid(String payCid) {
        this.payCid = payCid;
    }

    public String getPayORid() {
        return payORid;
    }

    public void setPayORid(String payORid) {
        this.payORid = payORid;
    }

    public Double getPayCORAmount() {
        return payCORAmount;
    }

    public void setPayCORAmount(Double payCORAmount) {
        this.payCORAmount = payCORAmount;
    }

    public Date getPayCdate() {
        return payCdate;
    }

    public void setPayCdate(Date payCdate) {
        this.payCdate = payCdate;
    }

    public String getPayCType() {
        return payCType;
    }

    public void setPayCType(String payCType) {
        this.payCType = payCType;
    }

    @Override
    public String toString() {
        return "CustomerPaymentTM{" +
                "payCid='" + payCid + '\'' +
                ", payORid='" + payORid + '\'' +
                ", payCORAmount=" + payCORAmount +
                ", payCdate=" + payCdate +
                ", payCType='" + payCType + '\'' +
                '}';
    }
}
