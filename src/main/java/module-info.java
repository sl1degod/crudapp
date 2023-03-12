module com.example.crudapp {
    requires javafx.controls;
    requires javafx.fxml;
//    requires org.postgresql.jdbc;
    requires java.sql;


    exports com.example.crudapp;


    exports com.example.crudapp.Models;
    opens com.example.crudapp;
    exports com.example.crudapp.Controllers;
    opens com.example.crudapp.Controllers;
    opens com.example.crudapp.Models;
}