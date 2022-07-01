package views.tm;

public class SupplierTM {

    private String supplier_id;
    private String supplier_name;
    private String supplier_address;
    private String supplier_contact;
    private String supplier_type;

    public SupplierTM() {
    }

    public SupplierTM(String supplier_id, String supplier_name, String supplier_address, String supplier_contact, String supplier_type) {
        this.supplier_id = supplier_id;
        this.supplier_name = supplier_name;
        this.supplier_address = supplier_address;
        this.supplier_contact = supplier_contact;
        this.supplier_type = supplier_type;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getSupplier_address() {
        return supplier_address;
    }

    public void setSupplier_address(String supplier_address) {
        this.supplier_address = supplier_address;
    }

    public String getSupplier_contact() {
        return supplier_contact;
    }

    public void setSupplier_contact(String supplier_contact) {
        this.supplier_contact = supplier_contact;
    }

    public String getSupplier_type() {
        return supplier_type;
    }

    public void setSupplier_type(String supplier_type) {
        this.supplier_type = supplier_type;
    }

    @Override
    public String toString() {
        return "SupplierTM{" +
                "supplier_id='" + supplier_id + '\'' +
                ", supplier_name='" + supplier_name + '\'' +
                ", supplier_address='" + supplier_address + '\'' +
                ", supplier_contact='" + supplier_contact + '\'' +
                ", supplier_type='" + supplier_type + '\'' +
                '}';
    }
}
