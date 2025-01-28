package com.library.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Librarian extends Person {
    private  String password;
    private List<Book> books;

    public Librarian(String name, String surname, String password,List<String> books ) {
        super(name,surname);
        this.password=password;
        this.books=new ArrayList<>();
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

    public void returnBook(Book book) {
      if(!books.contains(book)){
          books.add(book);
          book.setStatus("available");
          System.out.println(book.getName() + " başarıyla iade edildi.");
      } else {
          System.out.println(book.getName() + " zaten kütüphanede mevcut.");
      }
    }

    public void searchBooks(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Aradığınız Kitabın İsmini Giriniz: ");
        String input= scanner.nextLine();

        boolean found = false;

        for (Book book : books) {
            if (book.getName().toLowerCase().contains(input.toLowerCase())) {
                System.out.println("Kitap bulundu: " + book.getName());
                System.out.println("Durumu: " + book.getStatus());
                found = true; // Kitap bulundu
            }
        }

        if (!found) {
            System.out.println("Aradığınız kitap kütüphanede bulunamadı.");
        }
    }

    public boolean verifyMember(List<Reader> readers, String name, String surname) {
        for (Reader reader : readers) {
            if (reader.getName().equalsIgnoreCase(name) && reader.getSurname().equalsIgnoreCase(surname)) {
                System.out.println("Üye doğrulandı: " + name + " " + surname);
                return true;
            }
        }
        System.out.println("Üye bulunamadı: " + name + " " + surname);
        return false;
    }

    public void issueBook(List<Reader> readers, String readerName, String readerSurname, Book book) {
        if (!verifyMember(readers, readerName, readerSurname)) {
            System.out.println("Üye doğrulaması başarısız. Kitap ödünç verilemedi.");
            return;
        }

        if (!book.getStatus().equalsIgnoreCase("available")) {
            System.out.println("Kitap şu anda ödünç verilemez: " + book.getName());
            return;
        }

        for (Reader reader : readers) {
            if (reader.getName().equalsIgnoreCase(readerName) && reader.getSurname().equalsIgnoreCase(readerSurname)) {
                if (reader.borrowBook(book)) {
                    System.out.println(book.getName() + " kitabı " + readerName + " " + readerSurname + " üyesine ödünç verildi.");
                } else {
                    System.out.println("Kitap limiti aşıldı. Kitap ödünç verilemedi.");
                }
                return;
            }
        }
    }

    public double calculateFine(int overdueDays) {
        double finePerDay = 1.5;
        if (overdueDays <= 0) {
            System.out.println("Gecikme yok. Ceza uygulanmadı.");
            return 0.0;
        }
        double totalFine = overdueDays * finePerDay;
        System.out.println("Toplam ceza: " + totalFine + " TL");
        return totalFine;
    }

    public void createBill(List<Reader> readers, String readerName, String readerSurname, double fineAmount) {
        if (!verifyMember(readers, readerName, readerSurname)) {
            System.out.println("Üye doğrulaması başarısız. Fatura oluşturulamadı.");
            return;
        }

        System.out.println("Fatura oluşturuldu:");
        System.out.println("Üye: " + readerName + " " + readerSurname);
        System.out.println("Toplam Ceza: " + fineAmount + " TL");
    }


    @Override
    public void whoYouAre() {
        System.out.println("Ben bir kütüphaneciyim: " + getName());
    }
}
