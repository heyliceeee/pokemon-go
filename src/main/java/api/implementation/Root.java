package api.implementation;

import api.interfaces.IRoot;

import java.util.ArrayList;

/**
 * Representacao da classe da raiz do JSON
 */
public class Root implements IRoot
{
    /**
     * locais (portal e connector)
     */
    private ArrayList<Local> locals;

    /**
     * rotas
     */
    private ArrayList<Route> routes;

    /**
     * jogadores
     */
    private ArrayList<Player> players;

    /**
     * definicoes globais do jogo
     */
    private ArrayList<GlobalGameSetting> globalGameSettings;


    @Override
    public ArrayList<Local> getLocals()
    {
        return locals;
    }

    @Override
    public void setLocals(ArrayList<Local> locals)
    {
        this.locals = locals;
    }

    @Override
    public ArrayList<Route> getRoutes()
    {
        return routes;
    }

    @Override
    public void setRoutes(ArrayList<Route> routes)
    {
        this.routes = routes;
    }

    @Override
    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    @Override
    public void setPlayers(ArrayList<Player> players)
    {
        this.players = players;
    }

    @Override
    public ArrayList<GlobalGameSetting> getGlobalGameSettings()
    {
        return globalGameSettings;
    }

    @Override
    public void setGlobalGameSettings(ArrayList<GlobalGameSetting> globalGameSettings)
    {
        this.globalGameSettings = globalGameSettings;
    }
}
