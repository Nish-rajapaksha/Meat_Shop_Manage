package controller;

import com.jfoenix.controls.JFXTextField;
import controller.CrudContoller.CustomerCrudController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Customer;
import util.CrudUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class UpdateCustomerFormController {
    public AnchorPane CUpdateContext;
    public ComboBox <String> cmbUpdateOrderId;
    public JFXTextField txtUpdateCName;
    public JFXTextField txtUpdateCType;
    public JFXTextField txtUpdateCAddress;

    public void initialize(){

        setCustomerupIDs();

        cmbUpdateOrderId.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    setCustomerUpDetails(newValue);
                });
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) CUpdateContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/ManageCustomerForm.fxml"))));
    }

    public void btnCustomerUpdateOnAction(ActionEvent actionEvent) {

        System.out.println("Updated");

        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Successfully Updated The Customer", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get().equals(ButtonType.YES)){
            try {
                CrudUtil.execute("UPDATE Customer set name=?, address=?,customerType=? where customerId=?" ,
                        txtUpdateCName.getText(),
                        txtUpdateCAddress.getText(),
                        txtUpdateCType.getText(),
                        cmbUpdateOrderId.getValue()
                );
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }



    }

    private void setCustomerupIDs() {

        try {
            ObservableList<String> cuIdObList = FXCollections.observableArrayList(
                    CustomerCrudController.getCustomerIds()
            );
            cmbUpdateOrderId.setItems(cuIdObList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void setCustomerUpDetails(String selectedUpCustomerId) {

        try {
            Customer cu = CustomerCrudController.getCustomer(selectedUpCustomerId);
            if (cu != null) {
                txtUpdateCName.setText(cu.getcName());
                txtUpdateCType.setText(cu.getcType());
                txtUpdateCAddress.setText(cu.getcAddress());


            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
