package controller;

import com.jfoenix.controls.JFXTextField;
import controller.CrudContoller.CustomerCrudController;
import controller.CrudContoller.SupplierCrudController;
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
import model.Supplier;
import util.CrudUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class UpdateSupplierFormController {
    public AnchorPane SupdateContext;
    public ComboBox <String>cmbUpdateSupplierIds;
    public JFXTextField txtUpdateSupName;
    public JFXTextField txtUpdateSupplierType;
    public JFXTextField txtUpdateSupplierAddress;
    public JFXTextField txtUpdateSupContact;

    public void initialize(){

        setSupplierupIDs();

        cmbUpdateSupplierIds.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    setCustomerUpDetails(newValue);
                });
    }

    private void setSupplierupIDs() {

        try {
            ObservableList<String> suIdObList = FXCollections.observableArrayList(
                    SupplierCrudController.getSupplierIds()
            );
            cmbUpdateSupplierIds.setItems(suIdObList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void setCustomerUpDetails(String selectedUpSupplierId) {
        try {
            Supplier su = SupplierCrudController.getSupplier(selectedUpSupplierId);
            if (su != null) {
                txtUpdateSupName.setText(su.getsName());
                txtUpdateSupplierType.setText(su.getsType());
                txtUpdateSupplierAddress.setText(su.getsAddress());
                txtUpdateSupContact.setText(su.getsContact());


            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) SupdateContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/ManageSupplierForm.fxml"))));
    }

    public void btnSupplierUpdateOnACtion(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Update", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get().equals(ButtonType.YES)){
            try {
                CrudUtil.execute("UPDATE Supplier set name=?, address=?,contact=? ,supplierType=? where supplierId=?",
                        txtUpdateSupName.getText(),
                        txtUpdateSupplierAddress.getText(),
                        txtUpdateSupContact.getText(),
                        txtUpdateSupplierType.getText(),
                        cmbUpdateSupplierIds.getValue()
                );
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
