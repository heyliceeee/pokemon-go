package api.interfaces;

import api.implementation.Coordenate;

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
    ArrayList<Coordenate> getCoordenates();

    /**
     * Define as coordenadas do jogador
     * @param coordenates
     */
    void setCoordenates(ArrayList<Coordenate> coordenates);

    /**
     * Retorna o numero de portais conquistados pelo jogador
     * @return o numero de portais conquistados pelo jogador
     */
    int getConqueredPortals();

    /**
     * Define o numero de portais conquistados pelo jogador
     * @param conqueredPortals
     */
    void setConqueredPortals(int conqueredPortals);
}
