package api.implementation;

import api.interfaces.IPortal;

import java.util.ArrayList;

public class Portal extends Local implements IPortal {


    @Override
    public ArrayList<Interaction> getInteraction() {
        return null;
    }

    @Override
    public void setInteraction(ArrayList<Interaction> interaction) {

    }

    @Override
    public void setOwnership(ArrayList<Ownership> ownership) {

    }

    @Override
    public int getEnergy() {
        return 0;
    }

    @Override
    public void setEnergy(int energy) {

    }

    @Override
    public int getEnergyMax() {
        return 0;
    }

    @Override
    public void setEnergyMax(int energyMax) {

    }
}
