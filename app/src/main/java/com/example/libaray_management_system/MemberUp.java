package com.example.libaray_management_system;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MemberUp extends AppCompatActivity {
    private EditText editTextName, editTextAddress, editTextPhone,editTextUnpaid;
    private Button buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_up);

        editTextName = findViewById(R.id.editTextName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextUnpaid = findViewById(R.id.editTextUnpaid);
        buttonDone = findViewById(R.id.buttonDone);


        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMemberDetails();
            }
        });
    }

    private void updateMemberDetails() {
        String updatedName = editTextName.getText().toString();
        String updatedAddress = editTextAddress.getText().toString();
        String updatedPhone = editTextPhone.getText().toString();
        String updatedUnpaid = editTextUnpaid.getText().toString();


        Intent resultIntent = new Intent();
        resultIntent.putExtra("card_id", getIntent().getIntExtra("card_id", -1));
        resultIntent.putExtra("member_name", updatedName);
        resultIntent.putExtra("member_address", updatedAddress);
        resultIntent.putExtra("member_phone", updatedPhone);
        resultIntent.putExtra("unpaid_dues", updatedUnpaid);
        setResult(RESULT_OK, resultIntent);

        finish();
    }
}
