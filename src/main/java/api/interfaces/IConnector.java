package api.interfaces;

import api.implementation.Interaction;

import java.util.ArrayList;

public interface IConnector extends ILocal{

    /**
     * Retorna o intervalo de fornecimento de energia a um jogador apos uma interacao do conector
     * @return o intervalo de fornecimento de energia a um jogador apos uma interacao do conector
     */
    int getCooldown();

    /**
     * Define o intervalo de fornecimento de energia a um jogador apos uma interacao no conector
     * @param cooldown
     */
    void setCooldown(int cooldown);
}
