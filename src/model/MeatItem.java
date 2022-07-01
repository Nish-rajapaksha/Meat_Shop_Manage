package model;

public class MeatItem {
      private String meat_Id;
     private String meat_Type;
     private Double unitPrice;
     private int meatqtyOnHand;

    public MeatItem() {
    }

    public MeatItem(String meat_Id, String meat_Type, Double unitPrice, int meatqtyOnHand) {
        this.meat_Id = meat_Id;
        this.meat_Type = meat_Type;
        this.unitPrice = unitPrice;
        this.meatqtyOnHand = meatqtyOnHand;
    }

    public String getMeat_Id() {
        return meat_Id;
    }

    public void setMeat_Id(String meat_Id) {
        this.meat_Id = meat_Id;
    }

    public String getMeat_Type() {
        return meat_Type;
    }

    public void setMeat_Type(String meat_Type) {
        this.meat_Type = meat_Type;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getMeatqtyOnHand() {
        return meatqtyOnHand;
    }

    public void setMeatqtyOnHand(int meatqtyOnHand) {
        this.meatqtyOnHand = meatqtyOnHand;
    }

    @Override
    public String toString() {
        return "MeatItem{" +
                "meat_Id='" + meat_Id + '\'' +
                ", meat_Type='" + meat_Type + '\'' +
                ", unitPrice=" + unitPrice +
                ", meatqtyOnHand=" + meatqtyOnHand +
                '}';
    }
}
