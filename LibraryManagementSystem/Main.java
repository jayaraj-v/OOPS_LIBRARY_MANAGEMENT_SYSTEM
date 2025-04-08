package LibraryManagementSystem;

import java.util.Scanner;

public class Main
{
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        System.out.println("\t\tWelcome to the JJ Library!");
        while (true)
        {
            System.out.println("1.) LogIn as User");
            System.out.println("2.) LogIn as Librarian");
            System.out.println("3.) Exit");
            System.out.print("Enter your choice: ");
            int input = sc.nextInt();

            switch (input)
            {
                case 1:
                    User user = new User(sc);
                    user.logIn();
                    break;
                case 2:
                    Librarian lb = new Librarian(sc);
                    lb.logIn();
                    break;
                case 3:
                    System.out.println("Thank You for Visiting!");
                    return;
                default:
                    System.out.println("Invalid input. Please choose a valid option.");
            }
        }
    }
}
