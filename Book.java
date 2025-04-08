package LibraryManagementSystem;

import java.util.Date;

public class Book
{
    String bookName;
    String authorName;
    int bookId;
    boolean available=true;

    public Book(String bname,String authName,int bId)
    {
        bookName=bname;
        authorName=authName;
        bookId=bId;
    }
}