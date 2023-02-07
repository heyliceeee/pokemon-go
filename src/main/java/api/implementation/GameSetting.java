package api.implementation;

import api.interfaces.IGameSetting;
import org.json.simple.JSONObject;

/**
 * Representação do identificador único da definição
 */
public class GameSetting implements IGameSetting
{
    /**
     * identificador único da definição
     */
    private int ID;

    /**
     * tipo de definição
     */
    private String type;

    /**
     * pontos de experiencia da definição
     */
    private int points;

    /**
     * velocidade dos pontos de experiencia da definição
     */
    private int speedXp;

    public GameSetting(int ID, String type, int points, int speedXp) {
        this.ID = ID;
        this.type = type;
        this.points = points;
        this.speedXp = speedXp;
    }

    @Override
    public JSONObject gameSettingToJsonObject()
    {
        JSONObject root = new JSONObject();

        root.put("id", this.ID);
        root.put("type", this.type);
        root.put("points", this.points);
        root.put("speedXp", this.speedXp);

        return root;
    }

    //region getters and setters
    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public int getSpeedXp() {
        return speedXp;
    }

    @Override
    public void setType(String type) {

        this.type = type;
    }

    @Override
    public void setPoints(int points) {

        this.points = points;
    }

    @Override
    public void setSpeedXp(int speedXp) {

        this.speedXp = speedXp;
    }

    //endregion
}
