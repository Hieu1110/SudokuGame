package com.example.project2.Controller.Client;

import com.example.project2.Main;
import com.example.project2.Controller.Login.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientController {


    @FXML private Label user_name;

    @FXML private Label user_ID;





    String nextScene = "";

    private GameRoom gameroom;
    private InRoom inroom;




    Alert alert = new Alert(Alert.AlertType.INFORMATION);


    private Socket client;
    private DataOutputStream os;
    private DataInputStream is;
    private int ID = LoginController.ID;
    private Thread thread;
    private String Ten = LoginController.Ten;
    private Player player;




    public void initialize(){
        try {
            thread = new Thread() {
                @Override
                public void run() {
                    try {
                        client = new Socket("127.0.0.1", 6666);
                        System.out.println("Ket noi thanh cong toi server!");
                        // Tạo luồng đầu ra tại client (Gửi dữ liệu tới server)
                        os = new DataOutputStream(client.getOutputStream());
                        // Luồng đầu vào tại Client (Nhận dữ liệu từ server).
                        is = new DataInputStream(client.getInputStream());
                        player = new Player(client);
                        String message;

                        while (true) {
                            message = is.readUTF();
                            if (message == null) {
                                break;
                            }
                            String[] messageSplit = message.split(",");
                            if (messageSplit[0].equals("set-player")) {
                                player = new Player(LoginController.ID, LoginController.Ten);
//                                viewonline = new Home(client, player);
                            }
                            if (messageSplit[0].equals("khong-ton-tai")) {
                                alert.setTitle("Thông báo");
                                alert.setHeaderText(null);
                                alert.setContentText("Không tồn tại phòng");
                            }
                            if (messageSplit[0].equals("create-success")) {
                                //viewonline.setVisible(false);
                                inroom = new InRoom(client,player);
                                inroom.SetIDRoom(messageSplit[1]);
                                //
                                Main m = new Main();

                                nextScene = "LoginFXML/Home.fxml";
                                FXMLLoader loader = new FXMLLoader(m.getClass().getResource(nextScene));
                                Parent parent = loader.load();

                                m.showSceneB(nextScene);
                                Main.globalState.setWidth(900);
                                Main.globalState.setHeight(530);
                            }
                            if (messageSplit[0].equals("create-now")) {
                                inroom = new InRoom(client,player);
//                                inroom.setVisible(false);
                                inroom.SetIDRoom(messageSplit[1]);
                            }
                            if (messageSplit[0].equals("doi-thu-join-room")) {
                                inroom.setplayer1(LoginController.ID, LoginController.Ten);
                                System.out.println("Doi thu vao room");
                            }

                            if (messageSplit[0].equals("me-join-room")) {
                                System.out.println("toi vao room");

//                                viewonline.setVisible(false);
                                inroom = new InRoom(client,player);
                                inroom.SetIDRoom(messageSplit[1]);
                                inroom.setplayer2(LoginController.ID, LoginController.Ten);
                            }
                            if (messageSplit[0].equals("doi-thu-join-room-now")) {
//                                inroom.setVisible(true);
//                                viewonline.closeld();
//                                viewonline.setVisible(false);
                                inroom.setplayer1(LoginController.ID, LoginController.Ten);
                                System.out.println("Doi thu vao room");
                            }

                            if (messageSplit[0].equals("me-join-room-now")) {
//                                viewonline.closeld();
//
//                                viewonline.setVisible(false);
                                inroom = new InRoom(client,player);
                                inroom.SetIDRoom(messageSplit[1]);
                                inroom.setplayer2(LoginController.ID, LoginController.Ten);
                            }
                            if (messageSplit[0].equals("doi-thu-da-thoat")) {
//                                inroom.exitroom();
                            }

                            if (messageSplit[0].equals("start")) {
//                                player.setValue(messageSplit[1]);
//                                inroom.setVisible(false);
//                                gameroom = new GameRoom(client, player);
//                                gameroom.setplayer(messageSplit[2], messageSplit[3]);
                            }
                            if (messageSplit[0].equals("attack")) {
//                                gameroom.setAttack(Integer.parseInt(messageSplit[1]),Integer.parseInt(messageSplit[2]));

                            }
                            if (messageSplit[0].equals("lose")) {

//                                gameroom.SetWin(Integer.parseInt(messageSplit[1]), Integer.parseInt(messageSplit[2]),Integer.parseInt(messageSplit[3]));
//                                gameroom.lose();
//                                gameroom.dispose();
//                                inroom.setVisible(true);

                            }if (messageSplit[0].equals("win")) {
                                alert.setTitle("Thông báo");
                                alert.setHeaderText(null);
                                alert.setContentText("Bạn đã chiến thắng");
//                                gameroom.dispose();
//                                inroom.setVisible(true);

                            }
                            if(messageSplit[0].equals("exit-room")){
//                                inroom.dispose();
//                                viewonline.setVisible(true);
                            }
                            if(messageSplit[0].equals("delete")){
//                                inroom.dispose();
                            }
                            if(messageSplit[0].equals("sms")){
//                                gameroom.setsms(messageSplit[1]);
                            }
                            if(messageSplit[0].equals("chat-server")){
                                String sms = "";
                                for(int i=1; i<messageSplit.length;i++) {
                                    sms+= messageSplit[i]+" ";
                                }
//                                viewonline.SetChat(sms);
                            }
                            if(messageSplit[0].equals("doi-thu-da-thoat-game")){

//                                gameroom.end();
//                                gameroom.dispose();
//                                inroom.exitroom();
//                                inroom.setVisible(true);
                            }
                            if(messageSplit[0].equals("doi-thu-dau-hang")){
                                alert.setTitle("Thông báo");
                                alert.setHeaderText(null);
                                alert.setContentText("Đối thủ đầu hàng, bạn đã chiến thắng");
//                                gameroom.dispose();
//                                inroom.setVisible(true);
                            }
                            if(messageSplit[0].equals("dau-hang")){
                                alert.setTitle("Thông báo");
                                alert.setHeaderText(null);
                                alert.setContentText("Bạn đã thua");
//                                gameroom.dispose();
//                                inroom.setVisible(true);
                            }
                            if(messageSplit[0].equals("doi-thu-xin-hoa")){
                                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                                confirmationAlert.setTitle("Đối thủ gửi yêu cầu hòa");
                                confirmationAlert.setHeaderText("Bạn có đồng ý hòa không?");
                                confirmationAlert.setContentText("Vui lòng chọn một tùy chọn.");

                                ButtonType yesButton = new ButtonType("Có");
                                ButtonType noButton = new ButtonType("Không");

                                confirmationAlert.getButtonTypes().setAll(yesButton, noButton);

                                confirmationAlert.showAndWait().ifPresent(response -> {
                                    if (response == yesButton) {
                                        try {
                                            write("yes-hoa");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
                                        informationAlert.setTitle("Thông báo");
                                        informationAlert.setHeaderText(null);
                                        informationAlert.setContentText("Ván này hòa");

                                        informationAlert.showAndWait();
                                    }
                                    if (response == noButton){
                                        try {
                                            write("no-hoa");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });
                            }
                            if(messageSplit[0].equals("dong-y-hoa")){
                                alert.setTitle("Thông báo");
                                alert.setHeaderText(null);
                                alert.setContentText("Ván này hòa");
//                                gameroom.dispose();
//                                inroom.setVisible(true);
                            }
                            if(messageSplit[0].equals("khong-hoa")){
                                alert.setTitle("Thông báo");
                                alert.setHeaderText(null);
                                alert.setContentText("Đối thủ không đồng ý hòa, hãy tiếp tục thi đấu");
                            }
                        }

                    } catch (UnknownHostException e) {
                        return;
                    } catch (IOException e) {
                        return;
                    }
                }
            };

            thread.start();
        } catch (Exception ev) {
        }




        ID = LoginController.ID; Ten = LoginController.Ten;
        user_ID.setText(""+LoginController.ID);
        user_name.setText(LoginController.Ten);



    }


    public void write(String message) throws IOException {
        os.writeUTF(message);
        os.flush();
    }



    public void ThongTin(int ID, String Ten){
        user_ID.setText(""+ID);
        user_name.setText(Ten);
    }

    public void BackBT(ActionEvent e) throws IOException {
        Main m = new Main();

        nextScene = "LoginFXML/Home.fxml";
        FXMLLoader loader = new FXMLLoader(m.getClass().getResource(nextScene));
        Parent parent = loader.load();

        m.showSceneB(nextScene);
        Main.globalState.setWidth(900);
        Main.globalState.setHeight(530);
    }

    public void VaoPhongBT(ActionEvent e) throws IOException {
        Main m = new Main();

        /*nextScene = "LoginFXML/Login.fxml";
        FXMLLoader loader = new FXMLLoader(m.getClass().getResource(nextScene));
        Parent parent = loader.load();

        m.changeScene(nextScene);
        Main.globalState.setWidth(597);
        Main.globalState.setHeight(459);*/
    }

    public void TaoPhongBT(ActionEvent e) throws IOException {
        Main m = new Main();

        nextScene = "ClientFXML/InRoom.fxml";
        FXMLLoader loader = new FXMLLoader(m.getClass().getResource(nextScene));
        Parent parent = loader.load();

        m.changeScene(nextScene);
        Main.globalState.setWidth(900);
        Main.globalState.setHeight(500);
    }

}

