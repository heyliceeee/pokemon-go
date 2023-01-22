package demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class demoTest
{
    private Demo demo;

    public demoTest()
    {
        demo = new Demo();
    }

    @DisplayName("Greeting test")
    @Test
    public void testGreeting_ReturnHelloWorld_Valid()
    {
        String expected = "Hello World!";

        assertEquals(expected, demo.greeting());
    }

    @DisplayName("Greeting test from empty")
    @Test
    public void testGreeting_ReturnIllegalArgumentException_Valid()
    {
        assertThrows(java.lang.IllegalArgumentException.class, () -> demo.greeting());
    }
}
