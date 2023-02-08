package api.implementation;

import api.interfaces.*;
import collections.exceptions.EmptyCollectionException;
import collections.exceptions.NotLocalInstanceException;
import collections.implementation.ArrayUnorderedList;
import collections.implementation.DoubleLinkedUnorderedList;
import collections.implementation.Network;
import collections.interfaces.UnorderedListADT;
import demo.Demo;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

/**
 * Classe que implementa o contracto de um grafo de rede de route
 *
 * @param <T> tipo de locais no grafo
 */
public class RouteNetwork<T> extends Network<T> implements RouteNetworkADT<T> {
    /**
     * constructor
     */
    public RouteNetwork() {
        super();
    }

    /**
     * enumeração interna para identificar o tipo de local
     */
    private enum SEARCH_TYPE {
        PORTAL_ANY, //indica uma pesquisa para qualquer portal mais próximo
        CONNECTOR_ANY, //indica uma pesquisa para qualquer connector mais próximo
        CONNECTOR_WITHOUTCOOLDOWN, //indica uma pesquisa que passa por um connector sem cooldowm mais próximo
        CONNECTOR_WITHOUTCOOLDOWN_PORTAL, //indica uma pesquisa que passa por um connector sem cooldown e para no portal mais próximo
        CONNECTOR_WITHOUTCOOLDOWN_CONNECTOR //indica uma pesquisa que passa por um connector sem cooldown e para no conector mais próximo

    }

    /**
     * Classe interna que contém as informações necessárias para suportar um algoritmo dijkstra
     */
    private class DijkstraSupport {
        /**
         * array do node anterior mais próximo
         */
        private final int[] prev;

        /**
         * array das distâncias mais curtas para o ponto de partida
         */
        private final double[] distance;

        /**
         * array que diz se um node foi visitado ou não
         */
        private final boolean[] visited;

        /**
         * constructor
         *
         * @param numberOfNodes número de nodes
         */
        private DijkstraSupport(int numberOfNodes) {
            this.prev = new int[numberOfNodes];
            this.distance = new double[numberOfNodes];
            this.visited = new boolean[numberOfNodes];
        }
    }

    @Override
    public void addEdge(T vertex1, T vertex2, double weight) throws EmptyCollectionException {
        super.addEdge(vertex1, vertex2, weight);
    }

    @Override
    public int getNumberOfPortals() {
        return this.getNumberOf(new Portal(0, "", 0, "", 0, null, null));
    }

    @Override
    public int getNumberOfConnectors() {
        return this.getNumberOf(new Connector(0, "", 0, 0, null));
    }

    @Override
    public Iterator<IPortal> getPortals() {
        UnorderedListADT<IPortal> resultList = new ArrayUnorderedList<>();

        for (int i = 0; i < super.numVertices; i++) {
            if (super.vertices[i] instanceof IPortal) {
                resultList.addToRear((IPortal) super.vertices[i]); //adiciona no fim da lista
            }
        }

        return resultList.iterator();
    }

    @Override
    public Iterator<IConnector> getConnectors() {
        UnorderedListADT<IConnector> resultList = new ArrayUnorderedList<>();

        for (int i = 0; i < super.numVertices; i++) {
            if (super.vertices[i] instanceof IConnector) {
                resultList.addToRear((IConnector) super.vertices[i]); //adiciona no fim da lista
            }
        }

        return resultList.iterator();
    }

    @Override
    public Iterator<IRoute<ILocal>> getRoutes() {
        UnorderedListADT<IRoute<ILocal>> resultList = new ArrayUnorderedList<>();

        for (int i = 0; i < super.numVertices; i++) {
            for (int j = i; j < super.numVertices; j++) {
                if (super.adjMatrix[i][j] != 0) {
                    IRoute<ILocal> route = new Route(super.vertices[i], super.vertices[j], super.adjMatrix[i][j]);
                    resultList.addToRear(route);
                }
            }
        }

        return resultList.iterator();
    }

    @Override
    public Iterator<ILocal> shortestRouteToPortal(IRoot root, T source) throws NotLocalInstanceException, ParseException {
        DijkstraSupport ds = this.shortestRouteDijkstra(source);

        return this.shortestRouteTo(root, SEARCH_TYPE.PORTAL_ANY, ds.prev, ds.distance, ds.visited, source);
    }

    @Override
    public Iterator<ILocal> shortestRouteToConnector(IRoot root, T source) throws NotLocalInstanceException, ParseException {
        DijkstraSupport ds = this.shortestRouteDijkstra(source);

        return this.shortestRouteTo(root, SEARCH_TYPE.CONNECTOR_ANY, ds.prev, ds.distance, ds.visited, source);
    }

    @Override
    public Iterator<ILocal> shortestRouteGoConnectorWithoutCooldownToPortal(IRoot root, T source) throws NotLocalInstanceException, ParseException {
        DijkstraSupport ds = this.shortestRouteDijkstra(source);

        return this.shortestRouteTo(root, SEARCH_TYPE.CONNECTOR_WITHOUTCOOLDOWN, ds.prev, ds.distance, ds.visited, source);

        /*

        //obter o conector
        IConnector connector = null;
        while (connectorWithoutCooldown.hasNext()) {
            ILocal local = connectorWithoutCooldown.next();

            if (local instanceof IConnector) {
                connector = (IConnector) local;
                break;
            }
        }


        return this.shortestRouteToPortal(root, (T) connector);*/
    }

    @Override
    public Iterator<ILocal> shortestRouteGoConnectorWithoutCooldownToConnector(IRoot root, T source) throws NotLocalInstanceException, ParseException {
        DijkstraSupport ds = this.shortestRouteDijkstra(source);

        Iterator<ILocal> connectorWithoutCooldown = this.shortestRouteTo(root, SEARCH_TYPE.CONNECTOR_WITHOUTCOOLDOWN, ds.prev, ds.distance, ds.visited, source);

        //obter o conector
        IConnector connector = null;
        while (connectorWithoutCooldown.hasNext()) {
            ILocal local = connectorWithoutCooldown.next();

            if (local instanceof IConnector) {
                connector = (IConnector) local;
                break;
            }
        }

        return this.shortestRouteToConnector(root, (T) connector);
    }

    /**
     * Conta o número de instâncias que existem desse tipo
     *
     * @param type objeto a ser comparado
     * @param <T>  tipo a ser comparado
     * @return número de vezes
     */
    private <T extends ILocal> int getNumberOf(T type) {
        int count = 0;

        for (int i = 0; i < super.numVertices; i++) {
            if (super.vertices[i].getClass().isInstance(type)) {
                count++;
            }
        }

        return count;
    }

    /**
     * Retorna toda a informação sobre o algoritmo de Dijkstra, obtem a rota mais curta do ponto de partida até ao todo resto dos nodes
     *
     * @param source ponto de partida
     * @return toda a informação sobre o algoritmo de Dijkstra
     */
    private DijkstraSupport shortestRouteDijkstra(T source) throws IllegalArgumentException {
        if (source == null) {
            throw new IllegalArgumentException("Source cannot be null");
        }

        int startVertex = getIndex(source), currentIndex;

        if (startVertex == -1) {
            throw new IllegalArgumentException("Source doesn't exists in the graph");
        }

        DijkstraSupport ds = new DijkstraSupport(super.numVertices);

        for (int vertexIndex = 0; vertexIndex < super.numVertices; vertexIndex++) {
            ds.distance[vertexIndex] = Integer.MAX_VALUE;
            ds.visited[vertexIndex] = false;
        }

        ds.distance[startVertex] = 0; //a distância do primeiro node de si mesmo é 0
        ds.prev[startVertex] = -1; //o anterior do primeiro node é -1

        double tmpDistance, edgeDistance;

        for (int i = 0; i < super.numVertices; i++) {
            currentIndex = super.getSmallDistanceNode(ds.distance, ds.visited); //obter o indice do node com o peso baixo
            ds.visited[currentIndex] = true;

            for (int vertexIndex = 0; vertexIndex < super.numVertices; vertexIndex++) {
                edgeDistance = super.adjMatrix[currentIndex][vertexIndex]; //obter a distância entre o node proximo e todos os outros nodes
                tmpDistance = ds.distance[currentIndex] + edgeDistance; //calcular a distancia

                if (edgeDistance > 0 && tmpDistance < ds.distance[vertexIndex]) {
                    ds.distance[vertexIndex] = tmpDistance;
                    ds.prev[vertexIndex] = currentIndex;
                }
            }
        }

        return ds;
    }

    /**
     * Rota mais curta do local de ponta de partida até a outro local
     *
     * @param searchType tipo de local a ser procurado
     * @param prev
     * @param distance
     * @param visited
     * @param source     local de ponto de partida
     * @return iterador com a localização da rota
     */
    private Iterator<ILocal> shortestRouteTo(IRoot root, SEARCH_TYPE searchType, int[] prev, double[] distance, boolean[] visited, T source) throws NotLocalInstanceException, ParseException {
        if (!(source instanceof ILocal)) {
            throw new NotLocalInstanceException("Vertex need to be ILocal instance");
        }

        int minIndexPortal = -1;
        double minDistanceToPortal = Integer.MAX_VALUE;

        int minIndexConnector = -1;
        double minDistanceToConnector = Integer.MAX_VALUE;

        //verifica a distância mínima e atualiza o último indice do anterior
        for (int i = 0; i < distance.length; i++) {
            if (visited[i] && distance[i] != 0) //se foi visitado, e não é o mesmo que source
            {
                if (super.vertices[i] instanceof IPortal) //se for um portal
                {
                    if (distance[i] < minDistanceToPortal) {
                        if (searchType == SEARCH_TYPE.PORTAL_ANY) //se for qualquer portal
                        {
                            minDistanceToPortal = distance[i];
                            minIndexPortal = i;
                        }
                    }
                } else if (super.vertices[i] instanceof IConnector) //se for um conector
                {
                    if (distance[i] < minDistanceToConnector) {
                        if (searchType == SEARCH_TYPE.CONNECTOR_ANY) //se for qualquer conector
                        {
                            minDistanceToConnector = distance[i];
                            minIndexConnector = i;
                        } else if (searchType == SEARCH_TYPE.CONNECTOR_WITHOUTCOOLDOWN) //se for qualquer conector mas sem cooldown
                        {
                            //se o connector atual não tem interações, então pode carregar energia ao jogador
                            if (((IConnector) super.vertices[i]).getInteractionsListing().length() == 0) {
                                //data da interação
                                LocalDateTime dateInteraction = LocalDateTime.of(1970, Month.JANUARY, 1, 0, 0, 0);

                                long duration = Duration.between(dateInteraction, LocalDateTime.now()).toMinutes(); //obter em minutos, há quanto tempo foi realizado a interação

                                long recentMinutes = Long.MAX_VALUE;

                                //saber qual foi a ultima iteração do jogador com o connector
                                if (recentMinutes > duration) {
                                    recentMinutes = duration;
                                }

                                if (distance[i] < minDistanceToConnector && recentMinutes > ((IConnector) super.vertices[i]).getCooldown()) //se passou o periodo de cooldown do jogador atual com o connector
                                {
                                    minDistanceToConnector = distance[i];
                                    minIndexConnector = i;
                                }
                            }
                            else if(((IConnector) super.vertices[i]).getInteractionsListing().length() > 0)
                            {
                                int connectorID = ((IConnector) super.vertices[i]).getId(); //id do connector
                                boolean found = true; //encontrou interações do jogador no connector
                                IConnector connector = root.getConnectorByID(connectorID);
                                IInteraction lastConnectorInteractionByPlayerName = null;

                                try
                                {
                                    lastConnectorInteractionByPlayerName = connector.getConnectorLastInteractionByPlayerName(connectorID, Demo.playerName); //ultima interação do jogador com o connector

                                    if(lastConnectorInteractionByPlayerName == null)
                                    {
                                        found = false;
                                    }
                                    else
                                    {
                                        found = true;
                                    }
                                }
                                catch (Exception e)
                                {
                                    found = false;
                                }

                                if (found == false) //se não encontrou a ultima interação do jogador
                                {
                                    //data da interação
                                    LocalDateTime dateInteraction = LocalDateTime.of(1970, Month.JANUARY, 1, 0, 0, 0);

                                    long duration = Duration.between(dateInteraction, LocalDateTime.now()).toMinutes(); //obter em minutos, há quanto tempo foi realizado a interação

                                    long recentMinutes = Long.MAX_VALUE;

                                    //saber qual foi a ultima iteração do jogador com o connector
                                    if (recentMinutes > duration) {
                                        recentMinutes = duration;
                                    }

                                    if (distance[i] < minDistanceToConnector && recentMinutes > ((IConnector) super.vertices[i]).getCooldown()) //se passou o periodo de cooldown do jogador atual com o connector
                                    {
                                        minDistanceToConnector = distance[i];
                                        minIndexConnector = i;
                                    }
                                }
                                else if (found == true) //se a interação foi realizada pelo jogador atual
                                {
                                    //data da interação
                                    String date = lastConnectorInteractionByPlayerName.getDate();
                                    //String date = ((IConnector) super.vertices[i]).lastConnectorInteractionByPlayerName.getDate();
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                                    LocalDateTime dateInteraction;

                                    try {
                                        dateInteraction = LocalDateTime.parse(date, formatter);
                                    } catch (Exception e) {
                                        dateInteraction = LocalDateTime.of(1970, Month.JANUARY, 1, 0, 0, 0);
                                    }

                                    long duration = Duration.between(dateInteraction, LocalDateTime.now()).toMinutes(); //obter em minutos, há quanto tempo foi realizado a interação

                                    long recentMinutes = Long.MAX_VALUE;

                                    //saber qual foi a ultima iteração do jogador com o connector
                                    if (recentMinutes > duration) {
                                        recentMinutes = duration;
                                    }

                                    if (distance[i] < minDistanceToConnector && recentMinutes > ((IConnector) super.vertices[i]).getCooldown()) //se passou o periodo de cooldown do jogador atual com o connector
                                    {
                                        minDistanceToConnector = distance[i];
                                        minIndexConnector = i;
                                    }
                                }
                            }

                            /*for (int j = 0; j < ((IConnector) super.vertices[i]).getInteractionsListing().length(); j++) //verificar nas interações, a última vez que o jogador atual foi recarregar-se ao conector
                            {
                                int connectorID = ((IConnector) super.vertices[i]).getId();
                                boolean found = true;

                                try
                                {
                                    IConnector lastConnectorInteractionByPlayerName = root.getConnectorInteractionsByPlayerName(connectorID, Demo.playerName); //ultima interação do jogador com o connector

                                    if(lastConnectorInteractionByPlayerName == null)
                                    {
                                        found = false;
                                    }
                                    else
                                    {
                                        found = true;
                                    }
                                }
                                catch (Exception e)
                                {
                                    found = false;
                                }


                                if (found == false) //se não encontrou a ultima interação do jogador
                                {
                                    //data da interação
                                    LocalDateTime dateInteraction = LocalDateTime.of(1970, Month.JANUARY, 1, 0, 0, 0);

                                    long duration = Duration.between(dateInteraction, LocalDateTime.now()).toMinutes(); //obter em minutos, há quanto tempo foi realizado a interação

                                    long recentMinutes = Long.MAX_VALUE;

                                    //saber qual foi a ultima iteração do jogador com o connector
                                    if (recentMinutes > duration) {
                                        recentMinutes = duration;
                                    }

                                    if (distance[i] < minDistanceToConnector && recentMinutes > ((IConnector) super.vertices[i]).getCooldown()) //se passou o periodo de cooldown do jogador atual com o connector
                                    {
                                        minDistanceToConnector = distance[i];
                                        minIndexConnector = i;
                                    }
                                }
                                else if (found == true) //se a interação foi realizada pelo jogador atual
                                {
                                    //data da interação
                                    String date = ((IConnector) super.vertices[i]).getInteractionByID(j).getDate();
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                                    LocalDateTime dateInteraction;

                                    try {
                                        dateInteraction = LocalDateTime.parse(date, formatter);
                                    } catch (Exception e) {
                                        dateInteraction = LocalDateTime.of(1970, Month.JANUARY, 1, 0, 0, 0);
                                    }

                                    long duration = Duration.between(dateInteraction, LocalDateTime.now()).toMinutes(); //obter em minutos, há quanto tempo foi realizado a interação

                                    long recentMinutes = Long.MAX_VALUE;

                                    //saber qual foi a ultima iteração do jogador com o connector
                                    if (recentMinutes > duration) {
                                        recentMinutes = duration;
                                    }

                                    if (distance[i] < minDistanceToConnector && recentMinutes > ((IConnector) super.vertices[i]).getCooldown()) //se passou o periodo de cooldown do jogador atual com o connector
                                    {
                                        minDistanceToConnector = distance[i];
                                        minIndexConnector = i;
                                    }
                                }
                            }*/
                        }
                    }
                }
            }
        }

        UnorderedListADT<ILocal> resultList = new DoubleLinkedUnorderedList<>();
        int currentIndex = -1;

        /**
         * após encontra todas as distâncias, faremos a travessia até o grafo, partindo do local mais próximo e percorrendo o grafo até chegarmos ao ponto de partida
         */
        if (searchType == SEARCH_TYPE.PORTAL_ANY) {
            currentIndex = minIndexPortal;
        } else if (searchType == SEARCH_TYPE.CONNECTOR_ANY || searchType == SEARCH_TYPE.CONNECTOR_WITHOUTCOOLDOWN || searchType == SEARCH_TYPE.CONNECTOR_WITHOUTCOOLDOWN_PORTAL || searchType == SEARCH_TYPE.CONNECTOR_WITHOUTCOOLDOWN_CONNECTOR) {
            currentIndex = minIndexConnector;
        }

        if (currentIndex == -1) //se não existe locals no grafo
        {
            return null;
        }

        while (prev[currentIndex] != -1) {
            resultList.addToFront((ILocal) this.vertices[currentIndex]);
            currentIndex = prev[currentIndex];
        }

        //resultList.addToFront((ILocal) super.vertices[super.getIndex(source)]);

        return resultList.iterator();
    }

    @Override
    public Iterator<ILocal> shortestRouteOnlyConnectors(T source) throws NotLocalInstanceException {
        return null;
    }

    @Override
    public Iterator<ILocal> shortestRouteOnlyPortals(T source) throws NotLocalInstanceException {
        return null;
    }
}
