package com.library.Library;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;

public class Librarian extends Person {
    private String password;

    public Librarian(String name, String surname, String password) {
        super(name, surname);
        this.password = password;
    }

    // Kitap ekleme
    public void addBook(Library library, Book book) {
        library.new_book(book);
        System.out.println(book.getName() + " başarıyla kütüphaneye eklendi.");
    }

    // Kitap silme
    public void removeBook(Library library, Book book) {
        if (!library.getBooks().contains(book)) {
            System.out.println(book.getName() + " kütüphanede mevcut değil.");
            return;
        }
        library.getBooks().remove(book);
        System.out.println(book.getName() + " başarıyla kütüphaneden silindi.");
    }

    // Kitap güncelleme
    public void updateBook(Library library, Book book, String newName, String newAuthor, double newPrice) {
        if (!library.getBooks().contains(book)) {
            System.out.println("Güncellenecek kitap kütüphanede bulunamadı.");
            return;
        }
        book.setName(newName);
        book.setAuthor(newAuthor);
        book.setPrice(newPrice);
        System.out.println(book.getName() + " başarıyla güncellendi.");
    }

    // Kitap iade
    public void returnBook(Library library, Book book) {
        if (library.getBooks().contains(book)) {
            System.out.println(book.getName() + " zaten kütüphanede mevcut.");
            return;
        }
        library.getBooks().add(book);
        book.setStatus("available");
        System.out.println(book.getName() + " başarıyla kütüphaneye iade edildi.");
    }

    // Kitap arama
    public void searchBooks(Library library) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Aradığınız kitabın ismini giriniz: ");
        String input = scanner.nextLine();

        boolean found = false;

        for (Book book : library.getBooks()) {
            if (book.getName().toLowerCase().contains(input.toLowerCase())) {
                System.out.println("Kitap bulundu: " + book.getName());
                System.out.println("Durumu: " + book.getStatus());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Aradığınız kitap kütüphanede bulunamadı.");
        }
    }

    // Üye doğrulama
    public boolean verifyMember(Set<Reader> readers, String name, String surname) {
        // Var olan üyeyi kontrol et
        for (Reader reader : readers) {
            if (reader.getName().equalsIgnoreCase(name) && reader.getSurname().equalsIgnoreCase(surname)) {
                System.out.println("Üye doğrulandı: " + name + " " + surname);
                return true;
            }
        }

        // Üye bulunamadı, yeni üye kaydı başlatılıyor
        System.out.println("Üye bulunamadı: " + name + " " + surname);
        System.out.println("Yeni üye kaydı başlatılıyor...");

        // Kullanıcıdan dinamik bilgi al
        Scanner scanner = new Scanner(System.in);

        System.out.print("Lütfen üyenin yaşını girin: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Satır sonu karakterini temizle

        System.out.print("Lütfen üyenin adresini girin: ");
        String address = scanner.nextLine();

        System.out.print("Lütfen üyenin telefon numarasını girin: ");
        String phoneNo = scanner.nextLine();

        // Yeni MemberRecord oluştur
        MemberRecord memberRecord = new MemberRecord(
                address,
                name + " " + surname,
                5, // Maksimum kitap limiti
                0, // Henüz ödünç alınan kitap sayısı yok
                LocalDate.now(), // Üyelik tarihi
                "Reader", // Üye tipi
                System.currentTimeMillis(), // Üye ID'si
                phoneNo
        );

        // Yeni Reader oluştur
        Reader newReader = new Reader(name, surname, memberRecord);
        readers.add(newReader);

        System.out.println("Yeni üye başarıyla kaydedildi: " + newReader.getName() + " " + newReader.getSurname());
        return true;
    }

    // Kitap ödünç verme
    public void issueBook(Library library, Set<Reader> readers, String readerName, String readerSurname, Book book) {
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
                    library.lend_book(book, reader); // Kitap ödünç veriliyor
                    System.out.println(book.getName() + " kitabı " + readerName + " " + readerSurname + " üyesine ödünç verildi.");
                } else {
                    System.out.println("Kitap limiti aşıldı. Kitap ödünç verilemedi.");
                }
                return;
            }
        }
    }

    // Ceza hesaplama
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

    // Fatura oluşturma
    public void createBill(Set<Reader> readers, String readerName, String readerSurname, double fineAmount) {
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
