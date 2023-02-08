package api.interfaces;

import api.exceptions.ElementAlreadyExistsException;
import api.implementation.*;
import collections.exceptions.ElementNotFoundException;
import collections.exceptions.EmptyCollectionException;
import collections.exceptions.NotLocalInstanceException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Interface da raiz do JSON
 */
public interface IRoot
{
    /**
     * Retorna "Successful" se foi adicionado um portal/conector ao grafo
     * @param local a ser adicionado
     * @return "Successful" se foi adicionado um portal/conector ao grafo
     * @throws ElementAlreadyExistsException
     */
    String addLocal(ILocal local);

    /**
     * Retorna "Successful" se foi adicionado um jogador á lista dos jogadores
     * @param player a ser adicionado
     * @return "Successful" se foi adicionado um jogador á lista dos jogadores
     */
    String addPlayer(IPlayer player);

    String removePlayer(IPlayer player) throws EmptyCollectionException;

    /**
     * Retorna "Successful" se conseguir adicionar um route entre 2 portals e/ou connectors
     * @param local1 um portal/connector
     * @param local2 outro portal/connector
     * @param weight peso do vértice entre o local1 e local2
     * @return "Successful" se conseguir adicionar um route entre 2 portals e/ou connectors
     */
    String addRoute(ILocal local1, ILocal local2, double weight) throws EmptyCollectionException;

    String addGameSetting(IGameSetting gameSetting);

    /**
     * Exporta o grafo acerca das routes em .dot e em .png
     * @throws EmptyCollectionException se o grafo for vazio
     * @throws InterruptedException
     */
    void exportGraph() throws EmptyCollectionException, InterruptedException;

    /**
     * Retorna o iterador com o route mais curto
     * @param option ponto de chegada (portal ou conector ou conector sem cooldown e portal ou conector sem cooldown e conector)
     * @param local portal ou conector
     * @return o iterador com o route mais curto
     * @throws EmptyCollectionException
     */
    Iterator<ILocal> getRoute(IRoot root, int option, ILocal local) throws EmptyCollectionException, NotLocalInstanceException, ParseException;

    /**
     * Retorna em string uma listagem dos jogadores
     * @return em string uma listagem dos jogadores
     */
    String getPlayersListing();

    /**
     * Retorna em string uma listagem dos portais
     * @return em string uma listagem dos portais
     */
    String getPortalsListing();

    /**
     * Retorna em string uma listagem dos conectores
     * @return em string uma listagem dos conectores
     */
    String getConnectorsListing();

    /**
     * Exporta todos os jogadores associados ao jogo em JSON
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    void exportPlayersToJson() throws IOException;

    /**
     * Exporta todos os portais associados ao jogo em JSON
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    void exportPortalsToJson() throws IOException;

    /**
     * Exporta todos os conectores associados ao jogo em JSON
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    void exportConnectorsToJson() throws IOException;

    /**
     * Exporta todos os routes associados ao jogo em JSON
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    void exportRoutesToJson() throws IOException;

    void exportGameSettingsToJson() throws IOException;

    /**
     * Exporta toda a informação associados ao jogo em JSON
     * @throws IOException se ocorrer algum erro ao tentar escrever no ficheiro JSON
     */
    void exportRootToJson() throws IOException;

    /**
     * Retorna o {@link ILocal portal/connector} com o ID enviado por parâmetro
     * @param id do portal/connector a ser procurado
     * @return o portal/connector se foi encontrado ou null se não foi encontrado
     */
    ILocal getLocalByID(int id);

    IPortal getRandomPortal();

    /**
     * Retorna o portal com o id dado
     * @param id que vai ser procurado
     * @return o portal se foi encontrado, null se não foi encontrado
     */
    IPortal getPortalByID(int id);

    /**
     * Atualiza o ID do portal
     * @param id atual ID do portal
     * @param newID novo ID do portal
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum portal
     * @throws ElementAlreadyExistsException se já existe um portal com o novo ID
     */
    void setPortalID(int id, int newID) throws ElementNotFoundException, ElementAlreadyExistsException;

    /**
     * Atualiza o type do portal
     * @param id ID do portal
     * @param type novo type do portal
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum portal
     */
    void setPortalType(int id, String type) throws ElementNotFoundException;

    /**
     * Atualiza o nome do portal
     * @param id ID do portal
     * @param name novo nome do portal
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum portal
     */
    void setPortalName(int id, String name) throws ElementNotFoundException;

    /**
     * Atualiza a quantidade de energia atual do portal
     * @param id ID do portal
     * @param energy nova quantidade de energia atual do portal
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum portal
     */
    void setPortalEnergy(int id, int energy) throws ElementNotFoundException;

    /**
     * Atualiza a quantidade de energia máxima do portal
     * @param id ID do portal
     * @param energyMax nova quantidade de energia máxima do portal
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum portal
     */
    void setPortalEnergyMax(int id, int energyMax) throws ElementNotFoundException;

    /**
     * Atualiza o dono do portal
     * @param id ID do portal
     * @param ownership o novo dono do portal
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum portal
     */
    void setPortalOwnership(int id, Ownership ownership) throws ElementNotFoundException;

    /**
     * Atualiza as coordenadas do portal
     * @param id ID do portal
     * @param coordinate novas coordenadas do portal
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum portal
     */
    void setPortalCoordinate(int id, Coordinate coordinate) throws ElementNotFoundException;

    /**
     * Atualiza as interações do portal com o jogador
     * @param id ID do portal
     * @param interaction novas interações do portal
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum portal
     */
    void setPortalInteraction(int id, List<Interaction> interaction) throws ElementNotFoundException;

    IConnector getRandomConnector();

    /**
     * Retorna o conector com o id dado
     * @param id que vai ser procurado
     * @return o conector se foi encontrado, null se não foi encontrado
     */
    IConnector getConnectorByID(int id);

    IInteraction getConnectorInteractionsByPlayerName(int idConnector, String playerName);

    /**
     * Atualiza o ID do connector
     * @param id ID atual do connector
     * @param newID novo ID do connector
     * @throws ElementNotFoundException se o ID enviado por parametro não corresponde a nenhum connector
     * @throws ElementAlreadyExistsException se já existe um connector com o novo ID
     */
    void setConnectorID(int id, int newID) throws ElementNotFoundException, ElementAlreadyExistsException;

    /**
     * Atualiza o type do connector
     * @param id ID atual do connector
     * @param type novo type do connector
     */
    void setConnectorType(int id, String type);

    /**
     * Atualiza a quantidade de energia atual do connector
     * @param id ID atual do connector
     * @param energy nova quantidade de energia atual do connector
     */
    void setConnectorEnergy(int id, int energy);

    /**
     * Atualiza o periodo de cooldown do connector com o jogador
     * @param id ID atual do connector
     * @param cooldown nova periodo de cooldown do connector
     */
    void setConnectorCooldown(int id, int cooldown);

    /**
     * Atualiza as coordenadas do connector
     * @param id ID atual do connector
     * @param coordinate novas coordenadas do connector
     */
    void setConnectorCoordinate(int id, Coordinate coordinate);

    /**
     * Atualiza as interações do connector com o jogador
     * @param id ID atual do connector
     * @param interaction novas interações do connector
     */
    void setConnectorInteraction(int id, List<Interaction> interaction);

    /**
     * Retorna toda a informação do jogador pelo nome
     * @param name nome do jogador a ser pesquisado
     * @return toda a informação do jogador se for encontrado, null caso contrário
     */
    IPlayer getPlayerByName(String name);

    /**
     * Atualiza a equipa do jogador
     * @param name nome do jogador
     * @param team nova equipa do jogador
     */
    void setPlayerTeam(String name, String team);

    /**
     * Atualiza o nível do jogador
     * @param name nome do jogador
     * @param level nível do jogador
     */
    void setPlayerLevel(String name, int level);

    /**
     * Atualiza os pontos de experiência do jogador
     * @param name nome do jogador
     * @param xp pontos de experiência
     */
    void setPlayerXP(String name, int xp);

    /**
     * Atualiza a quantidade de energia atual do jogador
     * @param name nome do jogador
     * @param energy quantidade de energia atual
     */
    void setPlayerEnergy(String name, int energy);

    /**
     * Atualiza a quantidade de energia máxima do jogador
     * @param name nome do jogador
     * @param energyMax quantidade de energia máxima
     */
    void setPlayerEnergyMax(String name, int energyMax);

    /**
     * Atualiza as coordenadas do jogador
     * @param name nome do jogador
     * @param coordinate coordenadas
     */
    void setPlayerCoordinate(String name, Coordinate coordinate);

    /**
     * Atualiza a quantidade de portais conquistados pelo jogador
     * @param name nome do jogador
     * @param conqueredPortals quantidade de portais conquistados
     */
    void setPlayerConqueredPortals(String name, int conqueredPortals);

    IGameSetting getGameSettingByType(String type);

    /**
     * Retorna o iterador com os portais ordenados por algum parâmetro
     * @param sortType tipo de ordenação a ser aplicado
     * @return iterador com os dados ordenados
     */
    Iterator<IPortal> getPortalsOrderBy(SortType sortType);

    /**
     * Retorna o iterador com os conectores ordenados por algum parâmetro
     * @param sortType tipo de ordenação a ser aplicado
     * @return iterador com os dados ordenados
     */
    Iterator<IConnector> getConnectorsOrderBy(SortType sortType);

    /**
     * Retorna o iterador com os jogadores ordenados por algum parâmetro
     * @param sortType tipo de ordenação a ser aplicado
     * @return iterador com os dados ordenados
     */
    Iterator<IPlayer> getPlayersOrderBy(SortType sortType);

    /**
     * Retorna o iterador com as routes ordenados por algum parâmetro
     * @param sortType tipo de ordenação a ser aplicado
     * @return iterador com os dados ordenados
     */
    Iterator<IRoute<ILocal>> getRoutesOrderBy(SortType sortType);



    /* *//**
     * Retorna os locais (portal/connector)
     * @return os locais (portal/connector)
     *//*
    List<Local> getLocals();

    *//**
     * Define os locais (portal/connector)
     * @param locals
     *//*
    void setLocals(List<Local> locals);

    *//**
     * Retorna as rotas
     * @return as rotas
     *//*
    List<Route> getRoutes();

    *//**
     * Define as rotas
     * @param routes
     *//*
    void setRoutes(List<Route> routes);

    *//**
     * Retorna os jogadores
     * @return os jogadores
     *//*
    List<Player> getPlayers();

    *//**
     * Define os jogadores
     * @param players
     *//*
    void setPlayers(List<Player> players);*/
}