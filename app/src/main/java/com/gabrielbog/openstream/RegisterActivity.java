package com.gabrielbog.openstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gabrielbog.openstream.threads.ClientThread;
import com.gabrielbog.openstream.threads.Constants;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameRegisterBar;
    private EditText passwordRegisterBar;
    private Button registerButton;

    private int showMessage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameRegisterBar = findViewById(R.id.usernameRegisterBar);
        passwordRegisterBar = findViewById(R.id.passwordRegisterBar);
        registerButton = findViewById(R.id.register_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            //todo: save user creditentials on server, carry over username once logged in via intent
            @Override
            public void onClick(View view) {
                if(!usernameRegisterBar.getText().toString().equals("") && !passwordRegisterBar.getText().toString().equals(""))
                {
                    String msg = Constants.sRegisterReq + "-" + usernameRegisterBar.getText().toString() + "-" + passwordRegisterBar.getText().toString() + "-";

                    ClientThread thread = new ClientThread(Constants.IP, Constants.PORT, msg);
                    thread.start();
                }
                else
                {
                    Toast.makeText(view.getContext(), "Please fill out all details", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}