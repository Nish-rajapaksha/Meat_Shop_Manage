package controller;

import com.jfoenix.controls.JFXTextField;
import controller.CrudContoller.CustomerOrderDetailCrudController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.CustomerOrder;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.CrudUtil;
import util.HomeUtil;
import views.tm.CustomerPaymentTM;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

public class CviewFormController {
    public AnchorPane AllcContext;
    public TableView tblPayment;
    public TableColumn colCPorderId;
    public TableColumn colCPamount;
    public TableColumn colCPtype;
    public JFXTextField txtPTCustomer;
    public Label lblDatePCustomer;
    public JFXTextField txtPACustomer;
    public JFXTextField txtPIDCustomer;
    public ComboBox <String> cmbPOID;
    public TableColumn colCustomerPayId;
    public TableColumn colCustomerPDate;
    public TableColumn colPaymentTypeC;
    public JFXTextField txtDateCustomer;


    public void initialize() throws SQLException, ClassNotFoundException {

       colCustomerPayId.setCellValueFactory(new PropertyValueFactory<>("payCid"));
        colCPorderId.setCellValueFactory(new PropertyValueFactory<>("payORid"));
        colCPamount.setCellValueFactory(new PropertyValueFactory<>("payCORAmount"));
        colCustomerPDate.setCellValueFactory(new PropertyValueFactory<>("payCdate"));
        colPaymentTypeC.setCellValueFactory(new PropertyValueFactory<>("payCType"));





        setCusPaymentIds ();
        CustomerPaymentOrder_Id();
        loadAllPaymentsCus();




        cmbPOID.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    setCustomerPayIdDetails(newValue);

                });
    }

    private void loadAllPaymentsCus() throws ClassNotFoundException, SQLException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Customer_Payment");
        ObservableList<CustomerPaymentTM> CusPobList = FXCollections.observableArrayList();

        while (result.next()){
            CusPobList.add(
                    new CustomerPaymentTM(
                            result.getString("cusPayId"),
                            result.getString("cusOrderId"),
                            result.getDouble("payAmount"),
                            result.getDate("payDate"),
                            result.getString("PType")



                    )
            );




        }

        tblPayment.setItems(CusPobList);





    }

    private  void CustomerPaymentOrder_Id(){
        try{
            ResultSet result = CrudUtil.execute("SELECT cusPayId FROM Customer_Payment  ORDER BY cusPayId DESC LIMIT 1");
            if(result.next()){
                String cpOId=result.getString("cusPayId");
                int length = cpOId.length();
                String txt= cpOId.substring(0,1);
                String num=cpOId.substring(1,length);
                int n=Integer.parseInt(num);
                n++;
                String snum=Integer.toString(n);
                String ftxt=txt+snum;
                txtPIDCustomer.setText(ftxt);
            }else{
                txtPIDCustomer.setText("P1");
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        } }

    private void setCustomerPayIdDetails(String selectedCOrderId) {
        try {
            CustomerOrder cp =  CustomerOrderDetailCrudController.getCustomerOrder(selectedCOrderId);
            if (cp != null) {
                txtPACustomer.setText(String.valueOf(cp.getcCost()));
               // lblDatePCustomer.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    private void setCusPaymentIds (){
        try {
            ObservableList<String> cusPIdObList = FXCollections.observableArrayList(
                    CustomerOrderDetailCrudController.getCustomerOrderIds()
            );
            cmbPOID.setItems(cusPIdObList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } }



        public void btnCustomerPOnAction(ActionEvent actionEvent) throws IOException {
        HomeUtil.stageSet(AllcContext,"DashBordForm");
    }

    public void btnCustomerPayOnAction(ActionEvent actionEvent) {
        Alert palert=new Alert(Alert.AlertType.CONFIRMATION," Customer order Payment has been paid ", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> pbuttonType = palert.showAndWait();
        if (pbuttonType.get().equals(ButtonType.YES)) {

            try {
                CrudUtil.execute("INSERT INTO Customer_Payment Values(?,?,?,?,?)",txtPIDCustomer.getText(),cmbPOID.getValue(),txtPACustomer.getText(),txtDateCustomer.getText(),txtPTCustomer.getText());
                loadAllPaymentsCus();


            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();



        }

    }

    }

    public void btnCustomerPaymentPrintOnAction(ActionEvent actionEvent) {

        String cpid = cmbPOID.getValue();
        String pcId = txtPIDCustomer.getText();
        String cpAmount = txtPACustomer.getText();
        String  cDate = txtDateCustomer.getText();
        String cpType = txtPTCustomer.getText();




        HashMap cparamMap = new HashMap();
        cparamMap.put("CustomerOrId", cpid);// id = report param name // customerID = UI typed value
        cparamMap.put("CusPaymentId", pcId);
        cparamMap.put("CusPayDate", cDate);
        cparamMap.put("CusPayType", cpType);
        cparamMap.put("CusPayAmount", cpAmount);





        try {
            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/reports/CustomerPayments.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, cparamMap, new JREmptyDataSource(1));
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }


    }
}
