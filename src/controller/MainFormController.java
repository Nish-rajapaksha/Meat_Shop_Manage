package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {
    public AnchorPane MainContext;

    public void LoginOnAction(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");

    }


    private void setUI(String location) throws IOException {

        Stage stage = (Stage) MainContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../viewS/"+location+".fxml")))); }
}
