package com.example.p2pmessenger;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);

        textView.setPadding(32,32,32,32);

        textView.setTextSize(18);

        textView.setText(
                "ИНСТРУКЦИЯ\n\n" +
                        "1. Подключите оба устройства к одной Wi-Fi сети.\n\n" +
                        "2. На первом устройстве нажмите 'Создать сервер'.\n\n" +
                        "3. Узнайте IP сервера на главном экране.\n\n" +
                        "4. На втором устройстве введите IP сервера.\n\n" +
                        "5. Нажмите 'Подключиться'.\n\n" +
                        "6. После подключения можно обмениваться сообщениями."
        );

        setContentView(textView);
    }
}