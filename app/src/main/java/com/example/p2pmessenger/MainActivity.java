package com.example.p2pmessenger;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnServer, btnClient, btnInstructions;
    EditText etIp;
    TextView tvMyIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnServer = findViewById(R.id.btnServer);
        btnClient = findViewById(R.id.btnClient);
        btnInstructions = findViewById(R.id.btnInstructions);

        etIp = findViewById(R.id.etIp);
        tvMyIp = findViewById(R.id.tvMyIp);

        WifiManager wifiManager =
                (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

        int ip = wifiManager.getConnectionInfo().getIpAddress();

        String ipAddress = Formatter.formatIpAddress(ip);

        tvMyIp.setText("Ваш IP: " + ipAddress);

        btnServer.setOnClickListener(v -> {
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("mode", "server");
            startActivity(intent);
        });

        btnClient.setOnClickListener(v -> {
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("mode", "client");
            intent.putExtra("ip", etIp.getText().toString());
            startActivity(intent);
        });

        btnInstructions.setOnClickListener(v -> {
            startActivity(new Intent(this, InstructionsActivity.class));
        });
    }
}