package api.interfaces;

import api.implementation.Interaction;

import java.util.ArrayList;

public interface IConnector extends ILocal{

    /**
     * Retorna o intervalo de fornecimento de energia a um jogador apos uma interacao do conector
     * @return o intervalo de fornecimento de energia a um jogador apos uma interacao do conector
     */
    int getCooldown();

    /**
     * Define o intervalo de fornecimento de energia a um jogador apos uma interacao no conector
     * @param cooldown
     */
    void setCooldown(int cooldown);

    /**
     * Retorna "Successful" se foi adicionado uma interação á lista de interações
     * @param interaction a ser adicionado
     * @return "Successful" se foi adicionado uma interação á lista de interações
     */
    String addInteraction(IInteraction interaction);

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
