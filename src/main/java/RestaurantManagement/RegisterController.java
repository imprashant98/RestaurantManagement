package RestaurantManagement;

import com.mysql.jdbc.Statement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static RestaurantManagement.DB.Register;
import static RestaurantManagement.DB.count;

public class RegisterController implements Initializable {

    @FXML
    Pane registerLayout;
    @FXML
    TextField txtName;
    @FXML
    TextField txtAddress;
    @FXML
    TextField txtContact;
    @FXML
    TextField txtEmail;
    @FXML
    TextField txtUsername;
    @FXML
    PasswordField txtPassword;
    @FXML
    PasswordField txtConfirmPassword;
    @FXML
    Label lblName;

    @FXML
    Label lblAddress;
    @FXML
    Label lblContact;
    @FXML
    Label lblEmail;
    @FXML
    Label lblUsername;
    @FXML
    Label lblPassword;
    @FXML
    Label lblConfirmPassword;

public void entered(MouseEvent e) {
    ((Button) e.getSource()).setScaleX(1.1);
    ((Button) e.getSource()).setScaleY(1.1);
}

public void exited(MouseEvent e) {
    ((Button) e.getSource()).setScaleX(1);
    ((Button) e.getSource()).setScaleY(1);
}

    public void setAllFieldsToDefault() {
        txtConfirmPassword.clear();
        txtPassword.clear();
        txtUsername.clear();
        txtEmail.clear();
        txtContact.clear();
        txtAddress.clear();
        txtName.clear();
    }
    public void onRegister(ActionEvent e) {

        String   name, address, email, contact, username,password,password2;

        name = txtName.getText();
        address=txtAddress.getText();
        contact = txtContact.getText();
        email = txtEmail.getText();
        username = txtUsername.getText();
         password = txtPassword.getText();
         password2 = txtConfirmPassword.getText();

        if(password.equals(password2) ) {
            try {
              int  id =  DB.count("SN", "userbase");


                DB.Register(name, address, contact, email, username, password);
                new Alert(Alert.AlertType.CONFIRMATION, "Registered Successfully",ButtonType.FINISH).showAndWait();
                setAllFieldsToDefault();
                StageManager.getInstance().registerStage.hide();
                StageManager.getInstance().loginStage.show();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else {
            new Alert(Alert.AlertType.ERROR, "Password not Matched", ButtonType.OK).showAndWait();

        }


    }
    public void onClear(ActionEvent e)
    {
        new Alert(Alert.AlertType.WARNING, "Are you sure you want to clear entered Data?", ButtonType.APPLY).showAndWait();
        txtName.clear();
        txtAddress.clear();
        txtConfirmPassword.clear();
        txtContact.clear();
        txtEmail.clear();
        txtUsername.clear();
        txtPassword.clear();

    }
    public void onBack(ActionEvent e){
        new Alert(Alert.AlertType.WARNING, "Are You Sure?\nYou want to go Back?", ButtonType.YES).showAndWait();
        StageManager.getInstance().registerStage.hide();
        StageManager.getInstance().loginStage.show();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lblName.setLabelFor(txtName);
        lblAddress.setLabelFor(txtAddress);
        lblContact.setLabelFor(txtContact);
        lblEmail.setLabelFor(txtEmail);
        lblUsername.setLabelFor(txtUsername);
        lblPassword.setLabelFor(txtPassword);
        lblConfirmPassword.setLabelFor(txtConfirmPassword);

    }
}
