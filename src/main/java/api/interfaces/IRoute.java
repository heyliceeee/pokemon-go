package api.interfaces;

import java.io.IOException;

/**
 * Interface da rota
 */
public interface IRoute
{

    /**
     * Importar rotas e portals/connectors associados de um ficheiro JSON
     */
    String importRoutesFromJSONFile() throws IOException;

    /**
     * Retorna o identificador unico do portal/connector de partida da rota
     * @return o identificador unico do portal/connector de partida da rota
     */
    int getFrom();

    /**
     * Define identificador unico do portal/connector de partida da rota
     * @param from
     */
    void setFrom(int from);

    /**
     * Retorna o identificador unico do portal/connector de chegada da rota
     * @return o identificador unico do portal/connector de chegada da rota
     */
    int getTo();

    /**
     * Define o identificador unico do portal/connector de chegada da rota
     * @param to
     */
    void setTo(int to);
}