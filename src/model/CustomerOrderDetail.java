package model;

import java.util.Date;

public class CustomerOrderDetail {
     private String cusOrderId;
     private String meatId;
     private String cDate;
     private Double cUnitPrice;
     private int cQty;

    public CustomerOrderDetail() {
    }

    public CustomerOrderDetail(String cusOrderId, String meatId, String cDate, Double cUnitPrice, int cQty) {
        this.cusOrderId = cusOrderId;
        this.meatId = meatId;
        this.cDate = cDate;
        this.cUnitPrice = cUnitPrice;
        this.cQty = cQty;
    }

    public String getCusOrderId() {
        return cusOrderId;
    }

    public void setCusOrderId(String cusOrderId) {
        this.cusOrderId = cusOrderId;
    }

    public String getMeatId() {
        return meatId;
    }

    public void setMeatId(String meatId) {
        this.meatId = meatId;
    }

    public String getcDate() {
        return cDate;
    }

    public void setcDate(String cDate) {
        this.cDate = cDate;
    }

    public Double getcUnitPrice() {
        return cUnitPrice;
    }

    public void setcUnitPrice(Double cUnitPrice) {
        this.cUnitPrice = cUnitPrice;
    }

    public int getcQty() {
        return cQty;
    }

    public void setcQty(int cQty) {
        this.cQty = cQty;
    }

    @Override
    public String toString() {
        return "CustomerOrderDetail{" +
                "cusOrderId='" + cusOrderId + '\'' +
                ", meatId='" + meatId + '\'' +
                ", cDate='" + cDate + '\'' +
                ", cUnitPrice=" + cUnitPrice +
                ", cQty=" + cQty +
                '}';
    }
}
