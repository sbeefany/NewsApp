package com.example.user.mycardapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String[] EMAIL_ADDRESS = {"cu_009@mail.ru"};
    public static final String MAILTO = "cu_009@mail.ru";
    public static final String VK_URL = "https://vk.com";
    public static final String TWITTER_URL = "https://twitter.com";

    Button sendMessage;
    EditText message;
    ImageView vk;
    ImageView twitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!message.getText().toString().equals("")) {
                    sendEmail(message.getText().toString());
                }
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWeb(TWITTER_URL);
            }
        });
        vk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWeb(VK_URL);
            }
        });

    }

    private void openWeb(String url) {
        Intent webIntent = new Intent(Intent.ACTION_VIEW);
        webIntent.setData(Uri.parse(url));
        if (webIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(webIntent);
        } else {
            showMessage(getString(R.string.error_no_browser_app));
        }
    }

    private void sendEmail(String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse(MAILTO));
        intent.putExtra(Intent.EXTRA_EMAIL, EMAIL_ADDRESS);
        intent.putExtra(Intent.EXTRA_SUBJECT, R.string.email_subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            showMessage(getString(R.string.error_no_email_app));
        }
    }

    private void initViews() {
        sendMessage = findViewById(R.id.send_message);
        message = findViewById(R.id.message);
        vk=findViewById(R.id.vk);
        twitter=findViewById(R.id.twitter);
    }
    private void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
