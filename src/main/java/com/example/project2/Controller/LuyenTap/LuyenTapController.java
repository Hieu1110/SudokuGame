package com.example.project2.Controller.LuyenTap;

import com.example.project2.Main;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.Random;


import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;


public class LuyenTapController {


    @FXML
    private Label thoiGian;

    @FXML
    private Button keyButton;

    @FXML
    private Button startButton;

    @FXML
    private Button checkButton;

    @FXML
    private ComboBox<String> boxB;

    @FXML
    private Label labelTB;

    @FXML
    private Button buttonso1;

    @FXML
    private Button buttonso2;

    @FXML
    private Button buttonso3;

    @FXML
    private Button buttonso4;

    @FXML
    private Button buttonso5;

    @FXML
    private Button buttonso6;

    @FXML
    private Button buttonso7;

    @FXML
    private Button buttonso8;

    @FXML
    private Button buttonso9;


    private TextField activeTextField;


    int numberCellIllegal = 0;
    Random random = new Random();

    @FXML
    private GridPane boardPanel;

    @FXML
    private TextField[][] board = new TextField[9][9];

    int[][] state = new int[9][9];
    int[][] boardInt = new int[9][9];


    public void initialize() {

        boxB.getItems().setAll("Dễ", "Thường", "Khó");

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                TextField textField = new TextField();
                board[i][j] = textField;
                textField.setOnMouseClicked(e -> activeTextField = textField);

                board[i][j].setAlignment(Pos.BASELINE_CENTER);
                board[i][j].setBorder(new Border(new BorderStroke(Color.BROWN, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(2))));
                board[i][j].setFont(Font.font("Verdana", FontWeight.BOLD, 30));
                board[i][j].setPrefSize(70, 70);

                boardPanel.add(board[i][j], i, j);

            }
        }


        buttonso1.setOnAction(e -> {
            if (activeTextField != null) {
                activeTextField.setText(buttonso1.getText());
            }
        });

        buttonso2.setOnAction(e -> {
            if (activeTextField != null) {
                activeTextField.setText(buttonso2.getText());
            }
        });

        buttonso3.setOnAction(e -> {
            if (activeTextField != null) {
                activeTextField.setText(buttonso3.getText());
            }
        });

        buttonso4.setOnAction(e -> {
            if (activeTextField != null) {
                activeTextField.setText(buttonso4.getText());
            }
        });

        buttonso5.setOnAction(e -> {
            if (activeTextField != null) {
                activeTextField.setText(buttonso5.getText());
            }
        });

        buttonso6.setOnAction(e -> {
            if (activeTextField != null) {
                activeTextField.setText(buttonso6.getText());
            }
        });

        buttonso7.setOnAction(e -> {
            if (activeTextField != null) {
                activeTextField.setText(buttonso7.getText());
            }
        });

        buttonso8.setOnAction(e -> {
            if (activeTextField != null) {
                activeTextField.setText(buttonso8.getText());
            }
        });

        buttonso9.setOnAction(e -> {
            if (activeTextField != null) {
                activeTextField.setText(buttonso9.getText());
            }
        });


    }



    int level = 2;
    public void level() {
        switch (boxB.getSelectionModel().getSelectedIndex()) {
            case 0:
                level = 2;
                break;
            case 1:
                level = 4;
                break;
            case 2:
                level = 8;
                break;
            case -1:
                labelTB.setText("Chưa chọn độ khó, mặc định là dễ!");
                break;

            default:
                break;
        }
    }


    public void display2(int board[][]) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(" " + board[i][j]);
            }

            System.out.println();
        }

        System.out.println();
    }


    String nextScene = "";
    public void backBT(ActionEvent e) throws IOException{
        Main m =new Main();

        nextScene = "LoginFXML/Home.fxml";
        FXMLLoader loader = new FXMLLoader(m.getClass().getResource(nextScene));
        Parent parent = loader.load();

        m.changeScene(nextScene);
        Main.globalState.setWidth(900);
        Main.globalState.setHeight(530);

    }


    public void handleExceptionInput() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int i1 = i;
                int i2 = j;
            }
        }
    }


    int[][] result = new int[9][9];
    public int[][] copyState(int[][] state) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                result[i][j] = state[i][j];
            }
        }
        return result;
    }



    public void checkClick() throws IOException {
        boolean win = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j].isEditable()) {
                    String inputUser = board[i][j].getText();
                    if (!inputUser.equals(String.valueOf(result[i][j]))) {
                        win = false;
                        numberCellIllegal++;
                        board[i][j].setStyle("-fx-text-fill: red;");
                    } else {
                        board[i][j].setStyle("-fx-text-fill: green;");
                    }
                }
            }
        }
        if (win == true) {
            labelTB.setText("Chúc mừng bạn đã giải thành công");
        }else {
            labelTB.setText(numberCellIllegal+" ô không hợp lệ");
        }
        numberCellIllegal=0;
        stopTimer();
    }




    public void resetClick(ActionEvent e) throws IOException{
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j].setText("");
                state[i][j] = 0;
                board[i][j].setEditable(true);
                board[i][j].setStyle("-fx-text-fill: black;");
                boardInt[i][j] = 0;
            }
        }
        resetTimer();
        labelTB.setText("");
    }


    public void startClick(ActionEvent e) throws IOException{

        if (e.getSource() == startButton) {
            resetClick(e);

            generate();

            copyState(boardInt);
            level();
            for (int l = 0; l < level; l++) {
                for (int k = 0; k < 9; k++) {
                    int i = 1 + random.nextInt(8);
                    boardInt[k][i] = 0;
                }
                for (int k = 0; k < 9; k++) {
                    int i = 1 + random.nextInt(8);
                    boardInt[i][k] = 0;
                }
            }

            for (int i = 0; i < 9; i ++) {
                for (int j = 0; j < 9; j++) {
                    if (boardInt[i][j] != 0) {
                        board[i][j].setText(String.valueOf(boardInt[i][j]));
                        board[i][j].setEditable(false);
                        board[i][j].setStyle("-fx-text-fill: blue;");
                    }
                }
            }

        resetTimer();
        startTimer();


            labelTB.setText("Tạo trò chơi thành công");
        }

        if (e.getSource() == checkButton) {
            checkClick();
            handleExceptionInput();
            System.out.println("hello");
        }
    }

    public void keyClick(ActionEvent e) throws IOException{
        resetClick(e);
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j++) {
                    board[i][j].setText(String.valueOf(result[i][j]));
            }
        }
        labelTB.setText("</>");
        stopTimer();
    }









//bo dem gio
    int seconds = 0;
    private Timeline timeline;
    private void startTimer() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                seconds++;
                                int hours = (seconds / 3600) % 24;
                                int minutes = (seconds / 60) % 60;
                                int remainingSeconds = seconds % 60;
                                thoiGian.setText(String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds));
                            }
                        }
                )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void stopTimer() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    private void resetTimer() {
        stopTimer();
        seconds = 0;
        thoiGian.setText("00:00:00");
    }


    // thuat toan quay lui tao man sudoku tu day
    public void generate() {
        fillDiagonal(); // Điền các ô chéo 3x3
        fillRemaining(0, 3); // Điền các ô còn lại
    }

    private void fillDiagonal() {
        for (int i = 0; i < 9; i += 3) {
            fillBox(i, i);
        }
    }

    private void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                do {
                    num = random.nextInt(9) + 1;
                } while (!isValid(row, col, num));

                boardInt[row + i][col + j] = num;
            }
        }
    }

    private boolean isValid(int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (boardInt[row][i] == num || boardInt[i][col] == num) {
                return false;
            }
        }

        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boardInt[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean fillRemaining(int row, int col) {
        if (col >= 9 && row < 8) {
            row++;
            col = 0;
        }
        if (row >= 9 && col >= 9) {
            return true;
        }

        if (row < 3) {
            if (col < 3) {
                col = 3;
            }
        } else if (row < 6) {
            if (col == (int) (row / 3) * 3) {
                col += 3;
            }
        } else {
            if (col == 6) {
                row++;
                col = 0;
                if (row >= 9) {
                    return true;
                }
            }
        }

        for (int num = 1; num <= 9; num++) {
            if (isValid(row, col, num)) {
                boardInt[row][col] = num;
                if (fillRemaining(row, col + 1)) {
                    return true;
                }
                boardInt[row][col] = 0;
            }
        }

        return false;
    }
    // den day
}

