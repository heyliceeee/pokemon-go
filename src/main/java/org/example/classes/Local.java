package org.example.classes;

import java.util.ArrayList;

public class Local
{
    public int id;
    public String type;
    public String name;
    public ArrayList<Ownership> ownership;
    public ArrayList<GameSetting> gameSettings;
    public ArrayList<Coordenate> coordenates;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ownership> getOwnership() {
        return ownership;
    }

    public void setOwnership(ArrayList<Ownership> ownership) {
        this.ownership = ownership;
    }

    public ArrayList<GameSetting> getGameSettings() {
        return gameSettings;
    }

    public void setGameSettings(ArrayList<GameSetting> gameSettings) {
        this.gameSettings = gameSettings;
    }

    public ArrayList<Coordenate> getCoordenates() {
        return coordenates;
    }

    public void setCoordenates(ArrayList<Coordenate> coordenates) {
        this.coordenates = coordenates;
    }
}
