package com.example.libaray_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BookUp extends AppCompatActivity {
    private EditText editTextTitle, editTextPublisher;
    private Button buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_up);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextPublisher = findViewById(R.id.editTextPublisher);
        buttonDone = findViewById(R.id.buttonDone);

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBookDetails();
            }
        });
    }
    private void updateBookDetails() {
        String updatedTitle = editTextTitle.getText().toString();
        String updatedPublisher = editTextPublisher.getText().toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("book_id", getIntent().getIntExtra("book_id", -1));
        resultIntent.putExtra("updated_title", updatedTitle);
        resultIntent.putExtra("updated_publisher", updatedPublisher);
        setResult(RESULT_OK, resultIntent);

        finish();
    }


}