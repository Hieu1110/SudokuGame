package com.example.project2.Controller.Server;

import com.example.project2.Main;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Server extends Application {
    public static Stage globalState;
    public static  volatile ServerThreadBus serverThreadBus;
    public static Socket socketOfServer;
    public static ServerView serverview = new ServerView();



    @Override
    public void start(Stage stage) throws IOException {
        Task<Void> serverTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    ServerSocket listener = null;
                    serverThreadBus = new ServerThreadBus();
                    System.out.println("Server is waiting to accept user...");
                    int clientNumber = 0;

                    ThreadPoolExecutor executor = new ThreadPoolExecutor (10, // corePoolSize
                            100, // maximumPoolSize
                            10, // thread timeout
                            TimeUnit.SECONDS, new ArrayBlockingQueue<>(8) // queueCapacity
                    );

                    try {
                        listener = new ServerSocket(6666);
                    } catch (IOException e) {
                        System.out.println(e);
                        System.exit(1);
                    }

                    while (true) {

                        try {
                            socketOfServer = listener.accept();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        ServerThread serverThread = new ServerThread(socketOfServer, clientNumber++);
                        serverThreadBus.add(serverThread);
                        executor.execute(serverThread);

                    }
                }
            }
        };
        Thread serverThread = new Thread(serverTask);
        serverThread.setDaemon(true); // Đánh dấu thread là daemon để kết thúc khi ứng dụng kết thúc
        serverThread.start();


        globalState=stage;

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ServerFXML/Server.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {launch(args);}
}