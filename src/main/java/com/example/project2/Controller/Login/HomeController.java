package com.example.project2.Controller.Login;

import com.example.project2.Controller.Client.GameRoom;
import com.example.project2.Controller.Client.InRoom;
import com.example.project2.Controller.Client.Player;
import com.example.project2.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import com.example.project2.Controller.Client.ClientController;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class HomeController {


    @FXML
    private Label user_id;
    @FXML
    private Label user_ten;


    private String nextScene = "";

    public void initialize(){
        if(LoginController.ID == 0 || LoginController.Ten == ""){
            user_id.setText("");
            user_ten.setText("");
        }else {
            user_id.setText("ID:  " + LoginController.ID);
            user_ten.setText("Tên người chơi:  " + LoginController.Ten);
        }
    }


    public void nextLT(ActionEvent e) throws IOException{
        Main m =new Main();

        nextScene = "LoginFXML/ccd.fxml";
        FXMLLoader loader = new FXMLLoader(m.getClass().getResource(nextScene));
        Parent parent = loader.load();
        Main.globalState.setWidth(820);
        Main.globalState.setHeight(450);
        m.changeScene(nextScene);
    }

//    public void nextCN(ActionEvent e) throws IOException{
//        Main m =new Main();
//
//        nextScene = "ClientFXML/ClientHome.fxml";
//        FXMLLoader loader = new FXMLLoader(m.getClass().getResource(nextScene));
//        Parent parent = loader.load();
//        Main.globalState.setWidth(950);
//        Main.globalState.setHeight(550);
//
//        m.changeScene(nextScene);
//    }

    public void nextTD(ActionEvent e) throws IOException{
        Main m =new Main();

        nextScene = "LoginFXML/chonchedo.fxml";
        FXMLLoader loader = new FXMLLoader(m.getClass().getResource(nextScene));
        Parent parent = loader.load();
        Main.globalState.setWidth(820);
        Main.globalState.setHeight(450);
        m.changeScene(nextScene);
    }


    public void backLG(ActionEvent e) throws IOException {
        Main m = new Main();

        nextScene = "LoginFXML/Login.fxml";
        FXMLLoader loader = new FXMLLoader(m.getClass().getResource(nextScene));
        Parent parent = loader.load();

        m.changeScene(nextScene);
        Main.globalState.setWidth(597);
        Main.globalState.setHeight(489);
    }

}
