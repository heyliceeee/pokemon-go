package api.implementation;

import api.exceptions.ElementAlreadyExistsException;
import api.interfaces.*;
import collections.exceptions.ElementNotFoundException;
import collections.exceptions.EmptyCollectionException;
import collections.exceptions.NotLocalInstanceException;
import collections.implementation.ArrayUnorderedList;
import api.interfaces.RouteNetworkADT;
import collections.interfaces.UnorderedListADT;
import demo.Demo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Representacao da classe da raiz do JSON
 */
public class Root implements IRoot
{
    /**
     * grafo de rede que contém informações sobre os locals e rotas entre eles
     */
    public static RouteNetworkADT<ILocal> routeNetwork = new RouteNetwork<>();

    /**
     * Lista de jogadores associados ao jogo
     */
    public static UnorderedListADT<IPlayer> players = new ArrayUnorderedList<>();

    /**
     * Lista de configurações associados ao jogo
     */
    public static UnorderedListADT<IGameSetting> gameSettings = new ArrayUnorderedList<>();


    @Override
    public String addLocal(ILocal local)
    {
        return this.routeNetwork.addVertex(local);
    }

    @Override
    public String addPlayer(IPlayer player)
    {
        if(player == null)
        {
            throw new IllegalArgumentException("Player cannot be null");
        }

        String s = "Failed";

        if(this.players.isEmpty() || !this.players.contains(player)) //se a lista de jogadores estiver vazia ou não conter o jogador a ser adicionado, adiciona-o á lista
        {
            this.players.addToRear(player); //adiciona o player no fim da lista
            s = "Successful";
        }

        return s;
    }

    @Override
    public String removePlayer(IPlayer player) throws EmptyCollectionException {
        if(player == null)
        {
            throw new IllegalArgumentException("Player cannot be null");
        }

        String s = "Failed";

        if(this.players.isEmpty() || this.players.contains(player)) //se a lista de jogadores estiver vazia ou não conter o jogador a ser adicionado, adiciona-o á lista
        {
            this.players.remove(player); //remove o player no fim da lista
            s = "Successful";
        }

        return s;
    }

    @Override
    public String addRoute(ILocal local1, ILocal local2, double weight) throws EmptyCollectionException
    {
        this.routeNetwork.addEdge(local1, local2, weight);

        return "Successful";
    }

    @Override
    public String addGameSetting(IGameSetting gameSetting)
    {
        if(gameSetting == null)
        {
            throw new IllegalArgumentException("Game setting cannot be null");
        }

        String s = "Failed";

        if(this.gameSettings.isEmpty() || !this.gameSettings.contains(gameSetting)) //se a lista de gameSetting estiver vazia ou não conter o gameSetting a ser adicionado, adiciona-o á lista
        {
            this.gameSettings.addToRear(gameSetting); //adiciona o gameSetting no fim da lista
            s = "Successful";
        }

        return s;
    }

    @Override
    public void exportGraph() throws EmptyCollectionException, InterruptedException
    {
        Demo.exporter.exportGraph(this.routeNetwork, "route");
    }

    @Override
    public Iterator<ILocal> getRoute(IRoot root, int option, ILocal local) throws EmptyCollectionException, NotLocalInstanceException, ParseException {
        Iterator<ILocal> localsIterator;

        switch (option)
        {
            case 1:
                localsIterator = routeNetwork.shortestRouteToPortal(root, local);
                return localsIterator;

            case 2:
                localsIterator = routeNetwork.shortestRouteToConnector(root, local);
                return localsIterator;

            case 3:
                localsIterator = routeNetwork.shortestRouteToConnector(root, local);
                return localsIterator;

            case 4:
                localsIterator = routeNetwork.shortestRouteToConnector(root, local);
                return localsIterator;
        }

        return null;
    }

    @Override
    public String getPlayersListing()
    {
        String s = "Players: {\n";

        if(!this.players.isEmpty())
        {
            Iterator<IPlayer> iterator = players.iterator();

            while (iterator.hasNext())
            {
                s += iterator.next().toString() + "\n";
            }
        }
        else
        {
            s += "There is no players to list!\n";
        }

        s += "}";

        return s;
    }

    @Override
    public String getPortalsListing()
    {
        String s = "Portals: {\n";

        if (this.routeNetwork.getNumberOfPortals() != 0)
        {
            Iterator<IPortal> iteratorPortal = this.routeNetwork.getPortals();

            while (iteratorPortal.hasNext())
            {
                s += iteratorPortal.next().toString() + "\n";
            }
        }
        else
        {
            s += "There is no portals to list!\n";
        }

        s += "}";

        return s;
    }

    @Override
    public String getConnectorsListing()
    {
        String s = "Connectors: {\n";

        if (this.routeNetwork.getNumberOfConnectors() != 0)
        {
            Iterator<IConnector> iteratorConnector = this.routeNetwork.getConnectors();

            while (iteratorConnector.hasNext())
            {
                s += iteratorConnector.next().toString() + "\n";
            }
        }
        else
        {
            s += "There is no connectors to list!\n";
        }

        s += "}";

        return s;
    }

    @Override
    public void exportPlayersToJson() throws IOException
    {
        Demo.iEJson.exportToJSONFile(getPlayersJSONArray().toJSONString(), "Players");
    }

    @Override
    public void exportPortalsToJson() throws IOException
    {
        Demo.iEJson.exportToJSONFile(getPortalsJSONArray().toJSONString(), "Portals");
    }

    @Override
    public void exportConnectorsToJson() throws IOException
    {
        Demo.iEJson.exportToJSONFile(getConnectorsJSONArray().toJSONString(), "Connectors");
    }

    @Override
    public void exportRoutesToJson() throws IOException
    {
        Demo.iEJson.exportToJSONFile(getRoutesJSONArray().toJSONString(), "Routes");
    }


    @Override
    public void exportGameSettingsToJson() throws IOException
    {
        Demo.iEJson.exportToJSONFile(getGameSettingsJSONArray().toJSONString(), "GameSettings");
    }

    @Override
    public void exportRootToJson() throws IOException {

        JSONObject root = new JSONObject();

        root.put("portals", getPortalsJSONArray());
        root.put("connectors", getConnectorsJSONArray());
        root.put("routes", getRoutesJSONArray());
        root.put("players", getPlayersJSONArray());
        root.put("gameSettings", getGameSettingsJSONArray());

        Demo.iEJson.exportToJSONFile(root.toJSONString(), "Root");
    }

    private JSONArray getGameSettingsJSONArray()
    {
        JSONArray gameSettingsArray = new JSONArray();
        Iterator<IGameSetting> iteratorGameSetting = this.gameSettings.iterator();

        while (iteratorGameSetting.hasNext())
        {
            gameSettingsArray.add(iteratorGameSetting.next().gameSettingToJsonObject());
        }

        return gameSettingsArray;
    }

    /**
     * Exporta e mostra uma route no grafo
     * @param routeIterator route a ser visualizado
     */
    void exportRouteInGraph(Iterator<ILocal> routeIterator)
    {}

    /**
     * Colocar todos os jogadores associados ao jogo em uma JSONArray
     * @return o JSONArray com todos os jogadores
     */
    private JSONArray getPlayersJSONArray()
    {
        JSONArray playersArray = new JSONArray();
        Iterator<IPlayer> iteratorPlayer = this.players.iterator();

        while (iteratorPlayer.hasNext())
        {
            playersArray.add(iteratorPlayer.next().playerToJsonObject());
        }

        return playersArray;
    }

    /**
     * Colocar todos os portais associados ao jogo em uma JSONArray
     * @return o JSONArray com todos os portais
     */
    private JSONArray getPortalsJSONArray()
    {
        JSONArray portalsArray = new JSONArray();
        Iterator<IPortal> iteratorPortal = this.routeNetwork.getPortals();

        while (iteratorPortal.hasNext())
        {
            portalsArray.add(iteratorPortal.next().portalToJSONObject());
        }

        return portalsArray;
    }

    /**
     * Colocar todos os conectores associados ao jogo em uma JSONArray
     * @return o JSONArray com todos os conectores
     */
    private JSONArray getConnectorsJSONArray()
    {
        JSONArray connectorsArray = new JSONArray();
        Iterator<IConnector> iteratorConnector = this.routeNetwork.getConnectors();

        while (iteratorConnector.hasNext())
        {
            connectorsArray.add(iteratorConnector.next().connectorToJSONObject());
        }

        return connectorsArray;
    }

    /**
     * Transforma a route (entre 2 locals do grafo) enviados por parametro em JSONObject
     * @param route
     * @return o JSONObject da route
     */
    private JSONObject routeToJSONObject(IRoute<ILocal> route)
    {
        JSONObject routeObject = new JSONObject();

        routeObject.put("from", route.getFrom().getId());
        routeObject.put("to", route.getTo().getId());
        routeObject.put("distance", route.getWeight());

        return routeObject;
    }

    /**
     * Colocar todas as routes do grafo numa JSONArray
     * @return a JSONArray com todas as routes existentes do grafo
     */
    private JSONArray getRoutesJSONArray()
    {
        JSONArray routesArray = new JSONArray();
        Iterator<IRoute<ILocal>> iteratorRoute = this.routeNetwork.getRoutes();

        while (iteratorRoute.hasNext())
        {
            IRoute<ILocal> route = iteratorRoute.next();
            routesArray.add(routeToJSONObject(route));
        }

        return routesArray;
    }

    @Override
    public ILocal getLocalByID(int id)
    {
        IPortal portal = getPortalByID(id);
        IConnector connector = getConnectorByID(id);

        if(portal != null)
        {
            return portal;
        }
        else if(connector != null)
        {
            return connector;
        }
        else
        {
            return null;
        }
    }

    @Override
    public IPortal getRandomPortal()
    {
        Iterator<IPortal> iterator = this.routeNetwork.getPortals();
        IPortal portal;

        int size = this.routeNetwork.getNumberOfPortals(); //número de portais existentes
        Random r = new Random();
        int randomPortal = r.nextInt(size - 0) + 0; //random número de portal

        int count=0;
        while (iterator.hasNext())
        {
            portal = iterator.next();

            if(count == randomPortal)
            {
                return portal;
            }

            count++;
        }

        return null;
    }

    @Override
    public IPortal getPortalByID(int id)
    {
        Iterator<IPortal> iterator = this.routeNetwork.getPortals();
        IPortal portal;

        while (iterator.hasNext())
        {
            portal = iterator.next();

            if(portal.getId() == id)
            {
                return portal;
            }
        }

        return null;
    }

    @Override
    public void setPortalID(int id, int newID) throws ElementNotFoundException, ElementAlreadyExistsException {

    }

    @Override
    public void setPortalType(int id, String type) throws ElementNotFoundException {

    }

    @Override
    public void setPortalName(int id, String name) throws ElementNotFoundException {

    }

    @Override
    public void setPortalEnergy(int id, int energy) throws ElementNotFoundException {

    }

    @Override
    public void setPortalEnergyMax(int id, int energyMax) throws ElementNotFoundException {

    }

    @Override
    public void setPortalOwnership(int id, Ownership ownership) throws ElementNotFoundException {

    }

    @Override
    public void setPortalCoordinate(int id, Coordinate coordinate) throws ElementNotFoundException {

    }

    @Override
    public void setPortalInteraction(int id, List<Interaction> interaction) throws ElementNotFoundException {

    }

    @Override
    public IConnector getRandomConnector()
    {
        Iterator<IConnector> iterator = routeNetwork.getConnectors();
        IConnector connector;

        int size = this.routeNetwork.getNumberOfConnectors(); //número de conectores existentes
        Random r = new Random();
        int randomConnector = r.nextInt(size - 0) + 0; //random número de conector

        int count=0;
        while (iterator.hasNext())
        {
            connector = iterator.next();

            if(count == randomConnector)
            {
                return connector;
            }

            count++;
        }

        return null;
    }

    @Override
    public IConnector getConnectorByID(int id)
    {
        Iterator<IConnector> iterator = this.routeNetwork.getConnectors();
        IConnector connector;

        while (iterator.hasNext())
        {
            connector = iterator.next();

            if(connector.getId() == id)
            {
                return connector;
            }
        }

        return null;
    }

    @Override
    public IInteraction getConnectorInteractionsByPlayerName(int idConnector, String playerName)
    {
        Iterator<IConnector> iterator = this.routeNetwork.getConnectors();
        IConnector connector;

        while (iterator.hasNext())
        {
            connector = iterator.next();

            if(connector.getId() == idConnector)
            {
                IInteraction lastConnectorInteractionByPlayerName = connector.getConnectorLastInteractionByPlayerName(idConnector, playerName);

                if(lastConnectorInteractionByPlayerName == null) //caso não haja interações com o jogador
                {
                    return null;
                }
                else
                {
                    return lastConnectorInteractionByPlayerName;
                }
            }
        }

        return null;
    }

    @Override
    public void setConnectorID(int id, int newID) throws ElementNotFoundException, ElementAlreadyExistsException {

    }

    @Override
    public void setConnectorType(int id, String type) {

    }

    @Override
    public void setConnectorEnergy(int id, int energy) {

    }

    @Override
    public void setConnectorCooldown(int id, int cooldown) {

    }

    @Override
    public void setConnectorCoordinate(int id, Coordinate coordinate) {

    }

    @Override
    public void setConnectorInteraction(int id, List<Interaction> interaction) {

    }

    @Override
    public IPlayer getPlayerByName(String name)
    {
        Iterator<IPlayer> iterator = this.players.iterator();
        IPlayer player;

        while (iterator.hasNext())
        {
            player = iterator.next();

            if(player.getName().equals(name))
            {
                return player;
            }
        }

        return null;
    }

    @Override
    public void setPlayerTeam(String name, String team) {

    }

    @Override
    public void setPlayerLevel(String name, int level) {

    }

    @Override
    public void setPlayerXP(String name, int xp) {

    }

    @Override
    public void setPlayerEnergy(String name, int energy) {

    }

    @Override
    public void setPlayerEnergyMax(String name, int energyMax) {

    }

    @Override
    public void setPlayerCoordinate(String name, Coordinate coordinate) {

    }

    @Override
    public void setPlayerConqueredPortals(String name, int conqueredPortals) {

    }

    @Override
    public IGameSetting getGameSettingByType(String type)
    {
        Iterator<IGameSetting> iterator = this.gameSettings.iterator();
        IGameSetting gameSetting;

        while (iterator.hasNext())
        {
            gameSetting = iterator.next();

            if(gameSetting.getType().equals(type))
            {
                return gameSetting;
            }
        }

        return null;
    }

    @Override
    public Iterator<IPortal> getPortalsOrderBy(SortType sortType) {
        return null;
    }

    @Override
    public Iterator<IConnector> getConnectorsOrderBy(SortType sortType) {
        return null;
    }

    @Override
    public Iterator<IPlayer> getPlayersOrderBy(SortType sortType) {
        return null;
    }

    @Override
    public Iterator<IRoute<ILocal>> getRoutesOrderBy(SortType sortType) {
        return null;
    }

    //region getters and setters



    //endregion
}