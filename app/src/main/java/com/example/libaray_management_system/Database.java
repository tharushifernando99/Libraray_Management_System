package com.example.libaray_management_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "LMSdb";
    private static final int DATABASE_VERSION = 14;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table TABLE_BOOKS(Book_ID INTEGER primary key AUTOINCREMENT,title TEXT,book_publisher TEXT)");
        db.execSQL("create table TABLE_USER(User_ID INTEGER primary key AUTOINCREMENT,Usename TEXT,password TEXT)");
        db.execSQL("create table TABLE_PUBLISHER(Publisher_ID INTEGER primary key AUTOINCREMENT,name TEXT, address TEXT, phone TEXT)");
        db.execSQL("create table TABLE_BRANCH(Branch_ID INTEGER primary key AUTOINCREMENT,name TEXT, address TEXT)");
        db.execSQL("create table TABLE_MEMBER(Card_ID INTEGER primary key AUTOINCREMENT,name TEXT, address TEXT, phone TEXT,unpaid TEXT)");
        db.execSQL("create table TABLE_BOOK_AUTHOR(Book_ID INTGER, name TEXT, PRIMARY KEY(Book_ID, name), FOREIGN KEY(Book_ID) REFERENCES TABLE_BOOKS(Book_ID))");
        db.execSQL("create table TABLE_COPY(AccessNo TEXT, Book_ID INTGER, Branch_ID, PRIMARY KEY(Book_ID, Branch_ID), FOREIGN KEY(Book_ID) REFERENCES TABLE_BOOKS(Book_ID),FOREIGN KEY(Branch_ID) REFERENCES TABLE_BRANCH(Branch_ID))");

    }

    public boolean addUsers(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username", username);
        contentValues.put("password", password);
        long result = db.insert("TABLE_USER", null, contentValues);
        db.close();
        return result != -1;
    }

    public boolean addBooks(String title, String book_publisher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("book_publisher", book_publisher);
        long result = db.insert("TABLE_BOOKS", null, contentValues);
        db.close();
        return result != -1;
    }

    public boolean addPublisher(String name, String address, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("address", address);
        contentValues.put("phone", phone);
        long result = db.insert("TABLE_PUBLISHER", null, contentValues);
        db.close(); // Close the database connection
        return result != -1;
    }
    public boolean addBranch(String name, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("address", address);
        long result = db.insert("TABLE_BRANCH", null, contentValues);
        db.close(); // Close the database connection
        return result != -1;
    }
    public boolean addMember(String name, String address, String phone, String unpaid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("address", address);
        contentValues.put("phone", phone);
        contentValues.put("unpaid", unpaid);
        long result = db.insert("TABLE_MEMBER", null, contentValues);
        db.close(); // Close the database connection
        return result != -1;
    }
    public boolean addAuthor(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        long result = db.insert("TABLE_BOOK_AUTHOR", null, contentValues);
        db.close(); // Close the database connection
        return result != -1;
    }
    public boolean addCopy(String AccessNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("AccessNo", AccessNo);
        long result = db.insert("TABLE_COPY", null, contentValues);
        db.close(); // Close the database connection
        return result != -1;
    }

    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TABLE_BOOKS", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("Book_ID"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String book_publisher = cursor.getString(cursor.getColumnIndex("book_publisher"));
                Book book = new Book(id, title, book_publisher);
                bookList.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return bookList;
    }

    public List<Publisher> getAllPublishers() {
        List<Publisher> publisherList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TABLE_PUBLISHER", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("Publisher_ID"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                String phone = cursor.getString(cursor.getColumnIndex("phone"));
                Publisher publisher = new Publisher(id, name, address, phone);
                publisherList.add(publisher);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return publisherList;
    }
    public List<Branch> getAllBranch() {
        List<Branch> branchList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TABLE_BRANCH", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("Branch_ID"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                Branch branch = new Branch(id,name,address);
                branchList.add(branch);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return branchList;
    }
    public List<Member> getAllMember() {
        List<Member> memberList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TABLE_MEMBER", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("Card_ID"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                String phone = cursor.getString(cursor.getColumnIndex("phone"));
                String unpaid = cursor.getString(cursor.getColumnIndex("unpaid"));
                Member member = new Member(id,name,address,phone,unpaid);
                memberList.add(member);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return memberList;
    }
    public List<Author> getAllAuthor() {
        List<Author> authorList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TABLE_BOOK_AUTHOR", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("Book_ID"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                Author author = new Author(id,name);
                authorList.add(author);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return authorList;
    }
    public List<Copy> getAllCopy() {
        List<Copy> copyList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TABLE_COPY", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("Book_ID"));
                int id2 = cursor.getInt(cursor.getColumnIndex("Branch_ID"));
                String accessNo = cursor.getString(cursor.getColumnIndex("AccessNo"));
                Copy copy= new Copy(id,id2,accessNo);
                copyList.add(copy);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return copyList;
    }

    public boolean deleteBook(int bookId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("TABLE_BOOKS", "Book_ID=?", new String[]{String.valueOf(bookId)});
        db.close();
        return result > 0;
    }
    public boolean deletePublisher(int publisherID) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("TABLE_PUBLISHER", "Publisher_ID=?", new String[]{String.valueOf(publisherID)});
        db.close();
        return result > 0;
    }
    public boolean deleteBranch(int branchID) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("TABLE_PUBLISHER", "Branch_ID=?", new String[]{String.valueOf(branchID)});
        db.close();
        return result > 0;
    }
    public boolean deleteMember(int cardID) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("TABLE_MEMBER", "Card_ID=?", new String[]{String.valueOf(cardID)});
        db.close();
        return result > 0;
    }
    public boolean deleteAuthor(int bookID) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("TABLE_BOOK_AUTHOR", "Book_ID=?", new String[]{String.valueOf(bookID)});
        db.close();
        return result > 0;
    }
    public boolean deleteCopy(int bookID) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("TABLE_COPY", "Book_ID=?", new String[]{String.valueOf(bookID)});
        db.close();
        return result > 0;
    }

    public boolean updateBook(int bookId, String updatedTitle, String updatedPublisher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", updatedTitle);
        contentValues.put("book_publisher", updatedPublisher);
        int result = db.update("TABLE_BOOKS", contentValues, "Book_ID=?", new String[]{String.valueOf(bookId)});
        db.close();
        return result > 0;
    }

    public boolean updatePublisher(int publisherID, String updatedName, String updatedAddress, String updatedPhone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", updatedName);
        contentValues.put("address", updatedAddress);
        contentValues.put("phone", updatedPhone);
        int result = db.update("TABLE_PUBLISHER", contentValues, "Publisher_ID=?", new String[]{String.valueOf(publisherID)});
        db.close();
        return result > 0;
    }
    public boolean updateBranch(int branchID, String updatedName, String updatedAddress) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", updatedName);
        contentValues.put("address", updatedAddress);
        int result = db.update("TABLE_BRANCH", contentValues, "Branch_ID=?", new String[]{String.valueOf(branchID)});
        db.close();
        return result > 0;
    }
    public boolean updateMember(int cardID, String updatedName, String updatedAddress,String updatedPhone,String updatedUnpaid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", updatedName);
        contentValues.put("address", updatedAddress);
        contentValues.put("phone", updatedPhone);
        contentValues.put("unpaid", updatedUnpaid);
        int result = db.update("TABLE_MEMBER", contentValues, "Card_ID=?", new String[]{String.valueOf(cardID)});
        db.close();
        return result > 0;
    }
    public boolean updateAuthor(int bookID, String updatedName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", updatedName);
        int result = db.update("TABLE_BOOK_AUTHOR", contentValues, "Book_ID=?", new String[]{String.valueOf(bookID)});
        db.close();
        return result > 0;
    }
    public boolean updateCopy(int bookID, int branchID,String updatedAccessNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("AccessNo", updatedAccessNo);
        int result = db.update("TABLE_COPY", contentValues, "Book_ID=?", new String[]{String.valueOf(bookID)});
        db.close();
        return result > 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TABLE_USER");
        db.execSQL("DROP TABLE IF EXISTS TABLE_BOOKS");
        db.execSQL("DROP TABLE IF EXISTS TABLE_PUBLISHER");
        db.execSQL("DROP TABLE IF EXISTS TABLE_BRANCH");
        db.execSQL("DROP TABLE IF EXISTS TABLE_MEMBER");
        db.execSQL("DROP TABLE IF EXISTS TABLE_BOOK_AUTHOR");
        db.execSQL("DROP TABLE IF EXISTS TABLE_COPY");
        onCreate(db);
    }
}
