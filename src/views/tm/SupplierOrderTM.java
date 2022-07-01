package views.tm;

import javafx.scene.control.Button;

import java.util.Date;

public class SupplierOrderTM {
    private String supplier_orderId;
     private String supplier_MeatorderId;
     private Date supplier_orderDate;
     private Double supplier_orderUnitPriceCost;
     private int supplier_orderQty;

    public SupplierOrderTM() {
    }

    public SupplierOrderTM(String supplier_orderId, String supplier_MeatorderId, Date supplier_orderDate, Double supplier_orderUnitPriceCost, int supplier_orderQty) {
        this.supplier_orderId = supplier_orderId;
        this.supplier_MeatorderId = supplier_MeatorderId;
        this.supplier_orderDate = supplier_orderDate;
        this.supplier_orderUnitPriceCost = supplier_orderUnitPriceCost;
        this.supplier_orderQty = supplier_orderQty;
    }

    public String getSupplier_orderId() {
        return supplier_orderId;
    }

    public void setSupplier_orderId(String supplier_orderId) {
        this.supplier_orderId = supplier_orderId;
    }

    public String getSupplier_MeatorderId() {
        return supplier_MeatorderId;
    }

    public void setSupplier_MeatorderId(String supplier_MeatorderId) {
        this.supplier_MeatorderId = supplier_MeatorderId;
    }

    public Date getSupplier_orderDate() {
        return supplier_orderDate;
    }

    public void setSupplier_orderDate(Date supplier_orderDate) {
        this.supplier_orderDate = supplier_orderDate;
    }

    public Double getSupplier_orderUnitPriceCost() {
        return supplier_orderUnitPriceCost;
    }

    public void setSupplier_orderUnitPriceCost(Double supplier_orderUnitPriceCost) {
        this.supplier_orderUnitPriceCost = supplier_orderUnitPriceCost;
    }

    public int getSupplier_orderQty() {
        return supplier_orderQty;
    }

    public void setSupplier_orderQty(int supplier_orderQty) {
        this.supplier_orderQty = supplier_orderQty;
    }

    @Override
    public String toString() {
        return "SupplierOrderTM{" +
                "supplier_orderId='" + supplier_orderId + '\'' +
                ", supplier_MeatorderId='" + supplier_MeatorderId + '\'' +
                ", supplier_orderDate=" + supplier_orderDate +
                ", supplier_orderUnitPriceCost=" + supplier_orderUnitPriceCost +
                ", supplier_orderQty=" + supplier_orderQty +
                '}';
    }
}
