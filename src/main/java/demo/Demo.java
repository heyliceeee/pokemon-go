package demo;

import java.util.Scanner;

public class Demo {
    static String value = "Hello World!";




    /**
     * Mostra o menu inicial
     */
    public static void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option=0;

        while (!exit) {
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

            System.out.println("select an option: ");
            option = scanner.nextInt();

            /**
             * Depois de selecionar a opcao do menu, faz o que pretende
             */
            switch (option)
            {
                case 1:
                    System.out.println("selected option 1");
                    break;

                case 2:
                    showPortalsManagementMenu();
                    break;

                case 3:
                    showConnectorsManagementMenu();
                    break;

                case 4:
                    showRoutesManagementMenu();
                    break;

                case 5:
                    showPlayersManagementMenu();
                    break;

                case 6:
                    showGameManagementMenu();
                    break;

                case 7:
                    System.out.println("selected option 7");
                    break;

                case 99:
                    System.out.println("exiting of the main menu...");
                    exit = true;
                    break;

                default:
                    System.out.println("invalid option, selected option between 1 and 7 or 99 to exit.");
                    break;
            }
        }
    }

    private static void showGameManagementMenu() {
    }

    private static void showPlayersManagementMenu() {
    }

    private static void showRoutesManagementMenu() {
    }

    /**
     * Mostra o menu acerca da gestao dos portais
     */
    public static void showPortalsManagementMenu()
    {

    }
    private static void showConnectorsManagementMenu()
    {
    }



    public static void main(String[] args) {
        showMainMenu();
    }

    public String greeting() {
        if (value.equals("")) {
            throw new IllegalArgumentException("vazio");
        }

        return value;
    }
}
