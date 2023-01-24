package api.interfaces;

import api.implementation.Ownership;

import java.util.ArrayList;

public interface IPortal extends ILocal {

    /**
     * Retorna o nome do portal
     * @return o nome do portal
     */
    String getName();

    /**
     * Define o nome do portal
     * @param name
     */
    void setName(String name);

    /**
     * Retorna o dono do portal
     * @return o dono do portal
     */
    ArrayList<Ownership> getOwnership();

    /**
     * Define o dono do portal
     * @param ownership
     */
    void setOwnership(ArrayList<Ownership> ownership);

    /**
     * Retorna a energia do portal/connector
     * @return a energia do portal/connector
     */
    int getEnergy();

    /**
     * Define a energia do portal/connector
     * @param energy
     */
    void setEnergy(int energy);

    /**
     * Retorna a energia maxima do portal
     * @return a energia maxima do portal
     */
    int getEnergyMax();

    /**
     * Define a energia maxima do portal
     * @param energyMax
     */
    void setEnergyMax(int energyMax);
}
