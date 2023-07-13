package com.example.project2.Model;

import java.net.Socket;

public class CaNhan {
    public int ID;
    public String Ten;
    private String Username;
    private String Password;

    private Socket client;


    public CaNhan(int ID, String Ten, String Username, String Password){
        this.ID = ID;
        this.Ten = Ten;
        this.Username = Username;
        this.Password = Password;
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

    public String getUsername(){
        return Username;
    }
    public void setUsername(String Username){
        this.Username = Username;
    }

    public String getPassword(){
        return Password;
    }
    public void setPassword(String Password){
        this.Password = Password;
    }

    public CaNhan(Socket client){
        this.client = client;
    }


}
