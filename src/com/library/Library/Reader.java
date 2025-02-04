package com.library.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Reader extends Person {
    private MemberRecord memberRecord;
    private final List<Book> borrowedBooks;
    private final int maxBookLimit;

    public Reader(String name, String surname, MemberRecord memberRecord) {
        super(name, surname);
        this.maxBookLimit = 5;
        this.borrowedBooks = new ArrayList<>();
        this.memberRecord = memberRecord;
    }

    public MemberRecord getMemberRecord() {
        return memberRecord;
    }

    public void setMemberRecord(MemberRecord memberRecord) {
        this.memberRecord = memberRecord;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public boolean borrowBook(Book book) {
        if (borrowedBooks.size() >= maxBookLimit) {
            System.out.println("Kitap limiti aşıldı.");
            return false;
        }
        if (book.getStatus().equals("available")) {
            borrowedBooks.add(book);
            book.setStatus("borrowed");
            book.setOwner(this);
            memberRecord.incBookIssued(); // MemberRecord'daki kitap sayısını artır
            System.out.println(book.getName() + " ödünç alındı.");
            return true;
        }
        System.out.println(book.getName() + " şu anda mevcut değil.");
        return false;
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setStatus("available");
            book.setOwner(null);
            memberRecord.decBookIssued(); // MemberRecord'daki kitap sayısını azalt
            System.out.println(book.getName() + " iade edildi.");
        } else {
            System.out.println("Bu kitap sizin tarafınızdan alınmamış.");
        }
    }

    public void purchaseBook(Book book) {
        Scanner scanner = new Scanner(System.in);

        if (!borrowedBooks.contains(book)) {
            System.out.println("Kitap kütüphanemizde bulunmamaktadır.");
            return;
        }

        System.out.println("Kitabın fiyatı " + book.getPrice() + " TL'dir.");
        System.out.println("Bu kitabı satın almak istiyor musunuz? (Evet için 1, Hayır için 2):");
        int cevap = scanner.nextInt();

        switch (cevap) {
            case 1:
                borrowedBooks.remove(book);
                book.setStatus("purchased");
                book.setOwner(this);
                memberRecord.decBookIssued(); // Satın alınan kitap ödünç listesinden çıkarılmalı
                System.out.println("Kitabı başarıyla satın aldınız: " + book.getName());
                break;

            case 2:
                System.out.println("Satın alma işlemi iptal edildi.");
                break;

            default:
                System.out.println("Geçersiz bir seçim yaptınız. Lütfen tekrar deneyin.");
                break;
        }

        showBook();
    }

    public void showBook() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Henüz ödünç alınmış kitap yok.");
        } else {
            System.out.println("Ödünç alınan kitaplar:");
            borrowedBooks.forEach(book -> System.out.println("- " + book.getName()));
        }
    }

    @Override
    public void whoYouAre() {
        System.out.println("Ben bir okuyucuyum: " + getName());
    }

    @Override
    public String toString() {
        return getName() + ' ' + getSurname();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return getName().equalsIgnoreCase(reader.getName()) && getSurname().equalsIgnoreCase(reader.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName().toLowerCase(), getSurname().toLowerCase());
    }
}