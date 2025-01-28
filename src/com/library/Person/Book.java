package com.library.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Book {

    private  long id;
    private String name;
    private double price;
    private String author;
    private String status;
    private String edition;
    private LocalDate dateOfPurchase;
    private Reader owner;

      List<Book> books=new ArrayList<>();

    public Book(long id, String name, double price, String author, String status, String edition, LocalDate dateOfPurchase, Reader owner) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
        this.status = "available";
        this.edition = edition;
        this.dateOfPurchase = null;
        this.owner = null;
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
        for(Book book: books) {
            System.out.println("Sistemde bulunan kitaplar :" + book);
        }
    }

    public  void update_status(String newStatus){
        switch (newStatus) {
            case "available":
                this.status = "available";
                System.out.println("Kitap durumu 'Mevcut' olarak güncellendi.");
                break;

            case "purchased":
                this.status = "purchased";
                System.out.println("Kitap durumu 'Satın Alındı' olarak güncellendi.");
                break;

            case "borrowed":
                this.status = "borrowed";
                System.out.println("Kitap durumu 'Ödünç Alındı' olarak güncellendi.");
                break;

            default:
                System.out.println("Geçersiz bir durum girdiniz. Durum güncellenmedi.");
                break;
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

    public void changeOwner(Reader reader) {
        if (status.equals("borrowed")) {
            System.out.println("Kitap şu anda " + reader.getName() + " adlı kullanıcıda.");
        } else if (status.equals("purchased")) {
            System.out.println("Kitap " + reader.getName() + " adlı kullanıcı tarafından " + dateOfPurchase + " tarihinde satın alınmıştır.");
        } else {
            System.out.println("Kitap şu anda kimseye ait değildir.");
        }
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
}
