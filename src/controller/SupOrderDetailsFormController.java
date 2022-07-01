package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import util.CrudUtil;
import util.HomeUtil;
import views.tm.SupplierOrderTM;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupOrderDetailsFormController {
    public AnchorPane SupOrderDetContext;
    public TableView tblAllSupplierOrders;
    public TableColumn colAsName;
    public TableColumn colAsOrderId;
    public TableColumn colASdate;
    public TableColumn colSupplierMexatItemiD;
    public TableColumn colAsUnitPrice;
    public TableColumn colAsQty;
    public TextField txtSupplierOrderSearch;
    public TableColumn colDeleteSupplier;
    public TableColumn colAsSupplierOID;

    public void initialize() throws SQLException, ClassNotFoundException {

        colAsSupplierOID.setCellValueFactory(new PropertyValueFactory("supplier_orderId"));
        colSupplierMexatItemiD.setCellValueFactory(new PropertyValueFactory("supplier_MeatorderId"));
        colASdate.setCellValueFactory(new PropertyValueFactory("supplier_orderDate"));
        colAsUnitPrice.setCellValueFactory(new PropertyValueFactory("supplier_orderUnitPriceCost"));
        colAsQty.setCellValueFactory(new PropertyValueFactory("supplier_orderQty"));


       loadAllSupplierOrdersSeatils();

    }


    private void loadAllSupplierOrdersSeatils() throws ClassNotFoundException, SQLException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Supplier_OrderDetail");
        ObservableList<SupplierOrderTM> SupobList = FXCollections.observableArrayList();

        while (result.next()){
            SupobList.add(
                    new SupplierOrderTM(
                            result.getString("supOrderId"),
                            result.getString("meatItemId"),
                            result.getDate("orderDate"),
                            result.getDouble("unitPrice"),
                            result.getInt("qty")



                    )
            );




        }

        tblAllSupplierOrders.setItems(SupobList);

        FilteredList<SupplierOrderTM> filterData = new FilteredList<SupplierOrderTM>(SupobList, b -> true);

        txtSupplierOrderSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(SupplierOrderTM -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();


                if (SupplierOrderTM.getSupplier_orderId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else
                    return false;


            });
        });

        SortedList<SupplierOrderTM> sortedData = new SortedList<>(filterData);

        sortedData.comparatorProperty().bind(tblAllSupplierOrders.comparatorProperty());

        tblAllSupplierOrders.setItems(sortedData);






    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        HomeUtil.stageSet(SupOrderDetContext,"DashBordForm");
    }





}
