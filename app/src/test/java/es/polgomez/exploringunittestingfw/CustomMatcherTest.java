package es.polgomez.exploringunittestingfw;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import static es.polgomez.exploringunittestingfw.CustomMatcherTest.CustomStringMatcher.isString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * Sample of a simple matcher
 */
public class CustomMatcherTest {

    @Test
    public void testCustomMatcher() {
        assertThat("String", isString());
        assertThat(null, not(isString()));
        assertThat("Strong", not(isString()));
    }

    static class CustomStringMatcher extends TypeSafeMatcher<String> {

        @Override
        protected boolean matchesSafely(String item) {
            return item.equals("String");
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("String");
        }

        @Factory
        public static Matcher<String> isString() {
            return new CustomStringMatcher();
        }
    }
}

