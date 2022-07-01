package model;

public class Supplier {
    private String sId;
    private String sName;
    private String sAddress;
    private String sContact;
    private String sType;

    public Supplier() {
    }

    public Supplier(String sId, String sName, String sAddress, String sContact, String sType) {
        this.sId = sId;
        this.sName = sName;
        this.sAddress = sAddress;
        this.sContact = sContact;
        this.sType = sType;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getsContact() {
        return sContact;
    }

    public void setsContact(String sContact) {
        this.sContact = sContact;
    }

    public String getsType() {
        return sType;
    }

    public void setsType(String sType) {
        this.sType = sType;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "sId='" + sId + '\'' +
                ", sName='" + sName + '\'' +
                ", sAddress='" + sAddress + '\'' +
                ", sContact='" + sContact + '\'' +
                ", sType='" + sType + '\'' +
                '}';
    }
}
