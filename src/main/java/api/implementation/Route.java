package api.implementation;

import api.interfaces.IRoute;

public class Route implements IRoute
{
    /**
     * identificador unico do portal/connector de partida da rota
     */
    private int from;

    /**
     * identificador unico do portal/connector de chegada da rota
     */
    private int to;


    @Override
    public int getFrom()
    {
        return from;
    }

    @Override
    public void setFrom(int from)
    {
        this.from = from;
    }

    @Override
    public int getTo()
    {
        return to;
    }

    @Override
    public void setTo(int to)
    {
        this.to = to;
    }
}
