package api.implementation;

import api.interfaces.IPortal;

import java.io.IOException;
import java.util.ArrayList;

public class Portal extends Local implements IPortal {

    /**
     * The name of the place
     */
    private String name;

    /**
     * Constructor of the Portal class
     *
     * @param name name of the portal
     */
    public Portal(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name cannot be empty or null!");
        }
        this.name = name;
    }
    /**
     * Represents the max energy of the portal.
     */
    private int energyMax;
    /**
     * Represents the actual energy of the portal
     */
    private int energy;

    /**
     * Constructor of Portal
     * @param energy actual energy of the portal
     * @param energyMax max energy of the portal
     */
    public Portal( int energy, int energyMax) {

        if (energyMax < 1) {
            throw new IllegalArgumentException("EnergyMax must be equal to or greater than one!");
        } else if (energy < 0) {
            throw new IllegalArgumentException("Energy must be equal to or greater than zero!");
        } else if (energy > energyMax) {
            throw new IllegalArgumentException("Energy cannot be greater than the capacity!");
        }
        this.energy = energy;
        this.energyMax = energyMax;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name cannot be empty or null!");
        }
        this.name = name;
    }

    @Override
    public void setOwnership(ArrayList<Ownership> ownership) {

    }

    @Override
    public int getEnergyMax() {
        return energyMax;
    }

    @Override
    public void setEnergyMax(int energyMax) {

    }

    @Override
    public void setCapacity(int energyMax) {
        if (energyMax < 1) {
            throw new IllegalArgumentException("Max energy must be equal to or greater than one!");
        } else if (energyMax < this.energy) {
            throw new IllegalArgumentException("The new max energy cannot be less than the atual energy!");
        }
        this.energyMax = energyMax;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public void setEnergy(int energy) {
        if (energy < 0) {
            throw new IllegalArgumentException("Energy must be equal to or greater than zero!");
        } else if (energy > this.energyMax) {
            throw new IllegalArgumentException("The new energy cannot be greater than the max energy!");
        }
        this.energy = energy;

        @Override
        public JSONObject portalToJSONObject() {
            JSONObject root = new JSONObject();
            root.put("Name", getName());
            root.put("EnergyMax", this.energyMax);
            root.put("Energy", this.energy);
            return root;
        }

        @Override
        public void exportPortalToJson() throws IOException {
            ExporterJson.exportJSON(portalToJSONObject().toJSONString(), "Portal" + getName());
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            IPortal that = (IPortal) o;
            return getName().equals(that.getName());
        }

        @Override
        public String toString() {
            return "Portal{" +
                    "Name=" + getName() +
                    ", EnergyMax=" + energyMax +
                    ", Energy=" + energy +
                    '}';
        }
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


}

    @Override
    public ArrayList<Interaction> getInteraction() {
        return null;
    }

    @Override
    public void setInteraction(ArrayList<Interaction> interaction) {

    }
