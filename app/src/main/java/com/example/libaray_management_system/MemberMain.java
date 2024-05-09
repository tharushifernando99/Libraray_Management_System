package com.example.libaray_management_system;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MemberMain extends AppCompatActivity {
    Button button1, button2;
    EditText name, address, phone,unpaid;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_main);

        name = findViewById(R.id.editTextText2);
        address = findViewById(R.id.editTextText3);
        phone = findViewById(R.id.editTextText4);
        unpaid = findViewById(R.id.editTextText5);
        button1 = findViewById(R.id.button2);
        button2 = findViewById(R.id.button12);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String member_address = address.getText().toString();
                String member_phone = phone.getText().toString();
                String un_paid = unpaid.getText().toString();
                dbHelper = new Database(MemberMain.this);
                boolean insertionResult = dbHelper.addMember(Name,member_address,member_phone,un_paid);
                if (insertionResult) {
                    Toast.makeText(MemberMain.this, "Member added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MemberMain.this, "Failed to add member", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String member_address = address.getText().toString();
                String member_phone = phone.getText().toString();
                String un_paid = unpaid.getText().toString();
                dbHelper = new Database(MemberMain.this);
                Intent intent = new Intent(MemberMain.this, ViewMember.class);
                intent.putExtra("Name", Name);
                intent.putExtra("MemberAddress", member_address);
                intent.putExtra("MemberPhone", member_phone);
                intent.putExtra("UNpaid", un_paid);
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
