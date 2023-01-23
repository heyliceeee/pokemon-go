package api.interfaces;

import api.implementation.GlobalGameSetting;
import api.implementation.Local;
import api.implementation.Player;
import api.implementation.Route;

import java.util.ArrayList;

/**
 * Interface da raiz do JSON
 */
public interface IRoot
{
    /**
     * Retorna os locais (portal/connector)
     * @return os locais (portal/connector)
     */
    ArrayList<Local> getLocals();

    /**
     * Define os locais (portal/connector)
     * @param locals
     */
    void setLocals(ArrayList<Local> locals);

    /**
     * Retorna as rotas
     * @return as rotas
     */
    ArrayList<Route> getRoutes();

    /**
     * Define as rotas
     * @param routes
     */
    void setRoutes(ArrayList<Route> routes);

    /**
     * Retorna os jogadores
     * @return os jogadores
     */
    ArrayList<Player> getPlayers();

    /**
     * Define os jogadores
     * @param players
     */
    void setPlayers(ArrayList<Player> players);

    /**
     * Retorna as definicoes globais do jogo
     * @return
     */
    ArrayList<GlobalGameSetting> getGlobalGameSettings();

    /**
     * Define as definicoes do jogo
     * @param globalGameSettings
     */
    void setGlobalGameSettings(ArrayList<GlobalGameSetting> globalGameSettings);
}
