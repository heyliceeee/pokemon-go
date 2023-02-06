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
        int option = 0;

        System.out.println("\n");
        System.out.println("+----------------------------------------------+");
        System.out.println("| WELCOME BACK "+playerName+ " |");
        System.out.println("+----------------------------------------------+");
        System.out.println("select a location type where you want to start: ");
        System.out.println("+----------------------------------------------+");
        System.out.println(
                           "| 01. Portal                                   |\n" +
                           "| 02. Connector                                |"
        );
        System.out.println("+----------------------------------------------+\n\n");

        option = scanner.nextInt();

        /**
         * Depois de selecionar a opcao do menu, faz o que pretende
         */
        switch (option) {
            case 1:
                IPortal portalRandom = root.getRandomPortal();
                String teamPlayer = root.getPlayerByName(playerName).getTeam();//obter equipa do jogador atual
                String ownershipPortal = portalRandom.getOwnership().getState(); //obter o dono do portal

                if(ownershipPortal.equals(teamPlayer)) //se a equipa que conquistou o portal é a mesma equipa do jogador
                {
                    //mostrar interações do portal
                    showPortalYourTeamInteractionsMenu(portalRandom);
                }
                else if(ownershipPortal.equals("No team")) //se nenhuma equipa conquistou o portal
                {
                    //mostrar interações do portal
                    showPortalNoTeamInteractionsMenu(portalRandom);
                }
                else //se a equipa que conquistou o portal é a equipa adversária do jogador
                {
                    //mostrar interações do portal
                    showPortalTeamOpponentInteractionsMenu(portalRandom);
                }
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

            default:
                System.out.println("invalid option, selected option between 1 and 2.");
                break;
        }
    }

    /**
     * Mostra o menu acerca das interações do portal da equipa adversária
     * @param randomPortal portal em questão
     */
    private static void showPortalTeamOpponentInteractionsMenu(IPortal randomPortal)
    {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;

        while (!exit)
        {
            System.out.println("\n");
            System.out.println("you are on the '"+randomPortal.getName()+"' ("+randomPortal.getOwnership().getState()+") portal");
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|          PORTAL INTERACTIONS         |");
            System.out.println("+--------------------------------------+");
            System.out.println("select an option to interact: ");
            System.out.println("+--------------------------------------+");
            System.out.println(
                            "| 01. Attack the portal                |\n" +
                            "| 99. Back to previous menu            |"
            );
            System.out.println("+--------------------------------------+");

            option = scanner.nextInt();

            switch (option)
            {
                case 1:

                    break;

                case 99:
                    exit = true;
                    runGameFirst();
                    break;

                default:
                    System.out.println("invalid option, selected option 1 or 99 to exit.");
                    break;
            }
        }
    }

    /**
     * Mostra o menu acerca das interações do portal da sua equipa
     * @param randomPortal portal em questão
     */
    private static void showPortalYourTeamInteractionsMenu(IPortal randomPortal)
    {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;

        while (!exit)
        {
            System.out.println("\n");
            System.out.println("you are on the '"+randomPortal.getName()+"' ("+randomPortal.getOwnership().getState()+") portal");
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|          PORTAL INTERACTIONS         |");
            System.out.println("+--------------------------------------+");
            System.out.println("select an option to interact: ");
            System.out.println("+--------------------------------------+");
            System.out.println(
                            "| 01. Recharge the portal              |\n" +
                            "| 99. Back to previous menu            |"
            );
            System.out.println("+--------------------------------------+");

            option = scanner.nextInt();

            switch (option)
            {
                case 1:
                    boolean exit2 = false;

                    int energyMax = randomPortal.getEnergyMax(); //energia max do portal
                    int energyCurrent = randomPortal.getEnergy();//energia atual do portal

                    int energyCurrentPlayer = root.getPlayerByName(playerName).getEnergy(); //energia atual do jogador

                    int qttEnergy = 0;
                    int min=0, max=(energyMax - energyCurrent);

                    while (!exit2)
                    {
                        System.out.println("\n");
                        System.out.println("+--------------------------------------+");
                        System.out.println("|    PORTAL INTERACTIONS - RECHARGE    |");
                        System.out.println("+--------------------------------------+");
                        System.out.println("your energy: "+energyCurrentPlayer+"\n");
                        System.out.println("energy portal: "+energyCurrent+"\n");
                        System.out.println("how much energy do you want to charge   ");
                        System.out.println("the portal? ("+(min)+" - "+(max)+") ");
                        System.out.println("+--------------------------------------+");

                        qttEnergy = scanner.nextInt();

                        
                    }

                    exit = true;
                    runGameSecond();
                    break;

                case 99:
                    exit = true;
                    runGameSecond();
                    break;

                default:
                    System.out.println("invalid option, selected option 1 or 99 to exit.");
                    break;
            }
        }
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

        while (!exit)
        {
            System.out.println("\n");
            System.out.println("you are on the '"+randomPortal.getName()+"' ("+randomPortal.getOwnership().getState()+") portal");
            System.out.println("\n");
            System.out.println("+--------------------------------------+");
            System.out.println("|          PORTAL INTERACTIONS         |");
            System.out.println("+--------------------------------------+");
            System.out.println("select an option to interact: ");
            System.out.println("+--------------------------------------+");
            System.out.println(
                               "| 01. Conquer the portal               |\n" +
                               "| 99. Go another location              |"
            );
            System.out.println("+--------------------------------------+");

            option = scanner.nextInt();

            switch (option)
            {
                case 1:
                    boolean exit2 = false;

                    int energyMax = randomPortal.getEnergyMax(); //energia max do portal
                    int energyMax25percent = (energyMax * 25) / 100;//25% da energia max do portal
                    int energyCurrent = randomPortal.getEnergy();//energia atual do portal

                    int energyCurrentPlayer = root.getPlayerByName(playerName).getEnergy(); //energia atual do jogador

                    int qttEnergy = 0;
                    int min=0, max=(energyMax - energyCurrent);

                    if((energyMax25percent - energyCurrent) > 0)
                    {
                        min = (energyMax25percent - energyCurrent);
                    }

                    while (!exit2)
                    {
                        System.out.println("\n");
                        System.out.println("+--------------------------------------+");
                        System.out.println("|     PORTAL INTERACTIONS - CONQUER    |");
                        System.out.println("+--------------------------------------+");
                        System.out.println("your energy: "+energyCurrentPlayer+"\n");
                        System.out.println("energy portal: "+energyCurrent+"\n");
                        System.out.println("how much energy do you want to charge   ");
                        System.out.println("the portal? ("+(min)+" - "+(max)+") ");
                        System.out.println("+--------------------------------------+");

                        qttEnergy = scanner.nextInt();

                        if(qttEnergy >= min && qttEnergy <= max && energyCurrentPlayer >= qttEnergy) //verificar se o "qttEnergy" está entre os valores e se o jogador tem energia suficiente
                        {
                            exit2 = true;
                        }
                        else
                        {
                            System.out.println("\nvalue incorrect or you don't have enough energy");
                        }
                    }

                    //se o "qttEnergy" está entre os valores
                    System.out.println("charging (conquering) the portal...");

                    //adicionar "qttEnergy" á energia atual do portal
                    randomPortal.setEnergy(qttEnergy);

                    //remover a energia do jogador gasta no portal, na energia atual do jogador
                    root.getPlayerByName(playerName).setEnergy(energyCurrentPlayer - qttEnergy);

                    //portal torna-se do jogador
                    randomPortal.getOwnership().setState(root.getPlayerByName(playerName).getTeam()); //mudar de equipa
                    randomPortal.getOwnership().setPlayer(playerName); //mudar nome de jogador que conquistou
                    root.getPlayerByName(playerName).setConqueredPortals(1); //incrementar o numero de portais conquistados

                    System.out.println("conquered the portal successfully");

                    System.out.println("energy portal current: "+randomPortal.getEnergy());
                    System.out.println("your energy current: "+root.getPlayerByName(playerName).getEnergy());
                    System.out.println("team portal current: "+randomPortal.getOwnership().getState());
                    System.out.println("player conquered portal current: "+randomPortal.getOwnership().getPlayer());
                    System.out.println("player conquered portals: "+root.getPlayerByName(playerName).getConqueredPortals());

                    exit = true;
                    runGameSecond();
                    break;

                case 99:
                    exit = true;
                    runGameSecond();
                    break;

                default:
                    System.out.println("invalid option, selected option 1 or 99 to exit.");
                    break;
            }
        }
    }


    private static void runGameSecond()
    {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        System.out.println("\n");
        System.out.println("+--------------------------------------------------------------------+");
        System.out.println("|                                MAPS                                |");
        System.out.println("+--------------------------------------------------------------------+");
        System.out.println("select a location type where you want to go:                          ");
        System.out.println("+--------------------------------------------------------------------+");
        System.out.println(
                           "| 01. Portal                                                         |\n" +
                           "| 02. Connector                                                      |\n" +
                           "| 03. Portal, but with the need to go through a place to recharge    |\n" +
                           "| 04. Connector, but with the need to go through a place to recharge |\n" +
                           "| 05. Portal, but only go through portals - SOON                     |\n" +
                           "| 06. Connector, but only go through connectors - SOON               |"
        );
        System.out.println("+--------------------------------------------------------------------+\n\n");

        option = scanner.nextInt();

        switch (option)
        {
            case 1:
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:
                break;

            default:
                System.out.println("invalid option, selected option between 1 and 4.");
                break;
        }
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
