package LibraryManagementSystem;

import java.io.*;
import java.util.*;



public class User implements LogIn
{
    private static final String STUDENT_FILE = "data/StudentData.txt";
    private static final String TEACHER_FILE = "data/TeacherData.txt";
    private static final Map<String, String> userReg = new HashMap<>();
    private static Scanner sc;

    public User(Scanner sc) { User.sc = sc; }

    @Override
    public void logIn()
    {
        System.out.print("User type - Student or Teacher (s/t): ");
        String type = sc.next();

        if (type.equalsIgnoreCase("s")) new DataFetcher("st", userReg);
        else if (type.equalsIgnoreCase("t")) new DataFetcher("th", userReg);
        else
        {
            System.out.println("Invalid user type!");
            return;
        }

        System.out.print("Enter Username: ");
        String name = sc.next();
        System.out.print("Enter Password: ");
        String pass = sc.next();

        if (userReg.containsKey(name) && userReg.get(name).equals(pass))
        {
            System.out.println("Login successful!");
            doOperations();
        }
        else
        {
            System.out.println("Login failed. Want to sign up? (y/n): ");
            if (sc.next().equalsIgnoreCase("y")) putData(type.equals("s") ? STUDENT_FILE : TEACHER_FILE, type.equals("s") ? "Student" : "Teacher");
        }
    }

    private void doOperations()
    {
        System.out.println("1.) Borrow Book\n2.) Return Book");
        int choice = sc.nextInt();
        if (choice == 1) borrowBook();
        else if (choice == 2) returnBook();
    }

    private void putData(String filename, String userType)
    {
        System.out.print("Enter " + userType + " Name: ");
        String name = sc.next();
        System.out.print("Enter Password: ");
        String pass = sc.next();
        userReg.put(name, pass);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true)))
        {
            bw.write(name + "," + pass);
            bw.newLine();
        }
        catch (IOException e)
        {
            System.out.println("Error saving user data.");
        }
    }

    private void borrowBook()
    {
        System.out.print("Enter Book Name: ");
        String bookName = sc.next();
        System.out.print("Enter Author Name: ");
        String authorName = sc.next();

        if (Librarian.issueBook(bookName, authorName))
        {
            System.out.println("Book issued successfully!");
        }
        else
        {
            System.out.println("Book not available.");
        }
    }
    private static void returnBook()
    {
        System.out.print("Enter Book Name: ");
        String bookName = sc.next();
        System.out.print("Enter Author Name: ");
        String authorName = sc.next();
        Librarian.returnBook(bookName,authorName);
    }
}
