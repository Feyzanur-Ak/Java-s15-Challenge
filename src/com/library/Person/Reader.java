package com.library.Person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Reader extends  Person {

    private List<Book> borrowedBooks;
    private int maxBookLimit;

    public Reader(String name, String id,int maxBookLimit ) {
        super(name, id);
        this.maxBookLimit=maxBookLimit;
        this.borrowedBooks=new ArrayList<>();
    }

    public boolean borrowBook(Book book) {
        if (borrowedBooks.size() >= maxBookLimit) {
            System.out.println("Kitap limiti aşıldı.");
            return false;
        }
        if (book.getStatus().equals("available")) {
            borrowedBooks.add(book);
            book.setStatus("borrowed");
            System.out.println(book.getName() + " ödünç alındı.");
            return true;
        }
        System.out.println(book.getName() + " şu anda mevcut değil.");
        return false;
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setStatus("available");
            System.out.println(book.getName() + " iade edildi.");
        } else {
            System.out.println("Bu kitap sizin tarafınızdan alınmamış.");
        }
    }

    @Override
    public void whoYouAre() {
        System.out.println("Ben bir okuyucuyum: " + getName());
    }



}
