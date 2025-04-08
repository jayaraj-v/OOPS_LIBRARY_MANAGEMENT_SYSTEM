package LibraryManagementSystem;

public class Book
{
    String bookName;
    String authorName;
    int bookId;
    boolean available = true;

    public Book(String bookName, String authorName, int bookId)
    {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookId = bookId;
    }
}
