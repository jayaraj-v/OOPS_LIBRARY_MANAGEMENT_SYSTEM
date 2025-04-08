package LibraryManagementSystem;

import java.io.*;
import java.util.*;

class Librarian implements LogIn
{
    public static List<Book> books=new ArrayList<>();
    private static final String lib_fileName="LibrarianData.txt";
    private static Map<String,String> lbMap=new HashMap<>();
    private static Scanner sc;
    public Librarian(Scanner sc)
    {
        this.sc=sc;
        new DataFetcher("lb",lbMap);
    }
    @Override
    public void logIn()
    {
        System.out.print("\nEnter the Librarian name: ");
        String name=sc.next();
        System.out.print("\nEnter the password : ");
        String pass=sc.next();
        boolean found=false;
        for(String kvPair:lbMap.keySet())
        {
            if(kvPair.equals(name))
            {
                if(pass.equals(lbMap.get(kvPair)))
                {
                    System.out.println("Loged in successfully as librarian!");
                    found=true;
                    break;
                }
            }
        }
        if(found) doOperations();
        else
        {
            System.out.println("Loged in failed. Check Librarian name and password");
            System.out.println("Want to signUp?(y/n) :");
            String signCheck=sc.next();
            if(signCheck.equalsIgnoreCase("y"))
            {
                putData();
            }
        }
    }
    private static void doOperations()
    {
        while(true)
        {
            System.out.println("1.) Add book!");
            System.out.println("2.) Search for Book!");
            System.out.println("3.) Show all the books");
            System.out.println("4.) Exit the librarian Authorization!");
            int input = sc.nextInt();
            sc.nextLine();
            if(input==4)
            {
                System.out.println("Exiting the librarian authorization!");
                break;
            }
            if (input == 1)
            {
                System.out.print("Enter the book name: ");
                String bookName = sc.nextLine();
                System.out.print("\nEnter the author name: ");
                String authNam = sc.nextLine();
                books.add(new Book(bookName, authNam, books.size() + 1));
            }
            if (input == 2)
            {
                System.out.println("Enter the Book name : ");
                String bookName = sc.nextLine();
                boolean found = false;
                for (Book book : books)
                {
                    if (book.bookName.equals(bookName) && book.available)
                    {
                        System.out.println("Book is available!");
                        found = true;
                        break;
                    }
                }
                if (!found) System.out.println("Book is not available!");
            }
            else if (input == 3) print();
        }
    }
    private static void putData()
    {
        System.out.print("Enter the librarian name : ");
        String name=sc.next();
        System.out.print("\nEnter the password :");
        String pass=sc.next();
        lbMap.put(name,pass);
        File file=new File(lib_fileName);
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(file)))
        {
            bw.write(name+","+pass);
            bw.newLine();
        }
        catch(IOException e)
        {
            System.out.println("Error in pushing data!");
        }
    }
    private static void print()
    {
        for(Book book:books) System.out.println("Book id : "+book.bookId+" | Book Name : "+book.bookName+" | Author name : "+book.authorName+" | Availability : "+book.available);
    }
    public static boolean bookIssueing(String bname,String aname)
    {
        for(Book book:books)
        {
            if(book.bookName.equalsIgnoreCase(bname) && book.authorName.equalsIgnoreCase(aname))
            {
                if(book.available)
                {
                    book.available=false;
                    return true;
                }
            }
        }
        return false;
    }
    public static void returnBook(String bname,String aname)
    {
        for(Book book:books)
        {
            if(book.bookName.equalsIgnoreCase(bname))
            {
                if(!book.available)
                {
                    System.out.println("Book returned successfuly!");
                    book.available=true;
                    return;
                }
            }
        }
        System.out.println("Book returned!");
        books.add(new Book(bname,aname,books.size()+1));
    }
}