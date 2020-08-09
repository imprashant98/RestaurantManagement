package RestaurantManagement;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static RestaurantManagement.DB.E;
import static RestaurantManagement.DB.a;

public class OrderController implements Initializable {


// public String EditTable = "";
public int E_id_selection;
public String E_name_selection;
public String E_type_selection;
public String E_quantity_selection;
public String E_table_selection;
public java.sql.Date E_date_selection;


@FXML
ComboBox<String> selectTable;
@FXML
ComboBox<String> selectOrder;
@FXML
ComboBox<String> selectType;
@FXML
TextField selectQuantity;
@FXML
TextField selectId;
@FXML
DatePicker selectDate;
@FXML
Label labelNumber;

//  ObservableList<String> listQuantity   = FXCollections.observableArrayList("1", "2", "3","4","5","6",);
@FXML
Label labelName;
@FXML
Label labelType;
@FXML
Label labelQuantity;
@FXML
Label labelDate;
@FXML
Label labelId;
@FXML
TextArea txtReceipt;
//tableview Order:
ObservableList<Order> listO = null;
@FXML
TableView<Order> tableO;
@FXML
TableColumn<Order, Integer> idColumn;
@FXML
TableColumn<Order, String> nameColumn;
@FXML
TableColumn<Order, String> tableColumn;
//    @FXML
//    TableColumn<Order, Integer> costColumn;
@FXML
TableColumn<Order, String> quantityColumn;
//    @FXML
//    TableColumn<Order, Integer> totalColumn;
@FXML
TableColumn<Order, String> dateColumn;
@FXML
TableColumn<Order, String> typeColumn;

public void clear() {
    selectQuantity.clear();
    selectDate.hide();
    selectOrder.getSelectionModel().clearSelection();
    selectTable.getSelectionModel().clearSelection();
    selectType.getSelectionModel().clearSelection();
}

public void entered(MouseEvent e) {
    ((Button) e.getSource()).setScaleX(1.1);
    ((Button) e.getSource()).setScaleY(1.1);
}

public void exited(MouseEvent e) {
    ((Button) e.getSource()).setScaleX(1);
    ((Button) e.getSource()).setScaleY(1);
}

public void onDelete(ActionEvent e) {
    
    Order id = tableO.getSelectionModel().getSelectedItem();
    
    try {
        DB.delOrder(id.getId());
        listO = DB.getOrder();
        tableO.setItems(listO);
        selectId.setText(String.valueOf(DB.count("menuId", "menu")));
        //labelName.setText(employeeName.getText());
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    
    
}




@Override
public void initialize(URL location, ResourceBundle resources) {
    
    selectId.setText(String.valueOf(DB.count("menuId", "menu")));
    
    
    //  numDrinks.setText(String.valueOf(DB.count("Did", "drinks")));
    
    
    //cbo
    try {
        selectType.getItems().addAll("drinks", "meals");
        selectOrder.getItems().addAll("Coca-Cola", "Sprite", "Mountain Dew", "Gorkha Beer", "Tuborg Strong Beer", "Vodka","Royal Stag","Black Label", "Khas Cuisine", "Thakali Cuisine", "Maithili Cuisine","Bengali Cuisine","Bhutanese Cuisine","Nigerian Cuisine", "Newa Cuisine", "Special Mo:Mo Cuisine", "Nepali Cuisine");
        selectTable.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    } catch (Exception e) {
        e.printStackTrace();
    }

    
    labelName.setLabelFor(selectOrder);
    labelId.setLabelFor(selectId);
    labelDate.setLabelFor(selectDate);
    
    labelNumber.setLabelFor(selectTable);
    labelQuantity.setLabelFor(selectQuantity);
    labelType.setLabelFor(selectType);
    
    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    tableColumn.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("orderName"));
    typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

    
    
    try {
        listO = DB.getOrder();
        tableO.setItems(listO);
        
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    
    
}

public void onAdd(ActionEvent e) {
    
    int id = Integer.parseInt(selectId.getText());
    String tableNumber = selectTable.getValue();
    String orderName = selectOrder.getValue();
    String type = selectType.getValue();
    String quantity = selectQuantity.getText();
   java.sql.Date date = Date.valueOf(selectDate.getValue());
    
   
    
    
    if (DB.insertOrder("menu", id, tableNumber, orderName, type, quantity, date)) {
        a.show();
    } else {
        E.show();
    }
    
    
    try {
        selectId.setText(String.valueOf(DB.count("menuId", "menu")));
        listO = DB.getOrder();
        tableO.setItems(listO);
        
        
    } catch (SQLException throwables) {
        
        throwables.printStackTrace();
    }
    
    clear();
    
}

public void onUpdate(ActionEvent e) {
    
    
    int id = Integer.parseInt(selectId.getText());
    String tableNumber = selectTable.getValue();
    String orderName = String.valueOf(selectOrder.getSelectionModel().getSelectedItem());
    String type = String.valueOf(selectType.getSelectionModel().getSelectedItem());
    String quantity = selectQuantity.getText();
    java.sql.Date date = Date.valueOf(selectDate.getValue());
    
    
    try {
        DB.menuUpdate(id, tableNumber, orderName, type, quantity, date);
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    try {
        listO = DB.getOrder();
        tableO.setItems(listO);
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }

    clear();
}




public void onReceipt(ActionEvent e) {
    /*
    first we select the table and get value in respective Fields then we extract the datas and try to print receipt for the valid table.
 */
    
    
    String tableNumber = selectTable.getValue();
    String foodName = String.valueOf(selectOrder.getSelectionModel().getSelectedItem());
    String foodType = String.valueOf(selectType.getSelectionModel().getSelectedItem());
    java.sql.Date date = Date.valueOf(selectDate.getValue());
    
    
    dataManager.tableNumber = tableNumber;
    dataManager.foodName = foodName;
    dataManager.foodType = foodType;
    dataManager.date = date;
    
    StageManager.getInstance().createReceiptStage();
    StageManager.getInstance().receiptStage.show();
    
    
}

public void onHome(ActionEvent event) {
    StageManager.getInstance().orderStage.hide();
    StageManager.getInstance().homeStage.show();
}

public void getValue(MouseEvent mouseEvent) {
    E_id_selection = tableO.getSelectionModel().getSelectedItem().getId();
    E_name_selection = tableO.getSelectionModel().getSelectedItem().getOrderName();
    E_table_selection = tableO.getSelectionModel().getSelectedItem().getTableNumber();
    E_type_selection = tableO.getSelectionModel().getSelectedItem().getType();
     E_date_selection = tableO.getSelectionModel().getSelectedItem().getDate();
    E_quantity_selection = tableO.getSelectionModel().getSelectedItem().getQuantity();
    
    
    selectId.setText(String.valueOf(E_id_selection));
    selectTable.setValue(E_table_selection);
    selectOrder.setValue(E_name_selection);
    selectType.setValue(E_type_selection);
    selectQuantity.setText(E_quantity_selection);
    selectDate.setValue(E_date_selection.toLocalDate());
    
    
   
}
}




