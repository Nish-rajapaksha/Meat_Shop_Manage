package model;

import java.util.Date;

public class SupplierOrderDetail {
     private String supOrderId;
     private String supMeatId;
     private String sOrDate;
     private Double sUnitPrice;
     private int sQty;

    public SupplierOrderDetail() {
    }

    public SupplierOrderDetail(String supOrderId, String supMeatId, String sOrDate, Double sUnitPrice, int sQty) {
        this.supOrderId = supOrderId;
        this.supMeatId = supMeatId;
        this.sOrDate = sOrDate;
        this.sUnitPrice = sUnitPrice;
        this.sQty = sQty;
    }

    public String getSupOrderId() {
        return supOrderId;
    }

    public void setSupOrderId(String supOrderId) {
        this.supOrderId = supOrderId;
    }

    public String getSupMeatId() {
        return supMeatId;
    }

    public void setSupMeatId(String supMeatId) {
        this.supMeatId = supMeatId;
    }

    public String getsOrDate() {
        return sOrDate;
    }

    public void setsOrDate(String sOrDate) {
        this.sOrDate = sOrDate;
    }

    public Double getsUnitPrice() {
        return sUnitPrice;
    }

    public void setsUnitPrice(Double sUnitPrice) {
        this.sUnitPrice = sUnitPrice;
    }

    public int getsQty() {
        return sQty;
    }

    public void setsQty(int sQty) {
        this.sQty = sQty;
    }

    @Override
    public String toString() {
        return "SupplierOrderDetail{" +
                "supOrderId='" + supOrderId + '\'' +
                ", supMeatId='" + supMeatId + '\'' +
                ", sOrDate='" + sOrDate + '\'' +
                ", sUnitPrice=" + sUnitPrice +
                ", sQty=" + sQty +
                '}';
    }
}
