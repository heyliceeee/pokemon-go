package api.implementation;

import api.interfaces.IConnector;
import api.interfaces.ICoordinate;
import api.interfaces.IInteraction;
import collections.implementation.ArrayUnorderedList;
import collections.interfaces.UnorderedListADT;

import java.util.ArrayList;
import java.util.List;

public class Connector extends Local implements IConnector {

   /**
     * intervalo de fornecimento de energia a um jogador apos uma interacao do conector
     */
    private int cooldown;

    /**
     * Lista de interactions associados ao conector
     */
    public UnorderedListADT<IInteraction> interactions = new ArrayUnorderedList<>();

    public Connector(int id, String type, int energy, int cooldown, ICoordinate coordinates)
    {
        super(id, type, energy, coordinates);
        this.cooldown = cooldown;
    }

    @Override
    public String addInteraction(IInteraction interaction)
    {
        if(interaction == null)
        {
            throw new IllegalArgumentException("interaction cannot be null");
        }

        String s = "Failed";

        if(this.interactions.isEmpty() || !this.interactions.contains(interaction)) //se a lista de interaction estiver vazia ou não conter o interaction a ser adicionado, adiciona-o á lista
        {
            this.interactions.addToRear(interaction); //adiciona o interaction no fim da lista
            s = "Successful";
        }

        return s;
    }

    @Override
    public String toString() {
        return "Connector{" +
                "cooldown=" + cooldown +
                ", " + super.toString() +
                '}';
    }

    //region getters and setters

   @Override
    public int getCooldown()
    {
        return cooldown;
    }

    @Override
    public void setCooldown(int cooldown)
    {
        this.cooldown = cooldown;
    }

    @Override
    public void setInteractionType(int id, String type) {

    }

    @Override
    public void setInteractionPlayer(int id, String playerName) {

    }

    @Override
    public void setInteractionDate(int id, String date) {

    }

    @Override
    public void setInteractionPoints(int id, int points) {

    }

    @Override
    public void setInteractionSpeedXP(int id, int speedXP) {

    }

    //endregion
}
