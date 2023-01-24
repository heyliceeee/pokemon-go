package demo;

import java.util.Scanner;

public class Demo
{
    static String value = "Hello World!";

    /**
     * Mostra o menu inicial
     */
    public static void showMenu()
    {
        System.out.println("\n");
        System.out.println("+--------------------------------------+");
        System.out.println("|                 MENU                 |");
        System.out.println("+--------------------------------------+");
        System.out.println(
                        "| 01. Import JSON file                 |\n" +
                        "| 02. Portals management               |\n" +
                        "| 03. Connectors management            |\n" +
                        "| 04. Routes management                |\n" +
                        "| 05. Players management               |\n" +
                        "| 06. Game management                  |\n" +
                        "| 07. Export JSON file                 |\n" +
                        "| 99. Exit                             |"
        );
        System.out.println("+--------------------------------------+\n\n");
    }


    /**
     * Depois de selecionar a opcao do menu inicial, faz o que e pretendido
     * @param option e a opcao selecionada do menu inicial
     */
    public static void chooseOption(int option)
    {
        switch (option)
        {
            case 1:
                System.out.println("selected option 1");
                break;

            case 12:
                break;
            default:
        }
    }

    public static void main(String[] args)
    {
        int option=0;

        Scanner scanner = new Scanner(System.in);

        showMenu();

        /*do
        {
            showMenu();

            try
            {
                option = scanner.nextInt();
            } catch (Exception e)
            {
                System.out.println("ERROR: "+e.getMessage());
            }

            chooseOption(option);

        } while (option != 99);*/
    }

    public String greeting() {
        if(value.equals(""))
        {
            throw new IllegalArgumentException("vazio");
        }

        return value;
    }
}
