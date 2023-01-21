package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class mainTest
{
    private Main main;

    public mainTest()
    {
        main = new Main();
    }

    @DisplayName("Greeting test")
    @Test
    public void testGreeting_ReturnHelloWorld_Valid()
    {
        String expected = "Hello World!";

        assertEquals(expected, main.greeting());
    }

    @DisplayName("Greeting test from empty")
    @Test
    public void testGreeting_ReturnIllegalArgumentException_Valid()
    {
        assertThrows(java.lang.IllegalArgumentException.class, () -> main.greeting());
    }
}
