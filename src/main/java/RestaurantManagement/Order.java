package RestaurantManagement;

import java.time.LocalDate;

public class Order {
private int id;
private String tableNumber;
private String orderName;
private String type;
//private int cost;
// private int total;
private String quantity;
private java.sql.Date date;


public Order(int id, String tableNumber, String orderName, String type, String quantity, java.sql.Date date) {
    this.id = id;
    this.tableNumber = tableNumber;
    this.orderName = orderName;
    this.type = type;
    //this.cost = cost;
    // this.total = total;
    this.quantity = quantity;
    this.date = date;
}

public Order(String orderName, String type, String quantity, java.sql.Date date) {
    
    this.orderName = orderName;
    this.type = type;
    
    this.quantity = quantity;
    this.date = date;
}


public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getTableNumber() {
    return tableNumber;
}

public void setTableNumber(String tableNumber) {
    this.tableNumber = tableNumber;
}

public String getOrderName() {
    return orderName;
}

public void setOrderName(String orderName) {
    this.orderName = orderName;
}

public String getType() {
    return type;
}

public void setType(String type) {
    this.type = type;
}

public String getQuantity() {
    return quantity;
}


public void setQuantity(String quantity) {
    this.quantity = quantity;
}


public java.sql.Date getDate() {
    return date;
}

public void setDate(java.sql.Date date) {
    this.date = date;
    
}

}
