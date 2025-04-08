package LibraryManagementSystem;

import java.io.*;
import java.util.*;

public class Librarian implements LogIn
{
    public static List<Book> books = new ArrayList<>();
    private static final String lib_fileName = "data/LibrarianData.txt";
    private static final Map<String, String> lbMap = new HashMap<>();
    private static Scanner sc;

    public Librarian(Scanner sc)
    {
        Librarian.sc = sc;
        new DataFetcher("lb", lbMap);
    }

    @Override
    public void logIn()
    {
        System.out.print("Enter the Librarian name: ");
        String name = sc.next();
        System.out.print("Enter the password: ");
        String pass = sc.next();

        if (lbMap.containsKey(name) && lbMap.get(name).equals(pass))
        {
            System.out.println("Logged in successfully as Librarian!");
            doOperations();
        }
        else
        {
            System.out.println("Login failed. Want to sign up? (y/n): ");
            if (sc.next().equalsIgnoreCase("y")) putData();
        }
    }

    private void doOperations()
    {
        while (true)
        {
            System.out.println("1.) Add Book");
            System.out.println("2.) Search Book");
            System.out.println("3.) Show All Books");
            System.out.println("4.) Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice)
            {
                case 1 -> addBook();
                case 2 -> searchBook();
                case 3 -> printBooks();
                case 4 ->
                {
                    System.out.println("Exiting Librarian Menu.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void addBook()
    {
        System.out.print("Enter Book Name: ");
        String bookName = sc.nextLine();
        System.out.print("Enter Author Name: ");
        String authorName = sc.nextLine();
        books.add(new Book(bookName, authorName, books.size() + 1));
    }

    private void searchBook()
    {
        System.out.print("Enter Book Name to Search: ");
        String bookName = sc.nextLine();
        boolean found = books.stream().anyMatch(book -> book.bookName.equalsIgnoreCase(bookName) && book.available);
        System.out.println(found ? "Book is available!" : "Book is not available!");
    }

    private void printBooks()
    {
        for (Book book : books)
        {
            System.out.printf("ID: %d | Title: %s | Author: %s | Available: %s\n",
                    book.bookId, book.bookName, book.authorName, book.available);
        }
    }

    private void putData()
    {
        System.out.print("Enter Librarian Name: ");
        String name = sc.next();
        System.out.print("Enter Password: ");
        String pass = sc.next();
        lbMap.put(name, pass);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(lib_fileName, true)))
        {
            bw.write(name + "," + pass);
            bw.newLine();
        }
        catch (IOException e)
        {
            System.out.println("Error saving librarian data.");
        }
    }

    public static boolean issueBook(String bookName, String authorName)
    {
        for (Book book : books)
        {
            if (book.bookName.equalsIgnoreCase(bookName) && book.authorName.equalsIgnoreCase(authorName) && book.available)
            {
                book.available = false;
                return true;
            }
        }
        return false;
    }

    public static void returnBook(String bookName, String authorName)
    {
        for (Book book : books)
        {
            if (book.bookName.equalsIgnoreCase(bookName))
            {
                book.available = true;
                System.out.println("Book returned successfully!");
                return;
            }
        }
        books.add(new Book(bookName, authorName, books.size() + 1));
        System.out.println("Book added as new on return!");
    }
}