package collections.implementation;

public class PriorityQueueNode<T> implements Comparable<PriorityQueueNode>
{
    /**
     * próxima ordem
     */
    private static int nextorder = 0;

    /**
     * prioridade
     */
    private int priority;

    /**
     * ordem
     */
    private int order;

    /**
     * elemento
     */
    private T element;


    /**
     * constructor
     * @param priority prioridade do novo node da queue
     * @param element o elemento do novo node da queue de prioridade
     */
    public PriorityQueueNode(T element, int priority)
    {
        this.element = element;
        this.priority = priority;
        this.order = nextorder;
        this.nextorder++;
    }


    /**
     * Retorna o elemento do node
     *
     * @return o elemento do node
     */
    public T getElement()
    {
        return this.element;
    }

    /**
     * Retorna o valor de prioridade do node
     *
     * @return o valor de prioridade do node
     */
    public int getPriority()
    {
        return this.priority;
    }

    /**
     * Retorna a ordem do node
     *
     * @return a ordem do node
     */
    public int getOrder()
    {
        return this.order;
    }

    /**
     * Retorna uma string do node
     *
     */
    public String toString()
    {
        String temp = (element.toString() + priority + order);

        return temp;
    }

    /**
     * Retorna 1 se o node atual tiver maior prioridade que o node fornecido, -1 caso contrário
     * @param o o node a ser comparado com este node
     * @return 1 se o node atual tiver maior prioridade que o node fornecido
     */
    @Override
    public int compareTo(PriorityQueueNode o)
    {
        int result;
        PriorityQueueNode<T> temp = o;

        if (priority > temp.getPriority())
        {
            result = 1;
        }
        else if (priority < temp.getPriority())
        {
            result = -1;
        }
        else if (order > temp.getOrder())
        {
            result = 1;
        }
        else
        {
            result = -1;
        }

        return result;
    }
}
