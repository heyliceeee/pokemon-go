package api.interfaces;

import api.implementation.Local;
import api.implementation.Player;
import api.implementation.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface da raiz do JSON
 */
public interface IRoot
{
    /**
     * Retorna "Successful" se conseguir adicionar o portal/connector ao vértice
     * @param local
     * @return "Successful" se conseguir adicionar o portal/connector ao vértice
     */
    String addLocal(ILocal local);

    /**
     * Retorna os locais (portal/connector)
     * @return os locais (portal/connector)
     */
    List<Local> getLocals();

    /**
     * Define os locais (portal/connector)
     * @param locals
     */
    void setLocals(List<Local> locals);

    /**
     * Retorna as rotas
     * @return as rotas
     */
    List<Route> getRoutes();

    /**
     * Define as rotas
     * @param routes
     */
    void setRoutes(List<Route> routes);

    /**
     * Retorna os jogadores
     * @return os jogadores
     */
    List<Player> getPlayers();

    /**
     * Define os jogadores
     * @param players
     */
    void setPlayers(List<Player> players);
}