package api.interfaces;

import api.implementation.Coordenate;
import api.implementation.GameSetting;
import api.implementation.Ownership;

import java.util.ArrayList;

/**
 * Interface do portal/connector
 */
public interface ILocal
{
    /**
     * Retorna o identificador unico do portal/connector
     * @return o identificador unico do portal/connector
     */
    int getId();

    /**
     * Define o identificador unico do portal/connector
     * @param id
     */
    void setId(int id);

    /**
     * Retorna o tipo de local (portal ou connector)
     * @return o tipo de local (portal ou connector)
     */
    String getType();

    /**
     * Define o tipo de local (portal ou connector)
     * @param type
     */
    void setType(String type);

    /**
     * Retorna o nome do portal
     * @return o nome do portal
     */
    String getName();

    /**
     * Define o nome do portal
     * @param name
     */
    public void setName(String name);

    /**
     * Retorna o dono do portal
     * @return o dono do portal
     */
    public ArrayList<Ownership> getOwnership();

    /**
     * Define o dono do portal
     * @param ownership
     */
    public void setOwnership(ArrayList<Ownership> ownership);

    /**
     * Retorna as definicoes do jogo do portal/connector
     * @return as definicoes do jogo do portal/connector
     */
    public ArrayList<GameSetting> getGameSettings();

    /**
     * Define as definicoes do jogo do portal/connector
     * @param gameSettings
     */
    public void setGameSettings(ArrayList<GameSetting> gameSettings);

    /**
     * Retorna as coordenadas do portal/connector
     * @return as coordenadas do portal/connector
     */
    public ArrayList<Coordenate> getCoordenates();

    /**
     * Define as coordenadas do portal/connector
     * @param coordenates
     */
    public void setCoordenates(ArrayList<Coordenate> coordenates);
}
