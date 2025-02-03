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
        if (books.contains(book)) {
            System.out.println("Bu kitap zaten mevcut: " + book.getName());
        } else {
            books.add(book);
            System.out.println("Kitap başarıyla eklendi: " + book.getName());
        }
    }

    public void lend_book(Book book, Reader reader) {
        if (book == null || reader == null) {
            System.out.println("Geçersiz kitap veya okuyucu bilgisi.");
            return;
        }

        if (books.contains(book)) {
            if (book.getStatus().equals("available")) {
                book.setStatus("borrowed");
                book.setOwner(reader);
                System.out.println(reader.getName() + " kitabı ödünç aldı: " + book.getName());
            } else {
                System.out.println("Kitap şu anda ödünç verilmiş: " + book.getName());
            }
        } else {
            System.out.println("Kitap kütüphanede mevcut değil: " + book.getName());
        }
    }

    public void take_back_book(Book book) {
        if (books.contains(book)) {
            if (book.getStatus().equals("borrowed")) {
                book.setStatus("available");
                book.setOwner(null);
                System.out.println("Kitap geri alındı: " + book.getName());
            } else {
                System.out.println("Bu kitap zaten mevcut: " + book.getName());
            }
        } else {
            System.out.println("Kitap kütüphanede mevcut değil: " + book.getName());
        }
    }

    public void show_book() {
        if (books.isEmpty()) {
            System.out.println("Kütüphanede kitap bulunmamaktadır.");
            return;
        }

        books.forEach(book -> {
            System.out.println("Kitap: " + book.getName() +
                    " - Yazar: " + book.getAuthor() +
                    " - Durum: " + book.getStatus() +
                    " - Fiyat: " + book.getPrice() + " TL");
        });
    }


}
