import java.util.ArrayList;
import java.util.List;

public class Member {
    private String memberId;           
    private String name;             
    private List<String> borrowedBookIds;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBookIds = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public List<String> getBorrowedBookIds() {
        return borrowedBookIds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void borrowBook(String bookId) {
        if (!borrowedBookIds.contains(bookId)) {
            borrowedBookIds.add(bookId);
        }
    }
    public void returnBook(String bookId) {
        borrowedBookIds.remove(bookId);
    }

    @Override
    public String toString() {
        return String.format("Member[id=%s, name=%s, borrowed=%s]",
                memberId, name, borrowedBookIds);
    }
}
