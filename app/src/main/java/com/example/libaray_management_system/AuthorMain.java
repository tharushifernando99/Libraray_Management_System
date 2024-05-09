package com.example.libaray_management_system;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AuthorMain extends AppCompatActivity {
    Button button1, button2;
    EditText name;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_main);

        name = findViewById(R.id.editTextText2);
        button1 = findViewById(R.id.button2);
        button2 = findViewById(R.id.button12);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                dbHelper = new Database(AuthorMain.this);
                boolean insertionResult = dbHelper.addAuthor(Name);
                if (insertionResult) {
                    Toast.makeText(AuthorMain.this, "Author added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AuthorMain.this, "Failed to add authors", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                dbHelper = new Database(AuthorMain.this);
                Intent intent = new Intent(AuthorMain.this, ViewAuthor.class);
                intent.putExtra("Name", Name);
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
