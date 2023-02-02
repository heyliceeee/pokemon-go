package api.implementation;

import api.interfaces.ICoordinate;
import api.interfaces.IPlayer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;


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
    private ICoordinate coordinates;

    /**
     * total de portais conquistados pelo jogador
     */
    private int conqueredPortals;

    public Player(String name, String team, int level, int xp, int energy, int energyMax, int conqueredPortals, ICoordinate coordinates)
    {
        this.name = name;
        this.team = team;
        this.level = level;
        this.xp = xp;
        this.energy = energy;
        this.energyMax = energyMax;
        this.conqueredPortals = conqueredPortals;
        this.coordinates = coordinates;
    }


    /* @Override
    public void addConqueredPortals(String[] portals) {
        if (portals == null || portals.length == 0) {
            throw new IllegalArgumentException("List of markets cannot be null or empty!");
        }
        for (String portal : portals) {
            try {
                this.addPortal(portal);
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("None of the markets name in list can be null or empty!");
            }
        }
    }*/

     /*@Override
    public void addPortal(String portalName) {
        if (portalName == null || portalName.equals(""))
        {
            throw new IllegalArgumentException("Name of market cannot be null or empty!");
        }

        this.conqueredPortals.addToRear(portalName);
    }*/

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ", team=" + team +
                ", level=" + level +
                ", xp=" + xp +
                ", energy=" + energy +
                ", energyMax=" + energyMax +
                ", coordinates=" + coordinates +
                ", conqueredPortals=" + conqueredPortals +
                '}';
    }

    //region getters and setters
    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name cannot be null or empty!");
        }
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
        if (level < 1) {
            throw new IllegalArgumentException("Capacity must be equal to or greater than one!");
        }
        this.level = level;
    }

    @Override
    public int getXp()
    {
        return xp;
    }

    @Override
    public void setXp(int xp) {
        if (xp < 0) {
            throw new IllegalArgumentException("Capacity must be equal to or greater than one!");
        }
        this.xp = xp;
    }

    @Override
    public int getEnergy()
    {

        return energy;
    }

    @Override
    public void setEnergy(int energy) {
        if (energy < 0) {
            throw new IllegalArgumentException("Capacity must be equal to or greater than one!");
        }
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
    public Coordinate getCoordinates()
    {
        return (Coordinate) coordinates;
    }

    @Override
    public void setCoordinates(Coordinate coordinates)
    {
        this.coordinates = coordinates;
    }

    @Override
    public int getConqueredPortals() {
        return conqueredPortals = conqueredPortals;
    }

    @Override
    public void setConqueredPortals(int conqueredPortals)
    {
        this.conqueredPortals = conqueredPortals;
    }

    //endregion
}
