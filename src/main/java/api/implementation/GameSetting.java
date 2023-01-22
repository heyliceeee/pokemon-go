package api.implementation;

import api.interfaces.IGameSetting;

import java.util.ArrayList;

/**
 * Representacao da classe de uma definicao de jogo
 */
public class GameSetting implements IGameSetting
{
    /**
     * energia do portal/connector
     */
    private int energy;

    /**
     * energia maxima do portal
     */
    private int energyMax;

    /**
     * intervalo de fornecimento de energia a um jogador apos uma interacao no connector
     */
    private int cooldown;

    /**
     * registo de interacoes do portal/connector
     */
    private ArrayList<Interaction> interaction;


    @Override
    public int getEnergy()
    {
        return energy;
    }

    @Override
    public void setEnergy(int energy)
    {

        this.energy = energy;
    }

    @Override
    public int getEnergyMax()
    {
        return energyMax;
    }

    @Override
    public void setEnergyMax(int energyMax)
    {
        this.energyMax = energyMax;
    }

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
    public ArrayList<Interaction> getInteraction()
    {
        return interaction;
    }

    @Override
    public void setInteraction(ArrayList<Interaction> interaction)
    {
        this.interaction = interaction;
    }
}
