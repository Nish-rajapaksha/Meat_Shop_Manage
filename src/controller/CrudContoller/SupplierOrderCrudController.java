package controller.CrudContoller;

import model.SupplierOrder;
import model.SupplierOrderDetail;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderCrudController {
    public boolean saveSupplierOrder(SupplierOrder Sorder) throws  ClassNotFoundException, SQLException {
        return CrudUtil.execute("INSERT INTO Supplier_Order VALUES(?,?,?)",
                Sorder.getSorderId(), Sorder.getSupId(), Sorder.getSorderCost());
    }
    public boolean saveSupplierOrderDetails(ArrayList<SupplierOrderDetail> Sdetails) throws SQLException, ClassNotFoundException {
        for (SupplierOrderDetail Sdet:Sdetails
        ) {
            boolean isSupDetailsSaved=CrudUtil.execute("INSERT INTO Supplier_OrderDetail VALUES(?,?,?,?,?)",
                    Sdet.getSupOrderId(),Sdet.getSupMeatId(),Sdet.getsOrDate(),Sdet.getsUnitPrice(),Sdet.getsQty());
            if (isSupDetailsSaved){
                if (!updateQty(Sdet.getSupMeatId(), Sdet.getsQty())){
                    return false;
                }
            }else{
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(String Sup_meatId, int Sup_qty) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE MeatItem SET qtyOnHand=qtyOnHand-? WHERE meatItemId=?", Sup_qty,Sup_meatId);
    }
    public String getSupplierOrderId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT supOrderId FROM Supplier_Order ORDER BY supOrderId DESC LIMIT 1");
        if (set.next()){
            return set.getString(1);

        }else{
            return "SOR1";
        }
    }

}
