package com.library.Person;

import java.util.List;

public class Librarian extends Person {
    private  String password;

    public Librarian(String name,String password) {
        super(name);
        this.password=password;
    }

    public void addBook(List<Book> books, Book book) {
        books.add(book);
        System.out.println(book.getName() + " başarıyla eklendi.");
    }

    public  void removeBook(List<Book> books, Book book) {
        books.remove(book);
        System.out.println(book.getName() + "kitap silindi.");
    }
    public void updateBook(Book book, String newName, String newAuthor, double newPrice) {
        book.setName(newName);
        book.setAuthor(newAuthor);
        book.setPrice(newPrice);
        System.out.println(book.getName() + " başarıyla güncellendi.");
    }
    @Override
    public void whoYouAre() {
        System.out.println("Ben bir kütüphaneciyim: " + getName());
    }
}
