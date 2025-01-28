package com.library.Person;
import java.util.Set;

public class Library
{
    private Set<Book> books;
    private Set<Reader> readers;


    public Library(Set<Book> books, Set<Reader> readers) {
        this.books = books;
        this.readers = readers;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public Set<Reader> getReaders() {
        return readers;
    }

    public void new_book(Book book) {
        boolean bookExists = false;

        for (Book b : books) {
            if (b.getId() == book.getId()) {
                bookExists = true;
                break;
            }
        }

        if (bookExists) {
            System.out.println("Bu kitap zaten mevcut: " + book.getName());
        } else {

            books.add(book);
            System.out.println("Kitap başarıyla eklendi: " + book.getName());
        }
    }

    public void lend_book(Book book, Reader reader) {
        if (books.contains(book) && book.getStatus().equals("available")) {
            book.setStatus("borrowed");
            book.setOwner(reader);
            System.out.println(reader.getName() + " kitabı ödünç aldı: " + book.getName());
        } else {
            System.out.println("Kitap ödünç verilemez: " + book.getName());
        }
    }

    public void take_back_book(Book book) {
        if (book.getStatus().equals("borrowed")) {
            book.setStatus("available");
            book.setOwner(null);
            System.out.println("Kitap geri alındı: " + book.getName());
        } else {
            System.out.println("Bu kitap zaten mevcut: " + book.getName());
        }
    }

    public void show_book() {
        books.forEach(book -> {
            System.out.println("Kitap: " + book.getName() + " - Durum: " + book.getStatus());
        });
    }


}
