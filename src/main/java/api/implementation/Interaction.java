package api.implementation;

import api.interfaces.IIteraction;

import java.util.Date;

/**
 * Representacao da classe de uma interacao com um portal/connector
 */
public class Interaction implements IIteraction
{
    /**
     * tipo de interacao com o portal/connector
     */
    private String type;

    /**
     * pontos de experiencia que o jogador ganha apos uma interacao com o portal/connector
     */
    private int xp;

    /**
     * nome do jogador que teve interacao com o portal/connector
     */
    private String player;

    /**
     * data da interacao com o portal/connector
     */
    private Date date;

    /**
     * pontos de uma interacao com o portal/connector
     */
    private int points;

    /**
     * velocidade que vai ganhar pontos de experiencia apos uma interacao com o portal/connector
     */
    public int speedXP;


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
    public int getXp()
    {
        return xp;
    }

    @Override
    public void setXp(int xp)
    {
        this.xp = xp;
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
    public Date getDate()
    {
        return date;
    }

    @Override
    public void setDate(Date date)
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

    @Override
    public int getSpeedXP()
    {
        return speedXP;
    }

    @Override
    public void setSpeedXP(int speedXP)
    {
        this.speedXP = speedXP;
    }
}
