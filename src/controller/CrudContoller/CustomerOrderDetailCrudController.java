package controller.CrudContoller;

import model.CustomerOrder;
import model.SupplierOrder;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderDetailCrudController {
    public static ArrayList<String> getCustomerOrderIds() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT cusOrderId FROM Customer_Order");
        ArrayList<String> cOids = new ArrayList<>();
        while (result.next()) {
            cOids.add(result.getString(1));
        }
        return cOids;
    }

    public static CustomerOrder getCustomerOrder(String cUPOid) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Customer_Order WHERE cusOrderId=?", cUPOid);
        if (result.next()) {
            return new CustomerOrder(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3)


            );
        }
        return null;
    }
}
