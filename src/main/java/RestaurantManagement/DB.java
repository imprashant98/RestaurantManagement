package RestaurantManagement;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;

public class DB {

public static Alert a = new Alert(Alert.AlertType.INFORMATION, "Successfully Added", ButtonType.OK);
public static Alert E = new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);

public static Connection getConnection() {
    Connection con = null;
    try {
        Class.forName("java.sql.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?&serverTimezone=UTC&autoReconnect=true&failOverReadOnly=false&maxReconnects=10", "root", "");
        
        
    } catch (ClassNotFoundException | SQLException ex) {
      
        new Alert(Alert.AlertType.ERROR,  ex.getMessage(), ButtonType.OK);
    }
    return con;
}

public static boolean Register(String name, String address, String contact, String email, String username, String password) throws SQLException {
    Connection con = getConnection();
    
    String sqlSelect = "INSERT INTO userbase( name, address, contact, email, username, password) VALUES ('" + name + "','" + address + "','" + contact + "','" + email + "','" + username + "','" + password + "');";
    Statement s = con.createStatement();
    s.executeUpdate(sqlSelect);
    
    return true;
}

public static boolean Login(String username, String password) throws SQLException {
    Connection con = getConnection();
    Statement s = con.createStatement();
    String sqlSelect = "SELECT * FROM userbase WHERE username= '" + username + "' AND password= '" + password + "';";
    
    ResultSet rs = s.executeQuery(sqlSelect);
    return rs.next();
    
}

public static int count(String col, String Table) {
    Connection con = getConnection();
    
    try {
        Statement s = con.createStatement();
        String sqlSelect = "select count(" + col + ") from " + Table;
        ResultSet result = s.executeQuery(sqlSelect);
        if (result.next()) {
            return Integer.parseInt(result.getString(1));
        }
        
        
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    
    return 0;
}


public static boolean insertMeals(String table, String name, String type, String cost) {
    
    try {
        Connection con = getConnection();
        Statement s = con.createStatement();
        String SqlInsert = "INSERT INTO meals(Mname,Mtype,Mcost) " + "VALUES('" + name + "','" + type + "','" + cost + "');";
        
        s.executeUpdate(SqlInsert);
        return true;
    } catch (Exception e) {
        new Alert(Alert.AlertType.ERROR,  e.getMessage(), ButtonType.OK);
        return false;
    }
}

public static boolean insertDrinks(String name, String type, String cost) {
    try {
        Connection con = getConnection();
        Statement s = con.createStatement();
        String SqlInsert = "INSERT INTO drinks(Dname,Dtype,Dcost) " + "VALUES('" + name + "','" + type + "','" + cost + "');";
        s.executeUpdate(SqlInsert);
        return true;
    } catch (Exception e) {
        new Alert(Alert.AlertType.ERROR,  e.getMessage(), ButtonType.OK);
        return false;
    }
}

public static boolean insertOrder(String table, int id, String tableNumber, String orderName, String type, String quantity, java.sql.Date date) {
   
  
    try {
        Connection con = getConnection();
        Statement s = con.createStatement();
//        Date sqlDate=  java.sql.Date.valueOf( date );
        String SqlInsert = "INSERT INTO menu (menuId,tableNumber,menuName,menuType,menuQuantity,menuDate)" + " VALUES('" + id + "','" + tableNumber + "','" + orderName + "','" + type + "','" + quantity + "', '" + date + "');";
        //(menuId,tableNumber,menuName,menuType,menuQuantity,menuDate)" + "
        s.executeUpdate(SqlInsert);
        
        return true;
    } catch (SQLException throwables) {
        throwables.printStackTrace();
        new Alert(Alert.AlertType.ERROR,  throwables.getMessage(), ButtonType.OK);
        return false;
    }
}

public static boolean insertEmployee(String table, int id, String name,String post, String contact, String address, String email) {
    
    try {
        Connection con = getConnection();
        Statement s = con.createStatement();
        String SqlInsert = "INSERT INTO employee(EmployeeId,Ename,Epost,Eaddress,Econtact,Eemail) " +
                                   "VALUES('" + id + "','" + name + "','"+post+"','" + address + "','" + contact + "','" + email + "');";
        
        s.executeUpdate(SqlInsert);
        return true;
    } catch (Exception e) {
        new Alert(Alert.AlertType.ERROR,  e.getMessage(), ButtonType.OK);
        return false;
    }
}

public static ObservableList<Meals> getMeals() {
    Connection con = getConnection();
    ObservableList<Meals> mealsList = observableArrayList();
    try {
        Statement s = con.createStatement();
        String sqlSelect = "select * from meals;";
        ResultSet result = s.executeQuery(sqlSelect);
        
        while (result.next()) {
            int id = result.getInt("Mid");
            String name = result.getString("Mname");
            String type = result.getString("Mtype");
            String cost = result.getString("Mcost");
            mealsList.add(new Meals(id, cost, type, name));
            
        }
        
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    
    
    return mealsList;
}

public static boolean delMeals(int id) throws SQLException {
    Connection con = getConnection();
    Statement s = con.createStatement();
    String sqlDelete = "DELETE FROM meals WHERE Mid = " + id + ";";
    boolean execute = s.execute(sqlDelete);
    
    return execute;
}

public static boolean delDrinks(int id) throws SQLException {
    Connection con = getConnection();
    Statement s = con.createStatement();
    String sqlDelete = "DELETE FROM Drinks WHERE Did = " + id + ";";
    boolean execute = s.execute(sqlDelete);
    
    return execute;
}

public static boolean delEmployee(int id) throws SQLException {
    Connection con = getConnection();
    Statement s = con.createStatement();
    String sqlDelete = "DELETE FROM employee WHERE EmployeeId = " + id + ";";
    boolean execute = s.execute(sqlDelete);
    
    return execute;
}

public static boolean delOrder(int id) throws SQLException {
    Connection con = getConnection();
    Statement s = con.createStatement();
    String sqlDelete = "DELETE FROM menu WHERE menuId = " + id + ";";
    boolean execute = s.execute(sqlDelete);
    
    return execute;
}

public static ObservableList<Drinks> getDrinks() throws SQLException {
    Connection con = getConnection();
    Statement s = con.createStatement();
    String sqlSelect = "select * from drinks;";
    ResultSet result = s.executeQuery(sqlSelect);
    ObservableList<Drinks> drinkList = observableArrayList();
    while (result.next()) {
        int id = result.getInt("Did");
        String name = result.getString("Dname");
        String type = result.getString("Dtype");
        String cost = result.getString("Dcost");
        drinkList.add(new Drinks(id, cost, type, name));
    }
    return drinkList;
}

public static ObservableList<Employee> getEmployee() throws SQLException {
    Connection con = getConnection();
    Statement s = con.createStatement();
    String sqlSelect = "select * from employee;";
    ResultSet result = s.executeQuery(sqlSelect);
    ObservableList<Employee> employeeList = observableArrayList();
    while (result.next()) {
        int id = Integer.parseInt(result.getString("EmployeeId"));
        String name = result.getString("Ename");
        String post = result.getString("Epost");
        String contact = result.getString("Econtact");
        String address = result.getString("Eaddress");
        String email = result.getString("Eemail");
        employeeList.add(new Employee(id, name, post, address, contact, email));
    }
    return employeeList;
}

public static ObservableList<Order> getOrder() throws SQLException {
    Connection con = getConnection();
    Statement s = con.createStatement();
    String sqlSelect = "select * from menu;";
    
    ResultSet result = s.executeQuery(sqlSelect);
    ObservableList<Order> orderList = observableArrayList();
    while (result.next()) {
        int id = Integer.parseInt(result.getString("menuId"));
        String tableNumber = result.getString("tableNumber");
        String orderName = result.getString("menuName");
        String orderType = result.getString("menuType");
        
        String orderQuantity = result.getString("menuQuantity");
//        long asDate = Long.parseLong(result.getString("menuDate"));
//        LocalDate orderDate = LocalDate.ofEpochDay(asDate);
        java.sql.Date orderDate = Date.valueOf(result.getString("menuDate"));
        orderList.add(new Order(id, tableNumber, orderName, orderType, orderQuantity, orderDate));
    }
    
    
    return orderList;
}

public static boolean mainUpdate(String table, int id, String name, String type, String cost) throws SQLException {
    Connection con = getConnection();
    Statement s = con.createStatement();
    String sqlSelect = null;
    if (table.equals("drinks")) {
        sqlSelect = "UPDATE drinks SET Dname = '" + name + "' , Dtype = '" + type + "',Dcost = " + cost + " where Did=" + id + ";";
    } else {
        sqlSelect = "UPDATE meals SET Mname = '" + name + "' , Mtype = '" + type + "',Mcost = " + cost + " where Mid=" + id + ";";
        
    }
    
    
    s.executeUpdate(sqlSelect);
    return true;
}


public static boolean employeeUpdate(int id, String name, String post, String contact, String address, String email) throws SQLException {
    Connection con = getConnection();
    Statement s = con.createStatement();
    
    String sqlSelect = "UPDATE employee SET Ename = '" + name +
                               "' ,Epost = '" + post + "', Eaddress = '" + address + "' ,Econtact = '" + contact + "' ,Eemail = '" + email + "' where EmployeeId=" + id + ";";
    
    
    s.executeUpdate(sqlSelect);
    return true;
}

public static boolean menuUpdate(int id, String tableNumber, String orderName, String type, String quantity, java.sql.Date date) throws SQLException {
    Connection con = getConnection();
    Statement s = con.createStatement();
    
    
    
    String sqlUpdate = "UPDATE menu SET tableNumber = '"+tableNumber+"', menuName = '" + orderName + "' , menuType = '" + type + "' ,menuQuantity = '" + quantity + "',menuDate = '" + date + "' WHERE menuId = '" + id + "';";
    
    
    s.executeUpdate(sqlUpdate);
    return true;
}

public static String getCost(String foodType, String foodName) throws SQLException {
    Connection con = getConnection();
    Statement s = con.createStatement();
    String cost = "";
    String sqlSelect = null;
    if (foodType.equals("drinks")) {
        sqlSelect = "select * from drinks where Dname = '" + foodName + "';";
        ResultSet result = s.executeQuery(sqlSelect);
        while (result.next()) {
            cost = result.getString("Dcost");
        }
    } else {
        sqlSelect = "select * from meals where Mname = '" + foodName + "';";
        ResultSet result = s.executeQuery(sqlSelect);
        while (result.next()) {
            cost = result.getString("Mcost");
        }
        
    }
    return cost;
}

public static ArrayList<Order> getTableOrder(String tableNumber) throws SQLException {
    Connection con = getConnection();
    Statement s = con.createStatement();
    String sqlSelect = "select * from menu where tableNumber = '" + tableNumber + "';";
    //   String sqlSelect = "select * from ;";
    ResultSet result = s.executeQuery(sqlSelect);
    ArrayList<Order> orderList1 = new ArrayList<>();
    while (result.next()) {
        String orderName = result.getString("menuName");
        String orderType = result.getString("menuType");
        String orderQuantity = result.getString("menuQuantity");
        java.sql.Date orderDate = Date.valueOf(result.getString("menuDate"));
        orderList1.add(new Order(orderName, orderType, orderQuantity, orderDate));
    }
    return orderList1;
}
}





    
    
    



