package api.interfaces;

import api.implementation.Interaction;

import java.util.ArrayList;

/**
 * Interface da definicao de jogo
 */
public interface IGameSetting
{
    /**
     * Retorna a energia do portal/connector
     * @return a energia do portal/connector
     */
    int getEnergy();

    /**
     * Define a energia do portal/connector
     * @param energy
     */
    void setEnergy(int energy);

    /**
     * Retorna a energia maxima do portal
     * @return a energia maxima do portal
     */
    int getEnergyMax();

    /**
     * Define a energia maxima do portal
     * @param energyMax
     */
    void setEnergyMax(int energyMax);

    /**
     * Retorna o intervalo de fornecimento de energia a um jogador apos uma interacao do connector
     * @return o intervalo de fornecimento de energia a um jogador apos uma interacao do connector
     */
    int getCooldown();

    /**
     * Define o intervalo de fornecimento de energia a um jogador apos uma interacao no connector
     * @param cooldown
     */
    void setCooldown(int cooldown);

    /**
     * Retorna o registo de interacoes do portal/connector
     * @return o registo de interacoes do portal/connector
     */
    ArrayList<Interaction> getInteraction();

    /**
     * Define o registo de interacoes do portal/connector
     * @param interaction
     */
    void setInteraction(ArrayList<Interaction> interaction);
}
