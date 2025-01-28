package com.library.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class Reader extends  Person {

    private final List<Book> books;
    private final int maxBookLimit;

    public Reader(String name ) {
        super(name);
        this.maxBookLimit=5;
        this.books=new ArrayList<>();
    }

    public boolean borrow_book(Book book) {
        if (books.size() >= maxBookLimit) {
            System.out.println("Kitap limiti aşıldı.");
            return false;
        }
        if (book.getStatus().equals("available")) {
            books.add(book);
            book.setStatus("borrowed");
            System.out.println(book.getName() + " ödünç alındı.");
            return true;
        }
        System.out.println(book.getName() + " şu anda mevcut değil.");
        return false;
    }

    public void return_book(Book book) {
        if (books.remove(book)) {
            book.setStatus("available");
            System.out.println(book.getName() + " iade edildi.");
        } else {
            System.out.println("Bu kitap sizin tarafınızdan alınmamış.");
        }
    }

    public void purchase_book(Book book) {
        Scanner scanner = new Scanner(System.in);

        if(!books.contains(book)){
          System.out.println("Kitap Kütüphanemizde bulunmamaktadır");
      } else{
            System.out.println("Kitabın fiyatı " + book.getPrice() + " TL'dir.");
            System.out.println("Bu kitabı satın almak istiyor musunuz? (Evet için 1, Hayır için 2):");
            int cevap = scanner.nextInt();

            switch (cevap){
                case 1:
                    books.remove(book);
                    System.out.println("Kitabı başarıyla satın aldınız: " + book.getName());
                    break;

                case 2:
                    System.out.println("Satın alma işlemi iptal edildi.");
                    break;

                default:
                    System.out.println("Geçersiz bir seçim yaptınız. Lütfen tekrar deneyin.");
                    break;
            }
      }
    }


    public String show_book() {
        if (books.isEmpty()) {
            System.out.println("Sistemde şu anda hiçbir kitap bulunmamaktadır.");
            return "";
        }

        System.out.println("Sistemde bulunan kitaplar gösterilmektedir:");
        int counter=1;
        for(Book book : books) {
            System.out.println(counter + ". " + book);
            counter++;
        }
        return "";
    }

    @Override
    public void whoYouAre() {
        System.out.println("Ben bir okuyucuyum: " + getName());
    }



}
