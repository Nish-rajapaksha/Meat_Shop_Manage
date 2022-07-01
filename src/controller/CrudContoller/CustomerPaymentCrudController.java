package controller.CrudContoller;

import model.Customer;
import model.CustomerPayement;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerPaymentCrudController {
    public static ArrayList<String> getCustomerPayIds() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT cusPayId FROM Customer_Payment");
        ArrayList<String> cPids = new ArrayList<>();
        while (result.next()) {
            cPids.add(result.getString(1));
        }
        return cPids;
    }
    public static CustomerPayement getCustomerPayment(String cpid) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Customer_Payment WHERE cusPayId=?", cpid);
        if (result.next()) {
            return new CustomerPayement(
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
