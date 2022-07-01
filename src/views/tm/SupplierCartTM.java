package views.tm;

import javafx.scene.control.Button;

public class SupplierCartTM {

     private String supplier_meatCartId;
     private String supplier_cartType;
     private Double supplier_cartUnitPrice;
     private int supplier_cartQty;
     private Double supplier_cartTotalcost;
     private Button supplier_cartRemove;

    public SupplierCartTM() {
    }

    public SupplierCartTM(String supplier_meatCartId, String supplier_cartType, Double supplier_cartUnitPrice, int supplier_cartQty, Double supplier_cartTotalcost, Button supplier_cartRemove) {
        this.supplier_meatCartId = supplier_meatCartId;
        this.supplier_cartType = supplier_cartType;
        this.supplier_cartUnitPrice = supplier_cartUnitPrice;
        this.supplier_cartQty = supplier_cartQty;
        this.supplier_cartTotalcost = supplier_cartTotalcost;
        this.supplier_cartRemove = supplier_cartRemove;
    }

    public String getSupplier_meatCartId() {
        return supplier_meatCartId;
    }

    public void setSupplier_meatCartId(String supplier_meatCartId) {
        this.supplier_meatCartId = supplier_meatCartId;
    }

    public String getSupplier_cartType() {
        return supplier_cartType;
    }

    public void setSupplier_cartType(String supplier_cartType) {
        this.supplier_cartType = supplier_cartType;
    }

    public Double getSupplier_cartUnitPrice() {
        return supplier_cartUnitPrice;
    }

    public void setSupplier_cartUnitPrice(Double supplier_cartUnitPrice) {
        this.supplier_cartUnitPrice = supplier_cartUnitPrice;
    }

    public int getSupplier_cartQty() {
        return supplier_cartQty;
    }

    public void setSupplier_cartQty(int supplier_cartQty) {
        this.supplier_cartQty = supplier_cartQty;
    }

    public Double getSupplier_cartTotalcost() {
        return supplier_cartTotalcost;
    }

    public void setSupplier_cartTotalcost(Double supplier_cartTotalcost) {
        this.supplier_cartTotalcost = supplier_cartTotalcost;
    }

    public Button getSupplier_cartRemove() {
        return supplier_cartRemove;
    }

    public void setSupplier_cartRemove(Button supplier_cartRemove) {
        this.supplier_cartRemove = supplier_cartRemove;
    }

    @Override
    public String toString() {
        return "SupplierCartTM{" +
                "supplier_meatCartId='" + supplier_meatCartId + '\'' +
                ", supplier_cartType='" + supplier_cartType + '\'' +
                ", supplier_cartUnitPrice=" + supplier_cartUnitPrice +
                ", supplier_cartQty=" + supplier_cartQty +
                ", supplier_cartTotalcost=" + supplier_cartTotalcost +
                ", supplier_cartRemove=" + supplier_cartRemove +
                '}';
    }
}
