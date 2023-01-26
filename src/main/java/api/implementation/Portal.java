package api.implementation;

import api.interfaces.IPortal;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
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
    private Ownership ownership;

    @Override
    public String toString() {
        return "Portal{" +
                "name='" + name + '\'' +
                ", energyMax=" + energyMax +
                ", ownership=" + ownership +
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
        return ownership;
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
