package api.implementation;

import api.interfaces.IConnector;
import api.interfaces.ICoordinate;
import api.interfaces.IInteraction;
import collections.implementation.ArrayUnorderedList;
import collections.interfaces.UnorderedListADT;

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

    public Connector(int id, String type, int energy, int cooldown, ICoordinate coordinates)
    {
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

    //endregion
}
