package demo;

import api.implementation.*;
import api.interfaces.*;
import collections.exceptions.EmptyCollectionException;
import collections.exceptions.NotLocalInstanceException;
import collections.implementation.DoubleLinkedUnorderedList;
import collections.interfaces.UnorderedListADT;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Scanner;

public class Demo {
    static String value = "Hello World!";
    static ImporterExporterJson iEJson = new ImporterExporterJson();
    static IRoot root = new Root();
    static ILocal local = new Local(0, "", 0, null);
    static IPlayer player = new Player("", "", 0, 0, 0, 0, 0, null);
    public static String playerName = "";

    /**
     * grafo que tem informação acerca dos locais e das rotas entre eles
     */
    public RouteNetworkADT<ILocal> routeNetwork = new RouteNetwork<>();


    /**
     * Mostra o menu inicial
     */
    public static void showMainMenu() throws IOException, ParseException, EmptyCollectionException, NotLocalInstanceException, java.text.ParseException {
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
            System.out.println("+--------------------------------------+");

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

    /**
     * Mostra a opção para iniciar o jogo e introduzir o nome do jogador
     * @throws IOException
     * @throws ParseException
     * @throws EmptyCollectionException
     * @throws NotLocalInstanceException
     * @throws java.text.ParseException
     */
    private static void showGameMenu() throws IOException, ParseException, EmptyCollectionException, NotLocalInstanceException, java.text.ParseException {
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
            System.out.println("+--------------------------------------+");

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
                    System.out.println("+--------------------------------------+");

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

    /**
     * Mostra o menu de first setup do jogo
     * @throws IOException
     * @throws EmptyCollectionException
     * @throws NotLocalInstanceException
     * @throws java.text.ParseException
     */
    private static void runGameFirst() throws IOException, EmptyCollectionException, NotLocalInstanceException, java.text.ParseException {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        boolean firstTime = true;

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
        System.out.println("+----------------------------------------------+");

        option = scanner.nextInt();

        /**
         * Depois de selecionar a opcao do menu, faz o que pretende
         */
        switch (option) {
            case 1:
                IPortal portalRandom = root.getRandomPortal();
                String teamPlayer = root.getPlayerByName(playerName).getTeam();//obter equipa do jogador atual
                String ownershipPortal = portalRandom.getOwnership().getState(); //obter o dono do portal

                showPortalMenu(portalRandom, teamPlayer, ownershipPortal, firstTime);
                break;

            case 2:
                IConnector connectorRandom = root.getRandomConnector();

                showConnectorMenu(connectorRandom, firstTime);
                break;

            default:
                System.out.println("invalid option, selected option between 1 and 2.");
                break;
        }
    }

    /**
     * Mostra o menu após realizar o setup do jogo
     * @param local
     * @throws EmptyCollectionException
     * @throws NotLocalInstanceException
     * @throws java.text.ParseException
     * @throws IOException
     */
    private static void runGameSecond(ILocal local) throws EmptyCollectionException, NotLocalInstanceException, java.text.ParseException, IOException {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        boolean firstTime = false;

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
        System.out.println("+--------------------------------------------------------------------+");

        option = scanner.nextInt();

        switch (option)
        {
            case 1:
                IPortal portalRandom = (IPortal) root.getRoute(1, local).next(); //obter o portal mais perto
                String teamPlayer = root.getPlayerByName(playerName).getTeam();//obter equipa do jogador atual
                String ownershipPortal = portalRandom.getOwnership().getState(); //obter o dono do portal

                showPortalMenu(portalRandom, teamPlayer, ownershipPortal, firstTime);
                break;

            case 2:
                IConnector connectorRandom = (IConnector) root.getRoute(2, local).next(); //obter o connector mais perto

                showConnectorMenu(connectorRandom, firstTime);
                break;

            case 3:
                IConnector connectorCooldownGoPortalRandom = (IConnector) root.getRoute(3, local).next(); //obter o connector sem cooldown mais perto e depois portal

                showConnectorCooldownGoPortalMenu(connectorCooldownGoPortalRandom, firstTime);
                break;

            case 4:
                IConnector connectorCooldownGoConnectorRandom = (IConnector) root.getRoute(4, local).next(); //obter o connector sem cooldown mais perto e depois connector

                showConnectorCooldownGoConnectorMenu(connectorCooldownGoConnectorRandom, firstTime);
                break;

            default:
                System.out.println("invalid option, selected option between 1 and 4.");
                break;
        }
    }


    //region IN GAME - PORTAL MENU

    /**
     * Mostra, dependendo do dono do portal, o menu de interações do portal
     * @param portalRandom
     * @param teamPlayer
     * @param ownershipPortal
     * @throws EmptyCollectionException
     * @throws NotLocalInstanceException
     * @throws java.text.ParseException
     * @throws IOException
     */
    private static void showPortalMenu(IPortal portalRandom, String teamPlayer, String ownershipPortal, boolean firstTime) throws EmptyCollectionException, NotLocalInstanceException, java.text.ParseException, IOException
    {
        if(ownershipPortal.equals(teamPlayer)) //se a equipa que conquistou o portal é a mesma equipa do jogador
        {
            //mostrar interações do portal
            showPortalYourTeamInteractionsMenu(portalRandom, firstTime);
        }
        else if(ownershipPortal.equals("No team")) //se nenhuma equipa conquistou o portal
        {
            //mostrar interações do portal
            showPortalNoTeamInteractionsMenu(portalRandom, firstTime);
        }
        else //se a equipa que conquistou o portal é a equipa adversária do jogador
        {
            //mostrar interações do portal
            showPortalTeamOpponentInteractionsMenu(portalRandom, firstTime);
        }
    }

    /**
     * Mostra o menu acerca das interações do portal da equipa adversária
     * @param randomPortal portal em questão
     */
    private static void showPortalTeamOpponentInteractionsMenu(IPortal randomPortal, boolean firstTime) throws IOException, EmptyCollectionException, NotLocalInstanceException, java.text.ParseException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;
        IInteraction interaction = null;

        int energyCurrent = randomPortal.getEnergy();//energia atual do portal
        int energyCurrentPlayer = root.getPlayerByName(playerName).getEnergy(); //energia atual do jogador

        if(firstTime == false)
        {
            while (!exit)
            {
                System.out.println("\n");
                System.out.println("you are on the '"+randomPortal.getName()+"' ("+randomPortal.getOwnership().getState()+") portal");
                System.out.println("+--------------------------------------+");
                System.out.println("|          PORTAL INTERACTIONS         |");
                System.out.println("+--------------------------------------+");
                System.out.println("your energy: "+energyCurrentPlayer);
                System.out.println("energy portal: "+energyCurrent);
                System.out.println("+--------------------------------------+");
                System.out.println("select an option to interact: ");
                System.out.println("+--------------------------------------+");
                System.out.println(
                        "| 01. Attack the portal                |\n" +
                                "| 99. Go another location              |"
                );
                System.out.println("+--------------------------------------+");

                option = scanner.nextInt();

                switch (option)
                {
                    case 1:
                        boolean exit2 = false;
                        System.out.println("discharging (attacking) the portal...");

                        if(energyCurrent > energyCurrentPlayer)
                        {
                            //energia atual do portal
                            randomPortal.setEnergy((-energyCurrentPlayer)); // x + (- energy)

                            //remover a energia do jogador gasta no portal, na energia atual do jogador
                            root.getPlayerByName(playerName).setEnergy(0);

                            System.out.println("attacked the portal not complete");

                            System.out.println("\nyour energy current: "+root.getPlayerByName(playerName).getEnergy());
                            System.out.println("energy portal current: "+randomPortal.getEnergy());

                            //adicionar interação ao JSON
                            int id = randomPortal.getIDLastInteraction() + 1; //descobrir o último id de interação adicionado ao portal atual
                            LocalDateTime now = LocalDateTime.now(); //data agora

                            int level = root.getPlayerByName(playerName).getLevel(); //nivel do jogador
                            int points = root.getGameSettingByType("Não completa o ataque ao portal").getPoints(); //pontos do tipo de interação
                            int speedXP = root.getGameSettingByType("Não completa o ataque ao portal").getSpeedXp(); //velocidade de xp do tipo de interação

                            int pointsInteraction = (level/points)^speedXP;//definir os pontos (xp) que o jogador ganhou

                            interaction = new Interaction(id, "Não completa o ataque ao portal", playerName, now.toString(), pointsInteraction);
                            randomPortal.addInteraction(interaction); //adicionar interação ao portal

                            player.defineLevelByXP(root, playerName, pointsInteraction);//define o nivel do jogador

                            exit = true;
                            runGameSecond(randomPortal);
                        }
                        else
                        {
                            //energia atual do portal
                            randomPortal.setEnergy(-energyCurrent); //energy + x = 0

                            //remover a energia do jogador gasta no portal, na energia atual do jogador
                            root.getPlayerByName(playerName).setEnergy(energyCurrentPlayer - energyCurrent);

                            randomPortal.getOwnership().setState("No team"); //portal sem equipa
                            randomPortal.getOwnership().setPlayer(""); //portal sem dono

                            System.out.println("attacked the portal successfully");

                            System.out.println("\nyour energy current: "+root.getPlayerByName(playerName).getEnergy());
                            System.out.println("energy portal current: "+randomPortal.getEnergy());

                            //adicionar interação ao JSON
                            int id = randomPortal.getIDLastInteraction() + 1; //descobrir o último id de interação adicionado ao portal atual
                            LocalDateTime now = LocalDateTime.now(); //data agora

                            int level = root.getPlayerByName(playerName).getLevel(); //nivel do jogador
                            int points = root.getGameSettingByType("Ataca portal").getPoints(); //pontos do tipo de interação
                            int speedXP = root.getGameSettingByType("Ataca portal").getSpeedXp(); //velocidade de xp do tipo de interação

                            int pointsInteraction = (level/points)^speedXP;//definir os pontos (xp) que o jogador ganhou

                            interaction = new Interaction(id, "Ataca portal", playerName, now.toString(), pointsInteraction);
                            randomPortal.addInteraction(interaction); //adicionar interação ao portal

                            player.defineLevelByXP(root, playerName, pointsInteraction);//define o nivel do jogador

                            exit = true;
                            runGameSecond(randomPortal);
                        }
                        break;

                    case 99:
                        exit = true;
                        runGameSecond(randomPortal);
                        break;

                    default:
                        System.out.println("invalid option, selected option 1 or 99 to go another location.");
                        break;
                }
            }
        }
        else if(firstTime == true)
        {
            while (!exit)
            {
                System.out.println("\n");
                System.out.println("you are on the '"+randomPortal.getName()+"' ("+randomPortal.getOwnership().getState()+") portal");
                System.out.println("+--------------------------------------+");
                System.out.println("|          PORTAL INTERACTIONS         |");
                System.out.println("+--------------------------------------+");
                System.out.println("your energy: "+energyCurrentPlayer);
                System.out.println("energy portal: "+energyCurrent);
                System.out.println("+--------------------------------------+");
                System.out.println("select an option to interact: ");
                System.out.println("+--------------------------------------+");
                System.out.println(
                        "| 01. Attack the portal                |\n" +
                                "| 99. Go another location              |"
                );
                System.out.println("+--------------------------------------+");

                option = scanner.nextInt();

                switch (option)
                {
                    case 1:
                        boolean exit2 = false;
                        System.out.println("discharging (attacking) the portal...");

                        if(energyCurrent > energyCurrentPlayer)
                        {
                            //energia atual do portal
                            randomPortal.setEnergy((-energyCurrentPlayer)); // x + (- energy)

                            //remover a energia do jogador gasta no portal, na energia atual do jogador
                            root.getPlayerByName(playerName).setEnergy(0);

                            System.out.println("attacked the portal not complete");

                            System.out.println("\nyour energy current: "+root.getPlayerByName(playerName).getEnergy());
                            System.out.println("energy portal current: "+randomPortal.getEnergy());

                            //adicionar interação ao JSON
                            int id = randomPortal.getIDLastInteraction() + 1; //descobrir o último id de interação adicionado ao portal atual
                            LocalDateTime now = LocalDateTime.now(); //data agora

                            int level = root.getPlayerByName(playerName).getLevel(); //nivel do jogador
                            int points = root.getGameSettingByType("Não completa o ataque ao portal").getPoints(); //pontos do tipo de interação
                            int speedXP = root.getGameSettingByType("Não completa o ataque ao portal").getSpeedXp(); //velocidade de xp do tipo de interação

                            int pointsInteraction = (level/points)^speedXP;//definir os pontos (xp) que o jogador ganhou

                            interaction = new Interaction(id, "Não completa o ataque ao portal", playerName, now.toString(), pointsInteraction);
                            randomPortal.addInteraction(interaction); //adicionar interação ao portal

                            player.defineLevelByXP(root, playerName, pointsInteraction);//define o nivel do jogador
                        }
                        else
                        {
                            //energia atual do portal
                            randomPortal.setEnergy(-energyCurrent); //energy + x = 0

                            //remover a energia do jogador gasta no portal, na energia atual do jogador
                            root.getPlayerByName(playerName).setEnergy(energyCurrentPlayer - energyCurrent);

                            randomPortal.getOwnership().setState("No team"); //portal sem equipa
                            randomPortal.getOwnership().setPlayer(""); //portal sem dono

                            System.out.println("attacked the portal successfully");

                            System.out.println("\nyour energy current: "+root.getPlayerByName(playerName).getEnergy());
                            System.out.println("energy portal current: "+randomPortal.getEnergy());

                            //adicionar interação ao JSON
                            int id = randomPortal.getIDLastInteraction() + 1; //descobrir o último id de interação adicionado ao portal atual
                            LocalDateTime now = LocalDateTime.now(); //data agora

                            int level = root.getPlayerByName(playerName).getLevel(); //nivel do jogador
                            int points = root.getGameSettingByType("Ataca portal").getPoints(); //pontos do tipo de interação
                            int speedXP = root.getGameSettingByType("Ataca portal").getSpeedXp(); //velocidade de xp do tipo de interação

                            int pointsInteraction = (level/points)^speedXP;//definir os pontos (xp) que o jogador ganhou

                            interaction = new Interaction(id, "Ataca portal", playerName, now.toString(), pointsInteraction);
                            randomPortal.addInteraction(interaction); //adicionar interação ao portal

                            player.defineLevelByXP(root, playerName, pointsInteraction);//define o nivel do jogador

                            exit = true;
                            runGameSecond(randomPortal);
                        }
                        break;

                    case 99:
                        exit = true;
                        runGameFirst();
                        break;

                    default:
                        System.out.println("invalid option, selected option 1 or 99 to go another location.");
                        break;
                }
            }
        }
    }

    /**
     * Mostra o menu acerca das interações do portal da sua equipa
     * @param randomPortal portal em questão
     */
    private static void showPortalYourTeamInteractionsMenu(IPortal randomPortal, boolean firstTime) throws EmptyCollectionException, NotLocalInstanceException, java.text.ParseException, IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;
        IInteraction interaction = null;


        if (firstTime == false)
        {
            while (!exit)
            {
                System.out.println("\n");
                System.out.println("you are on the '"+randomPortal.getName()+"' ("+randomPortal.getOwnership().getState()+") portal");
                System.out.println("+--------------------------------------+");
                System.out.println("|          PORTAL INTERACTIONS         |");
                System.out.println("+--------------------------------------+");
                System.out.println("select an option to interact: ");
                System.out.println("+--------------------------------------+");
                System.out.println(
                        "| 01. Recharge the portal              |\n" +
                                "| 99. Go another location              |"
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
                            System.out.println("your energy: "+energyCurrentPlayer);
                            System.out.println("energy portal: "+energyCurrent);
                            System.out.println("+--------------------------------------+");
                            System.out.println("how much energy do you want to recharge ");
                            System.out.println("the portal? ("+(min)+" - "+(max)+") ");
                            System.out.println("+--------------------------------------+");

                            qttEnergy = scanner.nextInt();

                            if(qttEnergy >= min && qttEnergy <= max && energyCurrentPlayer >= qttEnergy) //verificar se o "qttEnergy" está entre os valores e se o jogador tem energia suficiente
                            {
                                exit2 = true;

                                //se o "qttEnergy" está entre os valores
                                System.out.println("\nrecharging the portal...");

                                //adicionar "qttEnergy" á energia atual do portal
                                randomPortal.setEnergy(qttEnergy);

                                //remover a energia do jogador gasta no portal, na energia atual do jogador
                                root.getPlayerByName(playerName).setEnergy(energyCurrentPlayer - qttEnergy);

                                System.out.println("recharged the portal successfully");

                                System.out.println("\nenergy portal current: "+randomPortal.getEnergy());
                                System.out.println("your energy current: "+root.getPlayerByName(playerName).getEnergy());

                                //adicionar interação ao JSON
                                int id = randomPortal.getIDLastInteraction() + 1; //descobrir o último id de interação adicionado ao portal atual
                                LocalDateTime now = LocalDateTime.now(); //data agora

                                int level = root.getPlayerByName(playerName).getLevel(); //nivel do jogador
                                int points = root.getGameSettingByType("Recarrega portal").getPoints(); //pontos do tipo de interação
                                int speedXP = root.getGameSettingByType("Recarrega portal").getSpeedXp(); //velocidade de xp do tipo de interação

                                int pointsInteraction = (level/points)^speedXP;//definir os pontos (xp) que o jogador ganhou

                                interaction = new Interaction(id, "Recarrega portal", playerName, now.toString(), pointsInteraction);
                                randomPortal.addInteraction(interaction); //adicionar interação ao portal

                                player.defineLevelByXP(root, playerName, pointsInteraction);//define o nivel do jogador

                                exit = true;
                                runGameSecond(randomPortal);
                            }
                            else
                            {
                                System.out.println("\nvalue incorrect or you don't have enough energy");
                                exit2 = true;
                            }
                        }
                        break;

                    case 99:
                        exit = true;
                        runGameSecond(randomPortal);
                        break;

                    default:
                        System.out.println("invalid option, selected option 1 or 99 to go another location.");
                        break;
                }
            }
        }
        else if(firstTime == true)
        {
            while (!exit)
            {
                System.out.println("\n");
                System.out.println("you are on the '"+randomPortal.getName()+"' ("+randomPortal.getOwnership().getState()+") portal");
                System.out.println("+--------------------------------------+");
                System.out.println("|          PORTAL INTERACTIONS         |");
                System.out.println("+--------------------------------------+");
                System.out.println("select an option to interact: ");
                System.out.println("+--------------------------------------+");
                System.out.println(
                        "| 01. Recharge the portal              |\n" +
                                "| 99. Go another location              |"
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
                            System.out.println("your energy: "+energyCurrentPlayer);
                            System.out.println("energy portal: "+energyCurrent);
                            System.out.println("+--------------------------------------+");
                            System.out.println("how much energy do you want to recharge ");
                            System.out.println("the portal? ("+(min)+" - "+(max)+") ");
                            System.out.println("+--------------------------------------+");

                            qttEnergy = scanner.nextInt();

                            if(qttEnergy >= min && qttEnergy <= max && energyCurrentPlayer >= qttEnergy) //verificar se o "qttEnergy" está entre os valores e se o jogador tem energia suficiente
                            {
                                exit2 = true;

                                //se o "qttEnergy" está entre os valores
                                System.out.println("\nrecharging the portal...");

                                //adicionar "qttEnergy" á energia atual do portal
                                randomPortal.setEnergy(qttEnergy);

                                //remover a energia do jogador gasta no portal, na energia atual do jogador
                                root.getPlayerByName(playerName).setEnergy(energyCurrentPlayer - qttEnergy);

                                System.out.println("recharged the portal successfully");

                                System.out.println("\nenergy portal current: "+randomPortal.getEnergy());
                                System.out.println("your energy current: "+root.getPlayerByName(playerName).getEnergy());

                                //adicionar interação ao JSON
                                int id = randomPortal.getIDLastInteraction() + 1; //descobrir o último id de interação adicionado ao portal atual
                                LocalDateTime now = LocalDateTime.now(); //data agora

                                int level = root.getPlayerByName(playerName).getLevel(); //nivel do jogador
                                int points = root.getGameSettingByType("Recarrega portal").getPoints(); //pontos do tipo de interação
                                int speedXP = root.getGameSettingByType("Recarrega portal").getSpeedXp(); //velocidade de xp do tipo de interação

                                int pointsInteraction = (level/points)^speedXP;//definir os pontos (xp) que o jogador ganhou

                                interaction = new Interaction(id, "Recarrega portal", playerName, now.toString(), pointsInteraction);
                                randomPortal.addInteraction(interaction); //adicionar interação ao portal

                                player.defineLevelByXP(root, playerName, pointsInteraction);//define o nivel do jogador

                                exit = true;
                                runGameSecond(randomPortal);
                            }
                            else
                            {
                                System.out.println("\nvalue incorrect or you don't have enough energy");
                                exit2 = true;
                            }
                        }
                        break;

                    case 99:
                        exit = true;
                        runGameFirst();
                        break;

                    default:
                        System.out.println("invalid option, selected option 1 or 99 to go another location.");
                        break;
                }
            }
        }
    }

    /**
     * Mostra o menu acerca das interações do portal sem equipa
     * @param randomPortal portal em questão
     */
    private static void showPortalNoTeamInteractionsMenu(IPortal randomPortal, boolean firstTime) throws IOException, EmptyCollectionException, NotLocalInstanceException, java.text.ParseException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;
        IInteraction interaction = null;

        int energyCurrent = randomPortal.getEnergy();//energia atual do portal
        int energyCurrentPlayer = root.getPlayerByName(playerName).getEnergy(); //energia atual do jogador


        if(firstTime == false)
        {
            while (!exit)
            {
                System.out.println("\n");
                System.out.println("you are on the '"+randomPortal.getName()+"' ("+randomPortal.getOwnership().getState()+") portal");
                System.out.println("+--------------------------------------+");
                System.out.println("|          PORTAL INTERACTIONS         |");
                System.out.println("+--------------------------------------+");
                System.out.println("your energy: "+energyCurrentPlayer);
                System.out.println("energy portal: "+energyCurrent);
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
                            System.out.println("your energy: "+energyCurrentPlayer);
                            System.out.println("energy portal: "+energyCurrent);
                            System.out.println("+--------------------------------------+");
                            System.out.println("how much energy do you want to charge   ");
                            System.out.println("the portal? ("+(min)+" - "+(max)+") ");
                            System.out.println("+--------------------------------------+");

                            qttEnergy = scanner.nextInt();

                            if(qttEnergy >= min && qttEnergy <= max && energyCurrentPlayer >= qttEnergy) //verificar se o "qttEnergy" está entre os valores e se o jogador tem energia suficiente
                            {
                                //se o "qttEnergy" está entre os valores
                                System.out.println("\ncharging (conquering) the portal...");

                                //adicionar "qttEnergy" á energia atual do portal
                                randomPortal.setEnergy(qttEnergy);

                                //remover a energia do jogador gasta no portal, na energia atual do jogador
                                root.getPlayerByName(playerName).setEnergy(energyCurrentPlayer - qttEnergy);

                                //portal torna-se do jogador
                                randomPortal.getOwnership().setState(root.getPlayerByName(playerName).getTeam()); //mudar de equipa
                                randomPortal.getOwnership().setPlayer(playerName); //mudar nome de jogador que conquistou
                                root.getPlayerByName(playerName).setConqueredPortals(1); //incrementar o numero de portais conquistados

                                System.out.println("conquered the portal successfully");

                                System.out.println("\nenergy portal current: "+randomPortal.getEnergy());
                                System.out.println("your energy current: "+root.getPlayerByName(playerName).getEnergy());
                                System.out.println("\nteam portal current: "+randomPortal.getOwnership().getState());
                                System.out.println("\nplayer conquered portal current: "+randomPortal.getOwnership().getPlayer());
                                System.out.println("player conquered portals: "+root.getPlayerByName(playerName).getConqueredPortals());

                                //adicionar interação ao JSON
                                int id = randomPortal.getIDLastInteraction() + 1; //descobrir o último id de interação adicionado ao portal atual
                                LocalDateTime now = LocalDateTime.now(); //data agora

                                int level = root.getPlayerByName(playerName).getLevel(); //nivel do jogador
                                int points = root.getGameSettingByType("Conquista portal").getPoints(); //pontos do tipo de interação
                                int speedXP = root.getGameSettingByType("Conquista portal").getSpeedXp(); //velocidade de xp do tipo de interação

                                int pointsInteraction = (level/points)^speedXP;//definir os pontos (xp) que o jogador ganhou

                                interaction = new Interaction(id, "Conquista portal", playerName, now.toString(), pointsInteraction);
                                randomPortal.addInteraction(interaction); //adicionar interação ao portal

                                player.defineLevelByXP(root, playerName, pointsInteraction);//define o nivel do jogador

                                exit = true;
                                runGameSecond(randomPortal);
                            }
                            else
                            {
                                System.out.println("\nvalue incorrect or you don't have enough energy");
                                exit2 = true;
                            }
                        }
                        break;

                    case 99:
                        exit = true;
                        runGameSecond(randomPortal);
                        break;

                    default:
                        System.out.println("invalid option, selected option 1 or 99 to go another location.");
                        break;
                }
            }
        }
        else if(firstTime == true)
        {
            while (!exit)
            {
                System.out.println("\n");
                System.out.println("you are on the '"+randomPortal.getName()+"' ("+randomPortal.getOwnership().getState()+") portal");
                System.out.println("+--------------------------------------+");
                System.out.println("|          PORTAL INTERACTIONS         |");
                System.out.println("+--------------------------------------+");
                System.out.println("your energy: "+energyCurrentPlayer);
                System.out.println("energy portal: "+energyCurrent);
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
                            System.out.println("your energy: "+energyCurrentPlayer);
                            System.out.println("energy portal: "+energyCurrent);
                            System.out.println("+--------------------------------------+");
                            System.out.println("how much energy do you want to charge   ");
                            System.out.println("the portal? ("+(min)+" - "+(max)+") ");
                            System.out.println("+--------------------------------------+");

                            qttEnergy = scanner.nextInt();

                            if(qttEnergy >= min && qttEnergy <= max && energyCurrentPlayer >= qttEnergy) //verificar se o "qttEnergy" está entre os valores e se o jogador tem energia suficiente
                            {
                                //se o "qttEnergy" está entre os valores
                                System.out.println("\ncharging (conquering) the portal...");

                                //adicionar "qttEnergy" á energia atual do portal
                                randomPortal.setEnergy(qttEnergy);

                                //remover a energia do jogador gasta no portal, na energia atual do jogador
                                root.getPlayerByName(playerName).setEnergy(energyCurrentPlayer - qttEnergy);

                                //portal torna-se do jogador
                                randomPortal.getOwnership().setState(root.getPlayerByName(playerName).getTeam()); //mudar de equipa
                                randomPortal.getOwnership().setPlayer(playerName); //mudar nome de jogador que conquistou
                                root.getPlayerByName(playerName).setConqueredPortals(1); //incrementar o numero de portais conquistados

                                System.out.println("conquered the portal successfully");

                                System.out.println("\nenergy portal current: "+randomPortal.getEnergy());
                                System.out.println("your energy current: "+root.getPlayerByName(playerName).getEnergy());
                                System.out.println("\nteam portal current: "+randomPortal.getOwnership().getState());
                                System.out.println("\nplayer conquered portal current: "+randomPortal.getOwnership().getPlayer());
                                System.out.println("player conquered portals: "+root.getPlayerByName(playerName).getConqueredPortals());

                                //adicionar interação ao JSON
                                int id = randomPortal.getIDLastInteraction() + 1; //descobrir o último id de interação adicionado ao portal atual
                                LocalDateTime now = LocalDateTime.now(); //data agora

                                int level = root.getPlayerByName(playerName).getLevel(); //nivel do jogador
                                int points = root.getGameSettingByType("Conquista portal").getPoints(); //pontos do tipo de interação
                                int speedXP = root.getGameSettingByType("Conquista portal").getSpeedXp(); //velocidade de xp do tipo de interação

                                int pointsInteraction = (level/points)^speedXP;//definir os pontos (xp) que o jogador ganhou

                                interaction = new Interaction(id, "Conquista portal", playerName, now.toString(), pointsInteraction);
                                randomPortal.addInteraction(interaction); //adicionar interação ao portal

                                player.defineLevelByXP(root, playerName, pointsInteraction);//define o nivel do jogador

                                exit = true;
                                runGameSecond(randomPortal);
                            }
                            else
                            {
                                System.out.println("\nvalue incorrect or you don't have enough energy");
                                exit2 = true;
                            }
                        }
                        break;

                    case 99:
                        exit = true;
                        runGameFirst();
                        break;

                    default:
                        System.out.println("invalid option, selected option 1 or 99 to go another location.");
                        break;
                }
            }
        }
    }

    //endregion


    //region IN GAME - CONNECTOR MENU

    /**
     * Mostra o menu de interações do conector
     * @param connectorRandom
     * @param firstTime
     * @throws EmptyCollectionException
     * @throws NotLocalInstanceException
     * @throws IOException
     * @throws java.text.ParseException
     */
    private static void showConnectorMenu(IConnector connectorRandom, boolean firstTime) throws EmptyCollectionException, NotLocalInstanceException, IOException, java.text.ParseException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;
        IInteraction interaction = null;

        int energyCurrentPlayer = root.getPlayerByName(playerName).getEnergy(); //energia atual do jogador
        int energyConnector = connectorRandom.getEnergy();//energia atual do connector
        int energyMaxPlayer = root.getPlayerByName(playerName).getEnergyMax(); //energia máxima do jogador

        if(firstTime == false)
        {
            while (!exit)
            {
                System.out.println("\n");
                System.out.println("you are on the connector nº "+connectorRandom.getId());
                System.out.println("+--------------------------------------+");
                System.out.println("|         CONNECTOR INTERACTIONS       |");
                System.out.println("+--------------------------------------+");
                System.out.println("your energy: "+energyCurrentPlayer);
                System.out.println("your max energy: "+energyMaxPlayer);
                System.out.println("energy connector: "+energyConnector);
                System.out.println("+--------------------------------------+");
                System.out.println("select an option to interact: ");
                System.out.println("+--------------------------------------+");
                System.out.println(
                        "| 01. Charge my energy                 |\n" +
                                "| 99. Go another location              |"
                );
                System.out.println("+--------------------------------------+");

                option = scanner.nextInt();

                switch (option)
                {
                    case 1:
                        String date = String.valueOf(root.getConnectorInteractionsByPlayerName(connectorRandom.getId(), playerName));//data da ultima interação do jogador com connector
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                        LocalDateTime dateLastInteraction;

                        try
                        {
                            dateLastInteraction = LocalDateTime.parse(date, formatter);
                        }
                        catch (Exception e)
                        {
                            dateLastInteraction = LocalDateTime.of(1970, Month.JANUARY, 1, 0, 0, 0);
                        }

                        int cooldown = root.getConnectorByID(connectorRandom.getId()).getCooldown(); //cooldown connector

                        long duration = Duration.between(dateLastInteraction, LocalDateTime.now()).toMinutes(); //obter em minutos, há quanto tempo foi realizado a última interação do jogador com o connector

                        if(cooldown < duration)//se já passou o periodo de cooldown do jogador atual
                        {
                            System.out.println("charging your energy...");

                            if(energyMaxPlayer <= energyConnector)
                            {
                                root.getPlayerByName(playerName).setEnergy(energyMaxPlayer);
                                System.out.println("charged your energy successfully");
                                System.out.println("\nyour energy current: "+root.getPlayerByName(playerName).getEnergy());

                                //adicionar interação ao JSON
                                int id = connectorRandom.getIDLastInteraction(); //descobrir o último id de interação adicionado ao portal atual
                                LocalDateTime now = LocalDateTime.now(); //data agora

                                int level = root.getPlayerByName(playerName).getLevel(); //nivel do jogador
                                int points = root.getGameSettingByType("Reforço de energia de connector").getPoints(); //pontos do tipo de interação
                                int speedXP = root.getGameSettingByType("Reforço de energia de connector").getSpeedXp(); //velocidade de xp do tipo de interação

                                int pointsInteraction = (level/points)^speedXP;//definir os pontos (xp) que o jogador ganhou

                                interaction = new Interaction(id, "Reforço de energia de connector", playerName, now.toString(), pointsInteraction);
                                connectorRandom.addInteraction(interaction); //adicionar interação ao connector

                                player.defineLevelByXP(root, playerName, pointsInteraction);//define o nivel do jogador

                                exit = true;
                                runGameSecond(connectorRandom);
                            }
                            else if(energyMaxPlayer > energyConnector)
                            {
                                if((energyCurrentPlayer + energyConnector) <= energyMaxPlayer) //se nao atingir a energia maxima
                                {
                                    root.getPlayerByName(playerName).setEnergy(energyCurrentPlayer + energyConnector);
                                    System.out.println("charged your energy successfully");
                                    System.out.println("\nyour energy current: "+root.getPlayerByName(playerName).getEnergy());

                                    //adicionar interação ao JSON
                                    int id = connectorRandom.getIDLastInteraction(); //descobrir o último id de interação adicionado ao portal atual
                                    LocalDateTime now = LocalDateTime.now(); //data agora

                                    int level = root.getPlayerByName(playerName).getLevel(); //nivel do jogador
                                    int points = root.getGameSettingByType("Reforço de energia de connector").getPoints(); //pontos do tipo de interação
                                    int speedXP = root.getGameSettingByType("Reforço de energia de connector").getSpeedXp(); //velocidade de xp do tipo de interação

                                    int pointsInteraction = (level/points)^speedXP;//definir os pontos (xp) que o jogador ganhou

                                    interaction = new Interaction(id, "Reforço de energia de connector", playerName, now.toString(), pointsInteraction);
                                    connectorRandom.addInteraction(interaction); //adicionar interação ao connector

                                    player.defineLevelByXP(root, playerName, pointsInteraction);//define o nivel do jogador

                                    exit = true;
                                    runGameSecond(connectorRandom);
                                }
                                else //se atingir a energia maxima
                                {
                                    root.getPlayerByName(playerName).setEnergy(Math.min(energyCurrentPlayer + energyConnector, energyMaxPlayer));
                                    System.out.println("charged your energy successfully");
                                    System.out.println("\nyour energy current: "+root.getPlayerByName(playerName).getEnergy());

                                    //adicionar interação ao JSON
                                    int id = connectorRandom.getIDLastInteraction(); //descobrir o último id de interação adicionado ao portal atual
                                    LocalDateTime now = LocalDateTime.now(); //data agora

                                    int level = root.getPlayerByName(playerName).getLevel(); //nivel do jogador
                                    int points = root.getGameSettingByType("Reforço de energia de connector").getPoints(); //pontos do tipo de interação
                                    int speedXP = root.getGameSettingByType("Reforço de energia de connector").getSpeedXp(); //velocidade de xp do tipo de interação

                                    int pointsInteraction = (level/points)^speedXP;//definir os pontos (xp) que o jogador ganhou

                                    interaction = new Interaction(id, "Reforço de energia de connector", playerName, now.toString(), pointsInteraction);
                                    connectorRandom.addInteraction(interaction); //adicionar interação ao connector

                                    player.defineLevelByXP(root, playerName, pointsInteraction);//define o nivel do jogador

                                    exit = true;
                                    runGameSecond(connectorRandom);
                                }
                            }
                        }
                        else if(cooldown > duration)//se NÃO passou o periodo de cooldown do jogador atual
                        {
                            System.out.println("\nconnector with cooldown. please wait more "+(cooldown - duration)+" minutes");
                            exit = true;
                        }
                        break;

                    case 99:
                        exit = true;
                        runGameSecond(connectorRandom);
                        break;

                    default:
                        System.out.println("invalid option, selected option between 1 or 99 to go another location.");
                        break;
                }
            }
        }
        else if(firstTime = true)
        {
            while (!exit)
            {
                System.out.println("\n");
                System.out.println("you are on the connector nº "+connectorRandom.getId());
                System.out.println("+--------------------------------------+");
                System.out.println("|         CONNECTOR INTERACTIONS       |");
                System.out.println("+--------------------------------------+");
                System.out.println("your energy: "+energyCurrentPlayer);
                System.out.println("your max energy: "+energyMaxPlayer);
                System.out.println("energy connector: "+energyConnector);
                System.out.println("+--------------------------------------+");
                System.out.println("select an option to interact: ");
                System.out.println("+--------------------------------------+");
                System.out.println(
                        "| 01. Charge my energy                 |\n" +
                                "| 99. Go another location              |"
                );
                System.out.println("+--------------------------------------+");

                option = scanner.nextInt();

                switch (option)
                {
                    case 1:
                        String date = String.valueOf(root.getConnectorInteractionsByPlayerName(connectorRandom.getId(), playerName));//data da ultima interação do jogador com connector
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                        LocalDateTime dateLastInteraction;

                        try
                        {
                            dateLastInteraction = LocalDateTime.parse(date, formatter);
                        }
                        catch (Exception e)
                        {
                            dateLastInteraction = LocalDateTime.of(1970, Month.JANUARY, 1, 0, 0, 0);
                        }

                        int cooldown = root.getConnectorByID(connectorRandom.getId()).getCooldown(); //cooldown connector

                        long duration = Duration.between(dateLastInteraction, LocalDateTime.now()).toMinutes(); //obter em minutos, há quanto tempo foi realizado a última interação do jogador com o connector

                        if(cooldown < duration)//se já passou o periodo de cooldown do jogador atual
                        {
                            System.out.println("charging your energy...");

                            if(energyMaxPlayer <= energyConnector)
                            {
                                root.getPlayerByName(playerName).setEnergy(energyMaxPlayer);
                                System.out.println("charged your energy successfully");
                                System.out.println("\nyour energy current: "+root.getPlayerByName(playerName).getEnergy());

                                //adicionar interação ao JSON
                                int id = connectorRandom.getIDLastInteraction(); //descobrir o último id de interação adicionado ao portal atual
                                LocalDateTime now = LocalDateTime.now(); //data agora

                                int level = root.getPlayerByName(playerName).getLevel(); //nivel do jogador
                                int points = root.getGameSettingByType("Reforço de energia de connector").getPoints(); //pontos do tipo de interação
                                int speedXP = root.getGameSettingByType("Reforço de energia de connector").getSpeedXp(); //velocidade de xp do tipo de interação

                                int pointsInteraction = (level/points)^speedXP;//definir os pontos (xp) que o jogador ganhou

                                interaction = new Interaction(id, "Reforço de energia de connector", playerName, now.toString(), pointsInteraction);
                                connectorRandom.addInteraction(interaction); //adicionar interação ao connector

                                player.defineLevelByXP(root, playerName, pointsInteraction);//define o nivel do jogador

                                exit = true;
                                runGameSecond(connectorRandom);
                            }
                            else if(energyMaxPlayer > energyConnector)
                            {
                                if((energyCurrentPlayer + energyConnector) <= energyMaxPlayer) //se nao atingir a energia maxima
                                {
                                    root.getPlayerByName(playerName).setEnergy(energyCurrentPlayer + energyConnector);
                                    System.out.println("charged your energy successfully");
                                    System.out.println("\nyour energy current: "+root.getPlayerByName(playerName).getEnergy());

                                    //adicionar interação ao JSON
                                    int id = connectorRandom.getIDLastInteraction(); //descobrir o último id de interação adicionado ao portal atual
                                    LocalDateTime now = LocalDateTime.now(); //data agora

                                    int level = root.getPlayerByName(playerName).getLevel(); //nivel do jogador
                                    int points = root.getGameSettingByType("Reforço de energia de connector").getPoints(); //pontos do tipo de interação
                                    int speedXP = root.getGameSettingByType("Reforço de energia de connector").getSpeedXp(); //velocidade de xp do tipo de interação

                                    int pointsInteraction = (level/points)^speedXP;//definir os pontos (xp) que o jogador ganhou

                                    interaction = new Interaction(id, "Reforço de energia de connector", playerName, now.toString(), pointsInteraction);
                                    connectorRandom.addInteraction(interaction); //adicionar interação ao connector

                                    player.defineLevelByXP(root, playerName, pointsInteraction);//define o nivel do jogador

                                    exit = true;
                                    runGameSecond(connectorRandom);
                                }
                                else
                                {
                                    root.getPlayerByName(playerName).setEnergy(energyCurrentPlayer + (energyMaxPlayer - energyConnector));
                                    System.out.println("charged your energy successfully");
                                    System.out.println("\nyour energy current: "+root.getPlayerByName(playerName).getEnergy());

                                    //adicionar interação ao JSON
                                    int id = connectorRandom.getIDLastInteraction(); //descobrir o último id de interação adicionado ao portal atual
                                    LocalDateTime now = LocalDateTime.now(); //data agora

                                    int level = root.getPlayerByName(playerName).getLevel(); //nivel do jogador
                                    int points = root.getGameSettingByType("Reforço de energia de connector").getPoints(); //pontos do tipo de interação
                                    int speedXP = root.getGameSettingByType("Reforço de energia de connector").getSpeedXp(); //velocidade de xp do tipo de interação

                                    int pointsInteraction = (level/points)^speedXP;//definir os pontos (xp) que o jogador ganhou

                                    interaction = new Interaction(id, "Reforço de energia de connector", playerName, now.toString(), pointsInteraction);
                                    connectorRandom.addInteraction(interaction); //adicionar interação ao connector

                                    player.defineLevelByXP(root, playerName, pointsInteraction);//define o nivel do jogador

                                    exit = true;
                                    runGameSecond(connectorRandom);
                                }
                            }
                        }
                        else if(cooldown > duration)//se NÃO passou o periodo de cooldown do jogador atual
                        {
                            System.out.println("\nconnector with cooldown. please wait more "+(cooldown - duration)+" minutes");
                            exit = true;
                        }
                        break;

                    case 99:
                        exit = true;
                        runGameFirst();
                        break;

                    default:
                        System.out.println("invalid option, selected option between 1 or 99 to go another location.");
                        break;
                }
            }
        }
    }

    //endregion


    //region IN GAME - CONNECTOR WITHOUT COOLDOWN AND GO ANY PORTAL

    private static void showConnectorCooldownGoPortalMenu(IConnector connectorCooldownGoPortalRandom, boolean firstTime) throws EmptyCollectionException, NotLocalInstanceException, java.text.ParseException, IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int option = 0;
        IInteraction interaction = null;

        int energyCurrentPlayer = root.getPlayerByName(playerName).getEnergy(); //energia atual do jogador
        int energyConnector = connectorCooldownGoPortalRandom.getEnergy();//energia atual do connector
        int energyMaxPlayer = root.getPlayerByName(playerName).getEnergyMax(); //energia máxima do jogador

        if(firstTime == false)
        {
            while (!exit)
            {
                System.out.println("\n");
                System.out.println("you are on the connector nº "+connectorCooldownGoPortalRandom.getId());
                System.out.println("+--------------------------------------+");
                System.out.println("|         CONNECTOR INTERACTIONS       |");
                System.out.println("+--------------------------------------+");
                System.out.println("your energy: "+energyCurrentPlayer);
                System.out.println("your max energy: "+energyMaxPlayer);
                System.out.println("energy connector: "+energyConnector);
                System.out.println("+--------------------------------------+");
                System.out.println("select an option to interact: ");
                System.out.println("+--------------------------------------+");
                System.out.println(
                        "| 01. Charge my energy                 |\n" +
                                "| 99. Go another location              |"
                );
                System.out.println("+--------------------------------------+");

                option = scanner.nextInt();

                switch (option)
                {
                    case 1:
                        String date = String.valueOf(root.getConnectorInteractionsByPlayerName(connectorCooldownGoPortalRandom.getId(), playerName));//data da ultima interação do jogador com connector
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                        LocalDateTime dateLastInteraction;

                        try
                        {
                            dateLastInteraction = LocalDateTime.parse(date, formatter);
                        }
                        catch (Exception e)
                        {
                            dateLastInteraction = LocalDateTime.of(1970, Month.JANUARY, 1, 0, 0, 0);
                        }

                        int cooldown = root.getConnectorByID(connectorCooldownGoPortalRandom.getId()).getCooldown(); //cooldown connector

                        long duration = Duration.between(dateLastInteraction, LocalDateTime.now()).toMinutes(); //obter em minutos, há quanto tempo foi realizado a última interação do jogador com o connector

                        if(cooldown < duration)//se já passou o periodo de cooldown do jogador atual
                        {
                            System.out.println("charging your energy...");

                            if(energyMaxPlayer <= energyConnector)
                            {
                                root.getPlayerByName(playerName).setEnergy(energyMaxPlayer);
                                System.out.println("charged your energy successfully");
                                System.out.println("\nyour energy current: "+root.getPlayerByName(playerName).getEnergy());

                                //adicionar interação ao JSON
                                int id = connectorCooldownGoPortalRandom.getIDLastInteraction(); //descobrir o último id de interação adicionado ao portal atual
                                LocalDateTime now = LocalDateTime.now(); //data agora

                                int level = root.getPlayerByName(playerName).getLevel(); //nivel do jogador
                                int points = root.getGameSettingByType("Reforço de energia de connector").getPoints(); //pontos do tipo de interação
                                int speedXP = root.getGameSettingByType("Reforço de energia de connector").getSpeedXp(); //velocidade de xp do tipo de interação

                                int pointsInteraction = (level/points)^speedXP;//definir os pontos (xp) que o jogador ganhou

                                interaction = new Interaction(id, "Reforço de energia de connector", playerName, now.toString(), pointsInteraction);
                                connectorCooldownGoPortalRandom.addInteraction(interaction); //adicionar interação ao connector

                                player.defineLevelByXP(root, playerName, pointsInteraction);//define o nivel do jogador

                                exit = true;

                                //ir para o portal mais próximo
                                IPortal portalRandom = (IPortal) root.getRoute(1, local).next(); //obter o portal mais perto
                                String teamPlayer = root.getPlayerByName(playerName).getTeam();//obter equipa do jogador atual
                                String ownershipPortal = portalRandom.getOwnership().getState(); //obter o dono do portal

                                showPortalMenu(portalRandom, teamPlayer, ownershipPortal, firstTime);
                            }
                            else if(energyMaxPlayer > energyConnector)
                            {
                                if((energyCurrentPlayer + energyConnector) <= energyMaxPlayer) //se nao atingir a energia maxima
                                {
                                    root.getPlayerByName(playerName).setEnergy(energyCurrentPlayer + energyConnector);
                                    System.out.println("charged your energy successfully");
                                    System.out.println("\nyour energy current: "+root.getPlayerByName(playerName).getEnergy());

                                    //adicionar interação ao JSON
                                    int id = connectorCooldownGoPortalRandom.getIDLastInteraction(); //descobrir o último id de interação adicionado ao portal atual
                                    LocalDateTime now = LocalDateTime.now(); //data agora

                                    int level = root.getPlayerByName(playerName).getLevel(); //nivel do jogador
                                    int points = root.getGameSettingByType("Reforço de energia de connector").getPoints(); //pontos do tipo de interação
                                    int speedXP = root.getGameSettingByType("Reforço de energia de connector").getSpeedXp(); //velocidade de xp do tipo de interação

                                    int pointsInteraction = (level/points)^speedXP;//definir os pontos (xp) que o jogador ganhou

                                    interaction = new Interaction(id, "Reforço de energia de connector", playerName, now.toString(), pointsInteraction);
                                    connectorCooldownGoPortalRandom.addInteraction(interaction); //adicionar interação ao connector

                                    player.defineLevelByXP(root, playerName, pointsInteraction);//define o nivel do jogador

                                    exit = true;

                                    //ir para o portal mais próximo
                                    IPortal portalRandom = (IPortal) root.getRoute(1, local).next(); //obter o portal mais perto
                                    String teamPlayer = root.getPlayerByName(playerName).getTeam();//obter equipa do jogador atual
                                    String ownershipPortal = portalRandom.getOwnership().getState(); //obter o dono do portal

                                    showPortalMenu(portalRandom, teamPlayer, ownershipPortal, firstTime);
                                }
                                else //se atingir a energia maxima
                                {
                                    root.getPlayerByName(playerName).setEnergy(Math.min(energyCurrentPlayer + energyConnector, energyMaxPlayer));
                                    System.out.println("charged your energy successfully");
                                    System.out.println("\nyour energy current: "+root.getPlayerByName(playerName).getEnergy());

                                    //adicionar interação ao JSON
                                    int id = connectorCooldownGoPortalRandom.getIDLastInteraction(); //descobrir o último id de interação adicionado ao portal atual
                                    LocalDateTime now = LocalDateTime.now(); //data agora

                                    int level = root.getPlayerByName(playerName).getLevel(); //nivel do jogador
                                    int points = root.getGameSettingByType("Reforço de energia de connector").getPoints(); //pontos do tipo de interação
                                    int speedXP = root.getGameSettingByType("Reforço de energia de connector").getSpeedXp(); //velocidade de xp do tipo de interação

                                    int pointsInteraction = (level/points)^speedXP;//definir os pontos (xp) que o jogador ganhou

                                    interaction = new Interaction(id, "Reforço de energia de connector", playerName, now.toString(), pointsInteraction);
                                    connectorCooldownGoPortalRandom.addInteraction(interaction); //adicionar interação ao connector

                                    player.defineLevelByXP(root, playerName, pointsInteraction);//define o nivel do jogador

                                    exit = true;

                                    //ir para o portal mais próximo
                                    IPortal portalRandom = (IPortal) root.getRoute(1, local).next(); //obter o portal mais perto
                                    String teamPlayer = root.getPlayerByName(playerName).getTeam();//obter equipa do jogador atual
                                    String ownershipPortal = portalRandom.getOwnership().getState(); //obter o dono do portal

                                    showPortalMenu(portalRandom, teamPlayer, ownershipPortal, firstTime);
                                }
                            }
                        }
                        else if(cooldown > duration)//se NÃO passou o periodo de cooldown do jogador atual
                        {
                            System.out.println("\nconnector with cooldown. please wait more "+(cooldown - duration)+" minutes");
                            exit = true;
                        }
                        break;

                    case 99:
                        exit = true;

                        break;

                    default:
                        System.out.println("invalid option, selected option between 1 or 99 to go another location.");
                        break;
                }
            }
        }
        else if(firstTime = true)
        {
            while (!exit)
            {
                System.out.println("\n");
                System.out.println("you are on the connector nº "+connectorCooldownGoPortalRandom.getId());
                System.out.println("+--------------------------------------+");
                System.out.println("|         CONNECTOR INTERACTIONS       |");
                System.out.println("+--------------------------------------+");
                System.out.println("your energy: "+energyCurrentPlayer);
                System.out.println("your max energy: "+energyMaxPlayer);
                System.out.println("energy connector: "+energyConnector);
                System.out.println("+--------------------------------------+");
                System.out.println("select an option to interact: ");
                System.out.println("+--------------------------------------+");
                System.out.println(
                        "| 01. Charge my energy                 |\n" +
                                "| 99. Go another location              |"
                );
                System.out.println("+--------------------------------------+");

                option = scanner.nextInt();

                switch (option)
                {
                    case 1:
                        String date = String.valueOf(root.getConnectorInteractionsByPlayerName(connectorCooldownGoPortalRandom.getId(), playerName));//data da ultima interação do jogador com connector
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                        LocalDateTime dateLastInteraction;

                        try
                        {
                            dateLastInteraction = LocalDateTime.parse(date, formatter);
                        }
                        catch (Exception e)
                        {
                            dateLastInteraction = LocalDateTime.of(1970, Month.JANUARY, 1, 0, 0, 0);
                        }

                        int cooldown = root.getConnectorByID(connectorCooldownGoPortalRandom.getId()).getCooldown(); //cooldown connector

                        long duration = Duration.between(dateLastInteraction, LocalDateTime.now()).toMinutes(); //obter em minutos, há quanto tempo foi realizado a última interação do jogador com o connector

                        if(cooldown < duration)//se já passou o periodo de cooldown do jogador atual
                        {
                            System.out.println("charging your energy...");

                            if(energyMaxPlayer <= energyConnector)
                            {
                                root.getPlayerByName(playerName).setEnergy(energyMaxPlayer);
                                System.out.println("charged your energy successfully");
                                System.out.println("\nyour energy current: "+root.getPlayerByName(playerName).getEnergy());

                                //adicionar interação ao JSON
                                int id = connectorCooldownGoPortalRandom.getIDLastInteraction(); //descobrir o último id de interação adicionado ao portal atual
                                LocalDateTime now = LocalDateTime.now(); //data agora

                                int level = root.getPlayerByName(playerName).getLevel(); //nivel do jogador
                                int points = root.getGameSettingByType("Reforço de energia de connector").getPoints(); //pontos do tipo de interação
                                int speedXP = root.getGameSettingByType("Reforço de energia de connector").getSpeedXp(); //velocidade de xp do tipo de interação

                                int pointsInteraction = (level/points)^speedXP;//definir os pontos (xp) que o jogador ganhou

                                interaction = new Interaction(id, "Reforço de energia de connector", playerName, now.toString(), pointsInteraction);
                                connectorCooldownGoPortalRandom.addInteraction(interaction); //adicionar interação ao connector

                                player.defineLevelByXP(root, playerName, pointsInteraction);//define o nivel do jogador

                                exit = true;

                                //ir para o portal mais próximo
                                IPortal portalRandom = (IPortal) root.getRoute(1, local).next(); //obter o portal mais perto
                                String teamPlayer = root.getPlayerByName(playerName).getTeam();//obter equipa do jogador atual
                                String ownershipPortal = portalRandom.getOwnership().getState(); //obter o dono do portal

                                showPortalMenu(portalRandom, teamPlayer, ownershipPortal, firstTime);
                            }
                            else if(energyMaxPlayer > energyConnector)
                            {
                                if((energyCurrentPlayer + energyConnector) <= energyMaxPlayer) //se nao atingir a energia maxima
                                {
                                    root.getPlayerByName(playerName).setEnergy(energyCurrentPlayer + energyConnector);
                                    System.out.println("charged your energy successfully");
                                    System.out.println("\nyour energy current: "+root.getPlayerByName(playerName).getEnergy());

                                    //adicionar interação ao JSON
                                    int id = connectorCooldownGoPortalRandom.getIDLastInteraction(); //descobrir o último id de interação adicionado ao portal atual
                                    LocalDateTime now = LocalDateTime.now(); //data agora

                                    int level = root.getPlayerByName(playerName).getLevel(); //nivel do jogador
                                    int points = root.getGameSettingByType("Reforço de energia de connector").getPoints(); //pontos do tipo de interação
                                    int speedXP = root.getGameSettingByType("Reforço de energia de connector").getSpeedXp(); //velocidade de xp do tipo de interação

                                    int pointsInteraction = (level/points)^speedXP;//definir os pontos (xp) que o jogador ganhou

                                    interaction = new Interaction(id, "Reforço de energia de connector", playerName, now.toString(), pointsInteraction);
                                    connectorCooldownGoPortalRandom.addInteraction(interaction); //adicionar interação ao connector

                                    player.defineLevelByXP(root, playerName, pointsInteraction);//define o nivel do jogador

                                    exit = true;

                                    //ir para o portal mais próximo
                                    IPortal portalRandom = (IPortal) root.getRoute(1, local).next(); //obter o portal mais perto
                                    String teamPlayer = root.getPlayerByName(playerName).getTeam();//obter equipa do jogador atual
                                    String ownershipPortal = portalRandom.getOwnership().getState(); //obter o dono do portal

                                    showPortalMenu(portalRandom, teamPlayer, ownershipPortal, firstTime);
                                }
                                else
                                {
                                    root.getPlayerByName(playerName).setEnergy(energyCurrentPlayer + (energyMaxPlayer - energyConnector));
                                    System.out.println("charged your energy successfully");
                                    System.out.println("\nyour energy current: "+root.getPlayerByName(playerName).getEnergy());

                                    //adicionar interação ao JSON
                                    int id = connectorCooldownGoPortalRandom.getIDLastInteraction(); //descobrir o último id de interação adicionado ao portal atual
                                    LocalDateTime now = LocalDateTime.now(); //data agora

                                    int level = root.getPlayerByName(playerName).getLevel(); //nivel do jogador
                                    int points = root.getGameSettingByType("Reforço de energia de connector").getPoints(); //pontos do tipo de interação
                                    int speedXP = root.getGameSettingByType("Reforço de energia de connector").getSpeedXp(); //velocidade de xp do tipo de interação

                                    int pointsInteraction = (level/points)^speedXP;//definir os pontos (xp) que o jogador ganhou

                                    interaction = new Interaction(id, "Reforço de energia de connector", playerName, now.toString(), pointsInteraction);
                                    connectorCooldownGoPortalRandom.addInteraction(interaction); //adicionar interação ao connector

                                    player.defineLevelByXP(root, playerName, pointsInteraction);//define o nivel do jogador

                                    exit = true;

                                    //ir para o portal mais próximo
                                    IPortal portalRandom = (IPortal) root.getRoute(1, local).next(); //obter o portal mais perto
                                    String teamPlayer = root.getPlayerByName(playerName).getTeam();//obter equipa do jogador atual
                                    String ownershipPortal = portalRandom.getOwnership().getState(); //obter o dono do portal

                                    showPortalMenu(portalRandom, teamPlayer, ownershipPortal, firstTime);
                                }
                            }
                        }
                        else if(cooldown > duration)//se NÃO passou o periodo de cooldown do jogador atual
                        {
                            System.out.println("\nconnector with cooldown. please wait more "+(cooldown - duration)+" minutes");
                            exit = true;
                        }
                        break;

                    case 99:
                        exit = true;

                        break;

                    default:
                        System.out.println("invalid option, selected option between 1 or 99 to go another location.");
                        break;
                }
            }
        }
    }

    //endregion


    //region IN GAME - CONNECTOR WITHOUT COOLDOWN AND GO ANY CONNECTOR

    private static void showConnectorCooldownGoConnectorMenu(IConnector connectorCooldownGoConnectorRandom, boolean firstTime)
    {

    }

    //endregion


    //region API MENU

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
            System.out.println("+--------------------------------------+");

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
    private static void showGameManagementMenu() throws IOException, ParseException {
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
            System.out.println("+--------------------------------------------------------------------------------+");

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
                    iEJson.importFromJSONFile(root, local, "docs/import/import.json");
                    break;

                case 6:
                    //root.exportGameSettingsToJson();
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
                            "| 07. Export statistics                                  |\n" +
                            "| 99. Back to previous menu                              |"
            );
            System.out.println("+--------------------------------------------------------+");

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
            System.out.println("+--------------------------------------+");

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
    public static void showPortalsManagementMenu() throws IOException, ParseException {
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
            System.out.println("+--------------------------------------+");

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
                    iEJson.importFromJSONFile(root, local, "docs/import/import.json");
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
    private static void showConnectorsManagementMenu() throws IOException, ParseException {
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
            System.out.println("+--------------------------------------+");

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
                    iEJson.importFromJSONFile(root, local, "docs/import/import.json");
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

    //endregion


    public static void main(String[] args) throws IOException, ParseException, EmptyCollectionException, NotLocalInstanceException, java.text.ParseException {
        showMainMenu();
    }

    public String greeting() {
        if (value.equals("")) {
            throw new IllegalArgumentException("vazio");
        }

        return value;
    }
}
