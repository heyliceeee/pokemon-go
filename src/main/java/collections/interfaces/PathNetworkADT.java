package collections.interfaces;

import api.implementation.Portal;
import api.interfaces.IConnector;
import api.interfaces.ILocal;
import api.interfaces.IPortal;
import api.interfaces.IRoute;
import collections.exceptions.NotLocalInstanceException;

import java.util.Iterator;

/**
 * Contrato de um gráfico de caminho no campo de fornecimento a jogadores
 * @param <T> tipo de locais no grafo
 */
public interface PathNetworkADT<T> extends NetworkADT<T>
{
    /**
     * Retorna o número de {@link api.implementation.Portal portais} neste grafo
     * @return o número de {@link api.implementation.Portal portais} neste grafo
     */
    int getNumberOfPortals();

    /**
     * Retorna o número de {@link api.implementation.Connector conectores} neste grafo
     * @return o número de {@link api.implementation.Connector conectores} neste grafo
     */
    int getNumberOfConnectors();

    /**
     * Retorna os portais no grafo
     * @return iterador de portais
     */
    Iterator<IPortal> getPortals();

    /**
     * Retorna os conectores no grafo
     * @return iterador de conectores
     */
    Iterator<IConnector> getConnectors();

    /**
     * Retorna as rotas existentes neste grafo
     * @return iterador com essas rotas
     */
    Iterator<IRoute> getRoutes();

    /**
     * Caminho mais curto entre 2 pontos (portais e/ou conectores)
     * @param source ponto de partida
     * @param destination destino
     * @return iterador com o caminho
     * @throws NotLocalInstanceException se o ponto de partida ou o destino não for instância {@link ILocal local}
     */
    /*Iterator<ILocal> shortestRoute(T source, T destination) throws NotLocalInstanceException;*/

    /**
     * Caminho mais curto do ponto de partida até ao portal mais próximo dos portais do grafo
     * @param source ponto de partida
     * @return iterador com a rota
     * @throws NotLocalInstanceException se o ponto de partida não for instância {@link ILocal ILocal}
     */
    Iterator<ILocal> shortestRouteToPortal(T source) throws NotLocalInstanceException;

    /**
     * Caminho mais curto do ponto de partida até ao conector mais próximo dos conectores do grafo
     * @param source ponto de partida
     * @return iterador com a rota
     * @throws NotLocalInstanceException se o ponto de partida não for instância {@link ILocal ILocal}
     */
    Iterator<ILocal> shortestRouteToConnector(T source) throws NotLocalInstanceException;

    /**
     * Caminho mais curto do ponto de partida até ao conector mais próximo que já tenha terminado o periodo de cooldown para o jogador
     * @param source ponto de partida
     * @return iterador com a rota
     * @throws NotLocalInstanceException se o ponto de partida não for instância {@link ILocal ILocal}
     */
    Iterator<ILocal> shortestRouteToConnectorWithoutCooldown(T source) throws NotLocalInstanceException;


    /**
     * Caminho mais curto entre 2 pontos, considerando a necessidade de passar por um conector para recarregar (o conector não pode estar em cooldown para o jogador)
     * @param source ponto de partida
     * @param destination destino
     * @return iterador com o caminho
     * @throws NotLocalInstanceException se o ponto de partida ou o destino não for instância {@link ILocal local}
     */
    //Iterator<ILocal> shortestRoutePassingConnector(T source, T destination) throws  NotLocalInstanceException;
}
