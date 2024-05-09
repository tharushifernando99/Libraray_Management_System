package com.example.libaray_management_system;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PublisherMain extends AppCompatActivity {
    Button button1, button2;
    EditText name, address, phone;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher);

        name = findViewById(R.id.editTextText2);
        address = findViewById(R.id.editTextText3);
        phone = findViewById(R.id.editTextText4);
        button1 = findViewById(R.id.button2);
        button2 = findViewById(R.id.button12);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String publisher_name = address.getText().toString();
                String publisher_phone = phone.getText().toString();
                dbHelper = new Database(PublisherMain.this);
                boolean insertionResult = dbHelper.addPublisher(Name, publisher_name, publisher_phone);
                if (insertionResult) {
                    Toast.makeText(PublisherMain.this, "Publisher added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PublisherMain.this, "Failed to add publisher", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String publisher_name = address.getText().toString();
                String publisher_phone = phone.getText().toString();
                dbHelper = new Database(PublisherMain.this);
                Intent intent = new Intent(PublisherMain.this, ViewPublisher.class);
                intent.putExtra("Name", Name);
                intent.putExtra("PublisherName", publisher_name);
                intent.putExtra("PublisherPhone", publisher_phone);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}
