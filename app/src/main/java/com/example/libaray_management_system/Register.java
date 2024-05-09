package com.example.libaray_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    private Database dbHelper;
    Button btn;
    EditText ediUname;
    EditText ediPword,ediConpword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btn = findViewById(R.id.button3);
        EditText ediUname = findViewById(R.id.editTextText);
        EditText ediPword = findViewById(R.id.editTextTextPassword2);
        EditText ediConpword = findViewById(R.id.editTextTextPassword3);
        dbHelper = new Database(Register.this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
                finish();
                String UseName = ediUname.getText().toString();
                String password = ediPword.getText().toString();
                String confirm_password = ediConpword.getText().toString();
                if(password.compareTo(confirm_password)==0) {
                }else{
                    Toast.makeText(getApplicationContext(),"password and confrim_password didn't match",Toast.LENGTH_SHORT).show();
                }
                dbHelper.addUsers(UseName,password);
            }

        });


    }
}