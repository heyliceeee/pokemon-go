package api.implementation;

import api.interfaces.IPlayer;

import java.util.ArrayList;

/**
 * Representacao da classe de um jogador
 */
public class Player implements IPlayer
{
    /**
     * nome do jogador
     */
    private String name;

    /**
     * equipa do jogador
     */
    private String team;

    /**
     * nivel do jogador
     */
    private int level;

    /**
     * pontos de experiencia do jogador
     */
    private int xp;

    /**
     * quantidade de energia atual do jogador
     */
    private int energy;

    /**
     * quantidade de energia maxima do jogador
     */
    private int energyMax;

    /**
     * coordenadas do jogador
     */
    private ArrayList<Coordenate> coordenates;

    /**
     * total de portais conquistados pelo jogador
     */
    private int conqueredPortals;


    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getTeam()
    {
        return team;
    }

    @Override
    public void setTeam(String team)
    {
        this.team = team;
    }

    @Override
    public int getLevel()
    {
        return level;
    }

    @Override
    public void setLevel(int level)
    {
        this.level = level;
    }

    @Override
    public int getXp()
    {
        return xp;
    }

    @Override
    public void setXp(int xp)
    {
        this.xp = xp;
    }

    @Override
    public int getEnergy()
    {
        return energy;
    }

    @Override
    public void setEnergy(int energy)
    {
        this.energy = energy;
    }

    @Override
    public int getEnergyMax()
    {
        return energyMax;
    }

    @Override
    public void setEnergyMax(int energyMax)
    {
        this.energyMax = energyMax;
    }

    @Override
    public ArrayList<Coordenate> getCoordenates()
    {
        return coordenates;
    }

    @Override
    public void setCoordenates(ArrayList<Coordenate> coordenates)
    {
        this.coordenates = coordenates;
    }

    @Override
    public int getConqueredPortals()
    {
        return conqueredPortals;
    }

    @Override
    public void setConqueredPortals(int conqueredPortals)
    {
        this.conqueredPortals = conqueredPortals;
    }
}
