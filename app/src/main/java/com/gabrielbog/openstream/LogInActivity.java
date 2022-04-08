package com.gabrielbog.openstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {

    private EditText usernameBar;
    private EditText passwordBar;
    private Button loginButton;
    private Button registerRedirectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        usernameBar = findViewById(R.id.usernameBar);
        passwordBar = findViewById(R.id.passwordBar);
        loginButton = findViewById(R.id.login_button);
        registerRedirectButton = findViewById(R.id.register_redirect_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            //todo: check server for user creditentials, carry over username once logged in via intent
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        registerRedirectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}