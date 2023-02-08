package api.implementation;

import api.interfaces.IConnector;
import api.interfaces.ICoordinate;
import api.interfaces.IInteraction;
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

    /**
     * Lista de interactions associados ao conector
     */
    public UnorderedListADT<IInteraction> interactions = new ArrayUnorderedList<>();

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
    public IInteraction getConnectorLastInteractionByPlayerName(String playerName) {

        Iterator<IInteraction> iterator = this.interactions.iterator();
        IInteraction interaction;
        int idInteraction = 0;

        while (iterator.hasNext()) {
            interaction = iterator.next();

            if (interaction.getPlayer().equals(playerName) && idInteraction < interaction.getID()) //se a interação pertence ao jogador e se for mais recente do que a comparação anterior
            {
                idInteraction = interaction.getID();
                return interaction;
            }
        }

        return null;
    }

    private Object getInteractionsJSONArray() {
        JSONArray interactionsArray = new JSONArray();
        Iterator<IInteraction> iteratorInteraction = this.interactions.iterator();

        while (iteratorInteraction.hasNext()) {
            interactionsArray.add(iteratorInteraction.next().interactionToJsonObject());
        }

        return interactionsArray;
    }

    private Object getCoordinatesJSONObject() {
        JSONObject coordinates = new JSONObject();

        coordinates.put("longitude", this.coordinates.getLongitude());
        coordinates.put("latitude", this.coordinates.getLatitude());

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
