package api.implementation;

import api.interfaces.IPlayer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


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
    private ArrayList<Coordinate> coordinates;

    /**
     * total de portais conquistados pelo jogador
     */

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
    public ArrayList<Coordinate> getcoordinates() {
        return coordinates;
    }

    @Override
    public void setcoordinates(ArrayList<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public Iterator<String> getConqueredPortals() {
        return conqueredPortals.iterator();
    }

    @Override
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
    }

    @Override
    public void addPortal(String portalName) {
        if (portalName == null || portalName.equals("")) {
            throw new IllegalArgumentException("Name of market cannot be null or empty!");
        }
        this.conqueredPortals.addToRear(portalName);
    }

    @Override
    public void clearPortalList() {

    }

    @Override
    public int numberOfPortals() {
        return 0;
    }


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject playerToJsonObject() {
        JSONObject root = new JSONObject();
        root.put("Name", this.name);
        root.put("Team", this.team);
        root.put("Level", this.level);
        root.put("Xp", this.xp);
        root.put("Energy", this.energy);
        root.put("EnergyMax", this.energyMax);
        root.put("Coordinates", this.coordinates);
        if (this.conqueredPortals.isEmpty()) {
            root.put("ConqueredPortals", "0");
        } else {
            JSONArray team = new JSONArray();
            Iterator<String> iterator = this.geConqueredPortals();
            while (iterator.hasNext()) {
                team.add(iterator.next());
            }
            root.put("ConqueredPortals", conqueredPortals);
        }
        return root;
    }

    @Override
    public void exportPlayerToJson() throws IOException {
        ExporterJson.exportJSON(playerToJsonObject().toJSONString(), "Player_" + this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IPlayer)) {
            return false;
        }
        IPlayer tmp = (IPlayer) o;
        return this.name == tmp.getName();
    }

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
}
