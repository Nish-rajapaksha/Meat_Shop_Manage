package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.Supplier;
import util.CrudUtil;
import views.tm.SupplierTM;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageSuppilerFormController {
    public AnchorPane ManageSupplierContext;
    public TableView <SupplierTM>tblSuppliers;
    public TableColumn colSupId;
    public TableColumn colSName;
    public TableColumn colSupadress;
    public TableColumn colContactN;
    public TableColumn colSremove;
    public TableColumn colSuptype;
    public TextField txtStype;
    public TextField txtSname;
    public TextField txtSupId;
    public TextField txtSupAddress;
    public TextField txtSupContact;
    Boolean sup_namePattern;
    Boolean sup_idPattern;
    Boolean sup_addresspatter;
    Boolean sup_TypePattern;
    Boolean sup_conPattern;


    public void initialize() {
        colSupId.setCellValueFactory(new PropertyValueFactory("supplier_id"));
        colSName.setCellValueFactory(new PropertyValueFactory("supplier_name"));
        colSupadress.setCellValueFactory(new PropertyValueFactory("supplier_address"));
        colContactN.setCellValueFactory(new PropertyValueFactory("supplier_contact"));
        colSuptype.setCellValueFactory(new PropertyValueFactory("supplier_type"));

        try {
            loadAllSupplier();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void isSupValid(){
        sup_idPattern =txtSupId.getText().matches("[S]{1}[0-9]{1}[0-9]{1}[0-9]{1}");
        sup_namePattern = txtSname.getText().matches("^[A-z ]{3,}$");
        sup_addresspatter =txtSupAddress.getText().matches("^[A-z ]{3,}|,[A-z ]{3,}$");
        sup_TypePattern =txtStype.getText().matches("^(new)|(regular)|(New)|(Regular)$");
        sup_conPattern =txtSupContact.getText().matches("^[0-9]{3}(-)[0-9]{7,10}$");

    }

    public void validConfirm(Boolean data,TextField txtS){
        if (data){
            if (txtS.equals(txtSupId)) {
                txtSupId.setStyle("-fx-border-color: #c0c0c0 ; -fx-border-width: 1px;");
            }else if (txtS.equals(txtSname)){
                txtSname.setStyle("-fx-border-color: #c0c0c0 ; -fx-border-width: 1px;");
            }else if (txtS.equals(txtSupAddress)){
                txtSupAddress.setStyle("-fx-border-color: #c0c0c0 ; -fx-border-width: 1px;");
            }else if (txtS.equals(txtStype)){
                txtStype.setStyle("-fx-border-color: #c0c0c0 ; -fx-border-width: 1px;");
            }
            else if (txtS.equals(txtSupContact)){
                txtSupContact.setStyle("-fx-border-color: #c0c0c0 ; -fx-border-width: 1px;");
            }
        }else {
            txtS.requestFocus();

            if (txtS.equals(txtSupId)){
                txtSupId.setStyle("-fx-border-color: red ; -fx-border-width: 1px; ");
            }else if (txtS.equals(txtSname)){
                txtSname.setStyle("-fx-border-color: red ; -fx-border-width: 1px; ");
            }else if (txtS.equals(txtSupAddress)){
                txtSupAddress.setStyle("-fx-border-color: red ; -fx-border-width: 1px; ");
            }else if (txtS.equals(txtStype)){
                txtStype.setStyle("-fx-border-color: red ; -fx-border-width: 1px; ");
            }
            else if (txtS.equals(txtSupContact)){
                txtSupContact.setStyle("-fx-border-color: red ; -fx-border-width: 1px; ");
            }
        }
    }

        public void HomeOnAction(ActionEvent actionEvent) throws IOException {

        setUI("DashBordForm");
    }

    public void btnSupdateOnAction(ActionEvent actionEvent) throws IOException {
        setUI("UpdateSupplierForm");

    }
    private void setUI(String location) throws IOException {

        Stage stage = (Stage) ManageSupplierContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../viewS/"+location+".fxml")))); }

    public void btnAddSupplierOnAction(ActionEvent actionEvent) {

        isSupValid();
        validConfirm(sup_idPattern,txtSupId);
        validConfirm(sup_namePattern, txtSname);
        validConfirm(sup_addresspatter, txtSupAddress);
        validConfirm(sup_TypePattern, txtStype);
        validConfirm(sup_conPattern, txtSupContact);

        if(sup_idPattern && sup_namePattern && sup_addresspatter && sup_TypePattern && sup_conPattern) {


            Supplier s = new Supplier(
                    txtSupId.getText(), txtSname.getText(), txtSupAddress.getText(), txtSupContact.getText(), txtStype.getText());

            try {


                if (CrudUtil.execute("INSERT INTO Supplier VALUES (?,?,?,?,?)", s.getsId(), s.getsName(), s.getsAddress(), s.getsContact(), s.getsType())) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
                    loadAllSupplier();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Inavalid data type added.. !").show();
            }
        }
    }

    private void loadAllSupplier() throws ClassNotFoundException, SQLException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Supplier");
        ObservableList<SupplierTM> obList = FXCollections.observableArrayList();

        while (result.next()){
            obList.add(
                    new SupplierTM(
                            result.getString("supplierId"),
                            result.getString("name"),
                            result.getString("address"),
                            result.getString("contact"),
                            result.getString("supplierType")



                    )
            );
        }

        tblSuppliers.setItems(obList);


    }
}

