package views.tm;

import javafx.scene.control.Button;

public class MeatStockTM {
    private String stockMeatItemId;
    private String stockMeatType;
    private Double stockMeatUnitP;
    private int stockMeatQty;
    private Button stockDeleteMeat;

    public MeatStockTM() {
    }

    public MeatStockTM(String stockMeatItemId, String stockMeatType, Double stockMeatUnitP, int stockMeatQty, Button stockDeleteMeat) {
        this.stockMeatItemId = stockMeatItemId;
        this.stockMeatType = stockMeatType;
        this.stockMeatUnitP = stockMeatUnitP;
        this.stockMeatQty = stockMeatQty;
        this.stockDeleteMeat = stockDeleteMeat;
    }



    public String getStockMeatItemId() {
        return stockMeatItemId;
    }

    public void setStockMeatItemId(String stockMeatItemId) {
        this.stockMeatItemId = stockMeatItemId;
    }

    public String getStockMeatType() {
        return stockMeatType;
    }

    public void setStockMeatType(String stockMeatType) {
        this.stockMeatType = stockMeatType;
    }

    public Double getStockMeatUnitP() {
        return stockMeatUnitP;
    }

    public void setStockMeatUnitP(Double stockMeatUnitP) {
        this.stockMeatUnitP = stockMeatUnitP;
    }

    public int getStockMeatQty() {
        return stockMeatQty;
    }

    public void setStockMeatQty(int stockMeatQty) {
        this.stockMeatQty = stockMeatQty;
    }

    public Button getStockDeleteMeat() {
        return stockDeleteMeat;
    }

    public void setStockDeleteMeat(Button stockDeleteMeat) {
        this.stockDeleteMeat = stockDeleteMeat;
    }

    @Override
    public String toString() {
        return "MeatStockTM{" +
                "stockMeatItemId='" + stockMeatItemId + '\'' +
                ", stockMeatType='" + stockMeatType + '\'' +
                ", stockMeatUnitP=" + stockMeatUnitP +
                ", stockMeatQty=" + stockMeatQty +
                ", stockDeleteMeat=" + stockDeleteMeat +
                '}';
    }
}
