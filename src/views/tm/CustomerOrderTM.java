package views.tm;

import java.util.Date;

public class CustomerOrderTM {
     private String customer_orderId;
     private String customer_MeatorderId;
     private Date Customer_orderDate;
     private Double customer_orderUnitPriceCost;
    private int customer_orderQty;

    public CustomerOrderTM() {
    }

    public CustomerOrderTM(String customer_orderId, String customer_MeatorderId, Date customer_orderDate, Double customer_orderUnitPriceCost, int customer_orderQty) {
        this.customer_orderId = customer_orderId;
        this.customer_MeatorderId = customer_MeatorderId;
        Customer_orderDate = customer_orderDate;
        this.customer_orderUnitPriceCost = customer_orderUnitPriceCost;
        this.customer_orderQty = customer_orderQty;
    }

    public String getCustomer_orderId() {
        return customer_orderId;
    }

    public void setCustomer_orderId(String customer_orderId) {
        this.customer_orderId = customer_orderId;
    }

    public String getCustomer_MeatorderId() {
        return customer_MeatorderId;
    }

    public void setCustomer_MeatorderId(String customer_MeatorderId) {
        this.customer_MeatorderId = customer_MeatorderId;
    }

    public Date getCustomer_orderDate() {
        return Customer_orderDate;
    }

    public void setCustomer_orderDate(Date customer_orderDate) {
        Customer_orderDate = customer_orderDate;
    }

    public Double getCustomer_orderUnitPriceCost() {
        return customer_orderUnitPriceCost;
    }

    public void setCustomer_orderUnitPriceCost(Double customer_orderUnitPriceCost) {
        this.customer_orderUnitPriceCost = customer_orderUnitPriceCost;
    }

    public int getCustomer_orderQty() {
        return customer_orderQty;
    }

    public void setCustomer_orderQty(int customer_orderQty) {
        this.customer_orderQty = customer_orderQty;
    }

    @Override
    public String toString() {
        return "CustomerOrderTM{" +
                "customer_orderId='" + customer_orderId + '\'' +
                ", customer_MeatorderId='" + customer_MeatorderId + '\'' +
                ", Customer_orderDate=" + Customer_orderDate +
                ", customer_orderUnitPriceCost=" + customer_orderUnitPriceCost +
                ", customer_orderQty=" + customer_orderQty +
                '}';
    }
}
