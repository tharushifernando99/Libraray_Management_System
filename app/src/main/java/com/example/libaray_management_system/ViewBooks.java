package com.example.libaray_management_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.Nullable;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;

public class ViewBooks extends AppCompatActivity implements BookAdapter.OnBookClickListener {

    private static final int UPDATE_BOOK_REQUEST = 1;

    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private List<Book> bookList;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);

        recyclerView = findViewById(R.id.recyclerView);
        bookList = new ArrayList<>();
        dbHelper = new Database(this);


        fetchBooks();


        bookAdapter = new BookAdapter(bookList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(bookAdapter);
    }

    private void fetchBooks() {
        bookList.clear();
        bookList.addAll(dbHelper.getAllBooks());
    }

    @Override
    public void onUpdateClick(int position) {
        Book book = bookList.get(position);
        Intent intent = new Intent(this, BookUp.class);
        intent.putExtra("book_id", book.getId());
        startActivityForResult(intent, UPDATE_BOOK_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPDATE_BOOK_REQUEST && resultCode == RESULT_OK && data != null) {
            int bookId = data.getIntExtra("book_id", -1);
            String updatedTitle = data.getStringExtra("updated_title");
            String updatedPublisher = data.getStringExtra("updated_publisher");
            if (bookId != -1) {
                for (Book book : bookList) {
                    if (book.getId() == bookId) {
                        book.setTitle(updatedTitle);
                        book.setPublisherName(updatedPublisher);
                        break;
                    }
                }
                bookAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onBookClick(int position) {
        Book book = bookList.get(position);
        Toast.makeText(this, "Clicked Book: " + book.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        Book book = bookList.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this book?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deleteBook(book.getId());
                        bookList.remove(position);
                        bookAdapter.notifyItemRemoved(position);
                        Toast.makeText(ViewBooks.this, "Book deleted successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
