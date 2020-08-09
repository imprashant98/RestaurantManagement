package RestaurantManagement;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static RestaurantManagement.DB.a;
import static RestaurantManagement.DB.count;

/**
 * FXML Controller class
 *
 * @author Prashant
 */
public class MainMenuController implements Initializable {

public static String EditTable = "";
//public static String number = "";
public static int E_id_selection;
public static String E_name_selection;
public static String E_type_selection;
public static String E_cost_selection;
public static Stage MainStage;
public static Pane m;
public static Pane d;
@FXML
TextField numMeals;
@FXML
TextField numDrinks;
@FXML
Pane Meals;
@FXML
Pane Drinks;
@FXML
ComboBox typeD;
@FXML
ComboBox typeM;
@FXML
TextField numM;
@FXML
TextField nameM;
@FXML
Label lblnameM;
@FXML
TextField costM;
@FXML
Label lblcostM;
@FXML
Label lblTypeM;
@FXML
TextField numD;
@FXML
TextField nameD;
@FXML
Label lblnameD;
@FXML
Label lblTypeD;
@FXML
TextField costD;
@FXML
Label lblcostD;
//tableview m:
ObservableList<Meals> listM = null;
@FXML
TableView<Meals> tableM;
@FXML
TableColumn<RestaurantManagement.Meals, Integer> numCM;
@FXML
TableColumn<Meals, String> nameCM;
@FXML
TableColumn<Meals, String> typeCM;
@FXML
TableColumn<RestaurantManagement.Meals, String> costCM;
//tableview d:
ObservableList<RestaurantManagement.Drinks> listD = null;
@FXML
TableView<Drinks> tableD;
@FXML
TableColumn<Drinks, Integer> numCD;
@FXML
TableColumn<Drinks, String> nameCD;
@FXML
TableColumn<Drinks, String> typeCD;
@FXML
TableColumn<RestaurantManagement.Drinks, String> costCD;

public static void close() {
    MainStage.close();
}

public void entered(Event e) {
    ((Button) e.getSource()).setScaleX(1.1);
    ((Button) e.getSource()).setScaleY(1.1);
}

public void exited(Event e) {
    ((Button) e.getSource()).setScaleX(1);
    ((Button) e.getSource()).setScaleY(1);
}

public void Drinks() {
    Meals.setVisible(false);
    Drinks.setVisible(true);
    
}

public void Meals() {
    Meals.setVisible(true);
    Drinks.setVisible(false);
    
}

public void ClearMeals() {
    numM.clear();
    nameM.clear();
    typeM.getSelectionModel().clearSelection();
    costM.clear();
}

public void ClearDrinks() {
    numD.clear();
    nameD.clear();
    typeD.getSelectionModel().clearSelection();
    costD.clear();
}

public void onLogout(Event e) {
    
    StageManager.getInstance().mainStage.hide();
    StageManager.getInstance().loginStage.show();
    
    
}

public void addMeals() {
    // String number =
    String name = nameM.getText();
    String type = typeM.getSelectionModel().getSelectedItem().toString();
    String cost = costM.getText();
    
    if (DB.insertMeals("Meals", name, type, cost)) {
        
        a.show();
    }
    numMeals.setText(String.valueOf(count("Mid", "meals")));
    int counter = Integer.parseInt(numMeals.getText());
    
    numM.setText(String.valueOf(counter));
    
    
    listM = DB.getMeals();
    
    tableM.setItems(listM);
    
    loadData();
    
    
}


public void deleteMeals() {
    Meals id = tableM.getSelectionModel().getSelectedItem();
    
    try {
        DB.delMeals(id.getId());
        listM = DB.getMeals();
        
        tableM.setItems(listM);
        
        numMeals.setText(String.valueOf(count("Mid", "meals")));
        
        int counter = Integer.parseInt(numMeals.getText());
        numM.setText(String.valueOf(counter));
        
        loadData();
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    
    
}

public void deleteDrinks() {
    Drinks id = tableD.getSelectionModel().getSelectedItem();
    
    try {
        DB.delDrinks(id.getId());
        
        listD = DB.getDrinks();
        
        tableD.setItems(listD);
        
        numDrinks.setText(String.valueOf(count("Did", "drinks")));
        
        int counter = Integer.parseInt(numDrinks.getText());
        numD.setText(String.valueOf(counter));
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    
}


public void addDrinks() {
    String name = nameD.getText();
    String type = typeD.getSelectionModel().getSelectedItem().toString();
    String cost = costD.getText();
    
    if (DB.insertDrinks(name, type, cost)) {
        
        a.show();
    }
    
    try {
        numDrinks.setText(String.valueOf(count("Did", "drinks")));
        listD = DB.getDrinks();
        
        tableD.setItems(listD);
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    
    
}

public void onEdit(ActionEvent e) {
    if (tableM.getSelectionModel().getSelectedItem() == null || tableD.getSelectionModel().getSelectedItem() == null) {
        new Alert(Alert.AlertType.ERROR, "Editable Row Not Found!!\nPlease Select the Row", ButtonType.OK).show();
    } else {
        //   Stage stage1 = (Stage) ((Node) e.getSource()).getScene().getWindow();
        // MainStage = stage1;
        EditTable = ((Button) e.getSource()).getText();
        
        if (EditTable.equals("Edit Drinks")) {
            
            E_id_selection = tableD.getSelectionModel().getSelectedItem().getId();
            E_name_selection = tableD.getSelectionModel().getSelectedItem().getName();
            E_type_selection = tableD.getSelectionModel().getSelectedItem().getType();
            E_cost_selection = tableD.getSelectionModel().getSelectedItem().getCost();
            
        } else {
            E_id_selection = tableM.getSelectionModel().getSelectedItem().getId();
            E_name_selection = tableM.getSelectionModel().getSelectedItem().getName();
            E_type_selection = tableM.getSelectionModel().getSelectedItem().getType();
            E_cost_selection = tableM.getSelectionModel().getSelectedItem().getCost();
            
        }
        
        //System.out.println("line 267 executed");
        StageManager.getInstance().editStage.show();
        StageManager.getInstance().mainStage.hide();
        //  System.out.println("line 270 executed");
    }
}


public void loadData() {
    listM = DB.getMeals();
    tableM.setItems(listM);
    
    
}

@Override
public void initialize(URL url, ResourceBundle rb) {
    m = Meals;
    d = Drinks;
    
    //Meals: , Drinks:
    
    numMeals.setText(String.valueOf(DB.count("Mid", "meals")));
    
    
    numDrinks.setText(String.valueOf(DB.count("Did", "drinks")));
    
    
    //cbo
    typeD.getItems().addAll("Normal Drink", "Hot Drink", "Cold Drink", "Wines", "Soft Drink", "Hard Drink");
    typeM.getItems().addAll("Breakfast", "Lunch", "Snacks", "Dinner", "Supper");
    
    lblnameD.setLabelFor(nameD);
    lblTypeD.setLabelFor(typeD);
    lblcostD.setLabelFor(costD);
    
    lblnameM.setLabelFor(nameM);
    lblTypeM.setLabelFor(typeM);
    lblcostM.setLabelFor(costM);
    
    numCM.setCellValueFactory(new PropertyValueFactory<Meals, Integer>("id"));
    nameCM.setCellValueFactory(new PropertyValueFactory<Meals, String>("name"));
    typeCM.setCellValueFactory(new PropertyValueFactory<Meals, String>("type"));
    costCM.setCellValueFactory(new PropertyValueFactory<Meals, String>("cost"));
    
    numCD.setCellValueFactory(new PropertyValueFactory<Drinks, Integer>("id"));
    nameCD.setCellValueFactory(new PropertyValueFactory<Drinks, String>("name"));
    typeCD.setCellValueFactory(new PropertyValueFactory<Drinks, String>("type"));
    costCD.setCellValueFactory(new PropertyValueFactory<Drinks, String>("cost"));
    
    try {
        listM = DB.getMeals();
        tableM.setItems(listM);
        
        listD = DB.getDrinks();
        tableD.setItems(listD);
        
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    
    
}

public void onEmployee(ActionEvent e) {
    
    StageManager.getInstance().employeeStage.show();
    StageManager.getInstance().mainStage.hide();
    
    
}

public void onHome(ActionEvent e) {
    new Alert(Alert.AlertType.WARNING, "You Will be Logged Out\nAre you Sure You want to Exit", ButtonType.YES).showAndWait();
    
    StageManager.getInstance().homeStage.show();
    StageManager.getInstance().mainStage.hide();
    
    
}
}
