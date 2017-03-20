package es.polgomez.exploringunittestingfw;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by PoL on 17/03/17.
 */

public class ExceptedExceptionsTest {

    @Test(expected = NoSuchMethodException.class)
    public void exceptException() throws Exception {
        Foo.foo();
    }

    @Test
    public void testExceptionMessage() {
        try {
            Foo.foo();
        } catch (Exception exception) {
            assertThat(exception.getMessage(), is("Method not implemented"));
        }
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testExceptionMessageUsingRule() throws Exception {
        thrown.expect(NoSuchMethodException.class);
        thrown.expectMessage(is("Method not implemented"));

        Foo.foo();
    }


    static class Foo {

        public static void foo() throws Exception {
            throw new NoSuchMethodException("Method not implemented");
        }
    }
}

