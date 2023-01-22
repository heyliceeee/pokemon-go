package org.example.classes;

import java.util.ArrayList;

public class Player
{
    public String name;
    public String team;
    public int level;
    public int xp;
    public int energy;
    public int energyMax;
    public ArrayList<Coordenate> coordenates;
    public int conqueredPortals;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEnergyMax() {
        return energyMax;
    }

    public void setEnergyMax(int energyMax) {
        this.energyMax = energyMax;
    }

    public ArrayList<Coordenate> getCoordenates() {
        return coordenates;
    }

    public void setCoordenates(ArrayList<Coordenate> coordenates) {
        this.coordenates = coordenates;
    }

    public int getConqueredPortals() {
        return conqueredPortals;
    }

    public void setConqueredPortals(int conqueredPortals) {
        this.conqueredPortals = conqueredPortals;
    }
}
