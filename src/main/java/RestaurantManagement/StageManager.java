package RestaurantManagement;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class StageManager {

//For authentication
public Stage loginStage;


//For applications
public Stage mainStage;
public Stage receiptStage;
public Stage employeeStage;

public Stage registerStage;
public Stage homeStage;
public Stage orderStage;



private StageManager() {
    
    createLoginStage();

    createHomeStage();
    createMainStage();
    createEmployeeStage();

    createRegisterStage();
    createOrderStage();
    
}

public static StageManager getInstance() {
    return Singleton.INSTANCE;
}

private void createLoginStage() {
    loginStage = new Stage();
    try {
        
        Parent root = load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        loginStage.setScene(scene);
        loginStage.initStyle(StageStyle.UNDECORATED);
        loginStage.setResizable(false);
        loginStage.centerOnScreen();
        loginStage.setResizable(false);
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private void createHomeStage() {
    homeStage = new Stage();
    try {

        Parent root = load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        homeStage.setScene(scene);
       homeStage.initStyle(StageStyle.UNDECORATED);
        homeStage.setResizable(false);
        homeStage.centerOnScreen();
        homeStage.setResizable(false);

    } catch (IOException e) {
        e.printStackTrace();
    }
}

private void createEmployeeStage() {
    employeeStage = new Stage();
    try {
        Parent root = load(getClass().getResource("employeeDetails.fxml"));
        Scene scene = new Scene(root);
        employeeStage.setScene(scene);
        employeeStage.initStyle(StageStyle.UNDECORATED);
        employeeStage.setResizable(false);
        
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private void createRegisterStage() {
    registerStage = new Stage();
    try {
        Parent root = load(getClass().getResource("Register.fxml"));
        Scene scene = new Scene(root);
        registerStage.setScene(scene);
        registerStage.initStyle(StageStyle.UNDECORATED);
        registerStage.setResizable(false);
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}



public void createMainStage() {
    mainStage = new Stage();
    try {
        Parent root = load(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.centerOnScreen();
        mainStage.initStyle(StageStyle.UNDECORATED);
        mainStage.setResizable(false);
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public void createOrderStage() {
    orderStage = new Stage();
    try {
        Parent root = load(getClass().getResource("Order.fxml"));
        Scene scene = new Scene(root);
        orderStage.setScene(scene);
        orderStage.centerOnScreen();
       orderStage.initStyle(StageStyle.UNDECORATED);
        orderStage.setResizable(false);
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public void createReceiptStage() {
    receiptStage = new Stage();
    try {
        Parent root = load(getClass().getResource("ReceiptMemo.fxml"));
        Scene scene = new Scene(root);
        receiptStage.setScene(scene);
        receiptStage.centerOnScreen();
        receiptStage.initStyle(StageStyle.UNDECORATED);
        receiptStage.setResizable(false);
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private static class Singleton {
    private static final StageManager INSTANCE = new StageManager();
}
}


