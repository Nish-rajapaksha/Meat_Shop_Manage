package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.Customer;
import util.CrudUtil;
import views.tm.CustomerTM;

import java.awt.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ManageCustomerFormController {

    public AnchorPane ManageCustomerContext;
    public TextField txtAddress;
    public TextField txtCustomerID;
    public TextField txtCname;
    public TextField txtCustomerType;
    public TableView <CustomerTM> tblCustomers;
    public TableColumn colAddress;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colCType;
    Boolean cus_Name;
    Boolean cus_id;
    Boolean cus_Address;
    Boolean cus_Type;



    public void initialize()  {


        try {
            loadAllCustomers();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }




        colId.setCellValueFactory(new PropertyValueFactory("customer_id"));
        colName.setCellValueFactory(new PropertyValueFactory("customer_name"));
        colAddress.setCellValueFactory(new PropertyValueFactory("customer_address"));
        colCType.setCellValueFactory(new PropertyValueFactory("customer_type"));


    }

    public void isValid(){
        cus_id =txtCustomerID.getText().matches("[C]{1}[0-9]{1}[0-9]{1}[0-9]{1}");
        cus_Name = txtCname.getText().matches("^[A-z ]{3,}$");
        cus_Address =txtAddress.getText().matches("^[A-z ]{3,}|,[A-z ]{3,}$");
        cus_Type =txtCustomerType.getText().matches("^(new)|(regular)|(New)|(Regular)$");

    }

    public void validConfirm(Boolean info,TextField txt){
        if (info){
            if (txt.equals(txtCustomerID)) {
                txtCustomerID.setStyle("-fx-border-color: #c0c0c0 ; -fx-border-width: 1px;");
            }else if (txt.equals(txtAddress)){
                txtAddress.setStyle("-fx-border-color: #c0c0c0 ; -fx-border-width: 1px;");
            }else if (txt.equals(txtCname)){
                txtCname.setStyle("-fx-border-color: #c0c0c0 ; -fx-border-width: 1px;");
            }else if (txt.equals(txtCustomerType)){
                txtCustomerType.setStyle("-fx-border-color: #c0c0c0 ; -fx-border-width: 1px;");
            }
        }else {
            txt.requestFocus();

            if (txt.equals(txtCustomerID)){
                txtCustomerID.setStyle("-fx-border-color: red ; -fx-border-width: 1px; ");
            }else if (txt.equals(txtAddress)){
                txtAddress.setStyle("-fx-border-color: red ; -fx-border-width: 1px; ");
            }else if (txt.equals(txtCname)){
                txtCname.setStyle("-fx-border-color: red ; -fx-border-width: 1px; ");
            }else if (txt.equals(txtCustomerType)){
                txtCustomerType.setStyle("-fx-border-color: red ; -fx-border-width: 1px; ");
            }
        }
    }

    public void HomeOnAction(ActionEvent actionEvent) throws IOException {

        setUI("DashBordForm");
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        setUI("UpdateCustomerForm");

    }
    private void setUI(String location) throws IOException {

        Stage stage = (Stage) ManageCustomerContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../viewS/"+location+".fxml")))); }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) {

        isValid();
        validConfirm(cus_id, txtCustomerID);
        validConfirm(cus_Name, txtCname);
        validConfirm(cus_Address, txtAddress);
        validConfirm(cus_Type, txtCustomerType);
        if (cus_id && cus_Name && cus_Address && cus_Type) {


            Customer c = new Customer(
                    txtCustomerID.getText(), txtCname.getText(), txtAddress.getText(), txtCustomerType.getText());

            try {


                if (CrudUtil.execute("INSERT INTO Customer VALUES (?,?,?,?)", c.getcId(), c.getcName(), c.getcAddress(), c.getcType())) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
                    loadAllCustomers();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Inavalid data type added.. !").show();
            }

        }
    }

        private void loadAllCustomers () throws ClassNotFoundException, SQLException {


            ResultSet result = CrudUtil.execute("SELECT * FROM Customer");
            ObservableList<CustomerTM> obList = FXCollections.observableArrayList();

            while (result.next()) {
                obList.add(
                        new CustomerTM(
                                result.getString("customerId"),
                                result.getString("name"),
                                result.getString("address"),
                                result.getString("customerType")


                        )


                );

            }
            tblCustomers.setItems(obList);
            tblCustomers.refresh();


        }
    }




