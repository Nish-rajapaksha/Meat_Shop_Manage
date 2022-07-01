package controller.CrudContoller;

import model.Customer;
import model.Supplier;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierCrudController {

    public static ArrayList<String> getSupplierIds() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT supplierId FROM Supplier");
        ArrayList<String> ids = new ArrayList<>();
        while (result.next()) {
            ids.add(result.getString(1));
        }
        return ids;
    }

    public static Supplier getSupplier(String sid) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Supplier WHERE supplierId=?", sid);
        if (result.next()) {
            return new Supplier(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)

            );
        }
        return null;
    }
}
