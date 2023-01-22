package org.example.classes;

import java.util.Date;

public class Interaction
{
    public String type;
    public int xp;
    public String player;
    public Date date;
    public int points;
    public int speedXP;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getSpeedXP() {
        return speedXP;
    }

    public void setSpeedXP(int speedXP) {
        this.speedXP = speedXP;
    }
}
