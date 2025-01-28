package com.library.Person;

public class Book {

    private  long id;
    private String name;
    private double price;
    private String author;
    private String category;
    private String status;

    public Book(long id, String name, double price, String author, String category,String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
        this.category = category;
        this.status = "Mevcut";
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
