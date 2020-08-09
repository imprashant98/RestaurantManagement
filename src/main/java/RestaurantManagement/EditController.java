package RestaurantManagement;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Prashant
 */
public class EditController implements Initializable {
@FXML
Pane editPane;
@FXML
TextField Eid;
@FXML
TextField Ename;
@FXML
ComboBox<String> Etype;
@FXML
TextField Ecost;

@FXML
public void onApply(ActionEvent e) {
    int id = Integer.parseInt(Eid.getText());
    String name = Ename.getText();
    String type = Etype.getSelectionModel().getSelectedItem();
    String cost = Ecost.getText();
    
    if (RestaurantManagement.MainMenuController.EditTable.equals("Edit Drinks")) {
        
        try {
            DB.mainUpdate("drinks", id, name, type, cost);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    } else {
        try {
            DB.mainUpdate("meals", id, name, type, cost);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        
    }
    StageManager.getInstance().editStage.hide();
    

    
    if (RestaurantManagement.MainMenuController.EditTable.equals("Edit Drinks")) {
        // stage2.show();
        StageManager.getInstance().mainStage.show();
        RestaurantManagement.MainMenuController.m.setVisible(false);
        RestaurantManagement.MainMenuController.d.setVisible(true);
    } else {
        // stage2.show();
        StageManager.getInstance().mainStage.show();
        RestaurantManagement.MainMenuController.m.setVisible(true);
        RestaurantManagement.MainMenuController.d.setVisible(false);
    }
    
    
    RestaurantManagement.MainMenuController.close();
    
}

public void entered(MouseEvent e) {
    ((Button) e.getSource()).setScaleX(1.1);
    ((Button) e.getSource()).setScaleY(1.1);
}

public void exited(MouseEvent e) {
    ((Button) e.getSource()).setScaleX(1);
    ((Button) e.getSource()).setScaleY(1);
}

public void onCancel(Event e) {
    StageManager.getInstance().editStage.hide();
    StageManager.getInstance().mainStage.show();
    
}

@Override
public void initialize(URL url, ResourceBundle rb) {
    int id = RestaurantManagement.MainMenuController.E_id_selection;
    
    Eid.setText(String.valueOf(id));
    Ename.setText(RestaurantManagement.MainMenuController.E_name_selection);
    Etype.setValue(RestaurantManagement.MainMenuController.E_type_selection);
    Ecost.setText(String.valueOf(RestaurantManagement.MainMenuController.E_cost_selection));
    if (RestaurantManagement.MainMenuController.EditTable.equals("Edit Drinks")) {
        Etype.getItems().addAll("Normal Drink", "Hot Drink", "Cold Drink", "Wines", "Soft Drink", "Hard Drink");
    } else {
        Etype.getItems().addAll("Breakfast", "Lunch", "Snacks", "Dinner", "Supper");
    }
    
}

}
