package com.example.project2.Controller.Server;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


public class ServerView {

    @FXML
    public TextArea textArea;

    @FXML
    public TextArea textArea_1;


    public static String nho;
    public void initialize() {
        textArea_1.setText("Đang chờ user...");
    }


    public void Room(String sms) {
        textArea.setText(sms+"\n");
    }

    public void setnotify(String sms) {
        nho = textArea_1.getText() + "\n" + sms;
        textArea_1.setText(nho);
    }



}
