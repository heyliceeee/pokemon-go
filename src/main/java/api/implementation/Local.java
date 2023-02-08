package api.implementation;

import api.interfaces.ICoordinate;
import api.interfaces.IInteraction;
import api.interfaces.ILocal;
import collections.implementation.ArrayUnorderedList;
import collections.interfaces.UnorderedListADT;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.IOException;
import java.util.Iterator;

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
    public UnorderedListADT<IInteraction> interactions = new ArrayUnorderedList<>();


    public Local(int id, String type, int energy, ICoordinate coordinates)
    {
        this.id = id;
        this.type = type;
        this.energy = energy;
        this.coordinates = coordinates;
    }


    @Override
    public String addInteraction(IInteraction interaction)
    {
        if(interaction == null)
        {
            throw new IllegalArgumentException("interaction cannot be null");
        }

        String s = "Failed";

        if(this.interactions.isEmpty() || !this.interactions.contains(interaction)) //se a lista de interaction estiver vazia ou não conter o interaction a ser adicionado, adiciona-o á lista
        {
            this.interactions.addToRear(interaction); //adiciona o interaction no fim da lista
            s = "Successful";
        }

        return s;
    }

    @Override
    public String getInteractionsListing()
    {
        String s = "Interactions: {\n";

        if(!this.interactions.isEmpty())
        {
            Iterator<IInteraction> iterator = interactions.iterator();

            while (iterator.hasNext())
            {
                s += iterator.next().toString() + "\n";
            }
        }
        else
        {
            s += "There is no interactions to list!\n";
        }

        s += "}";

        return s;
    }

    @Override
    public IInteraction getInteractionByID(int id)
    {
        Iterator<IInteraction> iterator = this.interactions.iterator();
        IInteraction interaction;

        while (iterator.hasNext())
        {
            interaction = iterator.next();

            if(interaction.getID() == id)
            {
                return interaction;
            }
        }

        return null;
    }

    @Override
    public int getIDLastInteraction()
    {
        int idLastInteraction=0;

        Iterator<IInteraction> iterator = this.interactions.iterator();
        IInteraction interaction;

        while (iterator.hasNext())
        {
            interaction = iterator.next();

            if(interaction.getID() > idLastInteraction)
            {
                return idLastInteraction;
            }
        }

        return idLastInteraction;
    }

    @Override
    public String toString() {
        return "Local{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", energy=" + energy +
                ", coordinates=" + coordinates +
                ", interactions=" + interactions +
                '}';
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
        this.energy += energy;
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
    public void setInteractionType(int id, String type)
    {

    }

    @Override
    public void setInteractionPlayer(int id, String playerName) {

    }

    @Override
    public void setInteractionDate(int id, String date) {

    }

    @Override
    public void setInteractionPoints(int id, int points) {

    }

    //endregion
}
