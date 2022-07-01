package model;

public class CustomerOrder {
    private String cOrderId;
    private String cusId;
    private Double cCost;

    public CustomerOrder() {
    }

    public CustomerOrder(String cOrderId, String cusId, Double cCost) {
        this.cOrderId = cOrderId;
        this.cusId = cusId;
        this.cCost = cCost;
    }

    public String getcOrderId() {
        return cOrderId;
    }

    public void setcOrderId(String cOrderId) {
        this.cOrderId = cOrderId;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public Double getcCost() {
        return cCost;
    }

    public void setcCost(Double cCost) {
        this.cCost = cCost;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "cOrderId='" + cOrderId + '\'' +
                ", cusId='" + cusId + '\'' +
                ", cCost=" + cCost +
                '}';
    }
}
