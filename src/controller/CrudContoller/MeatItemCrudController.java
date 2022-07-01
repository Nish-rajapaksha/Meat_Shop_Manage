package controller.CrudContoller;

import model.MeatItem;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MeatItemCrudController {

    public static ArrayList<String> getMeatIds() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT meatItemId FROM MeatItem");
        ArrayList<String> mIds = new ArrayList<>();
        while (result.next()) {
            mIds.add(result.getString(1));
        }
        return mIds;

    }

    public static MeatItem getMeatItem(String code) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM meatItem WHERE meatItemId=?", code);
        System.out.println(result.toString());
        if (result.next()) {
            return new MeatItem(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3),
                    result.getInt(4)

            );
        }
        return null;
    }
}
