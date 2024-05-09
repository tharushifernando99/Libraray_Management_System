package com.example.libaray_management_system;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class ViewCopy extends AppCompatActivity implements CopyAdapter.OnCopyClickListener {
    private static final int UPDATE_COPY_REQUEST = 1;

    private RecyclerView recyclerView;
    private CopyAdapter copyAdapter;
    private List<Copy> copyList;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_copy);

        recyclerView = findViewById(R.id.recyclerView5);
        copyList = new ArrayList<>();
        dbHelper = new Database(this);

        fetchCopy();

        copyAdapter = new CopyAdapter(copyList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(copyAdapter);
    }

    private void fetchCopy() {
        copyList.clear();
       copyList.addAll(dbHelper.getAllCopy());
    }

    @Override
    public void onUpdateClick(int position) {
        Copy copy = copyList.get(position);
        Intent intent = new Intent(this, CopyUp.class);
        intent.putExtra("book_id",copy.getId());
        startActivityForResult(intent, UPDATE_COPY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPDATE_COPY_REQUEST && resultCode == RESULT_OK && data != null) {
            int bookID = data.getIntExtra("book_id", -1);
            int branchID = data.getIntExtra("branch_id", -1);
            String updatedAccessNo = data.getStringExtra("updated_Accessno");
            if (bookID != -1) {
                for (Copy copy : copyList) {
                    if (copy.getId() == bookID) {
                        copy.setaccessNo(updatedAccessNo);
                        dbHelper.updateCopy(bookID,branchID, updatedAccessNo);
                        break;
                    }
                }
                copyAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Copy updated successfully", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onCopyClick(int position) {
        Copy copy = copyList.get(position);
        Toast.makeText(this, "Clicked Copy: " + copy.getaccessNo(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        Copy copy = copyList.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this copy?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean deletionResult = dbHelper.deleteCopy(copy.getId());
                        if (deletionResult) {
                            copyList.remove(position);
                            copyAdapter.notifyItemRemoved(position);
                            Toast.makeText(ViewCopy.this, "Copy deleted successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ViewCopy.this, "Failed to delete copy", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
