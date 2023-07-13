package com.example.project2.Controller.Client;

import com.example.project2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;




public class InRoom {

    @FXML private Label id_room;
    @FXML private Label ten_player1;
    @FXML private Label id_player1;
    @FXML private Label ten_player2;
    @FXML private Label id_player2;



    private Socket client;
    private DataOutputStream os;
    private Player player;

    String nextScene = "";

    public InRoom(Socket client, Player player) {
        this.client = client;
        this.player = player;
        try {
            this.os = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void write(String message) throws IOException {
        os.writeUTF(message);
        os.flush();
    }

    public void setplayer1(int ID, String Ten) {
        ten_player1.setText(Ten);
        id_player1.setText(""+ID);


    }
    public void SetIDRoom(String id) {
        id_room.setText("ID ROOM: "+id);
    }
    public void setplayer2(int ID, String Ten) {
        ten_player2.setText(Ten);
        id_player2.setText(""+ID);
    }


    public void BackBT(){
        //thoat
    }

    public void  StartBT() throws  IOException{
        Main m = new Main() ;

        nextScene = "ClientFXML/GameRoom.fxml";
        FXMLLoader loader = new FXMLLoader(m.getClass().getResource(nextScene));
        Parent parent = loader.load();

        m.changeScene(nextScene);
        Main.globalState.setWidth(1500);
        Main.globalState.setHeight(850);
    }



}

