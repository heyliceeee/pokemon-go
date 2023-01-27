package api.implementation;

import api.interfaces.ILocal;
import api.interfaces.IRoot;
import collections.implementation.RouteNetwork;
import collections.interfaces.RouteNetworkADT;

import java.util.ArrayList;
import java.util.List;

/**
 * Representacao da classe da raiz do JSON
 */
public class Root implements IRoot
{
    /**
     * locais (portal e connector)
     */
    private List<Local> locals;

    /**
     * rotas
     */
    private List<Route> routes;

    /**
     * jogadores
     */
    private List<Player> players;

    /**
     * grafo de rede que contém informações sobre os locals e rotas entre eles
     */
    public RouteNetworkADT<ILocal> routeNetwork = new RouteNetwork<>();


    @Override
    public String addLocal(ILocal local)
    {
        if(local == null)
        {
            throw new IllegalArgumentException("Local cannot be null");
        }

        return this.routeNetwork.addVertex(local);
    }



    @Override
    public String toString() {
        return "Root{" +
                "locals=" + locals +
                ", routes=" + routes +
                ", players=" + players +
                '}';
    }

    //region getters and setters
    @Override
    public List<Local> getLocals()
    {
        return locals;
    }

    @Override
    public void setLocals(List<Local> locals)
    {
        this.locals = locals;
    }

    @Override
    public List<Route> getRoutes()
    {
        return routes;
    }

    @Override
    public void setRoutes(List<Route> routes)
    {
        this.routes = routes;
    }

    @Override
    public List<Player> getPlayers()
    {
        return players;
    }

    @Override
    public void setPlayers(List<Player> players)
    {
        this.players = players;
    }
    //endregion
}