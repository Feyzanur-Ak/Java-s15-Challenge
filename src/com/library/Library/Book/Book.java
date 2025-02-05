package com.library.Library.Book;
import com.library.Library.Person.Reader;

import java.time.LocalDate;
import java.util.Objects;

public class Book {

    private  long id;
    private String name;
    private double price;
    private String author;
    private String status;
    private String edition;
    private LocalDate dateOfPurchase;
    private Reader owner;



    public Book(long id, String name, double price, String author, String status, String edition, LocalDate dateOfPurchase, Reader owner) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
        this.status = "available";
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
        this.owner = owner;
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

    public String getStatus() {
        return status;
    }

    public String getEdition() {
        return edition;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Reader getOwner() {
        return owner;
    }

    public void setOwner(Reader owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void display(){
        System.out.println(this);
    }


    public void updateStatus(String newStatus) {
        if (newStatus.equals("available") || newStatus.equals("borrowed") || newStatus.equals("purchased")) {
            this.status = newStatus;
            System.out.println("Kitap durumu güncellendi: " + newStatus);
        } else {
            System.out.println("Geçersiz durum: " + newStatus);
        }
    }


    public void getOwner(Reader reader) {
        if (status.equals("borrowed")) {
            if (reader.equals(owner)) {
                System.out.println("Kitap şu anda size ait ve ödünç alınmış durumda.");
            } else {
                System.out.println("Kitap şu anda " + (owner != null ? owner.getName() : "Bilinmeyen") + " adlı kullanıcıda.");
            }
        } else if (status.equals("purchased")) {
            if (reader.equals(owner)) {
                System.out.println("Kitap size ait ve " + dateOfPurchase + " tarihinde satın alınmıştır.");
            } else {
                System.out.println("Kitap " + (owner != null ? owner.getName() : "Bilinmeyen") + " adlı kullanıcı tarafından " + dateOfPurchase + " tarihinde satın alınmıştır.");
            }
        } else if (status.equals("available")) {
            System.out.println("Kitap şu anda kimseye ait değil ve mevcut durumda.");
        } else {
            System.out.println("Kitabın durumu bilinmiyor.");
        }
    }

    public void changeOwner(Reader newOwner) {
        this.owner = newOwner;
        System.out.println("Kitap sahibi değiştirildi: " + (newOwner != null ? newOwner.getName() : "Hiç kimse"));
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", status='" + status + '\'' +
                ", edition='" + edition + '\'' +
                ", dateOfPurchase=" + dateOfPurchase +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
