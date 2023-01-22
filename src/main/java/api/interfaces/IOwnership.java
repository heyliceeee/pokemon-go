package api.interfaces;

/**
 * Interface do dono de um portal
 */
public interface IOwnership
{
    /**
     * Retorna a equipa que conquistou o portal
     * @return a equipa que conquistou o portal
     */
    String getState();

    /**
     * Define a equipa que conquistou o portal
     * @param state
     */
    void setState(String state);

    /**
     * Retorna o nome do jogador que conquistou o portal
     * @return o nome do jogador que conquistou o portal
     */
    String getPlayer();

    /**
     * Define o nome do jogador que conquistou o portal
     * @param player
     */
    void setPlayer(String player);
}
