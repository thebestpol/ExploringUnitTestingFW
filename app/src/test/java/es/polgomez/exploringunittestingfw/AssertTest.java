package es.polgomez.exploringunittestingfw;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Exploring assertions in {@link org.junit.Assert}
 */
public class AssertTest {

    @Test
    public void testAssertEquals() {
        assertEquals("failure - strings are not equal", "text", "text");
    }

    @Test
    public void testAssertFalse() {
        assertFalse("failure - should be false", false);
    }

    @Test
    public void testAssertNotNull() {
        assertNotNull("should not be null", new Object());
    }

    @Test
    public void testAssertNull() {
        assertNull("should be null", null);
    }

    @Test
    public void testAssertNotSame() {
        assertNotSame("should not be same Object", new Object(), new Object());
    }

    @Test
    public void testAssertSame() {
        Integer aNumber = Integer.valueOf(420);
        assertSame("should be same", aNumber, aNumber);
    }

    @Test
    public void testAssertArrayEquals() {
        byte[] expected = "trial".getBytes();
        byte[] actual = "trial".getBytes();
        assertArrayEquals("failure - byte arrays not same", expected, actual);
    }

    @Test
    public void testAssertStringArrayEquals() {
        String[] expected = new String[]{"trial"};
        String[] actual = new String[]{"trial"};

        assertArrayEquals("failure - String arrays not same", expected, actual);
    }

    @Test
    public void testAssertObjectArrayEquals() {
        Fake trial = new Fake("trial");
        Fake[] expected = new Fake[]{trial};
        Fake[] actual = new Fake[]{trial};

        assertArrayEquals("failure - String arrays not same", expected, actual);
    }

    static class Fake {
        String id;

        public Fake(String id) {
            this.id = id;
        }
    }
}
