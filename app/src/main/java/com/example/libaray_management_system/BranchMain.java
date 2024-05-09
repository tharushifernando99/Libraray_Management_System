package com.example.libaray_management_system;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BranchMain extends AppCompatActivity {
    Button button1, button2;
    EditText name, address;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_main);

        name = findViewById(R.id.editTextText2);
        address = findViewById(R.id.editTextText3);
        button1 = findViewById(R.id.button2);
        button2 = findViewById(R.id.button12);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String branch_address = address.getText().toString();
                dbHelper = new Database(BranchMain.this);
                boolean insertionResult = dbHelper.addBranch(Name,branch_address);
                if (insertionResult) {
                    Toast.makeText(BranchMain.this, "Branch added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BranchMain.this, "Failed to add branch", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String branch_address = address.getText().toString();
                dbHelper = new Database(BranchMain.this);
                Intent intent = new Intent(BranchMain.this, ViewBranch.class);
                intent.putExtra("Name", Name);
                intent.putExtra("PublisherName", branch_address);
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
