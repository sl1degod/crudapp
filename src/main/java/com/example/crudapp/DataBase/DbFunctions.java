package com.example.crudapp.DataBase;

import com.example.crudapp.Models.Singleton;
import com.example.crudapp.Models.Entrant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;

import java.sql.*;
import java.time.LocalDate;

public class DbFunctions {
    public Connection connect_to_db() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + "pk", "postgres", "123");
            if (connection != null) {
                System.out.println("Connection successful");
            } else {
                System.out.println("Connection failed");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }


    public void createEntrant(String surname, String name, String patronymic, LocalDate dateBirthday, String phone, String city, String avgScore, String image, String login, String password) {
        try {
            String query = String.format("insert into abiturient(surname, name, patronymic, dateBirthday, phone, city, avgScore, image, login, password) values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');",
                    surname, name, patronymic, dateBirthday, phone, city, avgScore, image, login, password);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            System.out.println("Entrant created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int check_login(String login) {
        try {
            String query = String.format("select * from abiturient where login = '%s'", login);
            Statement statement = connect_to_db().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.last();
            if (resultSet.getRow() >= 1) {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 404;
        }
        return 201;
    }

    public int signIn(String login, String password) {
        try {
            String query = String.format("select * from abiturient where login = '%s' and password = '%s'", login, password);
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                Singleton.getInstance().setAbitId(resultSet.getString("id"));
            }

            Variables.ACTIVE_USER = resultSet.getString("login");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 404;
        }
        return 201;
    }

    public int signInWorker(String login, String password) {
        try {
            String query = String.format("select * from employees where login = '%s' and password = '%s'", login, password);
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
                return 0;
            }

            Variables.ACTIVE_USER = resultSet.getString("login");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 404;
        }
        return 201;
    }


    public void updateDataUser(String status, String id, String spec) {
        try {
            String query = String.format("update form set status='%s' where abitid='%s' and spec = '%s'", status, id, spec);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            System.out.println("Data updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteDataUser(String id, String formeduc, String spec) {
        try {
            String query = String.format("delete from form where id = '%s' and formeduc = '%s' and spec = '%s'", id, formeduc, spec);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            System.out.println("Анкета была удалена");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setInformation(String login) {
        try {
            String query = String.format("select id, name, surname, patronymic, phone, city, image, datebirthday, avgscore from abiturient where login = '%s'", login);
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                Singleton.getInstance().setId(resultSet.getString("id"));
                Singleton.getInstance().setName(resultSet.getString("name"));
                Singleton.getInstance().setSurname(resultSet.getString("surname"));
                Singleton.getInstance().setPatronymic(resultSet.getString("patronymic"));
                Singleton.getInstance().setPhone(resultSet.getString("phone"));
                Singleton.getInstance().setCity(resultSet.getString("city"));
                Singleton.getInstance().setImage(resultSet.getString("image"));
                Singleton.getInstance().setDateBirthday(resultSet.getString("datebirthday"));
                Singleton.getInstance().setAvgScore(resultSet.getString("avgscore"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<PieChart.Data> setPieChart() {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        try {
            String query = String.format("select count(id), city from abiturient group by city");
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data.add(new PieChart.Data(resultSet.getString(2), resultSet.getDouble(1)));
                Variables.COUNT += resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }


    public String createForm(String abitId, String formEduc, String spec) {
        try {
            String query = String.format("insert into form(abitid, formeduc, spec) values('%s','%s','%s');",
                    abitId, spec, formEduc);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            System.out.println("Form created");
            return "Анкета создана";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }


    public ObservableList<Entrant> getAllEntrant() {
        ObservableList<Entrant> entrants = FXCollections.observableArrayList();
        try {
            String query = String.format("select form.id as formid, form.abitid, concat(abiturient.surname, ' ', abiturient.name, ' ', abiturient.patronymic) as FIO, " +
                    "form.formeduc, form.spec, form.status, abiturient.avgscore from form, abiturient where form.abitid = abiturient.id");
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                entrants.add(new Entrant(
                        resultSet.getString("abitid"),
                        resultSet.getString("FIO"),
                        resultSet.getString("formeduc"),
                        resultSet.getString("spec"),
                        resultSet.getString("status"),
                        resultSet.getString("avgscore"),
                        resultSet.getString("formid")
                        ));
            }
            return entrants;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return entrants;
        }
    }

    public ObservableList<Entrant> getAllEntrantSelect(String id) {
        ObservableList<Entrant> entrants = FXCollections.observableArrayList();
        try {
            String query = String.format("select form.id as formid, form.abitid, concat(abiturient.surname, ' ', abiturient.name, ' ', abiturient.patronymic) as FIO, " +
                    "form.formeduc, form.spec, form.status, abiturient.avgscore from form, abiturient where form.abitid = abiturient.id order by case when form.abitid = '%s' " +
                    "then 0 else 1 end, form.id;", id);
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                entrants.add(new Entrant(
                        resultSet.getString("abitid"),
                        resultSet.getString("FIO"),
                        resultSet.getString("formeduc"),
                        resultSet.getString("spec"),
                        resultSet.getString("status"),
                        resultSet.getString("avgscore"),
                        resultSet.getString("formid")
                        ));
            }
            return entrants;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return entrants;
        }
    }

    public ObservableList<Entrant> filter(String status, String formeduc, String spec) {
        ObservableList<Entrant> entrants = FXCollections.observableArrayList();
        try {
            String query = String.format("select form.id as formid, form.abitid, concat(abiturient.surname, ' ', abiturient.name, ' ', abiturient.patronymic) as FIO, " +
                    "form.formeduc, form.spec, form.status, abiturient.avgscore from form, abiturient where form.abitid = abiturient.id and " +
                    "form.status = '%s' and form.formeduc = '%s' and form.spec = '%s'", status, formeduc, spec);
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                entrants.add(new Entrant(
                        resultSet.getString("abitid"),
                        resultSet.getString("FIO"),
                        resultSet.getString("formeduc"),
                        resultSet.getString("spec"),
                        resultSet.getString("status"),
                        resultSet.getString("avgscore"),
                        resultSet.getString("formid")
                ));
        }
            return entrants;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return entrants;
        }
    }





//try {
//            String query = String.format("select form.id, abiturient.name, abiturient.surname, abiturient.patronymic, form.spec, form.formeduc from abiturient, form where form.abitid = abiturient.id and form.abitid ='%s'", id);
//            Statement statement = dbFunctions.connect_to_db().createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//            if (resultSet.next()) {
//                labelSurname.setText(resultSet.getString("abiturient.surname"));
//                labelName.setText(resultSet.getString("abiturient.name"));
//                labelPatronymic.setText(resultSet.getString("abiturient.patronymic"));
//                labelSpec.setText(resultSet.getString("form.spec"));
//                labelFormEduc.setText(resultSet.getString("form.formeduc"));
//            }
//        } catch(SQLException e) {
//            System.out.println(e.getMessage());
//        }


}