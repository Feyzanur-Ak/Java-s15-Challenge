import com.library.Library.*;
import com.library.Library.Book.Book;
import com.library.Library.Member.Faculty;
import com.library.Library.Member.MemberRecord;
import com.library.Library.Person.Author;
import com.library.Library.Person.Librarian;
import com.library.Library.Person.Reader;
import com.library.Library.Member.Student;

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

        // Örnek okuyucular (Reader)
        MemberRecord record1 = new MemberRecord("İstanbul", "John Doe", 5, 0, LocalDate.now(), "Reader", 1L, "555-1234");
        MemberRecord record2 = new MemberRecord("Ankara", "Jane Smith", 5, 0, LocalDate.now(), "Reader", 2L, "555-5678");

        Reader reader1 = new Reader("John", "Doe", record1);
        Reader reader2 = new Reader("Jane", "Smith", record2);
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
            System.out.println("8. Kullanıcı Ekle (Student/Faculty)");
            System.out.println("9. Kullanıcıları Listele");
            System.out.println("10. Kullanıcı Kitap Limitini Kontrol Et");
            System.out.println("11. Kitap Satın Al");
            System.out.println("12. Çıkış");
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
                                .forEach(System.out::println); // metot referans
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
                    // Kitap güncelle
                    System.out.print("Güncellemek istediğiniz kitabın ID'sini girin: ");
                    long updateBookId = scanner.nextLong();
                    scanner.nextLine(); // Satır sonu karakterini temizle

                    Book bookToUpdate = books.stream() // burada stream kullanarak
                            .filter(book -> book.getId() == updateBookId)
                            .findFirst()
                            .orElse(null);

                    if (bookToUpdate != null) {
                        System.out.println("Kitap bulundu: " + bookToUpdate);

                        System.out.print("Yeni kitap adı (boş bırakılırsa değişmez): ");
                        String newName = scanner.nextLine();
                        if (!newName.isEmpty()) {
                            bookToUpdate.setName(newName);
                        }

                        System.out.print("Yeni yazar adı (boş bırakılırsa değişmez): ");
                        String newAuthor = scanner.nextLine();
                        if (!newAuthor.isEmpty()) {
                            bookToUpdate.setAuthor(newAuthor);
                        }

                        System.out.print("Yeni fiyat (boş bırakılırsa değişmez): ");
                        String newPrice = scanner.nextLine();
                        if (!newPrice.isEmpty()) {
                            bookToUpdate.setPrice(Double.parseDouble(newPrice));
                        }

                        System.out.println("Kitap başarıyla güncellendi: " + bookToUpdate);
                    } else {
                        System.out.println("Kitap bulunamadı.");
                    }
                    break;

                case 4:
                    // Kitap sil
                    System.out.print("Silmek istediğiniz kitabın ID'sini girin: ");
                    long deleteBookId = scanner.nextLong();
                    scanner.nextLine(); // Satır sonu karakterini temizle

                    Book bookToDelete = books.stream()
                            .filter(book -> book.getId() == deleteBookId)
                            .findFirst()
                            .orElse(null);

                    if (bookToDelete != null) {
                        books.remove(bookToDelete);
                        System.out.println("Kitap başarıyla silindi: " + bookToDelete);
                    } else {
                        System.out.println("Kitap bulunamadı.");
                    }
                    break;

                case 5:
                    // Yazara göre kitapları listele
                    System.out.print("Listelemek istediğiniz yazarın adını girin: ");
                    String authorToSearch = scanner.nextLine();

                    List<Book> booksByAuthor = books.stream()
                            .filter(book -> book.getAuthor().equalsIgnoreCase(authorToSearch))
                            .toList();

                    if (!booksByAuthor.isEmpty()) {
                        System.out.println("Yazarın kitapları:");
                        booksByAuthor.forEach(System.out::println);
                    } else {
                        System.out.println("Bu yazara ait kitap bulunamadı.");
                    }
                    break;

                case 6:
                    // Kitap ödünç alma
                    Reader borrowingReader = verifyMember(scanner, readers);

                    if (borrowingReader.getBorrowedBooks().size() >= borrowingReader.getMaxBookLimit()) {
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
                        // Kitap ödünç alınıyor
                        borrowingReader.borrowBook(borrowingBook);
                        borrowingBook.setStatus("borrowed"); // Kitap durumu güncelleniyor

                        // Fatura oluşturuluyor
                        librarian.createBill(readers, borrowingReader.getName(), borrowingReader.getSurname(), borrowingBook.getPrice());

                        System.out.println(borrowingBook.getName() + " kitabı başarıyla ödünç alındı.");
                    } else {
                        System.out.println("Kitap mevcut değil ya da başka bir kullanıcı tarafından ödünç alınmış.");
                    }
                    break;

                case 7:
                    // Kitap iade etme
                    System.out.print("İade işlemi için üye adını girin: ");
                    String memberName = scanner.nextLine();
                    System.out.print("Üye soyadını girin: ");
                    String memberSurname = scanner.nextLine();

// Üye doğrulama
                    Reader returningReader = readers.stream()
                            .filter(reader -> reader.getName().equalsIgnoreCase(memberName) && reader.getSurname().equalsIgnoreCase(memberSurname))
                            .findFirst()
                            .orElse(null);

                    if (returningReader != null) {
                        System.out.print("İade etmek istediğiniz kitabın adını girin: ");
                        String bookNameToReturn = scanner.nextLine();

                        Book bookToReturn = returningReader.getBorrowedBooks().stream()
                                .filter(book -> book.getName().equalsIgnoreCase(bookNameToReturn))
                                .findFirst()
                                .orElse(null);

                        if (bookToReturn != null) {
                            // Kitap iade ediliyor
                            returningReader.returnBook(bookToReturn);
                            bookToReturn.setStatus("available"); // Kitap durumu güncelleniyor

                            // Kullanıcıya ücret iadesi yapılıyor
                            System.out.println("İade işlemi tamamlandı. " + bookToReturn.getPrice() + " TL kullanıcıya iade edildi.");
                        } else {
                            System.out.println("Bu isimde bir kitap, bu kullanıcı tarafından ödünç alınmamış.");
                        }
                    } else {
                        System.out.println("Üye bulunamadı. Lütfen doğru bilgileri girdiğinizden emin olun.");
                    }
                    break;
                case 8:
                    // Yeni kullanıcı ekleme
                    System.out.println("Kullanıcı türünü seçin: 1. Student, 2. Faculty");
                    int userType = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Ad: ");
                    String userName = scanner.nextLine();
                    System.out.print("Soyad: ");
                    String userSurname = scanner.nextLine();
                    System.out.print("Adres: ");
                    String address = scanner.nextLine();
                    System.out.print("Telefon: ");
                    String phoneNo = scanner.nextLine();

                    if (userType == 1) {
                        // Öğrenci ekleme
                        MemberRecord studentRecord = new MemberRecord(address, userName + " " + userSurname, 5, 0, LocalDate.now(), "Student", System.currentTimeMillis(), phoneNo);
                        Student newStudent = new Student(address, userName, 5, 0, LocalDate.now(), "Student", System.currentTimeMillis(), phoneNo, "STU123", "Bilgisayar Mühendisliği");
                        readers.add(new Reader(userName, userSurname, studentRecord)); // Reader olarak ekleniyor
                        System.out.println("Yeni öğrenci başarıyla eklendi: " + newStudent.getName());
                    } else if (userType == 2) {
                        // Akademisyen ekleme
                        MemberRecord facultyRecord = new MemberRecord(address, userName + " " + userSurname, 10, 0, LocalDate.now(), "Faculty", System.currentTimeMillis(), phoneNo);
                        Faculty newFaculty = new Faculty(address, userName, 10, 0, LocalDate.now(), "Faculty", System.currentTimeMillis(), phoneNo, "FAC123", "Fizik");
                        readers.add(new Reader(userName, userSurname, facultyRecord)); // Reader olarak ekleniyor
                        System.out.println("Yeni akademisyen başarıyla eklendi: " + newFaculty.getName());
                    } else {
                        System.out.println("Geçersiz kullanıcı türü.");
                    }
                    break;

                case 9:
                    // Kullanıcıları listele
                    System.out.println("Kayıtlı kullanıcılar:");
                    readers.forEach(reader -> {
                        System.out.println(reader.getName() + " " + reader.getSurname() + " - " + reader.getMemberRecord().getType());
                    });
                    break;

                case 10:
                    // Kullanıcı kitap limitini kontrol et
                    Reader readerToCheck = verifyMember(scanner, readers); // Kullanıcı doğrulama (zaten mevcut bir kullanıcı ya da yeni kullanıcı ekleme)

                    System.out.println("Kullanıcı: " + readerToCheck.getName() + " " + readerToCheck.getSurname());
                    System.out.println("Mevcut ödünç alınan kitap sayısı: " + readerToCheck.getBorrowedBooks().size());
                    System.out.println("Maksimum kitap limiti: " + readerToCheck.getMaxBookLimit());
                    break;

                case 11:
                    // Kitap satın alma
                    Reader purchasingReader = verifyMember(scanner, readers);

                    System.out.print("Satın almak istediğiniz kitabın ID'sini girin: ");
                    long purchaseBookId = scanner.nextLong();
                    scanner.nextLine();

                    Book bookToPurchase = books.stream()
                            .filter(book -> book.getId() == purchaseBookId)
                            .findFirst()
                            .orElse(null);

                    if (bookToPurchase != null) {
                        purchasingReader.purchaseBook(bookToPurchase); // Reader sınıfındaki purchaseBook metodu çağrılıyor
                    } else {
                        System.out.println("Bu ID'ye sahip bir kitap bulunamadı.");
                    }
                    break;

                case 12:
                    System.out.println("Çıkış yapılıyor...");
                    break;

                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
                    break;
            }
        } while (choice != 12);
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
                    System.out.print("Adres: ");
                    String address = scanner.nextLine();
                    System.out.print("Telefon: ");
                    String phoneNo = scanner.nextLine();

                    MemberRecord newRecord = new MemberRecord(address, name + " " + surname, 5, 0, LocalDate.now(), "Reader", System.currentTimeMillis(), phoneNo);
                    Reader newReader = new Reader(name, surname, newRecord);
                    readers.add(newReader);
                    System.out.println("Kullanıcı sisteme eklendi: " + name + " " + surname);
                    return newReader;
                });
    }
}
