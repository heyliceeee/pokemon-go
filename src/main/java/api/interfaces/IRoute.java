package api.interfaces;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Comparator;

/**
 * Interface da rota
 */
public interface IRoute<T>
{

    /**
     * Retorna o portal/connector de partida da rota
     * @return o o portal/connector de partida da rota
     */
    T getFrom();

    /**
     * Retorna o portal/connector de chegada da rota
     * @return o portal/connector de chegada da rota
     */
    T getTo();

    /**
     * Retorna o peso entre o portal/connector de partida e o portal/connector de chegada
     * @return o peso
     */
    double getWeight();

    /**
     * Define o peso entre os 2 pontos apartir das coordenadas dos 2 pontos
     * peso = raizQuadrada((x2-x1)^2 + (y2-y1)^2)
     * @param x1 coordenadas do ponto de partida
     * @param y1 coordenadas do ponto de partida
     * @param x2 coordenadas do ponto de chegada
     * @param y2 coordenadas do ponto de chegada
     */
    void setWeight(int x1, int y1, int x2, int y2);


    Comparator<IRoute> WEIGHT = new Comparator<>()
    {
        @Override
        public int compare(IRoute o1, IRoute o2)
        {
            int compare = 0;

            if(o1.getWeight() > o2.getWeight())
            {
                compare = (int) (o1.getWeight() - o2.getWeight());
            }
            else if(o2.getWeight() > o1.getWeight())
            {
                compare = (int) (o2.getWeight() - o1.getWeight());
            }
            else
            {
                compare = (int) (o1.getWeight() - o2.getWeight());
            }

            return compare;
        }
    };
}