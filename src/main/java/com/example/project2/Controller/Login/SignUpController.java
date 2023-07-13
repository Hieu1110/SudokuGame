package com.example.project2.Controller.Login;

import com.example.project2.JdbcDAO;
import com.example.project2.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import com.example.project2.Model.CaNhan;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button buttonOK;
    @FXML
    private Label signupTB;
    @FXML
    private Button buttonBack;
    @FXML
    private TextField hoTen;
    @FXML
    private DatePicker ngaysinh;
    @FXML
    private RadioButton Men;
    @FXML
    private RadioButton Women;
    @FXML
    private TextField username;
    @FXML
    private PasswordField pass1;
    @FXML
    private PasswordField pass2;


    private String nextScene = "";

    private JdbcDAO repo = new JdbcDAO();

    public void initialize(URL url, ResourceBundle rb) {}

    public void signUpOK(ActionEvent e) throws IOException, SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận ");
        alert.setContentText("Xác nhận đăng ký tài khoản");

        ButtonType buttonTypeYes = new ButtonType("Xác nhận", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("Hủy", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();


        if(!pass1.getText().toString().equals(pass2.getText().toString())){
            signupTB.setText("Mật khẩu nhập lại không đúng!");
        }else {

            if (result.get() == buttonTypeYes) {
                String Ten = hoTen.getText();

                String usn = username.getText();
                String pass = pass2.getText();
                Random generator = new Random();
                int ID = generator.nextInt((99999 - 50) + 1) + 50;


                if (Ten == "" || usn == "" || pass == "") {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setContentText("Chưa nhập đầy đủ thông tin");
                    alert.show();

                }

                    CaNhan acc = new CaNhan(ID, Ten, usn, pass);
                    repo.InsertUser(acc);
                    signupTB.setVisible(true);
                    buttonOK.setVisible(false);
                    signupTB.setText("Đăng ký thành công, hãy quay lại trang đăng nhập!");
            }
        }



    }


    public void signupBack(ActionEvent e) throws IOException {
        Main m = new Main();

        nextScene = "LoginFXML/Login.fxml";
        FXMLLoader loader = new FXMLLoader(m.getClass().getResource(nextScene));
        Parent parent = loader.load();

        m.changeScene(nextScene);
        Main.globalState.setWidth(597);
        Main.globalState.setHeight(489);

    }

}
