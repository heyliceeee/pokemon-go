package api.implementation;


import api.interfaces.*;
import collections.exceptions.NotLocalInstanceException;
import collections.implementation.Network;
import collections.interfaces.PathNetworkADT;

import java.util.Iterator;
import java.util.List;

/**
 * Classe que implementa o contrato de um grafo de rede de route
 * @param <T> tipo de locais no grafo
 */
public class PathNetwork<T> extends Network<T> implements PathNetworkADT<T>
{
    //CONTINUAR AQUI

    @Override
    public int getNumberOfPortals()
    {
        Ownership ownership = null;
        ownership.setState("no team");
        ownership.setPlayer("");

        Coordinate coordinate = null;
        coordinate.setLongitude(41.5);
        coordinate.setLatitude(42.9);

        List<Interaction> interaction = null;

        return this.getNumberOf(new Portal(1, "Portal", "", 80, 100, ownership, coordinate, interaction));
    }

    @Override
    public int getNumberOfConnectors()
    {
        Coordinate coordinate = null;
        coordinate.setLongitude(21.5);
        coordinate.setLatitude(22.9);

        List<Interaction> interaction = null;

        return this.getNumberOf(new Connector(2, "Connector", 100, 5, coordinate, interaction));
    }

    @Override
    public Iterator<IPortal> getPortals()
    {
        UnorderedListADT<IPortal> resultList = new ArrayUnorderedList<>();

        for (int i = 0; i < super.numVertices; i++)
        {
            if (super.vertices[i] instanceof IPortal)
            {
                resultList.addToRear((IPortal) super.vertices[i]);
            }
        }

        return resultList.iterator();
    }

    @Override
    public Iterator<IConnector> getConnectors()
    {
        UnorderedListADT<IConnector> resultList = new ArrayUnorderedList<>();

        for (int i = 0; i < super.numVertices; i++)
        {
            if (super.vertices[i] instanceof IConnector)
            {
                resultList.addToRear((IConnector) super.vertices[i]);
            }
        }

        return resultList.iterator();
    }

    @Override
    public Iterator<IRoute> getRoutes()
    {
        UnorderedListADT<IRoute> resultList = new ArrayUnorderedList<>();

        for (int i = 0; i < super.numVertices; i++)
        {
            for (int j = i; j < super.numVertices; j++)
            {
                if (super.adjMatrix[i][j] != 0)
                {
                    IRoute path = new Route((int)super.vertices[i], (int)super.vertices[j]);
                    resultList.addToRear(path);
                }
            }
        }

        return resultList.iterator();
    }

    /**
     * Conta o número de instâncias que existem desse tipo
     * @param type objeto para ser comparado
     * @return numero de vezes
     * @param <T> tipo de variavel que vai ser comparado
     */
    private <T extends ILocal> int getNumberOf(T type)
    {
        int count = 0;

        for (int i = 0; i < super.numVertices; i++)
        {
            if (super.vertices[i].getClass().isInstance(type))
            {
                count++;
            }
        }

        return count;
    }


    private Iterator<ILocal> shortestRouteTo(SEARCH_TYPE searchType, int[] previous, double[] distance, boolean[] visited, T source) throws NotLocalInstanceException
    {

    }


    @Override
    public Iterator<ILocal> shortestRoute(T source, T destination) throws NotLocalInstanceException {
        return null;
    }

    @Override
    public Iterator<ILocal> shortestRoutePassingConnector(T source, T destination) throws NotLocalInstanceException {
        return null;
    }
}
