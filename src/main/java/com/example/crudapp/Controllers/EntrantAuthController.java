package com.example.crudapp.Controllers;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.example.crudapp.DataBase.DbFunctions;
import com.example.crudapp.Loader;
import com.example.crudapp.Models.Singleton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class EntrantAuthController {

    @FXML
    private Button buttonSignin;

    @FXML
    private Label captcha;

    @FXML
    private Label labelError;


    @FXML
    private Label labelReg;


    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField textFieldCaptcha;

    @FXML
    public TextField textFieldLogin;

    @FXML
    private Button buttonBack;

    @FXML
    private TextField textFieldPassword;

    private boolean checkCaptcha = true;

    DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void initialize() {
        buttonBack.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/start-window.fxml", "Абитуриент");
        });
        checkCaptcha();
        buttonSignin.setOnAction(e -> {
            validation();
        });

        labelReg.setOnMouseClicked(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/registration-window.fxml", "Регистрация");
        });
    }

    private void validation() {
        String login = textFieldLogin.getText();
        String password = textFieldPassword.getText();
        int codeError = dbFunctions.signIn(login, password);
        if (login.isEmpty()) {
            labelError.setText("Введите логин");
            checkCaptcha();
        } else if (password.isEmpty()) {
            labelError.setText("Введите пароль");
            checkCaptcha();
        } else if (textFieldCaptcha.getText().isEmpty()) {
            labelError.setText("Введите каптчу");
            checkCaptcha();
        } else if (codeError == 0) {
            labelError.setText("Не найден аккаунт");
            checkCaptcha();
        } else if (codeError == 404) {
            labelError.setText("Введенные данные неверны");
            checkCaptcha();
        } else if (!textFieldCaptcha.getText().equals(captcha.getText())) {
            labelError.setText("Введите каптчу повторно");
            checkCaptcha();
        } else {
            Singleton.getInstance().setLogin(textFieldLogin.getText());
            labelError.setText("");
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/main-entrant-window.fxml", "Главное меню");
        }
    }


    public void checkCaptcha() {
        captcha.setText(generateCaptcha());
    }

    public String generateCaptcha() {
        int[] num = new int[1];
        int a = (int) Math.round(Math.random() * 9);
        String[] spec = new String[]{
                "!", "@", "#", "$"
        };
        String randSpec = String.valueOf((getRandomElement(spec)));
        StringBuilder captcha = new StringBuilder();
        Random r = new Random();
        char z = (char) (r.nextInt(26) + 'a');
        char x = (char) (r.nextInt(26) + 'a');
        String c = String.valueOf((getRandomElement(spec)));

        String[] word = new String[]{
                String.valueOf(a), String.valueOf(z), String.valueOf(x), String.valueOf(c)
        };

        Random random = new Random();
        for (int i = 0; i < word.length - 1; i++) {
            int index = random.nextInt(i + 1, word.length);
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
