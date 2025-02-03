import com.library.Person.*;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Kütüphane için başlangıç verileri
        Set<Book> books = new HashSet<>();
        Set<Reader> readers = new HashSet<>();
        List<Author> authors = new ArrayList<>();
        Library library = new Library(books, readers);

        // Örnek yazarlar ve kitaplar
        Author author1 = new Author("George", "Orwell");
        Author author2 = new Author("J.K.", "Rowling");

        Book book1 = new Book(1, "1984", 50.0, "George Orwell", "available", "1st", LocalDate.now(), null);
        Book book2 = new Book(2, "Animal Farm", 40.0, "George Orwell", "available", "1st", LocalDate.now(), null);
        Book book3 = new Book(3, "Harry Potter", 100.0, "J.K. Rowling", "available", "1st", LocalDate.now(), null);

        author1.newBook(book1);
        author1.newBook(book2);
        author2.newBook(book3);

        books.add(book1);
        books.add(book2);
        books.add(book3);

        authors.add(author1);
        authors.add(author2);

        // Örnek okuyucular
        Reader reader1 = new Reader("John", "Doe");
        Reader reader2 = new Reader("Jane", "Smith");
        readers.add(reader1);
        readers.add(reader2);

        // Örnek kütüphaneci
        Librarian librarian = new Librarian("Alice", "Brown", "password123");

        // Konsol uygulaması
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Ana menü
            System.out.println("\n--- Kütüphane Sistemi ---");
            System.out.println("1. Yeni Kitap Ekle");
            System.out.println("2. Kitap Ara (ID, İsim veya Yazar)");
            System.out.println("3. Kitap Güncelle");
            System.out.println("4. Kitap Sil");
            System.out.println("5. Yazara Göre Kitapları Listele");
            System.out.println("6. Kitap Ödünç Al");
            System.out.println("7. Kitap İade Et");
            System.out.println("8. Kitapları Listele");
            System.out.println("9. Kitap Satın Al");
            System.out.println("10. Kullanıcı Kitap Limitini Kontrol Et");
            System.out.println("11. Çıkış");
            System.out.print("Seçiminizi yapın: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Satır sonu karakterini temizle

            switch (choice) {
                case 1:
                    // Yeni kitap ekleme
                    System.out.print("Kitap Adı: ");
                    String name = scanner.nextLine();
                    System.out.print("Yazar Adı: ");
                    String authorName = scanner.nextLine();
                    System.out.print("Fiyat: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Baskı: ");
                    String edition = scanner.nextLine();

                    Book newBook = new Book(books.size() + 1, name, price, authorName, "available", edition, LocalDate.now(), null);
                    library.new_book(newBook);
                    System.out.println("Yeni kitap başarıyla eklendi.");
                    break;

                case 2:
                    // Kitap arama
                    System.out.println("Arama türünü seçin: 1. ID, 2. İsim, 3. Yazar");
                    int searchType = scanner.nextInt();
                    scanner.nextLine();

                    if (searchType == 1) {
                        System.out.print("Kitap ID: ");
                        long id = scanner.nextLong();
                        scanner.nextLine();
                        books.stream()
                                .filter(book -> book.getId() == id)
                                .forEach(System.out::println);
                    } else if (searchType == 2) {
                        System.out.print("Kitap Adı: ");
                        String searchName = scanner.nextLine();
                        books.stream()
                                .filter(book -> book.getName().equalsIgnoreCase(searchName))
                                .forEach(System.out::println);
                    } else if (searchType == 3) {
                        System.out.print("Yazar Adı: ");
                        String searchAuthor = scanner.nextLine();
                        books.stream()
                                .filter(book -> book.getAuthor().equalsIgnoreCase(searchAuthor))
                                .forEach(System.out::println);
                    } else {
                        System.out.println("Geçersiz seçim.");
                    }
                    break;

                case 3:
                    // Kitap güncelleme
                    System.out.print("Güncellemek istediğiniz kitabın ID'sini girin: ");
                    long updateId = scanner.nextLong();
                    scanner.nextLine();

                    Book bookToUpdate = books.stream()
                            .filter(book -> book.getId() == updateId)
                            .findFirst()
                            .orElse(null);

                    if (bookToUpdate != null) {
                        System.out.print("Yeni Ad: ");
                        String newName = scanner.nextLine();
                        System.out.print("Yeni Yazar: ");
                        String newAuthor = scanner.nextLine();
                        System.out.print("Yeni Fiyat: ");
                        double newPrice = scanner.nextDouble();
                        scanner.nextLine();

                        librarian.updateBook(library, bookToUpdate, newName, newAuthor, newPrice);
                        System.out.println("Kitap başarıyla güncellendi.");
                    } else {
                        System.out.println("Kitap bulunamadı.");
                    }
                    break;

                case 4:
                    // Kitap silme
                    System.out.print("Silmek istediğiniz kitabın ID'sini girin: ");
                    long deleteId = scanner.nextLong();
                    scanner.nextLine();

                    Book bookToDelete = books.stream()
                            .filter(book -> book.getId() == deleteId)
                            .findFirst()
                            .orElse(null);

                    if (bookToDelete != null) {
                        librarian.removeBook(library, bookToDelete);
                        System.out.println("Kitap başarıyla silindi.");
                    } else {
                        System.out.println("Kitap bulunamadı.");
                    }
                    break;

                case 5:
                    // Yazara göre kitapları listeleme
                    System.out.print("Yazar Adı: ");
                    String authorSearch = scanner.nextLine();
                    authors.stream()
                            .filter(author -> author.getName().equalsIgnoreCase(authorSearch))
                            .forEach(Author::showBook);
                    break;

                case 6:
                    // Kitap ödünç alma
                    Reader borrowingReader = verifyMember(scanner, readers);

                    if (borrowingReader.getBorrowedBooks().size() >= 5) {
                        System.out.println("Kitap limitine ulaştınız! Daha fazla kitap ödünç alamazsınız.");
                        break;
                    }

                    System.out.print("Kitap ID: ");
                    long bookId = scanner.nextLong();
                    scanner.nextLine();

                    Book borrowingBook = books.stream()
                            .filter(book -> book.getId() == bookId && book.getStatus().equals("available"))
                            .findFirst()
                            .orElse(null);

                    if (borrowingBook != null) {
                        borrowingBook.setStatus("borrowed");
                        borrowingBook.setOwner(borrowingReader);
                        borrowingReader.getBorrowedBooks().add(borrowingBook);
                        System.out.println("Kitap başarıyla ödünç alındı: " + borrowingBook.getName());
                        System.out.println("Fatura: " + borrowingBook.getPrice() + " TL");
                    } else {
                        System.out.println("Kitap mevcut değil ya da başka bir kullanıcı tarafından ödünç alınmış.");
                    }
                    break;

                case 7:
                    // Kitap iade etme
                    System.out.print("Adınızı girin: ");
                    String returnerName = scanner.nextLine();
                    System.out.print("Soyadınızı girin: ");
                    String returnerSurname = scanner.nextLine();

                    Reader returner = readers.stream()
                            .filter(reader -> reader.getName().equalsIgnoreCase(returnerName) && reader.getSurname().equalsIgnoreCase(returnerSurname))
                            .findFirst()
                            .orElse(null);

                    if (returner == null) {
                        System.out.println("Kullanıcı bulunamadı.");
                        break;
                    }

                    System.out.print("İade etmek istediğiniz kitabın ID'sini girin: ");
                    long returnBookId = scanner.nextLong();
                    scanner.nextLine();

                    Book bookToReturn = returner.getBorrowedBooks().stream()
                            .filter(book -> book.getId() == returnBookId)
                            .findFirst()
                            .orElse(null);

                    if (bookToReturn == null) {
                        System.out.println("Bu kitabı iade edemezsiniz çünkü size ait değil.");
                    } else {
                        bookToReturn.setStatus("available");
                        bookToReturn.setOwner(null);
                        returner.getBorrowedBooks().remove(bookToReturn);
                        System.out.println("Kitap başarıyla iade edildi: " + bookToReturn.getName());
                        System.out.println("Ücret iade edildi: " + bookToReturn.getPrice() + " TL");
                    }
                    break;

                case 10:
                    // Kullanıcı kitap limitini kontrol etme
                    System.out.print("Adınızı girin: ");
                    String userName = scanner.nextLine();
                    System.out.print("Soyadınızı girin: ");
                    String userSurname = scanner.nextLine();

                    Reader user = readers.stream()
                            .filter(reader -> reader.getName().equalsIgnoreCase(userName) && reader.getSurname().equalsIgnoreCase(userSurname))
                            .findFirst()
                            .orElse(null);

                    if (user == null) {
                        System.out.println("Kullanıcı bulunamadı.");
                    } else {
                        int borrowedCount = user.getBorrowedBooks().size();
                        System.out.println("Şu anda ödünç aldığınız kitap sayısı: " + borrowedCount);
                        if (borrowedCount >= 5) {
                            System.out.println("Kitap limitine ulaştınız! Daha fazla kitap ödünç alamazsınız.");
                        } else {
                            System.out.println("Daha fazla kitap ödünç alabilirsiniz. Kalan limit: " + (5 - borrowedCount));
                        }
                    }
                    break;

                case 11:
                    System.out.println("Çıkış yapılıyor...");
                    break;

                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
                    break;
            }
        } while (choice != 11);
    }

    // Kullanıcı doğrulama (Verify Member) metodu
    private static Reader verifyMember(Scanner scanner, Set<Reader> readers) {
        System.out.print("Adınızı girin: ");
        String name = scanner.nextLine();
        System.out.print("Soyadınızı girin: ");
        String surname = scanner.nextLine();

        return readers.stream()
                .filter(reader -> reader.getName().equalsIgnoreCase(name) && reader.getSurname().equalsIgnoreCase(surname))
                .findFirst()
                .orElseGet(() -> {
                    Reader newReader = new Reader(name, surname);
                    readers.add(newReader);
                    System.out.println("Kullanıcı sisteme eklendi: " + name + " " + surname);
                    return newReader;
                });
    }
}
