package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.MeatItem;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.CrudUtil;
import util.HomeUtil;
import views.tm.MeatStockTM;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MeatStockFormController {
    public AnchorPane stockContext;
    public TextField txtStockId;
    public TextField txtStockType;
    public TextField txtmStockUnitPrice;
    public TableView <MeatStockTM>tblStock;
    public TableColumn colStockId;
    public TableColumn colStockType;
    public TableColumn colStockUnitPrice;
    public TableColumn colStockQty;
    public TableColumn colStockOption;
    public TextField txtmStockQty;
    MeatStockTM meats;
    Boolean meatIdpattern;
    Boolean unitPricePattern;
    Boolean typePattern;
    Boolean qtypattern;


    public void initialize()  {


        try {
            loadAllMeatItems();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }




        colStockId.setCellValueFactory(new PropertyValueFactory("stockMeatItemId"));
        colStockType.setCellValueFactory(new PropertyValueFactory("stockMeatType"));
        colStockUnitPrice.setCellValueFactory(new PropertyValueFactory("stockMeatUnitP"));
        colStockQty.setCellValueFactory(new PropertyValueFactory("stockMeatQty"));
        colStockOption.setCellValueFactory(new PropertyValueFactory("stockDeleteMeat"));

        tblStock.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            meats = newValue;
            if (newValue!=null){
                setMeatData(newValue);
            }
        });



    }

    public void isMeatValid(){
        meatIdpattern  =txtStockId.getText().matches("[M]{1}[0-9]{1,15}");
        unitPricePattern = txtmStockUnitPrice.getText().matches("^[0-9]{3,}(.)(00)$");
        qtypattern =txtmStockQty.getText().matches("^[0-9 ]{3,}$");

    }

    public void validMeatConfirm(Boolean pattern,TextField inputs){
        if (pattern){
            if (inputs.equals(txtStockId)) {
                txtStockId.setStyle("-fx-border-color: #c0c0c0 ; -fx-border-width: 1px;");
            }else if (inputs.equals(txtmStockUnitPrice)){
                txtmStockUnitPrice.setStyle("-fx-border-color: #c0c0c0 ; -fx-border-width: 1px;");
            }else if (inputs.equals(txtmStockQty)){
                txtmStockQty.setStyle("-fx-border-color: #c0c0c0 ; -fx-border-width: 1px;");
            }
        }else {
            inputs.requestFocus();

            if (inputs.equals(txtStockId)){
                txtStockId.setStyle("-fx-border-color: red ; -fx-border-width: 1px; ");
            }else if (inputs.equals(txtmStockUnitPrice)){
                txtmStockUnitPrice.setStyle("-fx-border-color: red ; -fx-border-width: 1px; ");
            }else if (inputs.equals(txtmStockQty)){
                txtmStockQty.setStyle("-fx-border-color: red ; -fx-border-width: 1px; ");
            }
        }
    }

    private void setMeatData(MeatStockTM newValue) {

        txtStockId.setText(newValue.getStockMeatItemId());
        txtStockType.setText(newValue.getStockMeatType());
        txtmStockQty.setText(String.valueOf(newValue.getStockMeatQty()));
        txtmStockUnitPrice.setText(String.valueOf(newValue.getStockMeatUnitP()));



    }
    ObservableList<MeatStockTM> msobList = FXCollections.observableArrayList();
    private void loadAllMeatItems() throws ClassNotFoundException, SQLException {
        ResultSet result = CrudUtil.execute("SELECT * FROM MeatItem");


        while (result.next()) {
            Button btn = new Button("Delete");
            msobList.add(
                    new MeatStockTM(
                            result.getString("meatItemId"),
                            result.getString("type"),
                            result.getDouble("unitPrice"),
                            result.getInt("qtyOnHand"),
                            btn


                    )


            );

            btn.setOnAction(event -> {
                try {
                    CrudUtil.execute("DELETE FROM MeatItem WHERE meatItemId =?;",meats.getStockMeatItemId());
                    msobList.remove(meats);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();

                }
                tblStock.refresh();
            });



        }

        tblStock.refresh();
        tblStock.setItems(msobList);

    }



    public void btnAddMeatItemOnAction(ActionEvent actionEvent) {


        isMeatValid();
        validMeatConfirm(meatIdpattern, txtStockId);
        validMeatConfirm(unitPricePattern, txtmStockUnitPrice);
        validMeatConfirm(qtypattern, txtmStockQty);



        if (meatIdpattern && unitPricePattern && qtypattern) {


            MeatItem mS = new MeatItem(
                    txtStockId.getText(), txtStockType.getText(), Double.parseDouble(txtmStockUnitPrice.getText()), Integer.parseInt(txtmStockQty.getText()));

            try {


                if (CrudUtil.execute("INSERT INTO MeatItem VALUES (?,?,?,?)", mS.getMeat_Id(), mS.getMeat_Type(), mS.getUnitPrice(), mS.getMeatqtyOnHand())) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Meat Item Added to the System!..").show();
                    Button btn = new Button("Add");
                    MeatStockTM mItem = new MeatStockTM( txtStockId.getText(), txtStockType.getText(), Double.parseDouble(txtmStockUnitPrice.getText()), Integer.parseInt(txtmStockQty.getText()),btn);
 ;
                    msobList.add(mItem);
                    tblStock.refresh();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Unfortunately Meat Item Not Added.. !").show();
            }


        }
    }

    public void btnUpdateMeatItemOnAction(ActionEvent actionEvent) {
        MeatItem tm =new MeatItem(
                txtStockId.getText(),
                txtStockType.getText(),
                Double.parseDouble(txtmStockUnitPrice.getText()),
                Integer.parseInt(txtmStockQty.getText())

        );

        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Successfully Updated The Meat Stock", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> MbuttonType = alert.showAndWait();
        if (MbuttonType.get().equals(ButtonType.YES)) {
            try {
                CrudUtil.execute("UPDATE MeatItem set type=?, unitPrice=?,qtyOnHand=? where meatItemId=?",
                        txtStockType.getText(),
                        txtmStockUnitPrice.getText(),
                        txtmStockQty.getText(),
                        txtStockId.getText()
                );
             loadAllMeatItems();



            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } }

    public void btnStockHomeOnAction(ActionEvent actionEvent) throws IOException {

        HomeUtil.stageSet(stockContext,"DashBordForm");
    }

    public void btnPrintMeatOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {



        try {
            JasperReport compiledReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/reports/MeatStock.jasper"));
            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReport, null, connection);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }










    }

}

