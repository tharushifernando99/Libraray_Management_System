package com.example.libaray_management_system;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PublisherUp extends AppCompatActivity {
    private EditText editTextName, editTextAddress, editTextPhone;
    private Button buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher_up);

        editTextName = findViewById(R.id.editTextName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonDone = findViewById(R.id.buttonDone);


        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePublisherDetails();
            }
        });
    }

    private void updatePublisherDetails() {
        String updatedName = editTextName.getText().toString();
        String updatedAddress = editTextAddress.getText().toString();
        String updatedPhone = editTextPhone.getText().toString();


        Intent resultIntent = new Intent();
        resultIntent.putExtra("publisher_id", getIntent().getIntExtra("publisher_id", -1));
        resultIntent.putExtra("publisher_name", updatedName);
        resultIntent.putExtra("updated_address", updatedAddress);
        resultIntent.putExtra("updated_phone", updatedPhone);
        setResult(RESULT_OK, resultIntent);

        finish();
    }
}
