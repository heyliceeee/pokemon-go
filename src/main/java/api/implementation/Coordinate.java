package api.implementation;

import api.interfaces.ICoordinate;

/**
 * Representacao da classe de uma coordenada
 */
public class Coordinate implements ICoordinate
{
    /**
     * longitude da coordenada
     */
    private double longitude;

    /**
     * latitude da coordenada
     */
    private double latitude;


    public Coordinate(double longitude, double latitude)
    {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    //region getters and setters
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

    //endregion
}
