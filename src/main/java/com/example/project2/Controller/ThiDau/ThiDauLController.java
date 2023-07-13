package com.example.project2.Controller.ThiDau;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class ThiDauLController {

    @FXML
    private Label P1_lb;

    @FXML
    private Label P2_lb;

    @FXML
    private ComboBox<String> boxB;

    @FXML
    private Label thoiGian1;

    @FXML
    private Label thoiGian2;



    private TextField activeTextField;
    Random random = new Random();
    String nextScene = "";



    @FXML
    private GridPane boardPanel_P1;

    @FXML
    private GridPane boardPanel_P2;

    @FXML
    private TextField[][] board_P1 = new TextField[9][9];

    @FXML
    private TextField[][] board_P2 = new TextField[9][9];


    int[][] boardInt1 = new int[9][9];
    int[][] boardInt2 = new int[9][9];

    int numberCellIllegal = 0;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);


    public void initialize() {
        boxB.getItems().setAll("Dễ", "Thường", "Khó");

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                TextField textField1 = new TextField();
                board_P1[i][j] = textField1;
                textField1.setOnMouseClicked(e -> activeTextField = textField1);
                board_P1[i][j].setAlignment(Pos.BASELINE_CENTER);
                board_P1[i][j].setBorder(new Border(new BorderStroke(Color.BROWN, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(2))));
                board_P1[i][j].setFont(Font.font("Verdana", FontWeight.BOLD, 30));
                board_P1[i][j].setPrefSize(70, 70);
                boardPanel_P1.add(board_P1[i][j], i, j);


                TextField textField2 = new TextField();
                board_P2[i][j] = textField2;
                textField2.setOnMouseClicked(e -> activeTextField = textField2);
                board_P2[i][j].setAlignment(Pos.BASELINE_CENTER);
                board_P2[i][j].setBorder(new Border(new BorderStroke(Color.BROWN, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(2))));
                board_P2[i][j].setFont(Font.font("Verdana", FontWeight.BOLD, 30));
                board_P2[i][j].setPrefSize(70, 70);
                boardPanel_P2.add(board_P2[i][j], i, j);
            }
        }
    }

    int[][] result1 = new int[9][9];
    int[][] result2 = new int[9][9];
    public void copyState() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                result1[i][j] = boardInt1[i][j];
                result2[i][j] = boardInt2[i][j];
            }
        }
    }

    int[][] dem1 = new int[9][9];
    int[][] dem2 = new int[9][9];
    public void copyState2() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                dem1[i][j] = boardInt1[i][j];
                dem2[i][j] = boardInt2[i][j];
            }
        }
    }



    int level = 2;
    public void level() {
        switch (boxB.getSelectionModel().getSelectedIndex()) {
            case 0:
                level = 2;
                break;
            case 1:
                level = 3;
                break;
            case 2:
                level = 5;
                break;
            default:
                break;
        }
    }

    public void backBT(ActionEvent e) throws IOException {
        resetClick();
        Main m =new Main();

        nextScene = "LoginFXML/Home.fxml";
        FXMLLoader loader = new FXMLLoader(m.getClass().getResource(nextScene));
        Parent parent = loader.load();

        m.changeScene(nextScene);
        Main.globalState.setWidth(900);
        Main.globalState.setHeight(530);

    }

    public void resetClick(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board_P1[i][j].setText("");
                board_P2[i][j].setText("");

                result1[i][j] = 0;
                result2[i][j] = 0;

                board_P1[i][j].setEditable(true);
                board_P2[i][j].setEditable(true);

                board_P1[i][j].setStyle("-fx-text-fill: black;");
                board_P2[i][j].setStyle("-fx-text-fill: black;");

                boardInt1[i][j] = 0;
                boardInt2[i][j] = 0;
            }
        }
        P1_lb.setText("");
        P2_lb.setText("");
        ResetTimer();
    }


    public void StartButton(ActionEvent e) throws IOException{

        resetClick();

        generate();
        generate2();

        copyState();
        level();

        for (int l = 0; l < level; l++) {
            for (int k = 0; k < 9; k++) {
                int i = 1 + random.nextInt(8);
                boardInt1[k][i] = 0;
                boardInt2[k][i] = 0;
            }
            for (int k = 0; k < 9; k++) {
                int i = 1 + random.nextInt(8);
                boardInt1[i][k] = 0;
                boardInt2[i][k] = 0;
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (boardInt1[i][j] != 0) {
                    board_P1[i][j].setText(String.valueOf(boardInt1[i][j]));
                    board_P1[i][j].setEditable(false);
                    board_P1[i][j].setStyle("-fx-text-fill: blue;");
                }
                if (boardInt2[i][j] != 0) {
                    board_P2[i][j].setText(String.valueOf(boardInt2[i][j]));
                    board_P2[i][j].setEditable(false);
                    board_P2[i][j].setStyle("-fx-text-fill: blue;");
                }
            }
        }
        copyState2();


        demtime1(thoiGian1);

    }






    public void P1_hang(ActionEvent e) throws IOException{
        // Thiết lập tiêu đề, nội dung và tiêu đề cửa sổ cho Alert
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Người chơi 1 xác nhận đầu hàng?");

// Tạo hai nút bấm Đồng ý và Hủy
        ButtonType buttonTypeYes = new ButtonType("Đồng ý", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("Hủy", ButtonBar.ButtonData.NO);

// Thêm hai nút bấm vào Alert
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

// Hiển thị Alert và đợi người dùng chọn một trong hai nút bấm
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

// Kiểm tra người dùng đã chọn nút bấm nào
        if (result == buttonTypeYes) {
            // Xử lý sự kiện khi người dùng chọn nút bấm Đồng ý
            resetClick();
            P1_lb.setText("Người chơi 1 nhận thua, người chơi 2 thắng!");
            P2_lb.setText("Người chơi 1 nhận thua, người chơi 2 thắng!");
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Người chơi 2 thắng!");
            alert.show();
            ResetTimer();
        } else {
            // Xử lý sự kiện khi người dùng chọn nút bấm Hủy hoặc đóng Alert

        }

    }

    public void P1_KT(ActionEvent e) throws IOException{
        boolean win = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board_P1[i][j].isEditable() && editableIndex == false) {
                    String inputUser = board_P1[i][j].getText();
                    if (!inputUser.equals(String.valueOf(result1[i][j]))) {
                        win = false;
                        numberCellIllegal++;
                        board_P1[i][j].setStyle("-fx-text-fill: red;");
                    } else {
                        board_P1[i][j].setStyle("-fx-text-fill: green;");
                    }
                }
            }
        }
        if (win == true && editableIndex == false) {
            P1_lb.setText("Chúc mừng bạn đã giải thành công");
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Người chơi 1 thắng!");
            alert.show();
            ResetTimer();
        }else if (win == false && editableIndex == false){
            P1_lb.setText(numberCellIllegal+" ô không hợp lệ");
        }
        numberCellIllegal=0;
    }
    public void P2_hang(ActionEvent e) throws IOException{
        // Thiết lập tiêu đề, nội dung và tiêu đề cửa sổ cho Alert
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Người chơi 2 xác nhận đầu hàng??");

// Tạo hai nút bấm Đồng ý và Hủy
        ButtonType buttonTypeYes = new ButtonType("Đồng ý", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("Hủy", ButtonBar.ButtonData.NO);

// Thêm hai nút bấm vào Alert
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

// Hiển thị Alert và đợi người dùng chọn một trong hai nút bấm
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

// Kiểm tra người dùng đã chọn nút bấm nào
        if (result == buttonTypeYes) {
            // Xử lý sự kiện khi người dùng chọn nút bấm Đồng ý
            resetClick();
            P1_lb.setText("Người chơi 2 nhận thua, người chơi 1 thắng!");
            P2_lb.setText("Người chơi 2 nhận thua, người chơi 1 thắng!");
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Người chơi 1 thắng!");
            alert.show();
            ResetTimer();
        } else {
            // Xử lý sự kiện khi người dùng chọn nút bấm Hủy hoặc đóng Alert
            // ...
        }

    }

    public void P2_KT(ActionEvent e) throws IOException{
        boolean win = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board_P2[i][j].isEditable() && editableIndex == true) {
                    String inputUser = board_P2[i][j].getText();
                    if (!inputUser.equals(String.valueOf(result2[i][j]))) {
                        win = false;
                        numberCellIllegal++;
                        board_P2[i][j].setStyle("-fx-text-fill: red;");
                    } else {
                        board_P2[i][j].setStyle("-fx-text-fill: green;");
                    }
                }
            }
        }
        if (win == true && editableIndex == true) {
            P2_lb.setText("Chúc mừng bạn đã giải thành công");
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Người chơi 2 thắng!");
            alert.show();
            ResetTimer();
        }else if(win == false && editableIndex == true) {
            P2_lb.setText(numberCellIllegal+" ô không hợp lệ");
        }
        numberCellIllegal=0;
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

                boardInt1[row + i][col + j] = num;
            }
        }
    }

    private boolean isValid(int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (boardInt1[row][i] == num || boardInt1[i][col] == num) {
                return false;
            }
        }

        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boardInt1[startRow + i][startCol + j] == num) {
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
                boardInt1[row][col] = num;
                if (fillRemaining(row, col + 1)) {
                    return true;
                }
                boardInt1[row][col] = 0;
            }
        }

        return false;
    }
    // den day

    // thuat toan quay lui tao man sudoku tu day
    public void generate2() {
        fillDiagonal2(); // Điền các ô chéo 3x3
        fillRemaining2(0, 3); // Điền các ô còn lại
    }

    private void fillDiagonal2() {
        for (int i = 0; i < 9; i += 3) {
            fillBox2(i, i);
        }
    }

    private void fillBox2(int row, int col) {
        int num;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                do {
                    num = random.nextInt(9) + 1;
                } while (!isValid2(row, col, num));

                boardInt2[row + i][col + j] = num;
            }
        }
    }

    private boolean isValid2(int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (boardInt2[row][i] == num || boardInt2[i][col] == num) {
                return false;
            }
        }

        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boardInt2[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean fillRemaining2(int row, int col) {
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
            if (isValid2(row, col, num)) {
                boardInt2[row][col] = num;
                if (fillRemaining2(row, col + 1)) {
                    return true;
                }
                boardInt2[row][col] = 0;
            }
        }

        return false;
    }
    // den day


    boolean editableIndex = false;
    int p1 = 0, p2 = 0;

    //bo dem gio

    private Timeline timeline1, timeline2;
    int seconds;
    private int demtime1(Label label){
        if(p1 == 1 && p2 == 1) return 0;
        if(p1 == 1) demtime2(thoiGian2);
        label.setText("00:15"); // Setup label thoigian1 thành 00:15

        timeline1 = new Timeline();
        timeline1.setCycleCount(Timeline.INDEFINITE); // Đếm ngược vô hạn
        timeline1.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                    // Đếm ngược sau mỗi giây
                    @Override
                    public void handle(ActionEvent event) {
                        String currentTime = label.getText();
                        seconds = Integer.parseInt(currentTime.split(":")[1]);

                        if(editableIndex == false){
                            for (int i = 0; i < 9; i++) {
                                for (int j = 0; j < 9; j++) {
                                    if (dem1[i][j] == 0) {
                                        board_P1[i][j].setEditable(true);
                                    }
                                    if (dem2[i][j] == 0) {
                                        board_P2[i][j].setEditable(false);
                                    }
                                }
                            }
                        }else{
                            for (int i = 0; i < 9; i++) {
                                for (int j = 0; j < 9; j++) {
                                    if (dem1[i][j] == 0) {
                                        board_P1[i][j].setEditable(false);
                                    }
                                    if (dem2[i][j] == 0) {
                                        board_P2[i][j].setEditable(true);
                                    }
                                }
                            }
                        }

                        if (seconds == 0) {
                            timeline1.stop(); // Dừng đếm ngược nếu đã đếm đến 0
                            editableIndex = !editableIndex;
                            demtime2(thoiGian2);
                        } else {
                            seconds--;
                            label.setText("00:" + String.format("%02d", seconds)); // Cập nhật label thoigian1
                        }
                    }
                }));
        timeline1.play(); // Bắt đầu đếm ngược
        return 0;
    }
    private int demtime2(Label label){
        if(p1 == 1 && p2 == 1) return 0;
        if(p2 == 1) demtime1(thoiGian1);
        label.setText("00:15"); // Setup label thoigian1 thành 00:15

        timeline2 = new Timeline();
        timeline2.setCycleCount(Timeline.INDEFINITE); // Đếm ngược vô hạn
        timeline2.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                    // Đếm ngược sau mỗi giây
                    @Override
                    public void handle(ActionEvent event) {
                        String currentTime = label.getText();
                        seconds = Integer.parseInt(currentTime.split(":")[1]);

                        if(editableIndex == false){
                            thoiGian2.setText("00:00");
                            for (int i = 0; i < 9; i++) {
                                for (int j = 0; j < 9; j++) {
                                    if (dem1[i][j] == 0) {
                                        board_P1[i][j].setEditable(true);
                                    }
                                    if (dem2[i][j] == 0) {
                                        board_P2[i][j].setEditable(false);
                                    }
                                }
                            }
                        }else{
                            thoiGian1.setText("00:00");
                            for (int i = 0; i < 9; i++) {
                                for (int j = 0; j < 9; j++) {
                                    if (dem1[i][j] == 0) {
                                        board_P1[i][j].setEditable(false);
                                    }
                                    if (dem2[i][j] == 0) {
                                        board_P2[i][j].setEditable(true);
                                    }
                                }
                            }
                        }

                        if (seconds == 0) {
                            timeline2.stop(); // Dừng đếm ngược nếu đã đếm đến 0
                            if(p1 == 0 && p2 == 0)editableIndex = !editableIndex;
                            demtime1(thoiGian1);
                        } else {
                            seconds--;
                            label.setText("00:" + String.format("%02d", seconds)); // Cập nhật label thoigian1
                        }
                    }
                }));
        timeline2.play(); // Bắt đầu đếm ngược
        return 0;
    }
    public void StopTimer(){
        if(timeline1 != null){
            timeline1.stop();
        }
        if(timeline2 != null){
            timeline2.stop();
        }
    }
    public void ResetTimer(){
        StopTimer();
        seconds = 0;
        thoiGian1.setText("00:00");
        thoiGian2.setText("00:00");
    }
}
