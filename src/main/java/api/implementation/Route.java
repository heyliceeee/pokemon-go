package api.implementation;

import api.interfaces.IRoute;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public void importRoutesFromJSONFile() throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        Root root = mapper.readValue(new File("docs/import/import.json"), Root.class);

        //todas as rotas
        List<Route> routes = root.getRoutes().stream().toList();

        //todos portals (incluindo a info associado que está no local)
        List<Portal> portals = new ArrayList<>();
        for(Local local : root.getLocals())
        {
            if(local instanceof Portal)
            {
                local.setType("Portal");
                portals.add((Portal) local);
            }
        }

        //todos os portais que estejam associados ás rotas existentes
        List<Portal> routePortals = new ArrayList<>();
        for (Portal portal : portals)
        {
            for (Route route : root.getRoutes())
            {
                if(route.getFrom() == portal.getId() || route.getTo() == portal.getId())
                {
                    routePortals.add(portal);
                    break;
                }
            }
        }


        //todos connectors
        List<Connector> connectors = new ArrayList<>();
        for(Local local : root.getLocals())
        {
            if(local instanceof Connector)
            {
                local.setType("Connector");
                connectors.add((Connector) local);
            }
        }

        //todos os connectors que estejam associados ás rotas existentes
        List<Connector> routeConnectors = new ArrayList<>();
        for (Connector connector : connectors)
        {
            for (Route route : root.getRoutes())
            {
                if(route.getFrom() == connector.getId() || route.getTo() == connector.getId())
                {
                    routeConnectors.add(connector);
                    break;
                }
            }
        }


        for (Route route : routes)
        {
            System.out.println(route);
        }

        for(Portal portal : routePortals)
        {
            System.out.println(portal);
        }

        for(Connector connector : routeConnectors)
        {
            System.out.println(connector);
        }
    }

    @Override
    public String toString() {
        return "Route{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }

    //region getters and setters

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

    //endregion
}
