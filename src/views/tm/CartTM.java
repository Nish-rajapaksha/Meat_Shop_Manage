package views.tm;

import javafx.scene.control.Button;

public class CartTM {

    private String meatCartId;
    private String cartType;
    private Double cartUnitPrice;
    private int cartQty;
    private Double cartTotalcost;
    private Button cartRemove;

    public CartTM() {
    }

    public CartTM(String meatCartId, String cartType, Double cartUnitPrice, int cartQty, Double cartTotalcost, Button cartRemove) {
        this.meatCartId = meatCartId;
        this.cartType = cartType;
        this.cartUnitPrice = cartUnitPrice;
        this.cartQty = cartQty;
        this.cartTotalcost = cartTotalcost;
        this.cartRemove = cartRemove;
    }

    public String getMeatCartId() {
        return meatCartId;
    }

    public void setMeatCartId(String meatCartId) {
        this.meatCartId = meatCartId;
    }

    public String getCartType() {
        return cartType;
    }

    public void setCartType(String cartType) {
        this.cartType = cartType;
    }

    public Double getCartUnitPrice() {
        return cartUnitPrice;
    }

    public void setCartUnitPrice(Double cartUnitPrice) {
        this.cartUnitPrice = cartUnitPrice;
    }

    public int getCartQty() {
        return cartQty;
    }

    public void setCartQty(int cartQty) {
        this.cartQty = cartQty;
    }

    public Double getCartTotalcost() {
        return cartTotalcost;
    }

    public void setCartTotalcost(Double cartTotalcost) {
        this.cartTotalcost = cartTotalcost;
    }

    public Button getCartRemove() {
        return cartRemove;
    }

    public void setCartRemove(Button cartRemove) {
        this.cartRemove = cartRemove;
    }

    @Override
    public String toString() {
        return "CartTM{" +
                "meatCartId='" + meatCartId + '\'' +
                ", cartType='" + cartType + '\'' +
                ", cartUnitPrice=" + cartUnitPrice +
                ", cartQty=" + cartQty +
                ", cartTotalcost=" + cartTotalcost +
                ", cartRemove=" + cartRemove +
                '}';
    }
}
