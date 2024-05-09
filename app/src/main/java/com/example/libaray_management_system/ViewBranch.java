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

public class ViewBranch extends AppCompatActivity implements BranchAdapter.OnBranchClickListener {
    private static final int UPDATE_BRANCH_REQUEST = 1;

    private RecyclerView recyclerView;
    private BranchAdapter BranchAdapter;
    private List<Branch> branchList;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_branch);

        recyclerView = findViewById(R.id.recyclerView2);
        branchList = new ArrayList<>();
        dbHelper = new Database(this);

        fetchBranch();

        BranchAdapter = new BranchAdapter(branchList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(BranchAdapter);
    }

    private void fetchBranch() {
        branchList.clear();
        branchList.addAll(dbHelper.getAllBranch());
    }

    @Override
    public void onUpdateClick(int position) {
        Branch branch = branchList.get(position);
        Intent intent = new Intent(this, BranchUp.class);
        intent.putExtra("branch_id", branch.getId());
        startActivityForResult(intent, UPDATE_BRANCH_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPDATE_BRANCH_REQUEST && resultCode == RESULT_OK && data != null) {
            int branchID = data.getIntExtra("branch_id", -1);
            String updatedName = data.getStringExtra("updated_Name");
            String updatedAddress = data.getStringExtra("updated_Address");
            if (branchID != -1) {
                for (Branch branch : branchList) {
                    if (branch.getId() == branchID) {
                        branch.setName(updatedName);
                        branch.setAddress(updatedAddress);
                        dbHelper.updateBranch(branchID, updatedName, updatedAddress);
                        break;
                    }
                }
                BranchAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Branch updated successfully", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBranchClick(int position) {
        Branch branch = branchList.get(position);
        Toast.makeText(this, "Clicked Branch: " + branch.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        Branch branch = branchList.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this branch?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean deletionResult = dbHelper.deleteBranch(branch.getId());
                        if (deletionResult) {
                            branchList.remove(position);
                            BranchAdapter.notifyItemRemoved(position);
                            Toast.makeText(ViewBranch.this, "Branch deleted successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ViewBranch.this, "Failed to delete branch", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
