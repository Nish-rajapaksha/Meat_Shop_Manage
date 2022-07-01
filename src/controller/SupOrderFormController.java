package controller;

import controller.CrudContoller.CustomerCrudController;
import controller.CrudContoller.MeatItemCrudController;
import controller.CrudContoller.SupplierCrudController;
import controller.CrudContoller.SupplierOrderCrudController;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.CrudUtil;
import util.HomeUtil;
import views.tm.CartTM;
import views.tm.SupplierCartTM;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

public class SupOrderFormController {
    public AnchorPane SupOrderContext;
    public TextField txtSupplierName;
    public TextField txtSupplierOId;
    public ComboBox <String > cmbSupplierIds;
    public TextField txtSupplierMeat;
    public TextField txtSupplierUnitPrice;
    public TextField txtSupplierQty;
    public TableView tblSCart;
    public TableColumn colSMeatId;
    public TableColumn colStype;
    public TableColumn colSunit;
    public TableColumn colSQty;
    public TableColumn colSTotalCost;
    public TableColumn colSRemove;
    public Label lblSupplierTotal;
    public Label lblSupDate;
    public ComboBox <String> cmbSupllierMeatId;

    public void initialize() {

        colSMeatId.setCellValueFactory(new PropertyValueFactory<>("supplier_meatCartId"));
        colStype.setCellValueFactory(new PropertyValueFactory<>("supplier_cartType"));
        colSunit.setCellValueFactory(new PropertyValueFactory<>("supplier_cartUnitPrice"));
        colSQty.setCellValueFactory(new PropertyValueFactory<>("supplier_cartQty"));
        colSTotalCost.setCellValueFactory(new PropertyValueFactory<>("supplier_cartTotalcost"));
        colSRemove.setCellValueFactory(new PropertyValueFactory<>("supplier_cartRemove"));


       loadDateSupplierOrder();
       setSupplierIDs();
       setsMeatID ();
       SupplierOrder_Id();


       cmbSupplierIds.getSelectionModel().selectedItemProperty()
               .addListener((observable, oldValue, newValue) -> {
                   setSupplierDetails(newValue);
               });

        cmbSupllierMeatId.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    setSupplierMeatItemIdDetails(newValue);
                });
   }



    private void setsMeatID (){
        try {
            ObservableList<String> msIdObList = FXCollections.observableArrayList(
                    MeatItemCrudController.getMeatIds()
            );
            cmbSupllierMeatId.setItems(msIdObList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    private void setSupplierMeatItemIdDetails(String selectedSMeatId) {
        try {
            MeatItem sm =  MeatItemCrudController.getMeatItem(selectedSMeatId);
            if (sm != null) {
                txtSupplierMeat.setText(sm.getMeat_Type());
                txtSupplierUnitPrice.setText(String.valueOf(sm.getUnitPrice()));

            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    private void loadDateSupplierOrder() {
        lblSupDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    private void setSupplierIDs(){

        try {
            ObservableList<String> sIdObList = FXCollections.observableArrayList(
                    SupplierCrudController.getSupplierIds()
            );
            cmbSupplierIds.setItems(sIdObList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void setSupplierDetails(String selectedSupplierId) {

        try {
            Supplier s = SupplierCrudController.getSupplier(selectedSupplierId);
            if (s != null) {
                txtSupplierName.setText(s.getsName());

            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    private  void SupplierOrder_Id(){
        try{
            ResultSet result = CrudUtil.execute("SELECT supOrderId FROM Supplier_Order  ORDER BY supOrderId DESC LIMIT 1");
            if(result.next()){
                String sOId=result.getString("supOrderId");
                int length=sOId.length();
                String txt= sOId.substring(0,3);
                String num=sOId.substring(3,length);
                int n=Integer.parseInt(num);
                n++;
                String snum=Integer.toString(n);
                String ftxt=txt+snum;
                txtSupplierOId.setText(ftxt);
            }else{
                txtSupplierOId.setText("SOR101");
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        } }




    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        HomeUtil.stageSet(SupOrderContext,"DashBordForm");
    }



    public void btnSupplierPlaceOrderOnAction(ActionEvent actionEvent) throws SQLException {

        Alert alert=new Alert(Alert.AlertType.CONFIRMATION," Supplier Place order Successfull ", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {

            try {
                CrudUtil.execute("INSERT INTO Supplier_Order Values(?,?,?)",txtSupplierOId.getText(),cmbSupplierIds.getValue(),Double.parseDouble(lblSupplierTotal.getText()));

                ArrayList<SupplierOrderDetail> sOdetails = new ArrayList<>();
                for (SupplierCartTM stm : stmList) {
                    sOdetails.add(
                            new SupplierOrderDetail(
                                    txtSupplierOId.getText(),
                                    stm.getSupplier_meatCartId(),
                                    lblSupDate.getText(),
                                    stm.getSupplier_cartUnitPrice(),
                                    stm.getSupplier_cartQty()

                            )
                    );
                    CrudUtil.execute("INSERT INTO Supplier_OrderDetail Values(?,?,?,?,?)",txtSupplierOId.getText(),stm.getSupplier_meatCartId(),
                            lblSupDate.getText(),stm.getSupplier_cartUnitPrice(),stm.getSupplier_cartQty()
                    );
                }


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }

    }

    ObservableList<SupplierCartTM> stmList = FXCollections.observableArrayList();

    public void btnSupplierAddToCartOnAction(ActionEvent actionEvent) {

        double sunitPrice = Double.parseDouble(txtSupplierUnitPrice.getText());
        int qty = Integer.parseInt(txtSupplierQty.getText());
        double totalCost = sunitPrice * qty;

        SupplierCartTM isExists = isExists(cmbSupllierMeatId.getValue());

        if (isExists != null) {
            for (SupplierCartTM temp : stmList
            ) {
                if (temp.equals(isExists)) {
                    temp.setSupplier_cartQty((temp.getSupplier_cartQty() + qty));
                    temp.setSupplier_cartTotalcost(temp.getSupplier_cartTotalcost() + totalCost);
                }
            }
        } else {
            Button btn = new Button("Delete");

            SupplierCartTM stm = new SupplierCartTM(
                    cmbSupllierMeatId.getValue(),
                    txtSupplierMeat.getText(),
                    sunitPrice,
                    qty,
                    totalCost,
                    btn
            );

            btn.setOnAction(e -> {
                stmList.remove(stm);
                calculateTotal();
            });


            stmList.add(stm);
            tblSCart.setItems(stmList);
        }
        tblSCart.refresh();
        calculateTotal();
    }



    private SupplierCartTM isExists(String mitemCode) {
        for (SupplierCartTM stm : stmList
        ) {
            if (stm.getSupplier_meatCartId().equals(mitemCode)) {
                return stm;
            }
        }
        return null;
    }

    private void calculateTotal() {
        double total = 0;
        for (SupplierCartTM stm : stmList
        ) {
            total += stm.getSupplier_cartTotalcost();
        }
        lblSupplierTotal.setText(String.valueOf(total));
    }

    public void btnSupOrderPrintOnAction(ActionEvent actionEvent) {
        String supTotal = lblSupplierTotal.getText();

        HashMap Sup_paramMap = new HashMap();
        Sup_paramMap.put("TotalCost", supTotal);

        if(!tblSCart.getItems().isEmpty()){

            try {
                JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/reports/SupplierOrderPrint.jasper"));
                ObservableList sitems = tblSCart.getItems();
                JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, Sup_paramMap, new JRBeanArrayDataSource(tblSCart.getItems().toArray()));
                JasperViewer.viewReport(jasperPrint,false);

            } catch (JRException e) {
                e.printStackTrace();
            }

        }else{


            tblSCart.refresh();
        }



    }
}
