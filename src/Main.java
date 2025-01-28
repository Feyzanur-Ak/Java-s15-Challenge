import com.library.Person.*;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Set<Book> books = new HashSet<>();
        Set<Reader> readers = new HashSet<>();
        List<Author> authors = new ArrayList<>();
        Library library = new Library(books, readers);

        // Örnek kitaplar ve okuyucular
        Author author1 = new Author("George", "Orwell", "1", new ArrayList<>());
        Author author2 = new Author("J.K.", "Rowling", "2", new ArrayList<>());

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

        Reader reader1 = new Reader("John", "Doe");
        Reader reader2 = new Reader("Jane", "Smith");
        readers.add(reader1);
        readers.add(reader2);

        Librarian librarian = new Librarian("Alice", "Brown", "password123", new ArrayList<>());

        // Konsol uygulaması
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Kütüphane Sistemi ---");
            System.out.println("1. Yeni Kitap Ekle");
            System.out.println("2. Kitap Ara");
            System.out.println("3. Kitap Güncelle");
            System.out.println("4. Kitap Sil");
            System.out.println("5. Yazarın Kitaplarını Listele");
            System.out.println("6. Kitap Ödünç Al");
            System.out.println("7. Kitap İade Et");
            System.out.println("8. Kitapları Listele");
            System.out.println("9. Çıkış");
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
                    break;

                case 2:
                    // Kitap arama
                    System.out.print("Aramak istediğiniz kitabın adını girin: ");
                    String searchName = scanner.nextLine();
                    library.getBooks().stream()
                            .filter(book -> book.getName().equalsIgnoreCase(searchName))
                            .forEach(book -> System.out.println("Bulunan Kitap: " + book));
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

                        librarian.updateBook(bookToUpdate, newName, newAuthor, newPrice);
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
                        librarian.removeBook(new ArrayList<>(books), bookToDelete);
                        books.remove(bookToDelete);
                    } else {
                        System.out.println("Kitap bulunamadı.");
                    }
                    break;

                case 5:
                    // Yazarın kitaplarını listeleme
                    System.out.print("Yazarın adını girin: ");
                    String authorSearch = scanner.nextLine();

                    authors.stream()
                            .filter(author -> author.getName().equalsIgnoreCase(authorSearch))
                            .forEach(Author::showBook);
                    break;

                case 6:
                    // Kitap ödünç alma
                    System.out.print("Okuyucu Adı: ");
                    String readerName = scanner.nextLine();
                    System.out.print("Okuyucu Soyadı: ");
                    String readerSurname = scanner.nextLine();
                    System.out.print("Kitap ID: ");
                    long bookId = scanner.nextLong();
                    scanner.nextLine();

                    Reader borrowingReader = readers.stream()
                            .filter(reader -> reader.getName().equalsIgnoreCase(readerName) && reader.getSurname().equalsIgnoreCase(readerSurname))
                            .findFirst()
                            .orElse(null);

                    Book borrowingBook = books.stream()
                            .filter(book -> book.getId() == bookId)
                            .findFirst()
                            .orElse(null);

                    if (borrowingReader != null && borrowingBook != null) {
                        library.lend_book(borrowingBook, borrowingReader);
                    } else {
                        System.out.println("Okuyucu veya kitap bulunamadı.");
                    }
                    break;

                case 7:
                    // Kitap iade etme
                    System.out.print("İade etmek istediğiniz kitabın ID'sini girin: ");
                    long returnId = scanner.nextLong();
                    scanner.nextLine();

                    Book returningBook = books.stream()
                            .filter(book -> book.getId() == returnId)
                            .findFirst()
                            .orElse(null);

                    if (returningBook != null) {
                        library.take_back_book(returningBook);
                    } else {
                        System.out.println("Kitap bulunamadı.");
                    }
                    break;

                case 8:
                    // Kitapları listeleme
                    library.show_book();
                    break;

                case 9:
                    System.out.println("Çıkış yapılıyor...");
                    break;

                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
                    break;
            }
        } while (choice != 9);
    }
    }
