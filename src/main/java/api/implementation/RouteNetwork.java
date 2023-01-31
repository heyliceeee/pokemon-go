package api.implementation;

import api.interfaces.*;
import collections.implementation.Network;

import java.util.Iterator;

/**
 * Classe que implementa o contracto de um grafo de rede de route
 * @param <T> tipo de locais no grafo
 */
public class RouteNetwork<T> extends Network<T> implements RouteNetworkADT<T>
{

    @Override
    public int getNumberOfPortals() {
        return 0;
    }

    @Override
    public int getNumberOfConnectors() {
        return 0;
    }

    @Override
    public Iterator<IPortal> getPortals() {
        return null;
    }

    @Override
    public Iterator<IConnector> getConnectors() {
        return null;
    }

    @Override
    public Iterator<IRoute<ILocal>> getRoutes() {
        return null;
    }
}
