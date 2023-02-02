package api.implementation;

import api.interfaces.*;
import collections.implementation.ArrayUnorderedList;
import collections.interfaces.UnorderedListADT;

import java.util.List;

public class Portal extends Local implements IPortal {

    /**
     * nome do portal
     */
    private String name;

    /**
     * energia maxima do portal
     */
    private int energyMax;

    /**
     * dono do portal
     */
    private IOwnership ownership;

    private ICoordinate coordinates;

    /**
     * Lista de interactions associados ao portal
     */
    public UnorderedListADT<IInteraction> interactions = new ArrayUnorderedList<>();


    public Portal(int id, String type, int energy, String name, int energyMax, IOwnership ownership, ICoordinate coordinates)
    {
        super(id, type, energy, coordinates);
        this.name = name;
        this.energyMax = energyMax;
        this.ownership = ownership;
        this.coordinates = coordinates;
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
    public String toString()
    {
        return "Portal{" +
                "name='" + name + '\'' +
                ", energyMax=" + energyMax +
                ", ownership=" + ownership +
                ", coordinates=" + coordinates +
                ", " + super.toString() +
                '}';
    }

    //region getters and setters

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
    public Ownership getOwnership()
    {
        return (Ownership) ownership;
    }

    @Override
    public int getEnergyMax() {
        return energyMax;
    }

    @Override
    public void setEnergyMax(int energyMax)
    {
        this.energyMax = energyMax;
    }

    @Override
    public void setInteractionType(int id, String type)
    {

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

    /*public void setCapacity(int energyMax) {
        if (energyMax < 1) {
            throw new IllegalArgumentException("Max energy must be equal to or greater than one!");
        } else if (energyMax < this.energy) {
            throw new IllegalArgumentException("The new max energy cannot be less than the atual energy!");
        }
        this.energyMax = energyMax;
    }*/

    @Override
    public void setOwnership(Ownership ownership) {
        this.ownership = ownership;
    }

    //endregion
    
    // /*public Portal(int energy, int energyMax) {
    //        if (energyMax < 1) {
    //            throw new IllegalArgumentException("EnergyMax must be equal to or greater than one!");
    //
    //        } else if (energy < 0) {
    //            throw new IllegalArgumentException("Energy must be equal to or greater than zero!");
    //
    //        } else if (energy > energyMax) {
    //            throw new IllegalArgumentException("Energy cannot be greater than the capacity!");
    //        }
    //        this.energy = energy;
    //        this.energyMax = energyMax;
    //    }*/
    //
    //    /*public Portal(String name) {
    //        if (name == null || name.equals("")) {
    //            throw new IllegalArgumentException("Name cannot be empty or null!");
    //        }
    //        this.name = name;
    //    }*/
    //
    //    /*public JSONObject portalToJSONObject() {
    //        JSONObject root = new JSONObject();
    //        root.put("Name", getName());
    //        root.put("EnergyMax", this.energyMax);
    //        return root;
    //    }*/
    //
    //    /*public void exportPortalToJson() throws IOException
    //    {
    //        ExporterJson.exportJSON(portalToJSONObject().toJSONString(), "Portal " + getName());
    //    }*/
    //
    //    /*@Override
    //    public boolean equals(Object o) {
    //        if (this == o) {
    //            return true;
    //        }
    //
    //        if (o == null || getClass() != o.getClass()) {
    //            return false;
    //        }
    //
    //        IPortal that = (IPortal) o;
    //
    //        return getName().equals(that.getName());
    //    }*/
}
