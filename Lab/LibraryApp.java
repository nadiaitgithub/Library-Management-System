import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "978-0061120084");
        Book book3 = new Book("1984", "George Orwell", "978-0451524935");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        
        User user = new User("Nadia");

        while (true) {
            System.out.println("Library Menu:");
            System.out.println("1. Browse Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                   
                    List<Book> availableBooks = library.getAvailableBooks();
                    for (Book book : availableBooks) {
                        System.out.println(book.getTitle() + " by " + book.getAuthor() + " (ISBN: " + book.getIsbn() + ")");
                    }
                    break;
                case 2:
                    
                    System.out.print("Enter the title of the book you want to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    List<Book> matchingBooks = library.searchByTitle(borrowTitle);
                    if (!matchingBooks.isEmpty()) {
                        Book bookToBorrow = matchingBooks.get(0);
                        user.borrowBook(bookToBorrow);
                        System.out.println("You have borrowed " + bookToBorrow.getTitle());
                    } else {
                        System.out.println("Book not found or not available for borrowing.");
                    }
                    break;
                case 3:
                    
                    System.out.print("Enter the title of the book you want to return: ");
                    String returnTitle = scanner.nextLine();
                    List<Book> borrowedBooks = user.getBorrowedBooks();
                    for (Book borrowedBook : borrowedBooks) {
                        if (borrowedBook.getTitle().equalsIgnoreCase(returnTitle)) {
                            user.returnBook(borrowedBook);
                            System.out.println("You have returned " + borrowedBook.getTitle());
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using the Library App. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
