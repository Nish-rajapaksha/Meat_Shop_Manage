package util;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

// Set Dashbord for all
import java.io.IOException;

public class HomeUtil {

    public static void stageSet(AnchorPane anchorPane,String location) throws IOException {
        Stage stage=(Stage) anchorPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(HomeUtil.class.getResource("../views/"+location+".fxml"))));
    }
    }




