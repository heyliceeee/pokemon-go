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
     * coordenadas do portal/connector
     */
    private ArrayList<Coordinate> coordinates;

    /**
     * registo de interacoes do portal/connector
     */
    private ArrayList<Interaction> interaction;

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
    public ArrayList<Coordinate> getCoordinates()
    {
        return coordinates;
    }

    @Override
    public void setCoordinates(ArrayList<Coordinate> coordinates)
    {
        this.coordinates = coordinates;
    }

    @Override
    public ArrayList<Interaction> getInteraction()
    {
        return interaction;
    }

    @Override
    public void setInteraction(ArrayList<Interaction> interaction)
    {
        this.interaction = interaction;
    }
}
