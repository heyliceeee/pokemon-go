package api.interfaces;

import api.implementation.Coordinate;

import java.util.ArrayList;

/**
 * Interface do jogador
 */
public interface IPlayer
{
    /**
     * Retorna o nome do jogador
     * @return o nome do jogador
     */
    String getName();

    /**
     * Define o nome do jogador
     * @param name
     */
    void setName(String name);

    /**
     * Retorna a equipa do jogador
     * @return a equipa do jogador
     */
    String getTeam();

    /**
     * Define a equipa do jogador
     * @param team
     */
    void setTeam(String team);

    /**
     * Retorna o nivel do jogador
     * @return o nivel do jogador
     */
    int getLevel();

    /**
     * Define o nivel do jogador
     * @param level
     */
    void setLevel(int level);

    /**
     * Retorna os pontos de experiencia do jogador
     * @return os pontos de experiencia do jogador
     */
    int getXp();

    /**
     * Define os pontos de experiencia do jogador
     * @param xp
     */
    void setXp(int xp);

    /**
     * Retorna a energia atual do jogador
     * @return a energia atual do jogador
     */
    int getEnergy();

    /**
     * Define a energia atual do jogador
     * @param energy
     */
    void setEnergy(int energy);

    /**
     * Retorna a energia maxima do jogador
     * @return a energia maxima do jogador
     */
    int getEnergyMax();

    /**
     * Define a energia maxima do jogador
     * @param energyMax
     */
    void setEnergyMax(int energyMax);

    /**
     * Retorna as coordenadas do jogador
     * @return as coordenadas do jogador
     */
    ArrayList<Coordinate> getcoordinates();

    /**
     * Define as coordenadas do jogador
     * @param coordinates
     */
    void setcoordinates(ArrayList<Coordinate> coordinates);

    /**
     * Retorna o numero de portais conquistados pelo jogador
     * @return o numero de portais conquistados pelo jogador
     */

    Iterator<String> getConqueredPortals();

    /**
     * Define o numero de portais conquistados pelo jogador
     */
    public void addConqueredPortals(String[] markets);

    /**
     * Adds one portal to the list of portals conquered by the player
     *
     * @param portalName to be added.
     */
    public void addPortal(String portalName);

    /**
     * Clears all portal associated.
     */
    void clearPortalList();

    /**
     * Gets the number of portals associated.
     *
     * @return number of portals
     */
    int numberOfPortals();

    /**
     * Transforms the Player into a JSONObject representation
     *
     * @return the JSONObject with all the details of the Seller
     */
    JSONObject playerToJsonObject();

    /**
     * Exports the JSON containing all the details of the Player
     *
     * @throws IOException if it happens an error trying to write the file
     */
    void exportPlayerToJson() throws IOException;

}
