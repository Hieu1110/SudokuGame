package com.example.project2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    public static Stage globalState;
    @Override
    public void start(Stage stage) throws IOException {
        globalState=stage;

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginFXML/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("SUDOKU");
        stage.setScene(scene);

        stage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        globalState.getScene().setRoot(pane);
    }

    public void showSceneB(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        // Tạo một stage mới chứa scene B
        Stage additionalStage = new Stage();
        additionalStage.getScene().setRoot(pane);
        additionalStage.show();
    }

    public static void main(String[] args) {launch();}
}