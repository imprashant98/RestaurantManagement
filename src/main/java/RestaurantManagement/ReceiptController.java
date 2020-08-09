package RestaurantManagement;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReceiptController {

@FXML
public TextArea receiptMemo;


public Label DescLabel;
public Label costLabel;
public Label qtyLabel;
public Label totalLabel;
public Label amountLabel;
public Label dateTime;

Double  amount = 0.00;

String start;

@FXML
public void initialize() {
    showReceipt();
    initClock();
    amountLabel.setText("Rs"+ amount);
}


public void entered(MouseEvent e) {
    ((Button) e.getSource()).setScaleX(1.1);
    ((Button) e.getSource()).setScaleY(1.1);
}

public void exited(MouseEvent e) {
    ((Button) e.getSource()).setScaleX(1);
    ((Button) e.getSource()).setScaleY(1);
}




private void initClock() {
    
    Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd   HH:mm:ss");
        dateTime.setText(LocalDateTime.now().format(formatter));
    }), new KeyFrame(Duration.seconds(1)));
    clock.setCycleCount(Animation.INDEFINITE);
    clock.play();
}

public void showReceipt() {
    receiptMemo.setDisable(true);
    receiptMemo.setStyle("-fx-fill: BLACK;-fx-font-weight:bold;");
    String tableNumber = dataManager.tableNumber;
    String foodName = dataManager.foodName;
    String foodType = dataManager.foodType;
    

    
   
    try {
        String cost = DB.getCost(foodType, foodName);
        ArrayList<Order> orderList1 = DB.getTableOrder(tableNumber);
     
       
        StringBuilder bill = new StringBuilder();
        for (Order order : orderList1) {
            bill.append(generateItemPriceLine(order.getOrderName(), order.getQuantity(), cost)).append("\n");
        }
        receiptMemo.setText(bill.toString());
        
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    
}

private String generateItemPriceLine(String foodName, String quantity, String cost) {
    
    int totalCost = Integer.parseInt(cost) * Integer.parseInt(quantity);
    String str = foodName + "\t\t\t "+"Rs" + cost + "\t\t\t\t" + quantity + "\t\t\t\t\t"  +"Rs" + totalCost;
    amount+=totalCost;
   
    return str;
  
}


public void onBack(ActionEvent event) {
    StageManager.getInstance().receiptStage.hide();
    StageManager.getInstance().orderStage.show();
    
}
}
