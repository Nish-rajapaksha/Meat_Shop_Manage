package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.CrudUtil;
import views.tm.MeatTM;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class DashBordFormController {
    public AnchorPane DashBordContext;
    public Label lblTime;
    public Label lblDate;
    public TableView tblMeatStock;
    public TableColumn colMID;
    public TableColumn colMType;
    public TableColumn colQTYON;
    public TableColumn colMUnitPrice;
    public PieChart pchrtMeatStock;
  int cOrders;
   int sOrders;




    public void initialize() {
        loadDateAndTime();

        try {
            loadAllMeatItems();
           loadPieChartMeat();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colMID.setCellValueFactory(new PropertyValueFactory("meatItem_Id"));
        colMType.setCellValueFactory(new PropertyValueFactory("meatItem_Type"));
        colQTYON.setCellValueFactory(new PropertyValueFactory("meatItem_qtyOnHand"));
        colMUnitPrice.setCellValueFactory(new PropertyValueFactory("meatItem_unitPrice"));





    }



    private void loadPieChartMeat() throws SQLException, ClassNotFoundException {

        ResultSet result = CrudUtil.execute("SELECT Count(cusOrderId) FROM Customer_Order;");
        result.next();
        cOrders=result.getInt(1);

        ResultSet sresult = CrudUtil.execute("SELECT Count(supOrderId) FROM Supplier_Order;");
        sresult.next();
        sOrders= sresult.getInt(1);


        ObservableList<PieChart.Data> productMeats = FXCollections.observableArrayList();
        productMeats.add(new PieChart.Data("Customer Orders ",cOrders));
        productMeats.add(new PieChart.Data("Supplier Orders",sOrders));
        productMeats.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(data.getName()," ",data.pieValueProperty())
                )
        );

        pchrtMeatStock.setData(productMeats);
        pchrtMeatStock.setTitle("Sales Counts");


    }

    private void loadAllMeatItems() throws SQLException, ClassNotFoundException {

        ResultSet result = CrudUtil.execute("SELECT * FROM MeatItem");
        ObservableList<MeatTM> obList = FXCollections.observableArrayList();

        while (result.next()){
            obList.add(
                    new MeatTM(
                            result.getString("meatItemId"),
                            result.getString("type"),
                            result.getDouble("unitPrice"),
                            result.getString("qtyOnHand")

                    )
            );
        }
        tblMeatStock.setItems(obList);



    }

    private void loadDateAndTime() {
        /*set Date*/
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        /*set Time*/
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.getHour() + ":" +
                    currentTime.getMinute() + ":" +
                    currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }


    public void LogOutOnAction(ActionEvent actionEvent) throws IOException {

        setUI("MainForm");
    }


    public void ManageCustomerOnAction(ActionEvent actionEvent) throws IOException {

        setUI("ManageCustomerForm");
    }

    public void ManageSupplierOnAction(ActionEvent actionEvent) throws IOException {


        setUI("ManageSupplierForm");

    }

    public void PlaceOrderSupplierOnAction(ActionEvent actionEvent) throws IOException {

        setUI("SupOrderForm");

    }

    public void PlaceOrderCustomerOnAction(ActionEvent actionEvent) throws IOException {


        setUI("CusOrderForm");

    }

    public void CustomerOrderDetailOnAction(ActionEvent actionEvent) throws IOException {


        setUI("CusOrderDetailsForm");
    }

    public void SupplierOrderDetailOnAction(ActionEvent actionEvent) throws IOException {

        setUI("SupOrderDetailsForm");
    }


    public void CustomerPaymentOnAction(ActionEvent actionEvent) throws IOException {


        setUI("CViewForm");
    }

    public void SupplierPaymentOnAction(ActionEvent actionEvent) throws IOException {

        setUI("SViewForm");
    }


    public void IncomeOnAction(ActionEvent actionEvent) throws IOException {

        setUI("IncomeForm");
    }

    private void setUI(String location) throws IOException {

        Stage stage = (Stage) DashBordContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/"+location+".fxml")))); }

    public void meatStockOnAction(ActionEvent actionEvent) throws IOException {

        setUI("MeatStockForm");


    }
}
