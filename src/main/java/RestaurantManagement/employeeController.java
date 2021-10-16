package RestaurantManagement;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static RestaurantManagement.DB.a;

/**
 * FXML Controller class
 *
 * @author Prashant
 */

public class employeeController implements Initializable {
// public String EditTable = "";
public int E_id_selection;
public String E_name_selection;
public String E_address_selection;
public String E_email_selection;
public String E_contact_selection;
public String E_post_selection;

@FXML
Label labelPost;
@FXML
ComboBox<String> enterPost;
@FXML
Pane employeeDetails;
@FXML
TextField employeeId;
@FXML
TextField employeeName;
@FXML
TextField employeeAddress;
@FXML
TextField employeeContact;
@FXML
TextField employeeEmail;
@FXML
Label labelId;
@FXML
Label labelName;
@FXML
Label labelAddress;
@FXML
Label labelContact;
@FXML
Label labelEmail;
//tableview Employee:
ObservableList<Employee> listE = null;
@FXML
TableView<Employee> tableE;
@FXML
TableColumn<Employee, Integer> idColumn;
@FXML
TableColumn<Employee, String> nameColumn;
@FXML
TableColumn<Employee, String> addressColumn;
@FXML
TableColumn<Employee, String> contactColumn;
@FXML
TableColumn<Employee, String> emailColumn;
@FXML
TableColumn<Employee, String> postColumn;


public void entered(MouseEvent e) {
    ((Button) e.getSource()).setScaleX(1.1);
    ((Button) e.getSource()).setScaleY(1.1);
}

public void exited(MouseEvent e) {
    ((Button) e.getSource()).setScaleX(1);
    ((Button) e.getSource()).setScaleY(1);
}


public void onClear(ActionEvent e) {
    employeeId.clear();
    employeeName.clear();
    employeeAddress.clear();
    employeeContact.clear();
    employeeEmail.clear();
    enterPost.getSelectionModel().clearSelection();
    
    
}

public void onHome(ActionEvent e) {
    
    StageManager.getInstance().mainStage.show();
    StageManager.getInstance().employeeStage.hide();
}


public void onAdd(ActionEvent e) {
    
    int id = Integer.parseInt(employeeId.getText());
    String name = employeeName.getText();
    String address = employeeAddress.getText();
    String contact = employeeContact.getText();
    String email = employeeEmail.getText();
    String post = String.valueOf(enterPost.getValue());
    
    
    if (DB.insertEmployee("employee", id, name, post, contact, address, email)) {
        
        a.show();
    }
    try {
        employeeId.setText(String.valueOf(DB.count("EmployeeId", "employee")));
        //labelName.setText(employeeName.getText());
        listE = DB.getEmployee();
        tableE.setItems(listE);
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    
  
    
    
}

public void onDelete(ActionEvent e) {
    Employee id = tableE.getSelectionModel().getSelectedItem();
    
    try {
        DB.delEmployee(id.getId());
        listE = DB.getEmployee();
        tableE.setItems(listE);
        employeeId.setText(String.valueOf(DB.count("EmployeeId", "employee")));
      
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }


}


public void onUpdate(ActionEvent event) {
    
    int id = Integer.parseInt(employeeId.getText());
    
    String name = employeeName.getText();
    String post = enterPost.getValue();
    String address = employeeAddress.getText();
    String contact = employeeContact.getText();
    String email = employeeEmail.getText();
    
    
    
    try {
        DB.employeeUpdate(id, name, post, contact, address, email);
        new Alert(Alert.AlertType.INFORMATION, "Data are updated!!", ButtonType.OK).show();
        listE = DB.getEmployee();
        tableE.setItems(listE);
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    
}


@Override
public void initialize(URL url, ResourceBundle rb) {
    
    
    enterPost.getItems().addAll("Owner", "Branch Manager","Associate Manager","Cook","Waiter","Front Desk Officer","Security Officer","Cleaner");
    
    employeeId.setText(String.valueOf(DB.count("EmployeeId", "employee")));
    
    labelId.setLabelFor(employeeId);
    labelName.setLabelFor(employeeName);
    labelAddress.setLabelFor(employeeAddress);
    labelContact.setLabelFor(employeeAddress);
    labelEmail.setLabelFor(employeeAddress);
    
    idColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
    nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
    addressColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
    postColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("post"));
    contactColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("contact"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
    
    
    try {
        listE = DB.getEmployee();
        tableE.setItems(listE);
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    
    
    
    
}

public void selectRow(MouseEvent mouseEvent) {
    E_id_selection = tableE.getSelectionModel().getSelectedItem().getId();
    E_name_selection = tableE.getSelectionModel().getSelectedItem().getName();
    E_post_selection = tableE.getSelectionModel().getSelectedItem().getPost();
    E_address_selection = tableE.getSelectionModel().getSelectedItem().getAddress();
    E_contact_selection = tableE.getSelectionModel().getSelectedItem().getContact();
    E_email_selection = tableE.getSelectionModel().getSelectedItem().getEmail();
    
    
    employeeId.setText(String.valueOf(E_id_selection));
    employeeName.setText(E_name_selection);
    enterPost.setValue(E_post_selection);
    employeeAddress.setText(E_address_selection);
    employeeContact.setText(E_contact_selection);
    employeeEmail.setText(E_email_selection);

}
}


