package com.gabrielbog.openstream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gabrielbog.openstream.models.MusicModel;
import com.gabrielbog.openstream.models.ServerResponseViewModel;
import com.gabrielbog.openstream.threads.ClientThread;
import com.gabrielbog.openstream.threads.Constants;

public class LogInActivity extends AppCompatActivity {

    private EditText usernameBar;
    private EditText passwordBar;
    private Button loginButton;
    private Button registerRedirectButton;

    private int showMessage = 1;

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
                if(!usernameBar.getText().toString().equals("") && !passwordBar.getText().toString().equals(""))
                {
                    String msg = Constants.sLoginReq + "-" + usernameBar.getText().toString() + "-" + passwordBar.getText().toString() + "-";

                    ClientThread thread = new ClientThread(Constants.IP, Constants.PORT, msg);
                    thread.start();
                    //Toast.makeText(view.getContext(), "Verifying creditentials", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(view.getContext(), "Please fill out all details", Toast.LENGTH_LONG).show();
                }
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