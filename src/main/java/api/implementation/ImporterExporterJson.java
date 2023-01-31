package api.implementation;

import api.interfaces.*;
import collections.exceptions.EmptyCollectionException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Date;

public class ImporterExporterJson
{
    /**
     * Retorna "Successful" se importou  os dados do ficheiro JSON com sucesso
     * @param root
     * @param fileName localização do ficheiro JSON
     * @return "Successful" se importou os dados
     * @throws IOException
     * @throws ParseException
     */
    public String importFromJSONFile(IRoot root, String fileName) throws IOException, ParseException
    {
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader(fileName);
        JSONObject object = (JSONObject) parser.parse(reader);

        JSONArray localsArray = (JSONArray) object.get("locals");
        try
        {
            for(int i=0; i < localsArray.size(); i++)
            {
                JSONObject local = (JSONObject) localsArray.get(i);

                long id = (long) local.get("id");
                String type = (String) local.get("type");
                long energy = (long) local.get("energy");

                JSONObject coordinates = (JSONObject) local.get("coordinates");
                double longitudeCoordinate = (double) coordinates.get("longitude");
                double latitudeCoordinate = (double) coordinates.get("latitude");

                ICoordinate coordinates1 = new Coordinate(longitudeCoordinate, latitudeCoordinate);

                JSONArray interactionsArray = (JSONArray) local.get("interaction");
                try
                {
                    for(int j=0; j < interactionsArray.size(); j++)
                    {
                        JSONObject interaction = (JSONObject) interactionsArray.get(j);

                        String typeInteraction = (String) interaction.get("type");
                        String playerInteraction = (String) interaction.get("player");
                        Date dateInteraction = (Date) interaction.get("date");
                        long pointsInteraction = (long) interaction.get("points");
                        long speedXPInteraction = (long) interaction.get("speedXP");

                        IIteraction iteraction = new Interaction(typeInteraction, playerInteraction, dateInteraction, (int)pointsInteraction, (int)speedXPInteraction);

                        //adicionar a interação ao local
                        
                    }

                } catch (Exception e)
                {
                    System.out.println("ERROR: "+e.getMessage());
                }

                if(type.equals("Portal"))
                {
                    String name = (String) local.get("name");
                    long energyMax = (long) local.get("energyMax");

                    JSONObject ownership = (JSONObject) local.get("ownership");
                    String stateOwnership = (String) ownership.get("state");
                    String playerOwnership = (String) ownership.get("player");

                    IOwnership ownership1 = new Ownership(stateOwnership, playerOwnership);

                    IPortal portal = new Portal((int)id, type, name, (int)energy, (int)energyMax, ownership1, );
                    root.addLocal(portal);
                }
                else if(type.equals("Connector"))
                {
                    long cooldown = (long) local.get("cooldown");
                    root.addLocal(connector);
                }
            }

        } catch (NullPointerException e)
        {
            System.out.println("ERROR: "+e.getMessage());
        }

        JSONArray routesArray = (JSONArray) object.get("routes");
        try
        {
            for(int i=0; i < routesArray.size(); i++)
            {
                JSONObject routes = (JSONObject) routesArray.get(i);
                long from = (long) routes.get("from");
                long to = (long) routes.get("to");
                long distance = (long) routes.get("distance");

                try
                {
                    ILocal fromLocal = root.getLocalByID((int)from); //retorna o portal/conector correspondente do id do portal/conector
                    ILocal toLocal = root.getLocalByID((int)to); //retorna o portal/conector correspondente do id do portal/conector

                    root.addRoute(fromLocal, toLocal, distance);

                } catch (IllegalArgumentException | EmptyCollectionException e)
                {
                    System.out.println("ERROR: "+e.getMessage());
                }
            }
        } catch (NullPointerException e)
        {
            System.out.println("ERROR: "+e.getMessage());
        }

        JSONArray playersArray = (JSONArray) object.get("players");
        try
        {
            for(int i=0; i < playersArray.size(); i++)
            {
                JSONObject players = (JSONObject) playersArray.get(i);

                String namePlayer = (String) players.get("name");
                String teamPlayer = (String) players.get("team");
                long levelPlayer = (long) players.get("level");
                long xpPlayer = (long) players.get("xp");
                long energyPlayer = (long) players.get("energy");
                long energyMaxPlayer = (long) players.get("energyMax");
                long conqueredPortalsPlayer = (long) players.get("conqueredPortals");

                JSONObject coordinates2 = (JSONObject) players.get("coordinates");
                double longitudeCoordinate = (double) coordinates2.get("longitude");
                double latitudeCoordinate = (double) coordinates2.get("latitude");

                ICoordinate coordinates3 = new Coordinate(longitudeCoordinate, latitudeCoordinate);

                IPlayer player = new Player(namePlayer, teamPlayer, (int)levelPlayer, (int)xpPlayer, (int)energyPlayer, (int)energyMaxPlayer, (int)conqueredPortalsPlayer, coordinates3);
                root.addPlayer(player);
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("ERROR: "+e.getMessage());
        }

        return "Successful";
    }
}
