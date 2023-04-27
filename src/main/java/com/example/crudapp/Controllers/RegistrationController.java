package com.example.crudapp.Controllers;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.example.crudapp.DataBase.DbFunctions;
import com.example.crudapp.Loader;
import com.example.crudapp.MaskField;
import com.example.crudapp.Models.Singleton;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonRegistration;

    @FXML
    private Button buttonSetPhoto;

    @FXML
    private Button buttonBack;

    @FXML
    private Label captcha;

    @FXML
    private ImageView imageEn;

    @FXML
    private Label labelError;

    @FXML
    private DatePicker datePicker;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField textFieldCaptcha;

    @FXML
    private TextField textFieldCity;

    @FXML
    private TextField textFieldName;

    @FXML
    private MaskField textFieldPhone;

    @FXML
    private TextField textFieldSecondName;

    @FXML
    private TextField textFieldThirdName;

    @FXML
    private MaskField textFieldScore;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private TextField textFieldPassword;

    Stage stage = new Stage();
    FileChooser fileChooser = new FileChooser();

    private String errorMessage = "";

    DbFunctions dbFunctions = new DbFunctions();

    String image = "";

    @FXML
    void initialize() {
        checkCaptcha();
        textFieldScore.setTooltip(new Tooltip("Введите средний балл аттестата"));
        buttonBack.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/entrant-auth.fxml", "Абитуриент");
        });
        buttonSetPhoto.setOnAction(e -> {
            try {
                File file = fileChooser.showOpenDialog(stage);
                image = file.getAbsolutePath();
                imageEn.setImage(new Image(image));
                System.out.println("123");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        });
        buttonRegistration.setOnAction(e -> {
            validation();
        });
    }


    private void validation() {
        String surname = textFieldSecondName.getText();
        String name = textFieldName.getText();
        String patronymic = textFieldThirdName.getText();
        LocalDate dateBirthday = datePicker.getValue();
        String phone = textFieldPhone.getText();
        String city = textFieldCity.getText();
        String login = textFieldLogin.getText();
        String password = textFieldPassword.getText();
        String avgScore = textFieldScore.getText();
        int codeError = dbFunctions.check_login(login);





        labelError.setText("");

        if(surname.isEmpty()){
            checkCaptcha();
            labelError.setText("Введите фамилию");
        }

        else if (name.isEmpty()) {
            checkCaptcha();
            labelError.setText("Введите имя");
        }

        else if (patronymic.isEmpty()) {
            checkCaptcha();
            labelError.setText("Введите Отчество");
        }

        else if (phone.equals("+7(___)___-____")) {
            checkCaptcha();
            labelError.setText("Введите номер телефона");
        }

        else if (dateBirthday == null) {
            checkCaptcha();
            labelError.setText("Введите дату рождения");
        }

        else if (image.isEmpty()) {
            checkCaptcha();
            labelError.setText("Выберите фото");
        }

        else if (city.isEmpty()) {
            checkCaptcha();
            labelError.setText("Введите город");
        }

        else if (avgScore.equals("_.__")) {
            checkCaptcha();
            labelError.setText("Введите балл");
        }

        else if (login.isEmpty()) {
            checkCaptcha();
            labelError.setText("Введите логин");
        }

        else if (codeError == 0) {
            checkCaptcha();
            labelError.setText("Такой логин уже существует");
        }

        else if (password.isEmpty()) {
            checkCaptcha();
            labelError.setText("Введите пароль");
        }

        else if (!password.matches("(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{6,}")) {
            checkCaptcha();
            labelError.setText("Ваш пароль не соответствует требованиям (минимум 6 символом, в которых содержится минимум 1 заглавная буква и минимум 1 цифра");
        }

        else if (textFieldCaptcha.getText().isEmpty()) {
            checkCaptcha();
            labelError.setText("Введите каптчу");
        } else if (!textFieldCaptcha.getText().equals(captcha.getText())) {
            checkCaptcha();
            labelError.setText("Введите каптчу верно");
        }
        else {
            labelError.setText("");
            Singleton.getInstance().setImage(image);
            Singleton.getInstance().setSurname(textFieldSecondName.getText());
            Singleton.getInstance().setName(textFieldName.getText());
            Singleton.getInstance().setPatronymic(textFieldThirdName.getText());
            Singleton.getInstance().setCity(textFieldCity.getText());
            Singleton.getInstance().setPhone(textFieldPhone.getText());
            Singleton.getInstance().setAvgScore(textFieldScore.getText());
            dbFunctions.createEntrant(surname, name, patronymic, dateBirthday, phone, city, avgScore, image, login, password);
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/entrant-auth.fxml", "Авторизация");
        }
    }

    public void checkCaptcha() {
        captcha.setText(generateCaptcha());
    }

    public String generateCaptcha() {
        int[] num = new int[1];
        int a = (int) Math.round(Math.random()*9);
        String[] spec = new String[] {
                "!", "@", "#", "$"
        };
        String randSpec = String.valueOf((getRandomElement(spec)));
        StringBuilder captcha = new StringBuilder();
        Random r = new Random();
        char z = (char)(r.nextInt(26)+'a');
        char x = (char)(r.nextInt(26)+'a');
        String c = String.valueOf((getRandomElement(spec)));

        String[] word = new String[]{
                String.valueOf(a), String.valueOf(z), String.valueOf(x), String.valueOf(c)
        };

        Random random = new Random();
        for (int i = 0; i < word.length - 1; i++) {
            int index = random.nextInt(i+1, word.length);
            String temp = String.valueOf(word[i]);
            word[i] = word[index];
            word[index] = String.valueOf(temp);
        }

        for (int i = 0; i < word.length; i++) {
            captcha.append(word[i]);
            System.out.print(word[i]);
        }
        return String.valueOf(captcha);
    }

    public static String getRandomElement(String[] spec) {
        return spec[ThreadLocalRandom.current().nextInt(spec.length)];
    }
}


