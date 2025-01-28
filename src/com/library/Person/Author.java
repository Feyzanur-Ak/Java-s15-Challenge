package com.library.Person;

import java.util.*;

public class Author extends Person {

    protected final  List<Book> books;


    public Author(String name,String surname, String id, List<Book> books) {
        super(name,surname);
        this.books=new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }


    public void newBook(Book book){
        books.add(book);
        System.out.println(book.getName() + " yazara eklendi: " + getName());
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
