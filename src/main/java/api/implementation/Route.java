package api.implementation;

import api.interfaces.ILocal;
import api.interfaces.IRoute;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Classe que representa a rota entre portals e/ou connectors
 * @param <T>
 */
public class Route<T> implements IRoute<T>
{
    /**
     * o portal/connector de partida da rota
     */
    private T from;

    /**
     * o portal/connector de chegada da rota
     */
    private T to;

    /**
     * o peso entre os dois portals e/ou connectors
     */
    private double weight;


    /**
     * constructor
     * @param from portal/connector de partida
     * @param to portal/connector de chegada
     * @param weight o peso entre os 2 portals e/ou connectors
     */
    public Route(T from, T to, double weight)
    {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString()
    {
        return "Route{" +
                "from=" + from +
                ", to=" + to +
                ", weight=" + weight +
                '}';
    }

    //region getters and setters

    @Override
    public T getFrom()
    {
        return from;
    }

    @Override
    public T getTo()
    {
        return to;
    }

    @Override
    public double getWeight()
    {
        return weight;
    }

    @Override
    public void setWeight(int x1, int y1, int x2, int y2)
    {
        this.weight = (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }

    //endregion
}