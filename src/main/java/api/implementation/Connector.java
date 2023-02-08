package api.implementation;

import api.interfaces.*;
import collections.implementation.ArrayUnorderedList;
import collections.interfaces.UnorderedListADT;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;

public class Connector extends Local implements IConnector {

    /**
     * intervalo de fornecimento de energia a um jogador apos uma interacao do conector
     */
    private int cooldown;

    private ICoordinate coordinates;

    public Connector(int id, String type, int energy, int cooldown, ICoordinate coordinates) {
        super(id, type, energy, coordinates);
        this.cooldown = cooldown;
    }

    @Override
    public String toString() {
        return "Connector{" +
                "cooldown=" + cooldown +
                ", " + super.toString() +
                '}';
    }

    @Override
    public JSONObject connectorToJSONObject() {
        JSONObject root = new JSONObject();

        root.put("id", getId());
        root.put("type", getType());
        root.put("energy", getEnergy());
        root.put("cooldown", this.cooldown);
        root.put("coordinates", getCoordinatesJSONObject());
        root.put("interaction", getInteractionsJSONArray());

        return root;
    }

    @Override
    public IInteraction getConnectorLastInteractionByPlayerName(int idConnector, String playerName) {

        Iterator<IConnector> iteratorConnector = Root.routeNetwork.getConnectors();
        IConnector connector;

        while (iteratorConnector.hasNext())
        {
            connector = iteratorConnector.next();

            if(connector.getId() == idConnector)
            {
                Iterator<IInteraction> iteratorInteraction = interactions.iterator();
                IInteraction interaction;
                int idInteraction = 0;

                while (iteratorInteraction.hasNext())
                {
                    interaction = iteratorInteraction.next();

                    if (interaction.getPlayer().equals(playerName) && idInteraction <= interaction.getID()) //se a interação pertence ao jogador e se for mais recente do que a comparação anterior
                    {
                        idInteraction = interaction.getID();

                        return interaction;
                    }
                }
            }
        }

        return null;
    }

    private JSONArray getInteractionsJSONArray() {
        JSONArray interactionsArray = new JSONArray();
        Iterator<IInteraction> iteratorInteraction = this.interactions.iterator();

        while (iteratorInteraction.hasNext()) {
            interactionsArray.add(iteratorInteraction.next().interactionToJsonObject());
        }

        return interactionsArray;
    }

    private JSONObject getCoordinatesJSONObject() {
        JSONObject coordinates = new JSONObject();

        try
        {
            coordinates.put("longitude", this.coordinates.getLongitude());

        } catch (Exception e)
        {
            coordinates.put("longitude", 0);
        }

        try
        {
            coordinates.put("latitude", this.coordinates.getLatitude());

        } catch (Exception e)
        {
            coordinates.put("latitude", 0);
        }

        return coordinates;
    }

    //region getters and setters

    @Override
    public int getCooldown() {
        return cooldown;
    }

    @Override
    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    //endregion
}
