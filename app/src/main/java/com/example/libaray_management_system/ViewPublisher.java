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

public class ViewPublisher extends AppCompatActivity implements PublisherAdapter.OnPublisherClickListener {
    private static final int UPDATE_PUBLISHER_REQUEST = 1;

    private RecyclerView recyclerView;
    private PublisherAdapter publisherAdapter;
    private List<Publisher> publisherList;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_branch);

        recyclerView = findViewById(R.id.recyclerView2);
        publisherList = new ArrayList<>();
        dbHelper = new Database(this);

        fetchPublishers();

        publisherAdapter = new PublisherAdapter(publisherList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(publisherAdapter);
    }

    private void fetchPublishers() {
        publisherList.clear();
        publisherList.addAll(dbHelper.getAllPublishers());
    }

    @Override
    public void onUpdateClick(int position) {
        Publisher publish = publisherList.get(position);
        Intent intent = new Intent(this, PublisherUp.class);
        intent.putExtra("publisher_id", publish.getId());
        startActivityForResult(intent, UPDATE_PUBLISHER_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPDATE_PUBLISHER_REQUEST && resultCode == RESULT_OK && data != null) {
            int publisherID = data.getIntExtra("publisher_id", -1);
            String updatedName = data.getStringExtra("updated_Name");
            String updatedAddress = data.getStringExtra("updated_Address");
            String updatedPhone = data.getStringExtra("updated_Phone");
            if (publisherID != -1) {
                for (Publisher publisher : publisherList) {
                    if (publisher.getId() == publisherID) {
                        publisher.setName(updatedName);
                        publisher.setAddress(updatedAddress);
                        publisher.setPhone(updatedPhone);
                        dbHelper.updatePublisher(publisherID, updatedName, updatedAddress, updatedPhone);
                        break;
                    }
                }
                publisherAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Publisher updated successfully", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onPublisherClick(int position) {
        Publisher publisher = publisherList.get(position);
        Toast.makeText(this, "Clicked Publisher: " + publisher.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        Publisher publisher = publisherList.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this publisher?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean deletionResult = dbHelper.deletePublisher(publisher.getId());
                        if (deletionResult) {
                            publisherList.remove(position);
                            publisherAdapter.notifyItemRemoved(position);
                            Toast.makeText(ViewPublisher.this, "Publisher deleted successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ViewPublisher.this, "Failed to delete publisher", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
