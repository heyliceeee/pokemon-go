package api.implementation;

import api.interfaces.IOwnership;

/**
 * Representacao da classe de um dono de um portal
 */
public class Ownership implements IOwnership
{
    /**
     * equipa que conquistou o portal (nenhuma equipa, sparks, giants)
     */
    private String state;

    /**
     * nome do jogador que conquistou o portal
     */
    private String player;


    public Ownership(String state, String player)
    {
        this.state = state;
        this.player = player;
    }

    @Override
    public String toString() {
        return "Ownership{" +
                "state='" + state + '\'' +
                ", player='" + player + '\'' +
                '}';
    }

    //region getters and setters
    @Override
    public String getState()
    {
        return state;
    }

    @Override
    public void setState(String state)
    {
        this.state = state;
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

    //endregion
}
