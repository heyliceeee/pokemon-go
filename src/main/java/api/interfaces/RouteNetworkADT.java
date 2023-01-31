package api.interfaces;

import api.interfaces.IConnector;
import api.interfaces.ILocal;
import api.interfaces.IPortal;
import api.interfaces.IRoute;
import collections.interfaces.NetworkADT;

import java.util.Iterator;

/**
 * Contrato de um gráfico de caminho no campo de caminhos a jogadores
 * @param <T> tipo de locais no grafo
 */
public interface RouteNetworkADT<T> extends NetworkADT<T>
{
    /**
     * Retorna o número {@link api.implementation.Portal portals} do grafo
     * @return o número {@link api.implementation.Portal portals} do grafo
     */
    int getNumberOfPortals();

    /**
     * Retorna o número {@link api.implementation.Connector conectores} do grafo
     * @return o número {@link api.implementation.Connector conectores} do grafo
     */
    int getNumberOfConnectors();

    /**
     * Retorna os portais do grafo
     * @return iterador com portais
     */
    Iterator<IPortal> getPortals();

    /**
     * Retorna os conectores do grafo
     * @return iterador com conectores
     */
    Iterator<IConnector> getConnectors();

    /**
     * Retorna as routes existentes no grafo
     * @return iterador com as rotas
     */
    Iterator<IRoute<ILocal>> getRoutes();
}
