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

public class ThiDauTDController {

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

    @FXML
    private Button BTXong;



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
        resetTimer();
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

        LuuBefore1();
        startTimer();

    }


    Alert alert = new Alert(Alert.AlertType.INFORMATION);



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
        } else {
            // Xử lý sự kiện khi người dùng chọn nút bấm Hủy hoặc đóng Alert

        }

    }

    int p1 = 0, p2 = 0;
    public void P1_KT(ActionEvent e) throws IOException{
        boolean win = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board_P1[i][j].isEditable()) {
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
        if (win == true) {
            P1_lb.setText("Chúc mừng bạn đã giải thành công");
            p1 = 1;
        }else {
            P1_lb.setText(numberCellIllegal+" ô không hợp lệ");
            p1 = 0;
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
        } else {
            // Xử lý sự kiện khi người dùng chọn nút bấm Hủy hoặc đóng Alert
            // ...
        }

    }

    public void P2_KT(ActionEvent e) throws IOException{
        boolean win = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board_P2[i][j].isEditable()) {
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
        if (win == true) {
            P2_lb.setText("Chúc mừng bạn đã giải thành công");
            p2 = 1;
        }else {
            P2_lb.setText(numberCellIllegal+" ô không hợp lệ");
            p2 = 0;
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






    private Timeline timeline1, timeline2;
    int seconds1 = 0, seconds2 = 0;;

    private void startTimer() {
        timeline1 = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                seconds1++;
                                int hours = (seconds1 / 3600) % 24;
                                int minutes = (seconds1 / 60) % 60;
                                int remainingSeconds = seconds1 % 60;
                                thoiGian1.setText(String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds));
                            }
                        }
                )
        );
        timeline1.setCycleCount(Animation.INDEFINITE);
        timeline1.play();

        timeline2 = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                seconds2++;
                                int hours = (seconds2 / 3600) % 24;
                                int minutes = (seconds2 / 60) % 60;
                                int remainingSeconds = seconds2 % 60;
                                thoiGian2.setText(String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds));
                            }
                        }
                )
        );
        timeline2.setCycleCount(Animation.INDEFINITE);

        BTXong.setOnAction(event -> {
            if (timeline1.getStatus() == Animation.Status.RUNNING) {
                int k = KTSua1();
                if (k == 1){
                    P1_lb.setText("");
                    timeline1.stop();
                    timeline2.play();
                    LuuBefore2();
                }else{
                    P1_lb.setText("Bạn đã sửa "+ k +" ô! Chỉ được sửa 1 ô!");
                }
            } else if (timeline2.getStatus() == Animation.Status.RUNNING) {
                int l = KTSua2();
                if(l == 1) {
                    P2_lb.setText("");
                    timeline2.stop();
                    timeline1.play();
                    LuuBefore1();
                }else{
                    P2_lb.setText("Bạn đã sửa "+ l +" ô! Chỉ được sửa 1 ô!");
                }
            } else {
                timeline1.play();
            }
            if(p1 == 1 && p2 == 1){
                // Lấy giá trị thời gian từ các Label
                String time1String = thoiGian1.getText();
                String time2String = thoiGian2.getText();

                // So sánh hai chuỗi thời gian
                if (time1String.compareTo(time2String) < 0) {
                    // time1 nhỏ hơn time2
                    P1_lb.setText("Người chơi 1 thắng!");
                    P2_lb.setText("Người chơi 2 thua!");
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Người chơi 1 thắng!");
                    alert.show();
                } else if (time1String.compareTo(time2String) > 0) {
                    // time1 lớn hơn time2
                    P1_lb.setText("Người chơi 1 thua!");
                    P2_lb.setText("Người chơi 2 thắng!");
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Người chơi 2 thắng!");
                    alert.show();
                } else {
                    // Hai giá trị thời gian bằng nhau
                    P1_lb.setText("Ngang tài ngang sức!");
                    P2_lb.setText("Ngang tài ngang sức!");
                    alert.setTitle("Thông báo");
                    alert.setHeaderText(null);
                    alert.setContentText("Hai người chơi ngang tài ngang sức!");
                    alert.show();
                }
            }
        });
    }

    private void stopTimer() {
        if(timeline1 != null) {
            timeline1.stop();
        }
        if(timeline2 != null) {
            timeline2.stop();
        }
    }

    private void resetTimer() {
        stopTimer();
        seconds1 = 0;
        seconds2 = 0;
        thoiGian1.setText("00:00:00");
        thoiGian2.setText("00:00:00");
    }


    String[][] initialValues1 = new String[9][9];
    String[][] initialValues2 = new String[9][9];

    public void LuuBefore1(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                initialValues1[i][j] = board_P1[i][j].getText();
            }
        }
    }
    public void LuuBefore2(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                initialValues2[i][j] = board_P2[i][j].getText();
            }
        }
    }

    public int KTSua1(){
        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!board_P1[i][j].getText().equals(initialValues1[i][j])) {
                    count++; // Tăng biến đếm nếu TextField tại vị trí này đã được sửa đổi
                }
            }
        }
        return count;
    }
    public int KTSua2(){
        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!board_P2[i][j].getText().equals(initialValues2[i][j])) {
                    count++; // Tăng biến đếm nếu TextField tại vị trí này đã được sửa đổi
                }
            }
        }
        return count;
    }

}
