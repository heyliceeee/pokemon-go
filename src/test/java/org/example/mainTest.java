package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class mainTest
{
    @Test
    public void mainTest()
    {
        Main main = new Main();
        String result = main.greeting();

        assertEquals("Hello World!", result);
    }
}
