package com.example.libaray_management_system;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AuthorUp extends AppCompatActivity {
    private EditText editTextName;
    private Button buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_up);

        editTextName = findViewById(R.id.editTextName);
        buttonDone = findViewById(R.id.buttonDone);


        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAuthorDetails();
            }
        });
    }

    private void updateAuthorDetails() {
        String updatedName = editTextName.getText().toString();



        Intent resultIntent = new Intent();
        resultIntent.putExtra("book_id", getIntent().getIntExtra("book_id", -1));
        resultIntent.putExtra("member_name", updatedName);
        setResult(RESULT_OK, resultIntent);

        finish();
    }
}
