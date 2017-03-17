package es.polgomez.exploringunittestingfw;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.describedAs;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.CoreMatchers.theInstance;
import static org.junit.Assert.assertThat;

/**
 * Exploring assertThat assertion using Harmcrest matchers
 */
public class AssertThatTest {

    @Test
    public void testAssertThat() {
        String string = "String";
        assertThat(string, is("String")); // This assertions does the same
        assertThat(string, equalTo("String"));
        assertThat(string, is(equalTo("String")));
    }

    @Test
    public void testAssertThatChain() {
        String string = "String";
        assertThat(string, allOf(containsString("ing"), startsWith("S"), endsWith("g")));
        assertThat(string, anyOf(containsString("tr"), startsWith("A"), endsWith("g")));
        assertThat(string, not(allOf(containsString("tr"), startsWith("A"), endsWith("g"))));
        assertThat(string, both(startsWith("S")).and(endsWith("g")));
        assertThat(string, both(startsWith("S")).and(endsWith("s")).or(endsWith("g")));
        assertThat(string, either(startsWith("X")).or(startsWith("S")).or(endsWith("g")));
    }


    @Test
    public void testVerboseHamcrestMatcher() {
        String string = "S";
        // This assertion if fails return a better description as:
        // java.lang.AssertionError: Expected: a String that start with "S" but: was "Foo"
        assertThat(string, describedAs("a String that start with %0", startsWith("S"), "S"));
    }

    @Test
    public void testSimpleHamcrestMatcher() {
        // Creates a matcher that always matches, regardless of the examined object.
        assertThat(null, anything());

        assertThat(null, nullValue());
        assertThat(null, is(nullValue()));

        Object actual = new Object();
        Object expected = actual;
        assertThat(actual, isA(Object.class));
        assertThat(actual, sameInstance(expected));
        assertThat(actual, theInstance(expected));
        assertThat(actual, not(sameInstance(new Object())));
    }

    @Test
    public void testArrayHamcrestMatcher() {
        List<String> strings = Arrays.asList("String", "Strong", "Street");

        assertThat(strings, everyItem(isA(String.class)));
        assertThat(strings, everyItem(startsWith("S")));
        assertThat(strings, hasItem("Strong"));
        assertThat(strings, hasItem(is("Street")));
        assertThat(strings, hasItems("Street", "String"));
        assertThat(strings, hasItems(endsWith("g"), endsWith("t")));
    }
}
