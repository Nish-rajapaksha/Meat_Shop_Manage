package model;

public class Customer {
    private String cId;
    private String cName;
    private String cAddress;
    private String cType;



    public Customer(String cId, String cName, String cAddress, String cType) {
        this.cId = cId;
        this.cName = cName;
        this.cAddress = cAddress;
        this.cType = cType;
    }

    public Customer() {
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cId='" + cId + '\'' +
                ", cName='" + cName + '\'' +
                ", cAddress='" + cAddress + '\'' +
                ", cType='" + cType + '\'' +
                '}';
    }
}

