package com.example.p2pmessenger;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.p2pmessenger.network.ClientThread;
import com.example.p2pmessenger.network.MessageListener;
import com.example.p2pmessenger.network.ServerThread;

public class ChatActivity extends AppCompatActivity implements MessageListener {

    private TextView chatBox;
    private EditText messageInput;
    private Button sendBtn;

    private ServerThread serverThread;
    private ClientThread clientThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatBox = findViewById(R.id.chatBox);
        messageInput = findViewById(R.id.messageInput);
        sendBtn = findViewById(R.id.sendBtn);

        String mode = getIntent().getStringExtra("mode");

        if ("server".equals(mode)) {
            serverThread = new ServerThread(this);
            serverThread.start();
        } else {
            String ip = getIntent().getStringExtra("ip");
            clientThread = new ClientThread(ip, this);
            clientThread.start();
        }

        sendBtn.setOnClickListener(v -> {
            String msg = messageInput.getText().toString();

            if (msg.isEmpty()) return;

            messageInput.setText("");

            if (serverThread != null) {
                serverThread.sendMessage(msg);
            }

            if (clientThread != null) {
                clientThread.sendMessage(msg);
            }

            chatBox.append("\nЯ: " + msg);
        });
    }

    @Override
    public void onMessageReceived(String message) {
        runOnUiThread(() ->
                chatBox.append("\nСобеседник: " + message)
        );
    }
}