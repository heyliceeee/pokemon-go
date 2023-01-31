package api.implementation;

import api.interfaces.ICoordinate;
import api.interfaces.IIteraction;
import api.interfaces.ILocal;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representacao da classe de um portal/connector
 */

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
) //indica o tipo de classe que sera definido com base no valor da propriedade type

@JsonSubTypes({
        @JsonSubTypes.Type(value = Portal.class, name = "Portal"),
        @JsonSubTypes.Type(value = Connector.class, name = "Connector")
}) //indica quais são as subclasses possiveis (portal e connector) e como elas são identificadas na propriedade type do JSON
public class Local implements ILocal
{
    /**
     * identificador unico do portal/connector
     */
    private int id;

    /**
     * tipo de local (portal/connector)
     */
    @JsonProperty(required = true)
    private String type;

    /**
     * energia atual do portal/conector
     */
    private int energy;

    /**
     * coordenadas do portal/connector
     */
    private ICoordinate coordinates;

    /**
     * registo de interacoes do portal/connector
     */
    private List<Interaction> interaction;


    public Local(int id, String type, int energy, ICoordinate coordinates, List<Interaction> interaction)
    {
        this.id = id;
        this.type = type;
        this.energy = energy;
        this.coordinates = coordinates;
        this.interaction = interaction;
    }

    @Override
    public String addIteraction(IIteraction iteraction)
    {
        return null;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", type='" + type + '\'' +
                ", energy=" + energy +
                ", coordinates=" + coordinates +
                ", interaction=" + interaction;
    }

    //region getters and setters

    @Override
    public int getId()
    {
        return id;
    }

    @Override
    public void setId(int id)
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
    public int getEnergy()
    {
        return energy;
    }

    @Override
    public void setEnergy(int energy)
    {
        this.energy = energy;
    }

    @Override
    public Coordinate getCoordinates()
    {
        return (Coordinate) coordinates;
    }

    @Override
    public void setCoordinates(Coordinate coordinates)
    {
        this.coordinates = coordinates;
    }

    @Override
    public List<Interaction> getInteraction()
    {
        return interaction;
    }

    @Override
    public void setInteraction(List<Interaction> interaction)
    {
        this.interaction = interaction;
    }

    //endregion
}
