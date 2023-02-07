package api.interfaces;

import org.json.simple.JSONObject;

/**
 * Interface da definição do jogo
 */
public interface IGameSetting
{
    /**
     * Retorna o identificador único da definição
     * @return o identificador único da definição
     */
    int getID();

    /**
     * Retorna o tipo de definição
     * @return o tipo de definição
     */
    String getType();

    /**
     * Retorna os pontos de experiência que o jogador ganhou
     * @return os pontos de experiência
     */
    int getPoints();

    /**
     * Retorna a velocidade dos pontos de experîencia
     * @return a velocidade dos pontos de experîencia
     */
    int getSpeedXp();


    /**
     * Define o tipo de definição
     * @param type
     */
    void setType(String type);

    /**
     * Define os pontos de experiência que o jogador ganhou
     * @param points
     */
    void setPoints(int points);

    /**
     * Define a velocidade dos pontos de experîencia
     * @param speedXp
     */
    void setSpeedXp(int speedXp);

    JSONObject gameSettingToJsonObject();
}
