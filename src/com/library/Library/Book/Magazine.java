package com.library.Library.Book;

import com.library.Library.Person.Reader;

import java.time.LocalDate;

public class Magazine extends Book {
    private String issue;
    private String genre;


    public Magazine(long id, String name, double price, String author, String status, String edition, LocalDate dateOfPurchase, Reader owner, String issue, String genre) {
        super(id, name, price, author, status, edition, dateOfPurchase, owner);
        this.genre=genre;
        this.issue=issue;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Dergi Sayısı: " + issue);
        System.out.println("Tür: " + genre);
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", author='" + getAuthor() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", edition='" + getEdition() + '\'' +
                ", dateOfPurchase=" + getDateOfPurchase() +
                ", issue='" + issue + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
