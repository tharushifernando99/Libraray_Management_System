package com.example.libaray_management_system;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CopyMain extends AppCompatActivity {
    Button button1, button2;
    EditText AccessNo;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_main);

        AccessNo = findViewById(R.id.editTextText2);
        button1 = findViewById(R.id.button2);
        button2 = findViewById(R.id.button12);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accessNo = AccessNo.getText().toString();
                dbHelper = new Database(CopyMain.this);
                boolean insertionResult = dbHelper.addCopy(accessNo);
                if (insertionResult) {
                    Toast.makeText(CopyMain.this, "Book copy added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CopyMain.this, "Failed to add book copy", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accessNo = AccessNo.getText().toString();
                dbHelper = new Database(CopyMain.this);
                Intent intent = new Intent(CopyMain.this, ViewCopy.class);
                intent.putExtra("accessNo", accessNo );
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
