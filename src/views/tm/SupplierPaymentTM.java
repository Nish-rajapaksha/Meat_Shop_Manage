package views.tm;

import java.util.Date;

public class SupplierPaymentTM {
     private String supplier_payid;
     private String supplier_payORid;
     private Double supplier_payORAmount;
     private Date supplier_paydate;
     private String supplier_payType;

    public SupplierPaymentTM() {
    }

    public SupplierPaymentTM(String supplier_payid, String supplier_payORid, Double supplier_payORAmount, Date supplier_paydate, String supplier_payType) {
        this.supplier_payid = supplier_payid;
        this.supplier_payORid = supplier_payORid;
        this.supplier_payORAmount = supplier_payORAmount;
        this.supplier_paydate = supplier_paydate;
        this.supplier_payType = supplier_payType;
    }

    public String getSupplier_payid() {
        return supplier_payid;
    }

    public void setSupplier_payid(String supplier_payid) {
        this.supplier_payid = supplier_payid;
    }

    public String getSupplier_payORid() {
        return supplier_payORid;
    }

    public void setSupplier_payORid(String supplier_payORid) {
        this.supplier_payORid = supplier_payORid;
    }

    public Double getSupplier_payORAmount() {
        return supplier_payORAmount;
    }

    public void setSupplier_payORAmount(Double supplier_payORAmount) {
        this.supplier_payORAmount = supplier_payORAmount;
    }

    public Date getSupplier_paydate() {
        return supplier_paydate;
    }

    public void setSupplier_paydate(Date supplier_paydate) {
        this.supplier_paydate = supplier_paydate;
    }

    public String getSupplier_payType() {
        return supplier_payType;
    }

    public void setSupplier_payType(String supplier_payType) {
        this.supplier_payType = supplier_payType;
    }

    @Override
    public String toString() {
        return "SupplierPaymentTM{" +
                "supplier_payid='" + supplier_payid + '\'' +
                ", supplier_payORid='" + supplier_payORid + '\'' +
                ", supplier_payORAmount=" + supplier_payORAmount +
                ", supplier_paydate=" + supplier_paydate +
                ", supplier_payType='" + supplier_payType + '\'' +
                '}';
    }
}
