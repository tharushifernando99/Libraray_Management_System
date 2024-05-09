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

public class ViewAuthor extends AppCompatActivity implements AuthorAdapter.OnAuthorClickListener {
    private static final int UPDATE_AUTHOR_REQUEST = 1;

    private RecyclerView recyclerView;
    private AuthorAdapter authorAdapter;
    private List<Author> authorList;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_author);

        recyclerView = findViewById(R.id.recyclerView4);
        authorList = new ArrayList<>();
        dbHelper = new Database(this);

        fetchAuthors();

        authorAdapter = new AuthorAdapter(authorList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(authorAdapter);
    }

    private void fetchAuthors() {
        authorList.clear();
        authorList.addAll(dbHelper.getAllAuthor());
    }

    @Override
    public void onUpdateClick(int position) {
        Author author = authorList.get(position);
        Intent intent = new Intent(this, AuthorUp.class);
        intent.putExtra("book_id",author.getId());
        startActivityForResult(intent, UPDATE_AUTHOR_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPDATE_AUTHOR_REQUEST && resultCode == RESULT_OK && data != null) {
            int bookID = data.getIntExtra("book_id", -1);
            String updatedName = data.getStringExtra("updated_Name");
            if (bookID != -1) {
                for (Author author : authorList) {
                    if (author.getId() == bookID) {
                        author.setName(updatedName);
                        dbHelper.updateAuthor(bookID, updatedName);
                        break;
                    }
                }
                authorAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Author updated successfully", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onAuthorClick(int position) {
        Author author = authorList.get(position);
        Toast.makeText(this, "Clicked Author: " + author.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        Author author = authorList.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this author?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean deletionResult = dbHelper.deleteAuthor(author.getId());
                        if (deletionResult) {
                            authorList.remove(position);
                            authorAdapter.notifyItemRemoved(position);
                            Toast.makeText(ViewAuthor.this, "Author deleted successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ViewAuthor.this, "Failed to delete author", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
