package com.example.project2;

import com.example.project2.Model.CaNhan;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class JdbcDAO {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/test";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";


    private static final String CHECK_LOGIN_USER = "SELECT username,password from canhan";

    public static Connection ConnectDB() throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        return connection;
    }

    //Kiểm tra đăng nhập
    public Map<String,String> checkloginUser() throws  SQLException {
        Map<String,String> map = new HashMap<>();
        Connection connection = ConnectDB();
        ResultSet result = connection.createStatement().executeQuery(CHECK_LOGIN_USER);

        while (result.next()) {
            map.put(result.getObject(1).toString(),result.getObject(2).toString());
        }
        return map;
    }



    //Đăng ký người chơi
    public void InsertUser(CaNhan acc) throws SQLException{
        Connection connection = ConnectDB();
        Statement st = connection.createStatement();

        int ID = acc.getID();
        String Ten = acc.getTen();
        String username = acc.getUsername();
        String password = acc.getPassword();
        String sql="INSERT INTO canhan VALUES ( '"+ ID+"','"+Ten +"','" +username+ "','"+ password +"')";
        System.out.println(sql);
        st.executeUpdate(sql);
        System.out.println("them user thanh cong");


    }
}
