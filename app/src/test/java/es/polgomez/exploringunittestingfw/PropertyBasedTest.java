package es.polgomez.exploringunittestingfw;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.When;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.generator.java.util.ListGenerator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeTrue;

/**
 * Created by PoL on 27/03/17.
 */
@RunWith(JUnitQuickcheck.class)
public class PropertyBasedTest {

    @Property
    public void isRandomPersonAdult(String name, int age) {
        System.out.println("Age " + age);
        assumeTrue("Age is greater than 0", age > 0);

        Person person = new Person(name, age);
        assertThat(person.isAdult(), is(equalTo(age >= 18)));
    }

    @Property
    public void isRandomPersonAdultInRange(String name, @InRange(minInt = 0, maxInt = 160) int age) {
        Person person = new Person(name, age);
        assertThat(person.isAdult(), is(equalTo(age >= 18)));
    }

    @Property(trials = 200)
    public void isRandomPersonAdultWhen(String name, @When(satisfies = "#_ > 0") int age) {
        System.out.println(age);
        Person person = new Person(name, age);
        assertThat(person.isAdult(), is(equalTo(age >= 18)));
    }

    @Property
    public void usingAdultGenerator(@From(AdultGenerator.class) Person person) {
        assertThat(person.isAdult(), is(true));
    }

    @Ignore
    @Property
    public void usingAdultsGenerator(@From(AdultsGenerator.class) List<Person> adults) {
        BaseMatcher<Person> itemMatcher = new BaseMatcher<Person>() {
            @Override
            public boolean matches(Object item) {
                return ((Person) item).isAdult();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Person.isAdult");
            }
        };

        assertThat(adults, everyItem(itemMatcher));
    }

    public static class Person {

        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public boolean isAdult() {
            return age >= 18;
        }

    }

    public static class AdultGenerator extends Generator<Person> {

        private static final Person[] ADULTS = new Person[]{
                new Person("foo", 18),
                new Person("foo", 33),
                new Person("foo", 19),
                new Person("foo", 23),
                new Person("foo", 76),
                new Person("foo", 31),
                new Person("foo", 97),
                new Person("foo", 48),
                new Person("foo", 127),
                new Person("foo", 55)
        };

        public AdultGenerator() {
            super(Person.class);
        }

        @Override
        public Person generate(SourceOfRandomness random, GenerationStatus status) {
            return ADULTS[random.nextInt(10)];
        }
    }

    public static class AdultsGenerator extends ListGenerator<List<Person>> {

        public AdultsGenerator(Class<List<Person>> type) {
            super(type);
        }

        @Override
        public List<Person> generate(SourceOfRandomness random, GenerationStatus status) {
            int size = random.nextInt(1, 50);
            List<Person> adults = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                int age = random.nextInt(0, 160);
                adults.add(new Person("foo", age));
            }

            return adults;
        }
    }
}
