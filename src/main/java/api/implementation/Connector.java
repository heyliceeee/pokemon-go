package api.implementation;

import api.interfaces.IConnector;

import java.util.ArrayList;
import java.util.List;

public class Connector extends Local implements IConnector {

   /**
     * intervalo de fornecimento de energia a um jogador apos uma interacao do conector
     */
    private int cooldown;


    public Connector(int id, String type, int energy, int cooldown, Coordinate coordinate, List<Interaction> interactions)
    {
        super(id, type, energy, coordinate, interactions);
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
