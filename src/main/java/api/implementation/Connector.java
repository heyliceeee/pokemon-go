package api.implementation;

import api.interfaces.IConnector;

import java.util.ArrayList;

public class Connector extends Local implements IConnector {

   /**
     * intervalo de fornecimento de energia a um jogador apos uma interacao do conector
     */
    private int cooldown;

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
