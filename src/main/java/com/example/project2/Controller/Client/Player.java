package com.example.project2.Controller.Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Player {

    public int ID;
    public String Ten;
    public Socket client;
    private DataOutputStream os;



    public Player(Socket client) {
        this.client = client;
        try {
            os = new DataOutputStream(client.getOutputStream());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public Player(int ID, String Ten){
        this.ID = ID;
        this.Ten = Ten;

    }



    public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }

    public String getTen(){
        return Ten;
    }
    public void setTen(String Ten){
        this.Ten = Ten;
    }


}
