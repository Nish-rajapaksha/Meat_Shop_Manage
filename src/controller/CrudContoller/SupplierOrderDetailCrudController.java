package controller.CrudContoller;

import model.Supplier;
import model.SupplierOrder;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDetailCrudController {
    public static ArrayList<String> getSupplierOrderIds() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT supOrderId FROM Supplier_Order");
        ArrayList<String> sOids = new ArrayList<>();
        while (result.next()) {
            sOids.add(result.getString(1));
        }
        return sOids;
    }

    public static SupplierOrder getSupplierOrder(String sUPOid) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Supplier_Order WHERE supOrderId=?", sUPOid);
        if (result.next()) {
            return new SupplierOrder(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3)


            );
        }
        return null;
    }
}
