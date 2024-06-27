package com.codingcosmic.flipperzero;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnIrSend = findViewById(R.id.btn_ir_send);
        Button btnSubGhz = findViewById(R.id.btn_subghz);
        Button btnNfc = findViewById(R.id.btn_nfc);

        btnIrSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand("ir_command");
            }
        });

        btnSubGhz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand("subghz_command");
            }
        });

        btnNfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand("nfc_command");
            }
        });
    }

    private void sendCommand(String command) {
        // Implement Bluetooth/Wi-Fi communication to send the command to Flipper Zero
        // Example using HTTP request (replace with actual implementation)
        new Thread(() -> {
            try {
                URL url = new URL("http://your-flipper-zero-ip:80/command");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write("command=" + command);
                writer.flush();
                writer.close();
                os.close();
                urlConnection.connect();
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == 200) {
                    // Success
                } else {
                    // Error
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
