package RestaurantManagement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

/**
 * FXML Controller class
 *
 * @author Prashant
 */

public class LoginController {

    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Hyperlink signup;


public void entered(MouseEvent e) {
    ((Button) e.getSource()).setScaleX(1.1);
    ((Button) e.getSource()).setScaleY(1.1);
}

public void exited(MouseEvent e) {
    ((Button) e.getSource()).setScaleX(1);
    ((Button) e.getSource()).setScaleY(1);
}


    public void login(ActionEvent e) {
        String varUsername,varPassword;
        varUsername = username.getText();
        varPassword = password.getText();
        if(varUsername.equals("") && varPassword.equals(""))
        {
            new Alert(Alert.AlertType.WARNING, "UserId and Password not Entered",ButtonType.OK).showAndWait();
        }
        else{
            try {
               if( DB.Login(varUsername,varPassword))
               {
                   new Alert(Alert.AlertType.CONFIRMATION, "Login Successful!", ButtonType.OK).showAndWait();
                   username.clear();
                    password.clear();
                     StageManager.getInstance().loginStage.hide();
                      StageManager.getInstance().mainStage.show();

               }
               else
               {
                   new Alert(Alert.AlertType.ERROR, "Incorrect UserId or Password!", ButtonType.OK).showAndWait();
               }



            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

//        if (username.getText().equals("admin") && password.getText().equals("Prashant")) {
//            username.clear();
//            password.clear();
//            StageManager.getInstance().loginStage.hide();
//            StageManager.getInstance().mainStage.show();
//
//        }
//        else {
//            new Alert(Alert.AlertType.ERROR, "Login failed!", ButtonType.OK).show();
//        }

    }
    public  void signup(ActionEvent e)
    {
       StageManager.getInstance().loginStage.hide();
       StageManager.getInstance().registerStage.show();
    }

    @FXML
    public void exit(ActionEvent e) {
        Platform.exit();
    }

}

