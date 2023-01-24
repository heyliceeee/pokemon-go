package demo;

import java.util.Scanner;

public class Demo
{
    static String value = "Hello World!";

    public static void showMenu()
    {
        System.out.println("\n");
        System.out.println("+--------------------------------------+");
        System.out.println("|                 MENU                 |");
        System.out.println("+--------------------------------------+");
        System.out.println(
                "| 01. a |\n" +
                        "| 02. b |\n" +
                        "| 03. c |\n" +
                        "| 04. d |\n" +
                        "| 05. e |\n" +
                        "| 51. f |\n" +
                        "| 99. Exit                            |"
        );
        System.out.println("+--------------------------------------+\n\n");
    }

    public static void main(String[] args)
    {
        int option=0;

        Scanner scanner = new Scanner(System.in);

        do
        {
            showMenu();

            try
            {
                option = scanner.nextInt();
            } catch (Exception e)
            {
                System.out.println("ERROR: "+e.getMessage());
            }
        } while (option != 99);
    }

    public String greeting() {
        if(value.equals(""))
        {
            throw new IllegalArgumentException("vazio");
        }

        return value;
    }
}
