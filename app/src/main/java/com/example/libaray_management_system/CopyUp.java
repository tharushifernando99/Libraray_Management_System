package com.example.libaray_management_system;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CopyUp extends AppCompatActivity {
    private EditText editTextAccessNo;
    private Button buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_up);

        editTextAccessNo = findViewById(R.id.editTextAccessNo);
        buttonDone = findViewById(R.id.buttonDone);


        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCopyDetails();
            }
        });
    }

    private void updateCopyDetails() {
        String updatedAccessNo = editTextAccessNo.getText().toString();



        Intent resultIntent = new Intent();
        resultIntent.putExtra("book_id", getIntent().getIntExtra("book_id", -1));
        resultIntent.putExtra("branch_id", getIntent().getIntExtra("branch_id", -1));
        resultIntent.putExtra("access_no", updatedAccessNo);
        setResult(RESULT_OK, resultIntent);

        finish();
    }
}
