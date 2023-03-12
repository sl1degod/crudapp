package com.example.crudapp.Controllers;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.example.crudapp.DataBase.DbFunctions;
import com.example.crudapp.Loader;
import com.example.crudapp.Models.Singleton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class WorkerAuthController {
    @FXML
    private Button buttonSignIn;


    @FXML
    private Button buttonBack;


    @FXML
    private Label captcha, labelError;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField textFieldCaptcha;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private TextField textFieldPassword;

    DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void initialize() {
        checkCaptcha();
        buttonSignIn.setOnAction(e -> {
            validation();
        });
        buttonBack.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/start-window.fxml", "Способ входа");
        });

    }

    private void validation() {
        String login = textFieldLogin.getText();
        String password= textFieldPassword.getText();
        int codeError = dbFunctions.signInWorker(login, password);
        if (login.isEmpty()) {
            labelError.setText("Логин пустой");
            checkCaptcha();
        } else if(password.isEmpty()) {
            labelError.setText("Пароль пустой");
            checkCaptcha();
        } else if(codeError == 0) {
            labelError.setText("Не найден аккаунт");
            checkCaptcha();
        } else if(codeError == 404) {
            labelError.setText("Какая-то ошибка");
            checkCaptcha();
        } else {
            labelError.setText("");
            new Loader().openNewScene(rootPane, "/com/example/crudapp/views/worker-main-window.fxml", "Главное меню");

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
