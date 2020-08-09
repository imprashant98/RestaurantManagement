package RestaurantManagement;

import com.sun.javafx.logging.PlatformLogger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class HomeController implements Initializable {

public void entered(MouseEvent e) {
    ((javafx.scene.control.Button) e.getSource()).setScaleX(1.1);
    ((javafx.scene.control.Button) e.getSource()).setScaleY(1.1);
}

public void exited(MouseEvent e) {
    ((javafx.scene.control.Button) e.getSource()).setScaleX(1);
    ((Button) e.getSource()).setScaleY(1);
}

@Override
public void initialize(URL location, ResourceBundle resources) {

}

public void onAdmin(ActionEvent event) {
    StageManager.getInstance().loginStage.show();
    StageManager.getInstance().homeStage.hide();
}

public void onOrder(ActionEvent event) {
    StageManager.getInstance().orderStage.show();
    StageManager.getInstance().homeStage.hide();
    
}

public void onExit(ActionEvent event) {
    System.exit(0);
}
}
