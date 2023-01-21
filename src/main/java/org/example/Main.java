package org.example;

public class Main {
    static String value = "Hello World!";

    public static void main(String[] args)
    {
        System.out.println(value);
    }

    public String greeting() {
        if(value.equals(""))
        {
            throw new IllegalArgumentException("vazio");
        }

        return value;
    }
}