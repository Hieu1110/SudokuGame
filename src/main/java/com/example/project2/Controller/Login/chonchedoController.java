package com.example.project2.Controller.Login;

import com.example.project2.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class chonchedoController {

    @FXML private TextArea myTextArea;

    @FXML private Button thuongID;

    @FXML private Button luotID;

    @FXML private Button tocdoID;



    public void initialize() {

        myTextArea.setEditable(false);

        thuongID.setOnMouseEntered(event -> {
            myTextArea.setText("2 người chơi độc lập so tốc độ hoành thành để thắng!");
        });
        luotID.setOnMouseEntered(event -> {
            myTextArea.setText("Chọn thời gian mỗi lượt để chuyển đổi qua lại thời gian được phép điền đáp án của 2 người chơi!");
        });
        tocdoID.setOnMouseEntered(event -> {
            myTextArea.setText("2 người chơi theo lượt, sau khi điền 1 số phải chuyển lượt cho người chơi khác!");
        });
    }



    public void thuongCD(ActionEvent e) throws IOException{

        Main m =new Main();

        nextScene = "ThiDauFXML/ThiDau.fxml";
        FXMLLoader loader = new FXMLLoader(m.getClass().getResource(nextScene));
        Parent parent = loader.load();
        Main.globalState.setWidth(1555);
        Main.globalState.setHeight(862);
        m.changeScene(nextScene);

    }


    public void luotCD(ActionEvent e)throws IOException{

        Main m =new Main();

        nextScene = "ThiDauFXML/ThiDauL.fxml";
        FXMLLoader loader = new FXMLLoader(m.getClass().getResource(nextScene));
        Parent parent = loader.load();
        Main.globalState.setWidth(1555);
        Main.globalState.setHeight(862);
        m.changeScene(nextScene);

    }


    public void tocdoCD(ActionEvent e)throws IOException{

        Main m =new Main();

        nextScene = "ThiDauFXML/ThiDauTD.fxml";
        FXMLLoader loader = new FXMLLoader(m.getClass().getResource(nextScene));
        Parent parent = loader.load();
        Main.globalState.setWidth(1555);
        Main.globalState.setHeight(862);
        m.changeScene(nextScene);

    }






    String nextScene = "";
    public void backBT(ActionEvent e) throws IOException {
        Main m =new Main();

        nextScene = "LoginFXML/Home.fxml";
        FXMLLoader loader = new FXMLLoader(m.getClass().getResource(nextScene));
        Parent parent = loader.load();

        m.changeScene(nextScene);
        Main.globalState.setWidth(900);
        Main.globalState.setHeight(530);

    }

}
