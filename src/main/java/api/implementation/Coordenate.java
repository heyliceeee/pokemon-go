package api.implementation;

import api.interfaces.ICoordenate;

/**
 * Representacao da classe de uma coordenada
 */
public class Coordenate implements ICoordenate
{
    /**
     * longitude da coordenada
     */
    private double longitude;

    /**
     * latitude da coordenada
     */
    private double latitude;


    @Override
    public double getLongitude()
    {
        return longitude;
    }

    @Override
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    @Override
    public double getLatitude()
    {
        return latitude;
    }

    @Override
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }
}
