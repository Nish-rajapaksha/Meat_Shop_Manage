package model;

public class SupplierOrder {
    private String SorderId;
    private String supId;
    private Double SorderCost;

    public SupplierOrder() {
    }

    public SupplierOrder(String sorderId, String supId, Double sorderCost) {
        SorderId = sorderId;
        this.supId = supId;
        SorderCost = sorderCost;
    }

    public String getSorderId() {
        return SorderId;
    }

    public void setSorderId(String sorderId) {
        SorderId = sorderId;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public Double getSorderCost() {
        return SorderCost;
    }

    public void setSorderCost(Double sorderCost) {
        SorderCost = sorderCost;
    }

    @Override
    public String toString() {
        return "SupplierOrder{" +
                "SorderId='" + SorderId + '\'' +
                ", supId='" + supId + '\'' +
                ", SorderCost=" + SorderCost +
                '}';
    }
}
