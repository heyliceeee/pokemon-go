package api.interfaces;

import org.json.simple.JSONObject;

/**
 * Interface da Interacao
 */
public interface IInteraction {
    /**
     * Retorna identificador único da interação
     *
     * @return identificador único da interação
     */
    int getID();

    /**
     * Define identificador único da interação
     *
     * @param id
     */
    void setID(int id);

    /**
     * Retorna o tipo de interacao do portal/connector
     *
     * @return o tipo de interacao do portal/connector
     */
    String getType();

    /**
     * Define o tipo de interacao do portal/connector
     *
     * @param type
     */
    void setType(String type);

    /**
     * Retornar o nome do jogador que teve interacao com o portal/connector
     *
     * @return o nome do jogador que teve interacao com o portal/connector
     */
    String getPlayer();

    /**
     * Define o nome do jogador que teve interacao com o portal/connector
     *
     * @param player
     */
    void setPlayer(String player);

    /**
     * Retorna a data da interacao com o portal/connector
     *
     * @return a data da interacao com o portal/connector
     */
    String getDate();

    /**
     * Define a data da interacao com o portal/connector
     *
     * @param date
     */
    void setDate(String date);

    /**
     * Retorna os pontos de uma interacao com o portal/connector
     *
     * @return os pontos de uma interacao com o portal/connector
     */
    int getPoints();

    /**
     * Define os pontos de uma interacao com o portal/connector
     *
     * @param points
     */
    void setPoints(int points);

    JSONObject interactionToJsonObject();
}
