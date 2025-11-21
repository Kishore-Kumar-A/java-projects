import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library lib = new Library();
        Scanner sc = new Scanner(System.in);

        boolean running = true;
        while (running) {
            printMenu();
            String choice = sc.nextLine().trim();

            try {
                switch (choice) {
                    case "1": 
                        System.out.print("Book ID: ");
                        String bookId = sc.nextLine().trim();
                        System.out.print("Title: ");
                        String title = sc.nextLine().trim();
                        System.out.print("Author: ");
                        String author = sc.nextLine().trim();
                    
                        if (bookId.isEmpty() || title.isEmpty() || author.isEmpty()) {
                            System.out.println("All fields are required. Book not added.");
                            break;
                        }
                        if (lib.hasBook(bookId)) {
                            System.out.println("A book with this ID already exists. Choose a different ID.");
                            break;
                        }

                        Book b = new Book(bookId, title, author);
                        lib.addBook(b);
                        System.out.println("Book added.");
                        break;

                    case "2":
                        System.out.print("Member ID: ");
                        String memId = sc.nextLine().trim();
                        System.out.print("Name: ");
                        String name = sc.nextLine().trim();

                        if (memId.isEmpty() || name.isEmpty()) {
                            System.out.println("All fields are required. Member not added.");
                            break;
                        }
                        if (lib.hasMember(memId)) {
                            System.out.println("A member with this ID already exists. Choose a different ID.");
                            break;
                        }

                        Member m = new Member(memId, name);
                        lib.addMember(m);
                        System.out.println("Member added.");
                        break;

                    case "3": 
                        System.out.print("Book ID to issue: ");
                        String issueBookId = sc.nextLine().trim();
                        System.out.print("Member ID: ");
                        String issueMemId = sc.nextLine().trim();

                        try {
                            lib.issueBook(issueBookId, issueMemId);
                            System.out.println("Book issued successfully.");
                        } catch (BookNotAvailableException ex) {
                            System.out.println("Error: " + ex.getMessage());
                        }
                        break;

                    case "4":
                        System.out.print("Book ID to return: ");
                        String returnBookId = sc.nextLine().trim();
                        System.out.print("Member ID: ");
                        String returnMemId = sc.nextLine().trim();
                        System.out.print("Days late (0 if on time): ");

                        try {
                            String daysStr = sc.nextLine().trim();
                            int daysLate = Integer.parseInt(daysStr);
                            int fine = lib.returnBook(returnBookId, returnMemId, daysLate);
                            System.out.println("Book returned. Fine = ₹" + fine);
                        } catch (NumberFormatException nfe) {
                            System.out.println("Invalid number for days late. Return cancelled.");
                        } catch (InvalidReturnException ire) {
                            System.out.println("Error: " + ire.getMessage());
                        }
                        break;

                    case "5":
                        lib.showInventory();
                        break;

                    case "6":
                        running = false;
                        System.out.println("Exiting. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                        break;
                }
            } catch (Exception e) {
                lib.logOperation("Unexpected error in Main: " + e.getMessage());
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n=== Library Management ===");
        System.out.println("1. Add a book");
        System.out.println("2. Add a member");
        System.out.println("3. Issue a book");
        System.out.println("4. Return a book");
        System.out.println("5. Show inventory");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }
}
