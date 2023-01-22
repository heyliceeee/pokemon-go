package api.implementation;

import api.interfaces.IGlobalGameSetting;

import java.util.ArrayList;

/**
 * Representacao da classe de uma definicao global de jogo
 */
public class GlobalGameSetting implements IGlobalGameSetting
{
    /**
     * interacoes possiveis do portal/connector
     */
    private ArrayList<Interaction> interaction;


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
