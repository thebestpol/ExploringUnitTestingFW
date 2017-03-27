package es.polgomez.exploringunittestingfw;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.runners.Parameterized.Parameters;

/**
 * Created by PoL on 27/03/17.
 */

@RunWith(Parameterized.class)
public class ParameterizedBarTest {

    private String input;
    private boolean expected;

    @Parameters(name = "Test {index}: isBar({0})={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {null, false}, {"", false}, {" ", false}, {"foo", false}, {"bar", true}
        });
    }

    public ParameterizedBarTest(String input, boolean expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void parameterizedTest() {
        assertThat(SuiteTest.FooBar.isBar(input), is(expected));
    }
}

