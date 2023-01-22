package api.interfaces;

import api.implementation.Interaction;

import java.util.ArrayList;

/**
 * Interface da definicao global do jogo
 */
public interface IGlobalGameSetting
{
    /**
     * Retorna as possiveis interacoes do portal/connector
     * @return as possiveis interacoes do portal/connector
     */
    ArrayList<Interaction> getInteraction();

    /**
     * Define as possiveis interacoes do portal/connector
     * @param interaction
     */
    void setInteraction(ArrayList<Interaction> interaction);
}
