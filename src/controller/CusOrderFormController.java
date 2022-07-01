package controller;

import controller.CrudContoller.CustomerCrudController;
import controller.CrudContoller.MeatItemCrudController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.CustomerOrderDetail;
import model.MeatItem;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.CrudUtil;
import util.HomeUtil;
import views.tm.CartTM;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

public class CusOrderFormController {
    public AnchorPane CusOrderContext;
    public TextField txtCustName;
    public TextField txtCusOrId;
    public ComboBox <String>cmbCustomerId;
    public TextField txtMeatType;
    public TextField txtUnitPrice;
    public TextField txtQtyOnHand;
    public TextField txtCusQty;
    public TableView tblCustomerCart;
    public TableColumn colMeatItemId;
    public TableColumn colCUnitPrice;
    public TableColumn colcType;
    public TableColumn colQty;
    public TableColumn colTotalCost;
    public TableColumn colRemove;
    public Label lblCusTotal;
    public ComboBox <String>cmbMeatItem;
    public Label lblDateCus;


    public void initialize() {


        colMeatItemId.setCellValueFactory(new PropertyValueFactory<>("meatCartId"));
        colcType.setCellValueFactory(new PropertyValueFactory<>("cartType"));
        colCUnitPrice.setCellValueFactory(new PropertyValueFactory<>("cartUnitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("cartQty"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory("cartTotalcost"));
        colRemove.setCellValueFactory(new PropertyValueFactory("cartRemove"));


    loadDateCustomerOrder();
    setCustomerIDs();
    setMeatID ();
    customerOrder_Id();


        cmbCustomerId.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    setCustomerDetails(newValue);
                });

        cmbMeatItem.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    setMeatItemIdDetails(newValue);
                });

   }

   private  void customerOrder_Id(){
       try{
           ResultSet result = CrudUtil.execute("SELECT cusOrderId FROM Customer_Order  ORDER BY cusOrderId DESC LIMIT 1");
           if(result.next()){
               String OId=result.getString("cusOrderId");
               int length=OId.length();
               String txt= OId.substring(0,3);
               String num=OId.substring(3,length);
               int n=Integer.parseInt(num);
               n++;
               String snum=Integer.toString(n);
               String ftxt=txt+snum;
               txtCusOrId.setText(ftxt);
           }else{
               txtCusOrId.setText("COR101");
           }
       }catch (ClassNotFoundException | SQLException e){
           e.printStackTrace();
       }


   }

    private void setMeatItemIdDetails(String selectedMeatId) {
        try {
          MeatItem m =  MeatItemCrudController.getMeatItem(selectedMeatId);
            if (m != null) {
                txtMeatType.setText(m.getMeat_Type());
                txtUnitPrice.setText(String.valueOf(m.getUnitPrice()));
                txtQtyOnHand.setText(String.valueOf(m.getMeatqtyOnHand()));

            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void setCustomerDetails(String selectedCustomerId) {

        try {
         Customer c = CustomerCrudController.getCustomer(selectedCustomerId);
            if (c != null) {
                txtCustName.setText(c.getcName());

            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void loadDateCustomerOrder()
    {
    lblDateCus.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    private void setMeatID (){
        try {
            ObservableList<String> mIdObList = FXCollections.observableArrayList(
                    MeatItemCrudController.getMeatIds()
            );
            cmbMeatItem.setItems(mIdObList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void setCustomerIDs(){

        try {
            ObservableList<String> cIdObList = FXCollections.observableArrayList(
                    CustomerCrudController.getCustomerIds()
            );
            cmbCustomerId.setItems(cIdObList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        HomeUtil.stageSet(CusOrderContext,"DashBordForm");
    }



    ObservableList<CartTM> tmList = FXCollections.observableArrayList();

    public void addToCartOnAction(ActionEvent actionEvent) {

        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtCusQty.getText());
        double totalCost = unitPrice * qty;

        CartTM isExists = isExists(cmbMeatItem.getValue());

        if (isExists != null) {
            for (CartTM temp : tmList
            ) {
                if (temp.equals(isExists)) {
                    temp.setCartQty((temp.getCartQty() + qty));
                    temp.setCartTotalcost(temp.getCartTotalcost() + totalCost);
                }
            }
        } else {
            Button btn = new Button("Delete");

            CartTM tm = new CartTM(
                    cmbMeatItem.getValue(),
                    txtMeatType.getText(),
                    unitPrice,
                    qty,
                    totalCost,
                    btn
            );

            btn.setOnAction(e -> {
                tmList.remove(tm);
                calculateTotal();
            });


            tmList.add(tm);
            tblCustomerCart.setItems(tmList);
        }
        tblCustomerCart.refresh();
        calculateTotal();
    }



    private CartTM isExists(String mitemCode) {
        for (CartTM tm : tmList
        ) {
            if (tm.getMeatCartId().equals(mitemCode)) {
                return tm;
            }
        }
        return null;
    }

    private void calculateTotal() {
        double total = 0;
        for (CartTM tm : tmList
        ) {
            total += tm.getCartTotalcost();
        }
        lblCusTotal.setText(String.valueOf(total));
    }


    public void btnCusPlaceOrderOnAction(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION," Customer Place order Successfull ", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {

            try {
                for (int i = 0; i < tmList.size(); i++) {
                    CrudUtil.execute("  UPDATE MeatItem set qtyOnHand =qtyOnHand-?  where meatItemId=?", tmList.get(i).getCartQty(), tmList.get(i).getMeatCartId());
                }

                CrudUtil.execute("INSERT INTO Customer_Order Values(?,?,?)",txtCusOrId.getText(),cmbCustomerId.getValue(),Double.parseDouble(lblCusTotal.getText()));

                ArrayList<CustomerOrderDetail> sOdetails = new ArrayList<>();
                for (CartTM ctm : tmList) {
                    sOdetails.add(
                            new CustomerOrderDetail(
                                    txtCusOrId.getText(),
                                    ctm.getMeatCartId(),
                                    lblDateCus.getText(),
                                    ctm.getCartUnitPrice(),
                                    ctm.getCartQty()

                            )
                    );
                    CrudUtil.execute("INSERT INTO Customer_OrderDetail Values(?,?,?,?,?)",txtCusOrId.getText(),ctm.getMeatCartId(),
                            lblDateCus.getText(),ctm.getCartUnitPrice(),ctm.getCartQty()
                    );
                }


            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }


        }
    }

    public void btnPlaceOrderPrintOnActionCus(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


   String cusTotal = lblCusTotal.getText();

        HashMap Cus_paramMap = new HashMap();
        Cus_paramMap.put("TotalCost", cusTotal);

            if(!tblCustomerCart.getItems().isEmpty()){

                try {
                    /** NO COMPILATION -LOAD AND VIEW */



                    //catch the report
                    JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/reports/CustomerCartPrint1.jasper"));

                    ObservableList items = tblCustomerCart.getItems();

                    //Fill the Information which report needed
                    JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, Cus_paramMap, new JRBeanArrayDataSource(tblCustomerCart.getItems().toArray()));

                    //then the report is ready let's view
                    JasperViewer.viewReport(jasperPrint,false);

                } catch (JRException e) {
                    e.printStackTrace();
                }

            }else{


                tblCustomerCart.refresh();
            }



    }
}
