package api.interfaces;

import api.interfaces.IConnector;
import api.interfaces.ILocal;
import api.interfaces.IPortal;
import api.interfaces.IRoute;
import collections.exceptions.NotLocalInstanceException;
import collections.interfaces.NetworkADT;

import java.text.ParseException;
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

    /**
     * Rota mais curta do ponto de partida até ao portal mais proximo dos portais do grafo
     * @param source ponto de partida
     * @return iterador com a route
     * @throws NotLocalInstanceException se o ponto de partida não for uma instância {@link ILocal local}
     */
    Iterator<ILocal> shortestRouteToPortal(T source) throws NotLocalInstanceException, ParseException;

    /**
     * Rota mais curta do ponto de partida até ao conector mais proximo dos conectores do grafo
     * @param source ponto de partida
     * @return iterador com o conector
     * @throws NotLocalInstanceException se o ponto de partida não for uma instância {@link ILocal local}
     */
    Iterator<ILocal> shortestRouteToConnector(T source) throws NotLocalInstanceException, ParseException;

    /**
     * Rota mais curta do ponto de partida passa pelo ao conector sem cooldown até ao portal mais proximo dos portais do grafo
     * @param source ponto de partida
     * @return iterador
     * @throws NotLocalInstanceException se o ponto de partida não for uma instância {@link ILocal local}
     */
    Iterator<ILocal> shortestRouteGoConnectorWithoutCooldownToPortal(T source) throws NotLocalInstanceException, ParseException;

    /**
     * Rota mais curta do ponto de partida passa pelo ao conector sem cooldown até ao conector mais proximo dos conectores do grafo
     * @param source ponto de partida
     * @return iterador
     * @throws NotLocalInstanceException se o ponto de partida não for uma instância {@link ILocal local}
     */
    Iterator<ILocal> shortestRouteGoConnectorWithoutCooldownToConnector(T source) throws NotLocalInstanceException, ParseException;

    /**
     * Rota mais curta passando apenas por conectores mais proximo dos conectores do grafo
     *
     * @param source conector
     * @return iterador com o conector
     * @throws NotLocalInstanceException se o conector não for uma instância {@link ILocal local}
     */
    Iterator<ILocal> shortestRouteOnlyConnectors(T source) throws NotLocalInstanceException;

    /**
     * Rota mais curta passando apenas por portais mais proximo dos portais do grafo
     *
     * @param source portal
     * @return iterador com o portal
     * @throws NotLocalInstanceException se o portal não for uma instância {@link ILocal local}
     */
    Iterator<ILocal> shortestRouteOnlyPortals(T source) throws NotLocalInstanceException;
}
