package com.example.project2.Controller.Login;

import com.example.project2.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class ccdController {

    @FXML private TextArea myTextArea;

    @FXML private Button thuongID;

    @FXML private Button luotID;



    public void initialize() {

        myTextArea.setEditable(false);

        thuongID.setOnMouseEntered(event -> {
            myTextArea.setText("Chọn độ khó và giải 1 màn Sudoku!");
        });
        luotID.setOnMouseEntered(event -> {
            myTextArea.setText("Giải 2 màn chơi Sudoku cùng lúc!");
        });
    }



    public void thuongCD(ActionEvent e) throws IOException{

        Main m =new Main();

        nextScene = "LuyenTapFXML/LuyenTap.fxml";
        FXMLLoader loader = new FXMLLoader(m.getClass().getResource(nextScene));
        Parent parent = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Sudoku Game");
        Main.globalState.setWidth(1050);
        Main.globalState.setHeight(850);


        m.changeScene(nextScene);

    }


    public void luotCD(ActionEvent e)throws IOException{

        Main m =new Main();

        nextScene = "LuyenTapFXML/LuyenTapTD.fxml";
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
