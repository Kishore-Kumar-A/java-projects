import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Library {
    private Map<String, Book> inventory;  
    private Map<String, Member> members;

    private static final String LOG_FILE = "library_log.txt";
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Library() {
        this.inventory = new HashMap<>();
        this.members = new HashMap<>();
    }

    public void addBook(Book book) {
        inventory.put(book.getId(), book);
        logOperation("Added book: " + book.toString());
    }

    public void addMember(Member member) {
        members.put(member.getMemberId(), member);
        logOperation("Added member: " + member.toString());
    }

    public void issueBook(String bookId, String memberId) throws BookNotAvailableException {
        Book book = inventory.get(bookId);
        if (book == null) {
            String msg = "Issue failed - Book not found: " + bookId;
            logOperation(msg);
            throw new BookNotAvailableException(msg);
        }
        if (book.isIssued()) {
            String msg = "Issue failed - Book already issued: " + bookId;
            logOperation(msg);
            throw new BookNotAvailableException(msg);
        }
        Member member = members.get(memberId);
        if (member == null) {
            String msg = "Issue failed - Member not found: " + memberId;
            logOperation(msg);
            throw new BookNotAvailableException(msg);
        }

        // perform issue
        book.setIssued(true);
        member.borrowBook(bookId);
        String msg = String.format("Issued book %s to member %s", bookId, memberId);
        logOperation(msg);
    }

    public int returnBook(String bookId, String memberId, int daysLate) throws InvalidReturnException {
        Book book = inventory.get(bookId);
        Member member = members.get(memberId);

        if (book == null) {
            String msg = "Return failed - Book not found: " + bookId;
            logOperation(msg);
            throw new InvalidReturnException(msg);
        }
        if (member == null) {
            String msg = "Return failed - Member not found: " + memberId;
            logOperation(msg);
            throw new InvalidReturnException(msg);
        }
        if (!member.getBorrowedBookIds().contains(bookId)) {
            String msg = String.format("Invalid return - Member %s did not borrow book %s", memberId, bookId);
            logOperation(msg);
            throw new InvalidReturnException(msg);
        }

        book.setIssued(false);
        member.returnBook(bookId);

        int fine = 0;
        if (daysLate > 0) {
            fine = 2 * daysLate;
        }
        String msg = String.format("Returned book %s from member %s, daysLate=%d, fine=%d",
                bookId, memberId, daysLate, fine);
        logOperation(msg);
        return fine;
    }

    public void showInventory() {
        System.out.println("----- Inventory -----");
        if (inventory.isEmpty()) {
            System.out.println("No books in inventory.");
        } else {
            for (Book b : inventory.values()) {
                System.out.println(b);
            }
        }
        System.out.println("---------------------");
    }

    public void logOperation(String message) {
        String timestamp = LocalDateTime.now().format(DTF);
        String line = String.format("[%s] %s", timestamp, message);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Logging error: " + e.getMessage());
        }
    }

    public boolean hasBook(String bookId) {
        return inventory.containsKey(bookId);
    }

    public boolean hasMember(String memberId) {
        return members.containsKey(memberId);
    }

    public Book getBook(String bookId) {
        return inventory.get(bookId);
    }

    public Member getMember(String memberId) {
        return members.get(memberId);
    }
}
