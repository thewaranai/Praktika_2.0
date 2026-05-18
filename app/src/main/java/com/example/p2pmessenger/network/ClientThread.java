package com.example.p2pmessenger.network;

import java.io.*;
import java.net.*;

public class ClientThread extends Thread {

    private String serverIp;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private MessageListener listener;

    public ClientThread(String ip, MessageListener listener) {
        this.serverIp = ip;
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(serverIp, 5000);

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String msg;
            while ((msg = in.readLine()) != null) {
                listener.onMessageReceived(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String msg) {
        if (out != null) {
            out.println(msg);
        }
    }
}