package com.example.project2.Controller.Login;

import com.example.project2.Controller.Client.GameRoom;
import com.example.project2.Controller.Client.InRoom;
import com.example.project2.Controller.Client.Player;
import com.example.project2.JdbcDAO;
import com.example.project2.Main;
import com.example.project2.Controller.Client.ClientController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LoginController {

    @FXML
    private Button buttonLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label wrongLogin;



    public static int ID;
    public static String Ten;





    private JdbcDAO repo = new JdbcDAO();

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/test";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";

    public static Connection ConnectDB() throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        return connection;
    }

    //Lưu thông tin khi đang nhập thành công
    public void In4user(String username, String password)  throws  SQLException {
        // Truy vấn cơ sở dữ liệu để lấy thông tin người dùng
        String query = "SELECT ID, Ten FROM canhan WHERE Username = '" +username+ "' AND Password = '" +password+ "'";
        System.out.println(query);
        Connection connection = ConnectDB();
        ResultSet result = connection.createStatement().executeQuery(query);

        // Lấy thông tin người dùng từ kết quả truy vấn
        if (result.next()) {
            ID = result.getInt("ID");
            Ten = result.getString("Ten");
        }
    }

    private String nextScene = "";

    public void userLogin(ActionEvent e) throws IOException, SQLException {
        Main m =new Main();

        Map<String,String> map=repo.checkloginUser();
        if(map.containsKey(username.getText().toString())) {
            if (map.get(username.getText().toString()).equals(password.getText().toString())) {
                wrongLogin.setText("Đăng nhập thành công!");

                In4user(username.getText().toString(), password.getText().toString());



                nextScene = "LoginFXML/Home.fxml";
                FXMLLoader loader = new FXMLLoader(m.getClass().getResource(nextScene));
                Parent parent = loader.load();

                m.changeScene(nextScene);
                Main.globalState.setWidth(900);
                Main.globalState.setHeight(530);

            } else wrongLogin.setText("Sai tài khoản hoặc mật khẩu!");
        }else wrongLogin.setText("Sai tài khoản hoặc mật khẩu!");

    }


    public void userSignup(ActionEvent e) throws IOException{
        Main m =new Main();

        nextScene = "LoginFXML/SignUp.fxml";
        FXMLLoader loader = new FXMLLoader(m.getClass().getResource(nextScene));
        Parent parent = loader.load();

        m.changeScene(nextScene);
        Main.globalState.setWidth(597);
        Main.globalState.setHeight(659);

    }

    public void choi_ngay(ActionEvent e) throws IOException{
        Main m =new Main();

        nextScene = "LoginFXML/Home.fxml";
        FXMLLoader loader = new FXMLLoader(m.getClass().getResource(nextScene));
        Parent parent = loader.load();

        m.changeScene(nextScene);
        Main.globalState.setWidth(900);
        Main.globalState.setHeight(530);
    }



}
