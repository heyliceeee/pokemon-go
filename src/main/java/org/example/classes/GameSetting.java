package org.example.classes;

import java.util.ArrayList;

public class GameSetting
{
    public int energy;
    public int energyMax;
    public int cooldown;
    public ArrayList<Interaction> interaction;


    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEnergyMax() {
        return energyMax;
    }

    public void setEnergyMax(int energyMax) {
        this.energyMax = energyMax;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public ArrayList<Interaction> getInteraction() {
        return interaction;
    }

    public void setInteraction(ArrayList<Interaction> interaction) {
        this.interaction = interaction;
    }
}
