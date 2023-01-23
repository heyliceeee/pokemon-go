package api.implementation;

import api.interfaces.ILocal;

import java.util.ArrayList;

/**
 * Representacao da classe de um portal/connector
 */
public class Local implements ILocal
{
    /**
     * identificador unico do portal/connector
     */
    private int id;

    /**
     * tipo de local (portal/connector)
     */
    private String type;

    /**
     * nome do portal
     */
    private String name;

    /**
     * dono do portal
     */
    private ArrayList<Ownership> ownership;

    /**
     * definicoes de jogo do portal/connector
     */
    private ArrayList<GameSetting> gameSettings;

    /**
     * coordenadas do portal/connector
     */
    private ArrayList<Coordinate> coordinates;


    @Override
    public int getId()
    {
        return id;
    }

    @Override
    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public String getType()
    {
        return type;
    }

    @Override
    public void setType(String type)
    {
        this.type = type;
    }

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
    public ArrayList<Ownership> getOwnership()
    {
        return ownership;
    }

    @Override
    public void setOwnership(ArrayList<Ownership> ownership)
    {
        this.ownership = ownership;
    }

    @Override
    public ArrayList<GameSetting> getGameSettings()
    {
        return gameSettings;
    }

    @Override
    public void setGameSettings(ArrayList<GameSetting> gameSettings)
    {
        this.gameSettings = gameSettings;
    }

    @Override
    public ArrayList<Coordinate> getcoordinates()
    {
        return coordinates;
    }

    @Override
    public void setcoordinates(ArrayList<Coordinate> coordinates)
    {
        this.coordinates = coordinates;
    }
}
