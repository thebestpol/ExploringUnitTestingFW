package es.polgomez.exploringunittestingfw;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by PoL on 27/03/17.
 * <p>
 * Suite test class to wrap parameterized test samples with two different wats.
 *
 * Also this sample show how to do a property-based testing with a "boundary". It's a junit approach
 * with concretes input and output values.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ParameterizedBarTest.class, ParameterizedFooTest.class})
public class SuiteTest {


    public static class FooBar {

        public static boolean isFoo(String value) {
            return value != null && value.contains("foo");
        }

        public static boolean isBar(String value) {
            return value != null && value.contains("bar");
        }
    }

}
