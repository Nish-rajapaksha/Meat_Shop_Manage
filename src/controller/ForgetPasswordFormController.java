package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ForgetPasswordFormController {
    public AnchorPane ForgetPasswordContext;
    public JFXTextField txtEmail;
    public JFXTextField txtMobileNumber;

    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ForgetPasswordContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/LoginForm.fxml"))));
    }

    public void btnVerifyIdentityOnAction(ActionEvent actionEvent) {

        String tempEmail = txtEmail.getText();
        String tempMob = txtMobileNumber.getText();

        if (tempEmail.equals("@gmail.com") && tempMob.equals("0777123123")) {

            new Alert(Alert.AlertType.CONFIRMATION, "We send a email and a message for reset password for the system please use that link").show();


        }
    }
}
