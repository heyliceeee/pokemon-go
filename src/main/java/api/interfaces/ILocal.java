package api.interfaces;

import api.implementation.*;
import api.implementation.Coordinate;

import java.util.List;

/**
 * Interface do portal/connector
 */
public interface ILocal
{
    String addInteraction(IInteraction interaction);

    String getInteractionsListing();

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
     * Retorna a energia atual do portal/connector
     * @return a energia atual do portal/connector
     */
    int getEnergy();

    /**
     * Define a energia atual do portal/connector
     * @param energy
     */
    void setEnergy(int energy);

    /**
     * Retorna as coordenadas do portal/connector
     * @return as coordenadas do portal/connector
     */
    public Coordinate getCoordinates();

    /**
     * Define as coordenadas do portal/connector
     * @param coordinates
     */
    public void setCoordinates(Coordinate coordinates);

    IInteraction getInteractionByID(int id);

    void setInteractionType(int id, String type);

    void setInteractionPlayer(int id, String playerName);

    void setInteractionDate(int id, String date);

    void setInteractionPoints(int id, int points);
}
