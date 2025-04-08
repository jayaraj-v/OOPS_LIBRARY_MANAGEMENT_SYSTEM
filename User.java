package LibraryManagementSystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class User implements LogIn
{

    private static final String stud_fileName="StudentData.txt";
    private static final String teach_fileName="teacherData.txt";
    static Map<String,String> userReg=new HashMap<>();
    static Scanner sc;
    public User(Scanner sc)
    {
        this.sc=sc;
    }
    @Override
    public void logIn()
    {
        System.out.println("Type of user Student or Teacher (s/t) : ");
        String in=sc.next();
        if(in.equalsIgnoreCase("s")) new DataFetcher("st",userReg);
        else if(in.equalsIgnoreCase("t")) new DataFetcher("th",userReg);
        else
        {
            System.out.println("Invalid choice of user selection!");
            logIn();
            return;
        }
        System.out.println("Enter the User name : ");
        String name=sc.next();
        System.out.println("Enter the password to log in : ");
        String pass=sc.next();
        boolean found=false;
        for(String kvPair:userReg.keySet())
        {
            if(kvPair.equals(name))
            {
                if(pass.equals(userReg.get(kvPair)))
                {
                    System.out.println("Loged in successfully!");
                    found=true;
                    break;
                }
            }
        }
        if(found) doOperations();
        else
        {
            System.out.println("Loged in failed. Check user name and password");
            System.out.println("Want to signUp?(y/n) :");
            String signCheck=sc.next();
            if(signCheck.equalsIgnoreCase("y"))
            {
                if(in.equalsIgnoreCase("s")) putData(stud_fileName,"Student");
                else putData(teach_fileName,"Teacher");
            }
        }
    }
    private static void doOperations()
    {
        System.out.println("1.) Borrow the book\n2.) Returning the book");
        int in=sc.nextInt();
        switch (in)
        {
            case 1:
                borrowBook();
                break;
            case 2:
                returnBook();
                break;
        }
    }
    private static void putData(String filename,String in)
    {
        System.out.print("Enter the "+in+" name : ");
        String name=sc.next();
        System.out.print("\nEnter the password :");
        String pass=sc.next();
        userReg.put(name,pass);
        File file=new File(filename);
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
    private static void borrowBook()
    {
        System.out.println("Enter the book name :");
        String bname=sc.next();
        System.out.println("For more referal enter the author name : ");
        String aname=sc.next();
        boolean issued=Librarian.bookIssueing(bname,aname);
        if(issued) System.out.println("Book issued!");
        else System.out.println("Book is not available");
    }
    private static void returnBook()
    {
        System.out.println("Enter the book name you want to return :");
        String bname=sc.next();
        System.out.println("Enter the author name of the book : ");
        String aname=sc.next();
        Librarian.returnBook(bname,aname);
    }
}