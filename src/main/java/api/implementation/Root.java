package api.implementation;

import api.exceptions.ElementAlreadyExistsException;
import api.interfaces.*;
import collections.exceptions.ElementNotFoundException;
import collections.exceptions.EmptyCollectionException;
import collections.implementation.ArrayUnorderedList;
import collections.implementation.ExporterGraph;
import collections.interfaces.IExporter;
import api.interfaces.RouteNetworkADT;
import collections.interfaces.UnorderedListADT;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
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
    public RouteNetworkADT<ILocal> routeNetwork = new RouteNetwork<>();

    /**
     * Lista de jogadores associados ao jogo
     */
    public UnorderedListADT<IPlayer> players = new ArrayUnorderedList<>();

    /**
     * Instância do exportar que transforma o grafo em uma imagem
     */
    private final IExporter exporter = new ExporterGraph("docs/export");


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
    public String addRoute(ILocal local1, ILocal local2, double weight) throws EmptyCollectionException
    {
        this.routeNetwork.addEdge(local1, local2, weight);

        return "Successful";
    }

    @Override
    public void exportGraph() throws EmptyCollectionException, InterruptedException
    {
        exporter.exportGraph(this.routeNetwork, "route");
    }

    @Override
    public Iterator<ILocal> getRoute() throws EmptyCollectionException
    {
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
    public void exportPlayersToJson() throws IOException {

    }

    @Override
    public void exportPortalsToJson() throws IOException {

    }

    @Override
    public void exportConnectorsToJson() throws IOException {

    }

    @Override
    public void exportRoutesToJson() throws IOException {

    }

    @Override
    public void exportRootToJson() throws IOException {

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
        return null;
    }

    /**
     * Colocar todos os portais associados ao jogo em uma JSONArray
     * @return o JSONArray com todos os portais
     */
    private JSONArray getPortalsJSONArray()
    {return null;}

    /**
     * Colocar todos os conectores associados ao jogo em uma JSONArray
     * @return o JSONArray com todos os conectores
     */
    private JSONArray getConnectorsJSONArray()
    {return null;}

    /**
     * Transforma a route (entre 2 locals do grafo) enviados por parametro em JSONObject
     * @param route
     * @return o JSONObject da route
     */
    private JSONObject routeToJSONObject(IRoute<ILocal> route)
    {return null;}

    /**
     * Colocar todas as routes do grafo numa JSONArray
     * @return a JSONArray com todas as routes existentes do grafo
     */
    private JSONArray getRoutesJSONArray()
    {return null;}

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
        Iterator<IConnector> iterator = this.routeNetwork.getConnectors();
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