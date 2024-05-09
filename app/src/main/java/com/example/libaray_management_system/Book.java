package com.example.libaray_management_system;

public class Book {
    private int id;
    private String title;
    private String book_publisher;

    public Book(int id, String title, String book_publisher) {
        this.id = id;
        this.title = title;
        this.book_publisher = book_publisher;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisherName() {
        return book_publisher;
    }

    public void setPublisherName(String book_publisher) {
        this.book_publisher = book_publisher;
    }
}
