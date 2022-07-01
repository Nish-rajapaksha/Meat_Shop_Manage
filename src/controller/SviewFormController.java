package controller;

import com.jfoenix.controls.JFXTextField;
import controller.CrudContoller.CustomerOrderDetailCrudController;
import controller.CrudContoller.SupplierOrderCrudController;
import controller.CrudContoller.SupplierOrderDetailCrudController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CustomerOrder;
import model.SupplierOrder;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.CrudUtil;
import util.HomeUtil;
import views.tm.CustomerPaymentTM;
import views.tm.SupplierPaymentTM;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public class SviewFormController {
    public AnchorPane AllsContext;
    public JFXTextField txtPTSupplier;
    public JFXTextField txtPASupplier;
    public JFXTextField txtPIDSupplier;
    public ComboBox<String> cmbPSOID;
    public TableColumn colSupplierPaymentType;
    public TableColumn colSupplierPDate;
    public TableColumn colSupplierPorderId;
    public TableColumn colSupplierPamount;
    public TableColumn colSupplierPayId;
    public TableView tblPaymentSupplier;
    public JFXTextField txtDateSupplier;

    public void initialize() throws SQLException, ClassNotFoundException {

        colSupplierPayId.setCellValueFactory(new PropertyValueFactory<>("supplier_payid"));
        colSupplierPorderId.setCellValueFactory(new PropertyValueFactory<>("supplier_payORid"));
        colSupplierPamount.setCellValueFactory(new PropertyValueFactory<>("supplier_payORAmount"));
        colSupplierPDate.setCellValueFactory(new PropertyValueFactory<>("supplier_paydate"));
        colSupplierPaymentType.setCellValueFactory(new PropertyValueFactory<>("supplier_payType"));





        setSupPaymentIds ();
        SupplierPaymentOrder_Id();
        loadAllPaymentsSup();




        cmbPSOID.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    setSupplierPayIdDetails(newValue);
                });
    }
    private  void SupplierPaymentOrder_Id(){
        try{
            ResultSet result = CrudUtil.execute("SELECT supPayId FROM Supplier_Payment  ORDER BY supPayId DESC LIMIT 1");
            if(result.next()){
                String spOId=result.getString("supPayId");
                int length = spOId.length();
                String txt= spOId.substring(0,2);
                String num=spOId.substring(2,length);
                int n=Integer.parseInt(num);
                n++;
                String snum=Integer.toString(n);
                String ftxt=txt+snum;
                txtPIDSupplier.setText(ftxt);
            }else{
                txtPIDSupplier.setText("PS1");
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        } }



    private void loadAllPaymentsSup() throws ClassNotFoundException, SQLException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Supplier_Payment");
        ObservableList<SupplierPaymentTM> SupPobList = FXCollections.observableArrayList();

        while (result.next()){
            SupPobList.add(
                    new SupplierPaymentTM(
                            result.getString("supPayId"),
                            result.getString("supOrderId"),
                            result.getDouble("payAmount"),
                            result.getDate("payDate"),
                            result.getString("PType")



                    )
            );




        }

        tblPaymentSupplier.setItems(SupPobList);





    }

    private void setSupplierPayIdDetails(String selectedSOrderId) {
        try {
            SupplierOrder sp =  SupplierOrderDetailCrudController.getSupplierOrder(selectedSOrderId);
            if (sp != null) {
                txtPASupplier.setText(String.valueOf(sp.getSorderCost()));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void setSupPaymentIds (){
        try {
            ObservableList<String> supPIdObList = FXCollections.observableArrayList(
                    SupplierOrderDetailCrudController.getSupplierOrderIds()
            );
           cmbPSOID.setItems(supPIdObList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } }

    public void btnSupplierPayOnAction(ActionEvent actionEvent) {
        Alert psalert=new Alert(Alert.AlertType.CONFIRMATION," Supplier order Payment has been paid ", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> psbuttonType = psalert.showAndWait();
        if (psbuttonType.get().equals(ButtonType.YES)) {

            try {
                CrudUtil.execute("INSERT INTO Supplier_Payment Values(?,?,?,?,?)",txtPIDSupplier.getText(),cmbPSOID.getValue(),txtPASupplier.getText(),txtDateSupplier.getText(),txtPTSupplier.getText());
                loadAllPaymentsSup();


            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();



            }

        }


    }

    public void btnHomeOnActionSupplier(ActionEvent actionEvent) throws IOException {
        HomeUtil.stageSet(AllsContext,"DashBordForm");

    }

    public void btnSupplierPaymentPrintOnAction(ActionEvent actionEvent) {

        String spid = cmbPSOID.getValue();
        String scId = txtPIDSupplier.getText();
        String spAmount = txtPASupplier.getText();
        String  sDate = txtDateSupplier.getText();
        String spType = txtPTSupplier.getText();




        HashMap sparamMap = new HashMap();
        sparamMap.put("SupOrderId", spid);
        sparamMap.put("SupPaymentId", scId);
        sparamMap.put("SupPayDate", sDate);
        sparamMap.put("SupPayType", spType);
        sparamMap.put("PaymentAmount", spAmount);





        try {
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/reports/SupplierPayment.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, sparamMap, new JREmptyDataSource(1));
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }




    }
}
