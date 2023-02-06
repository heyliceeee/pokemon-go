package demo;

import api.implementation.ImporterExporterJson;
import api.implementation.Local;
import api.implementation.Root;
import api.implementation.Route;
import api.interfaces.ILocal;
import api.interfaces.IPortal;
import api.interfaces.IRoot;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class Demo {
    static String value = "Hello World!";
    static ImporterExporterJson iEJson = new ImporterExporterJson();
    static IRoot root = new Root();
    static ILocal local = new Local(0, "", 0, null);
    public static String playerName = "";


    /**
     * Mostra o menu inicial
     */
    public static void showMainMenu() throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;

        while (!exit) {
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|                 MENU                 |");
            System.out.println("+--------------------------------------+");
            System.out.println("select an option:                       ");
            System.out.println("+--------------------------------------+");
            System.out.println(
                            "| 01. Game                             |\n" +
                            "| 02. API                              |\n" +
                            "| 99. Exit                             |"
            );
            System.out.println("+--------------------------------------+\n\n");

            option = scanner.nextInt();

            /**
             * Depois de selecionar a opcao do menu, faz o que pretende
             */
            switch (option) {
                case 1:
                    showGameMenu();
                    break;

                case 2:
                    showAPIMenu();
                    break;

                case 99:
                    System.out.println("exiting of the main menu...");
                    exit = true;
                    break;

                default:
                    System.out.println("invalid option, selected option between 1 and 2 or 99 to exit.");
                    break;
            }
        }
    }

    private static void showGameMenu() throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option=0;
        String name = "";

        while (!exit)
        {
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|                 GAME                 |");
            System.out.println("+--------------------------------------+");
            System.out.println("select an option:                       ");
            System.out.println("+--------------------------------------+");
            System.out.println(
                            "| 01. Start the game                   |\n" +
                            "| 99. Back to previous menu            |"
            );
            System.out.println("+--------------------------------------+\n\n");

            option = scanner.nextInt();

            switch (option)
            {
                case 1:
                    System.out.println("\n");
                    System.out.println("+--------------------------------------+");
                    System.out.println("|                 GAME                 |");
                    System.out.println("+--------------------------------------+");
                    System.out.println(
                                    "| Enter your name:                     |"
                    );
                    System.out.println("+--------------------------------------+\n\n");

                    scanner.nextLine();
                    playerName = scanner.nextLine();

                    iEJson.importFromJSONFile(root, local, "docs/import/import.json");//importar ficheiro JSON os jogadores

                    //verificar se escreveu um nome ou pretende sair do jogo
                    if(root.getPlayerByName(playerName) != null) //se existe o jogador
                    {
                        runGameFirst(); //corre o jogo como se fosse a 1ªvez
                    }
                    else
                    {
                        System.out.println("this player doesn´t exists.");
                        exit = true;
                        break;
                    }
                break;

                case 99:
                    exit = true;
                    break;

                default:
                    System.out.println("invalid option, selected option 1 or 99 to exit.");
                    break;
            }
        }
    }

    private static void runGameFirst()
    {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;

        System.out.println("\n");
        System.out.println("+----------------------------------------------+");
        System.out.println("| \tWELCOME BACK "+playerName+ "\t                   |");
        System.out.println("+----------------------------------------------+");
        System.out.println("select a location type where you want to start: ");
        System.out.println("+----------------------------------------------+");
        System.out.println(
                           "| 01. Portal                                   |\n" +
                           "| 02. Connector                                |\n" +
                           "| 99. Back to previous menu                    |"
        );
        System.out.println("+----------------------------------------------+\n\n");

        option = scanner.nextInt();

        /**
         * Depois de selecionar a opcao do menu, faz o que pretende
         */
        switch (option) {
            case 1:
                System.out.println("PORTAL A: "+ root.getRandomPortal()); //retorna um portal(ponto A) random

                String teamPlayer = root.getPlayerByName(playerName).getTeam();//obter equipa do jogador atual

                //verificar se o portal é da "sua equipa", "equipa adversária" ou "sem equipa"
                if(root.getRandomPortal().getOwnership().getState().equals(teamPlayer)) //se a equipa que conquistou o portal é a mesma equipa do jogador
                {
                    //mostrar interações do portal
                    showPortalYourTeamInteractionsMenu(root.getRandomPortal());
                }
                else if(root.getRandomPortal().getOwnership().getState().equals("No team")) //se nenhuma equipa conquistou o portal
                {
                    //mostrar interações do portal
                    showPortalTeamOpponentInteractionsMenu(root.getRandomPortal());
                }
                else //se a equipa que conquistou o portal é a equipa adversária do jogador
                {
                    //mostrar interações do portal
                    showPortalNoTeamInteractionsMenu(root.getRandomPortal());
                }

                //após determinado comportamento, pergunta onde quer ir (runGameSecond)
                //portal -> portal(ponto B) (mais proximo)
                //portal -> connector(ponto B) (mais proximo)
                //portal -> connector sem cooldown (mais proximo) -> portal(ponto B) (mais proximo) (portal mas com necessidade de passar um local para recarregar)
                //portal -> connector sem cooldown (mais proximo) -> connector(ponto B) (mais proximo) (connector mas com necessidade de passar um local para recarregar)
                //portal -> portal (mais proximo) -> portal(ponto B) (mais proximo) (passa apenas por portals)
                break;

            case 2:
                System.out.println("CONNECTOR A: "+ root.getRandomConnector());//retorna um connector(ponto A) random

                //mostrar interações do connector

                //após determinado comportamento, pergunta onde quer ir (runGameSecond)
                //connector -> connector(ponto B) (mais proximo)
                //connector -> portal(ponto B) (mais proximo)
                //connector -> connector sem cooldown (mais proximo) -> connector(ponto B) (mais proximo) (connector mas com necessidade de passar um local para recarregar)
                //connector -> connector sem cooldown (mais proximo) -> portal(ponto B) (mais proximo) (portal mas com necessidade de passar um local para recarregar)
                //connector -> connector (mais proximo) -> connector(ponto B) (mais proximo) (passa apenas por connectors)
                break;

            case 99:
                exit = true;
                break;

            default:
                System.out.println("invalid option, selected option between 1 and 2 or 99 to exit.");
                break;
        }
    }

    /**
     * Mostra o menu acerca das interações do portal da equipa adversária
     * @param randomPortal portal em questão
     */
    private static void showPortalTeamOpponentInteractionsMenu(IPortal randomPortal)
    {
    }

    /**
     * Mostra o menu acerca das interações do portal da sua equipa
     * @param randomPortal portal em questão
     */
    private static void showPortalYourTeamInteractionsMenu(IPortal randomPortal)
    {
    }

    /**
     * Mostra o menu acerca das interações do portal sem equipa
     * @param randomPortal portal em questão
     */
    private static void showPortalNoTeamInteractionsMenu(IPortal randomPortal)
    {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;

        System.out.println("\n");
        System.out.println("+----------------------------------------------+");
        System.out.println("|             PORTAL INTERACTIONS              |");
        System.out.println("+----------------------------------------------+");
        System.out.println("select an option: ");
        System.out.println("+----------------------------------------------+");
        System.out.println(
                "| 01. Portal                                   |\n" +
                        "| 02. Connector                                |\n" +
                        "| 99. Back to previous menu                    |"
        );
        System.out.println("+----------------------------------------------+\n\n");

        option = scanner.nextInt();
    }

    /**
     * Mostra o menu acerca da API
     */
    public static void showAPIMenu() throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;

        while (!exit) {
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|               API MENU               |");
            System.out.println("+--------------------------------------+");
            System.out.println("select an option: ");
            System.out.println("+--------------------------------------+");
            System.out.println(
                            "| 01. Portals management               |\n" +
                            "| 02. Connectors management            |\n" +
                            "| 03. Routes management                |\n" +
                            "| 04. Players management               |\n" +
                            "| 05. Game management                  |\n" +
                            "| 99. Back to previous menu            |"
            );
            System.out.println("+--------------------------------------+\n\n");

            option = scanner.nextInt();

            /**
             * Depois de selecionar a opcao do menu, faz o que pretende
             */
            switch (option) {
                case 1:
                    showPortalsManagementMenu();
                    break;

                case 2:
                    showConnectorsManagementMenu();
                    break;

                case 3:
                    showRoutesManagementMenu();
                    break;

                case 4:
                    showPlayersManagementMenu();
                    break;

                case 5:
                    showGameManagementMenu();
                    break;

                case 99:
                    exit = true;
                    break;

                default:
                    System.out.println("invalid option, selected option between 1 and 5 or 99 to exit.");
                    break;
            }
        }
    }

    /**
     * Mostra o menu acerca do jogo
     */
    private static void showGameManagementMenu()
    {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;

        while (!exit) {
            System.out.println("\n");
            System.out.println("+--------------------------------------------------------------------------------+");
            System.out.println("|                              GAME MANAGEMENT MENU                              |");
            System.out.println("+--------------------------------------------------------------------------------+");
            System.out.println("select an option: ");
            System.out.println("+--------------------------------------------------------------------------------+");
            System.out.println(
                               "| 01. Calculate the shortest path between 2 points                               |\n" +
                               "| 02. Calculate the shortest path if going through places to recharge            |\n" +
                               "| 03. Calculate the shortest path if passing through portals and connectors only |\n" +
                               "| 04. Export each portal/connector involved                                      |\n" +
                               "| 05. Import game settings                                                       |\n" +
                               "| 06. Export game settings                                                       |\n" +
                               "| 99. Back to previous menu                                                      |"
            );
            System.out.println("+--------------------------------------------------------------------------------+\n\n");

            option = scanner.nextInt();

            /**
             * Depois de selecionar a opcao do menu, faz o que pretende
             */
            switch (option) {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
                    break;

                case 99:
                    exit = true;
                    break;

                default:
                    System.out.println("invalid option, selected option between 1 and 8 or 99 to exit.");
                    break;
            }
        }
    }

    /**
     * Mostra o menu da gestao dos jogadores
     */
    private static void showPlayersManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;

        while (!exit) {
            System.out.println("\n");
            System.out.println("+--------------------------------------------------------+");
            System.out.println("|                 PLAYERS MANAGEMENT MENU                |");
            System.out.println("+--------------------------------------------------------+");
            System.out.println("select an option: ");
            System.out.println("+--------------------------------------------------------+");
            System.out.println(
                               "| 01. Add                                                |\n" +
                               "| 02. Update                                             |\n" +
                               "| 03. Delete                                             |\n" +
                               "| 04. Join the team                                      |\n" +
                               "| 05. Leave the team                                     |\n" +
                               "| 06. List (by team, level, number of conquered portals) |\n" +
                               "| 07. Import statistics                                  |\n" +
                               "| 08. Export statistics                                  |\n" +
                               "| 99. Back to previous menu                              |"
            );
            System.out.println("+--------------------------------------------------------+\n\n");

            option = scanner.nextInt();

            /**
             * Depois de selecionar a opcao do menu, faz o que pretende
             */
            switch (option) {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
                    break;

                case 99:
                    exit = true;
                    break;

                default:
                    System.out.println("invalid option, selected option between 1 and 8 or 99 to exit.");
                    break;
            }
        }
    }

    /**
     * Mostra o menu acerca da gestao das rotas
     */
    private static void showRoutesManagementMenu() throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;

        while (!exit) {
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|        ROUTES MANAGEMENT MENU        |");
            System.out.println("+--------------------------------------+");
            System.out.println("select an option: ");
            System.out.println("+--------------------------------------+");
            System.out.println(
                    "| 01. Create                           |\n" +
                            "| 02. Delete                           |\n" +
                            "| 03. Import portals/connectors        |\n" +
                            "| 04. Export portals/connectors        |\n" +
                            "| 99. Back to previous menu            |"
            );
            System.out.println("+--------------------------------------+\n\n");

            option = scanner.nextInt();

            /**
             * Depois de selecionar a opcao do menu, faz o que pretende
             */
            switch (option) {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    iEJson.importFromJSONFile(root, local, "docs/import/import.json");
                    break;

                case 4:
                    break;

                case 99:
                    exit = true;
                    break;

                default:
                    System.out.println("invalid option, selected option between 1 and 7 or 99 to exit.");
                    break;
            }
        }
    }

    /**
     * Mostra o menu acerca da gestao dos portais
     */
    public static void showPortalsManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;

        while (!exit) {
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|        PORTALS MANAGEMENT MENU       |");
            System.out.println("+--------------------------------------+");
            System.out.println("select an option: ");
            System.out.println("+--------------------------------------+");
            System.out.println(
                    "| 01. Add                              |\n" +
                            "| 02. Edit                             |\n" +
                            "| 03. Delete                           |\n" +
                            "| 04. List                             |\n" +
                            "| 05. Import                           |\n" +
                            "| 06. Export                           |\n" +
                            "| 99. Back to previous menu            |"
            );
            System.out.println("+--------------------------------------+\n\n");

            option = scanner.nextInt();

            /**
             * Depois de selecionar a opcao do menu, faz o que pretende
             */
            switch (option) {
                case 1:
                    break;

                case 2:

                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
                    break;

                case 7:
                    break;

                case 99:
                    exit = true;
                    break;

                default:
                    System.out.println("invalid option, selected option between 1 and 7 or 99 to exit.");
                    break;
            }
        }
    }

    /**
     * Mostra o menu acerca da gestao dos conectores
     */
    private static void showConnectorsManagementMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;

        while (!exit) {
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|      CONNECTORS MANAGEMENT MENU      |");
            System.out.println("+--------------------------------------+");
            System.out.println("select an option: ");
            System.out.println("+--------------------------------------+");
            System.out.println(
                    "| 01. Add                              |\n" +
                            "| 02. Edit                             |\n" +
                            "| 03. Delete                           |\n" +
                            "| 04. Add interactions                 |\n" +
                            "| 05. Delete interactions              |\n" +
                            "| 06. List                             |\n" +
                            "| 07. Import                           |\n" +
                            "| 08. Export                           |\n" +
                            "| 99. Back to previous menu            |"
            );
            System.out.println("+--------------------------------------+\n\n");

            option = scanner.nextInt();

            /**
             * Depois de selecionar a opcao do menu, faz o que pretende
             */
            switch (option) {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
                    break;

                case 7:
                    break;

                case 8:
                    break;

                case 99:
                    exit = true;
                    break;

                default:
                    System.out.println("invalid option, selected option between 1 and 8 or 99 to exit.");
                    break;
            }
        }
    }


    public static void main(String[] args) throws IOException, ParseException {
        showMainMenu();
    }

    public String greeting() {
        if (value.equals("")) {
            throw new IllegalArgumentException("vazio");
        }

        return value;
    }
}
