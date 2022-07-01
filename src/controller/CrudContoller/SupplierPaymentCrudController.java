package controller.CrudContoller;

import model.CustomerPayement;
import model.SupplierPayment;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierPaymentCrudController {
    public static ArrayList<String> getSupplierrPayIds() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT cusPayId FROM Supplier_Payment");
        ArrayList<String> sPids = new ArrayList<>();
        while (result.next()) {
            sPids.add(result.getString(1));
        }
        return sPids;
    }
    public static SupplierPayment getSupplierPayment(String spid) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Supplier_Payment WHERE supPayId=?", spid);
        if (result.next()) {
            return new SupplierPayment(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3),
                    result.getDate(4),
                    result.getString(5)


            );
        }
        return null;
    }

}
