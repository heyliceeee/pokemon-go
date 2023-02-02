package api.interfaces;

import api.implementation.Interaction;
import api.implementation.Ownership;

import java.util.ArrayList;
import java.util.List;

public interface IPortal extends ILocal {

    /**
     * Retorna "Successful" se foi adicionado uma interação á lista de interações
     * @param interaction a ser adicionado
     * @return "Successful" se foi adicionado uma interação á lista de interações
     */
    String addInteraction(IInteraction interaction);

    /**
     * Retorna o nome do portal
     * @return o nome do portal
     */
    String getName();

    /**
     * Define o nome do portal
     * @param name
     */
    void setName(String name);

    /**
     * Retorna o dono do portal
     * @return o dono do portal
     */
    Ownership getOwnership();

    /**
     * Define o dono do portal
     * @param ownership
     */
    void setOwnership(Ownership ownership);

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
     * Atualiza o tipo de interação com o portal
     * @param id identificador único da interação
     * @param type novo tipo de interação
     */
    void setInteractionType(int id, String type);

    /**
     * Atualiza o nome do jogador de interação com o portal
     * @param id identificador único da interação
     * @param playerName novo nome do jogador de interação
     */
    void setInteractionPlayer(int id, String playerName);

    /**
     * Atualiza a data de interação com o portal
     * @param id identificador único da interação
     * @param date data de interação
     */
    void setInteractionDate(int id, String date);

    /**
     * Atualiza os pontos de interação com o portal
     * @param id identificador único da interação
     * @param points os pontos de interação
     */
    void setInteractionPoints(int id, int points);

    /**
     * Atualiza velocidade que vai ganhar pontos de experiencia de interação com o portal
     * @param id identificador único da interação
     * @param speedXP velocidade que vai ganhar pontos de experiencia de interação
     */
    void setInteractionSpeedXP(int id, int speedXP);
}
