package com.example.libaray_management_system;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Books extends AppCompatActivity {
    Button button1,button2;
    EditText title,book_publisher;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        title = findViewById(R.id.editTextText2);
        book_publisher = findViewById(R.id.editTextText3);
        button1 = findViewById(R.id.button2);
        button2 = findViewById(R.id.button12);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title = title.getText().toString();
                String publisher_name = book_publisher.getText().toString();
                dbHelper = new Database(Books.this);
                boolean insertionResult = dbHelper.addBooks(Title, publisher_name);
                if (insertionResult) {
                    Toast.makeText(Books.this, "Book added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Books.this, "Failed to add book", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title = title.getText().toString();
                String publisher_name = book_publisher.getText().toString();
                dbHelper = new Database(Books.this);
                Intent intent = new Intent(Books.this,ViewBooks.class);
                startActivity(intent);
                finish();
            }
        });

    }
}