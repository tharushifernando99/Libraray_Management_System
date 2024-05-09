package com.example.libaray_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    Button button;
    EditText ediUsername, ediPassword;
    TextView tv;
    private Database dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        EditText ediUsername = findViewById(R.id.editTextUserName);
        EditText ediPassword = findViewById(R.id.editTextTextPassword);
        TextView tv = findViewById(R.id.textView8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ediUsername.getText().toString();
                String password = ediPassword.getText().toString();
                dbHelper = new Database(MainActivity.this);
                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Register.class));
            }
        });


    }
}