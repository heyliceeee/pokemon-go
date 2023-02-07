package api.implementation;

import api.interfaces.IInteraction;
import org.json.simple.JSONObject;

/**
 * Representacao da classe de uma interacao com um portal/connector
 */
public class Interaction implements IInteraction
{
    /**
     * identificador único da interação
     */
    private int id;

    /**
     * tipo de interacao com o portal/connector
     */
    private String type;

    /**
     * nome do jogador que teve interacao com o portal/connector
     */
    private String player;

    /**
     * data da interacao com o portal/connector
     */
    private String date;

    /**
     * pontos de uma interacao com o portal/connector
     */
    private int points;


    public Interaction(int id, String type, String player, String date, int points)
    {
        this.id = id;
        this.type = type;
        this.player = player;
        this.date = date;
        this.points = points;
    }

    @Override
    public JSONObject interactionToJsonObject()
    {
        JSONObject root = new JSONObject();

        root.put("id", this.id);
        root.put("type", this.type);
        root.put("player", this.player);
        root.put("date", this.date);
        root.put("points", this.points);

        return root;
    }

    @Override
    public String toString() {
        return "Interaction{" +
                "type='" + type + '\'' +
                ", player='" + player + '\'' +
                ", date=" + date +
                ", points=" + points +
                '}';
    }


    //region getters and setters

    @Override
    public int getID()
    {
        return id;
    }

    @Override
    public void setID(int id)
    {
        this.id = id;
    }

    @Override
    public String getType()
    {
        return type;
    }

    @Override
    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public String getPlayer()
    {
        return player;
    }

    @Override
    public void setPlayer(String player)
    {
        this.player = player;
    }

    @Override
    public String getDate()
    {
        return date;
    }

    @Override
    public void setDate(String date)
    {
        this.date = date;
    }

    @Override
    public int getPoints()
    {
        return points;
    }

    @Override
    public void setPoints(int points)
    {
        this.points = points;
    }

    //endregion
}
