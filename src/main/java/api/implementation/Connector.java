package api.implementation;

import api.interfaces.IConnector;

import java.util.ArrayList;

public class Connector extends Local implements IConnector {
    @Override
    public int getEnergy() {
        return 0;
    }

    @Override
    public void setEnergy(int energy) {

    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public void setCooldown(int cooldown) {

    }

    @Override
    public void setInteraction(ArrayList<Interaction> interaction) {

    }

    @Override
    public ArrayList<Interaction> getInteraction() {
        return null;
    }
}
