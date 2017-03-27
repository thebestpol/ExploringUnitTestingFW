package es.polgomez.exploringunittestingfw;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

/**
 * Created by PoL on 27/03/17.
 */

@RunWith(Parameterized.class)
public class ParameterizedFooTest {

    @Parameter
    public String input;
    @Parameter(1)
    public boolean expected;

    @Parameters(name = "Test {index}: isFoo({0})={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {null, false}, {"", false}, {" ", false}, {"foo", true}, {"bar", false}
        });
    }

    @Test
    public void parameterizedTest() {
        assertThat(SuiteTest.FooBar.isFoo(input), is(expected));
    }
}

