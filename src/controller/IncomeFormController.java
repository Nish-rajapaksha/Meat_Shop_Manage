package controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.CrudUtil;
import util.HomeUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IncomeFormController {
    public AnchorPane IncomeContext;
    public Label lblCustomerSales;
    public Label lblSupplierSales;
    public Label lblBalance;
    public TextField txtIncomeCsales;
    public TextField txtIncomeSupSales;
    public TextField txtGrossProfit;
    public TextField txtBills;
    public TextField txtVehicleOutgoings;
    public TextField txtMiscellneous;
    public Label lblNetProfit;
    public PieChart piechrtIncome;
    Double sSales;
    Double cSales;
    Double grossProfit;


    public void initialize(){
        try {
            customerSales();
            SupplierSales();
            TotalBalance();
            pieChartIncome();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void pieChartIncome() {

        ObservableList<PieChart.Data> productDate = FXCollections.observableArrayList();
        productDate.add(new PieChart.Data("Customer",cSales));
        productDate.add(new PieChart.Data("Supplier",sSales));
        productDate.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(data.getName()," ",data.pieValueProperty())
                )
        );
        // add data to the chart
        piechrtIncome.setData(productDate);
        piechrtIncome.setTitle("Finances");



    }

    private void TotalBalance() {
        Double totalBalnce = cSales - sSales ;
        lblBalance.setText(String.valueOf(totalBalnce));

        Double customerPredictable = sSales * 20 /100 + sSales;
        txtIncomeCsales.setText(String.valueOf(customerPredictable));

        txtIncomeSupSales.setText(String.valueOf(sSales));

         grossProfit = customerPredictable- sSales ;
        txtGrossProfit.setText(String.valueOf(grossProfit));




    }

    private void SupplierSales() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT  SUM(Cost) FROM Supplier_Order;");
        result.next();
        sSales=result.getDouble(1);
        lblSupplierSales.setText(String.valueOf(sSales));

    }

    private void customerSales() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT  SUM(Cost) FROM Customer_Order;");
        result.next();
        cSales=result.getDouble(1);
        lblCustomerSales.setText(String.valueOf(cSales));


    }

    public void btnIncomeHomeOnAction(ActionEvent actionEvent) throws IOException {
        HomeUtil.stageSet(IncomeContext,"DashBordForm");
    }


    public void btnCaluuateOnAction(ActionEvent actionEvent) {

                Double bills = Double.valueOf(txtBills.getText());
                Double vehicleOutgoings = Double.valueOf(txtVehicleOutgoings.getText());
                Double miscelleneous = Double.valueOf(txtMiscellneous.getText());
                lblNetProfit.setText(String.valueOf(grossProfit - bills-vehicleOutgoings-miscelleneous));






    }


}
