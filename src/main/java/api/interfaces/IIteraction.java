package api.interfaces;

import java.util.Date;

/**
 * Interface da Interacao
 */
public interface IIteraction
{
    /**
     * Retorna o tipo de interacao do portal/connector
     * @return o tipo de interacao do portal/connector
     */
    String getType();

    /**
     * Define o tipo de interacao do portal/connector
     * @param type
     */
    void setType(String type);

    /**
     * Retorna os pontos de experiencia que o jogador ganhou apos uma interacao com o portal/connector
     * @return os pontos de experiencia que o jogador ganhou apos uma interacao com o portal/connector
     */
    int getXp();

    /**
     * Define os os pontos de experiencia que o jogador ganha apos uma interacao com o portal/connector
     * @param xp
     */
    void setXp(int xp);

    /**
     * Retornar o nome do jogador que teve interacao com o portal/connector
     * @return o nome do jogador que teve interacao com o portal/connector
     */
    String getPlayer();

    /**
     * Define o nome do jogador que teve interacao com o portal/connector
     * @param player
     */
    void setPlayer(String player);

    /**
     * Retorna a data da interacao com o portal/connector
     * @return a data da interacao com o portal/connector
     */
    Date getDate();

    /**
     * Define a data da interacao com o portal/connector
     * @param date
     */
    void setDate(Date date);

    /**
     * Retorna os pontos de uma interacao com o portal/connector
     * @return os pontos de uma interacao com o portal/connector
     */
    int getPoints();

    /**
     * Define os pontos de uma interacao com o portal/connector
     * @param points
     */
    void setPoints(int points);

    /**
     * Retorna a velocidade a que vai ganhar pontos de experiencia apos uma interacao com o portal/connector
     * @return a velocidade a que vai ganhar pontos de experiencia apos uma interacao com o portal/connector
     */
    int getSpeedXP();

    /**
     * Define a velocidade a que vai ganhar pontos de experiencia apos uma interacao com o portal/connector
     * @param speedXP
     */
    void setSpeedXP(int speedXP);
}
