package views.tm;

import javafx.scene.control.Button;

public class MeatTM {

     private String meatItem_Id;
     private String meatItem_Type;
     private Double meatItem_unitPrice;
     private String meatItem_qtyOnHand;

    public MeatTM() {
    }

    public MeatTM(String meatItem_Id, String meatItem_Type, Double meatItem_unitPrice, String meatItem_qtyOnHand) {
        this.meatItem_Id = meatItem_Id;
        this.meatItem_Type = meatItem_Type;
        this.meatItem_unitPrice = meatItem_unitPrice;
        this.meatItem_qtyOnHand = meatItem_qtyOnHand;
    }

    public String getMeatItem_Id() {
        return meatItem_Id;
    }

    public void setMeatItem_Id(String meatItem_Id) {
        this.meatItem_Id = meatItem_Id;
    }

    public String getMeatItem_Type() {
        return meatItem_Type;
    }

    public void setMeatItem_Type(String meatItem_Type) {
        this.meatItem_Type = meatItem_Type;
    }

    public Double getMeatItem_unitPrice() {
        return meatItem_unitPrice;
    }

    public void setMeatItem_unitPrice(Double meatItem_unitPrice) {
        this.meatItem_unitPrice = meatItem_unitPrice;
    }

    public String getMeatItem_qtyOnHand() {
        return meatItem_qtyOnHand;
    }

    public void setMeatItem_qtyOnHand(String meatItem_qtyOnHand) {
        this.meatItem_qtyOnHand = meatItem_qtyOnHand;
    }

    @Override
    public String toString() {
        return "MeatTM{" +
                "meatItem_Id='" + meatItem_Id + '\'' +
                ", meatItem_Type='" + meatItem_Type + '\'' +
                ", meatItem_unitPrice=" + meatItem_unitPrice +
                ", meatItem_qtyOnHand='" + meatItem_qtyOnHand + '\'' +
                '}';
    }
}
