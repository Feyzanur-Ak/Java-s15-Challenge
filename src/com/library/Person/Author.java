package com.library.Person;

import java.util.*;

public class Author extends Person {

    private  final  List<Book> books;


    public Author(String name, String surname) {
        super(name, surname);
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }



    public void newBook(Book book) {
        if (!books.contains(book)) {
            books.add(book);
            System.out.println(book.getName() + " yazara eklendi: " + getName());
        } else {
            System.out.println("Bu kitap zaten mevcut.");
        }
    }


    public void showBook(){
        System.out.println(getName() + " tarafından yazılan kitaplar:");
        for (Book book : books) {
            System.out.println("- " + book.getName());
        };
    }

    @Override
    public void whoYouAre() {
        System.out.println("Ben bir Yazarım: " + getName());
    }
}
