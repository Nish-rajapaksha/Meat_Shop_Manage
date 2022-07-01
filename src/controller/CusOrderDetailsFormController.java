package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import util.CrudUtil;
import util.HomeUtil;
import views.tm.CustomerOrderTM;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CusOrderDetailsFormController {

    public AnchorPane CusOrderDetContext;
    public TableView tblAllCustomerOrders;
    public TableColumn colACusOrId;
    public TableColumn colAdate;
    public TableColumn colACusMeatId;
    public TableColumn colQtydetailsCus;
    public TableColumn colACusUnitPrice;
    public TextField txtCustomerOrderSearch;

    public void initialize() throws SQLException, ClassNotFoundException {

        colACusOrId.setCellValueFactory(new PropertyValueFactory("customer_orderId"));
        colACusMeatId.setCellValueFactory(new PropertyValueFactory("customer_MeatorderId"));
        colAdate.setCellValueFactory(new PropertyValueFactory("Customer_orderDate"));
        colACusUnitPrice.setCellValueFactory(new PropertyValueFactory("customer_orderUnitPriceCost"));
        colQtydetailsCus.setCellValueFactory(new PropertyValueFactory("customer_orderQty"));


        loadAllCustomersOrders();

    }

    private void loadAllCustomersOrders() throws ClassNotFoundException, SQLException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Customer_OrderDetail");
        ObservableList<CustomerOrderTM> CusobList = FXCollections.observableArrayList();

        while (result.next()){
            CusobList.add(
                    new CustomerOrderTM(
                            result.getString("cusOrderId"),
                            result.getString("meatItemId"),
                            result.getDate("orderDate"),
                            result.getDouble("unitPrice"),
                            result.getInt("qty")



                    )
            );




        }

        tblAllCustomerOrders.setItems(CusobList);

        FilteredList<CustomerOrderTM> filterData = new FilteredList<CustomerOrderTM>(CusobList, b -> true);

        txtCustomerOrderSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(CustomerOrderTM -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();


                if (CustomerOrderTM.getCustomer_orderId().toLowerCase().indexOf(lowerCaseFilter) != -1)  {
                    return true;
                }
                else
                    return false;


            });
        });

        SortedList<CustomerOrderTM> sortedData = new SortedList<CustomerOrderTM>(filterData);

        sortedData.comparatorProperty().bind(tblAllCustomerOrders.comparatorProperty());

        tblAllCustomerOrders.setItems(sortedData);





    }


    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {


        HomeUtil.stageSet(CusOrderDetContext,"DashBordForm");
    }

    public void txtCustomerOrderSearchOnAction(ActionEvent actionEvent) {
    }
}
