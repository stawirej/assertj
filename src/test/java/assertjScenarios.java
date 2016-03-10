import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.assertj.guava.api.Assertions.assertThat;
import static org.assertj.guava.api.Assertions.entry;
import static org.assertj.jodatime.api.Assertions.assertThat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Preconditions;
import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class assertjScenarios {

    @Test
    public void shouldCreatePersonWithFullInformations() {
        // Given
        final int age = 23;
        final String name = "John";

        // When
        final Person john = new Person(name, age);

        // Then
        final Person expectedPerson = new Person(name, age);
        assertThat(john).isEqualTo(expectedPerson);
    }

    @Test
    public void shouldCreateSpecificPerson() {
        // Given
        final int age = 23;
        final String name = "John";

        // When
        final Person john = new Person(name, age);

        // Then
        PersonAssert.assertThat(john).hasAge(23);
        PersonAssert.assertThat(john).hasName("John");
    }

    @Test
    public void shouldContainSpecificString() {
        // Given
        final List<String> names = new ArrayList<String>();

        // When
        names.add("Mike");
        names.add("Nick");
        names.add("Betty");

        // Then
        assertThat(names).contains("Mike", "Nick", "Betty");
    }

    @Test
    public void shouldPersonHaveSpecificFriends() {
        // Given
        final Person john = new Person("John", 23);
        final Person paul = new Person("Paul", 24);

        // When
        john.addFriend(paul);
        john.addFriend(john);

        // Then
        final Persons johnFriends = john.getFriends();
        assertThat(johnFriends).isNotEmpty().containsExactly(paul, john);
    }

    @Test
    public void shouldCreatePersonWithCorrectAgeAndName() {
        // Given
        final String name = "John";
        final int age = 23;

        // When
        final Person john = new Person(name, age);

        // Then
        assertThat(john.getAge()).isEqualTo(age);
        assertThat(john.getName()).isEqualToIgnoringCase("JOHN");

        // or
        PersonAssert.assertThat(john).hasAge(23);
        PersonAssert.assertThat(john).hasName("John");
    }

    @Test
    public void yodaAssertions() {
        final DateTime dateTime1 = new DateTime(2000, 1, 1, 0, 0, 1, 0);
        final DateTime dateTime2 = new DateTime(2000, 1, 1, 0, 0, 1, 456);
        assertThat(dateTime1).isEqualToIgnoringMillis(dateTime2);

        final DateTime dateTime3 = new DateTime(2000, 1, 1, 23, 59, 59, 999);
        final DateTime dateTime4 = new DateTime(2000, 1, 1, 00, 00, 00, 000);
        assertThat(dateTime3).isEqualToIgnoringHours(dateTime4);
    }

    @Test
    public void guavaMultimapAssertions() {
        final Multimap<String, String> actual = ArrayListMultimap.create();
        actual.putAll("Lakers", newArrayList("Kobe Bryant", "Magic Johnson", "Kareem Abdul Jabbar"));
        actual.putAll("Spurs", newArrayList("Tony Parker", "Tim Duncan", "Manu Ginobili"));

        assertThat(actual).containsKeys("Lakers", "Spurs");
        assertThat(actual).contains(entry("Lakers", "Kobe Bryant"), entry("Spurs", "Tim Duncan"));
    }

    @Test
    public void optional() {
        // Given
        final Optional<String> optional = Optional.of("Test");

        // Then
        assertThat(optional).isPresent().contains("Test");
    }

    @Test
    public void optionalPerson() {
        final Optional<PersonR> person = Optional.of(new PersonR("Person R"));

        if (person.isPresent()) {
            final PersonR personR = person.get();
            System.out.println(personR);
        } else {
            System.out.println("Not present");
        }
    }

    @Test
    public void optionalReturnDefaultValueWhenNullPerson() {
        final Optional<PersonR> person = Optional.empty();

        final PersonR personR = person.orElse(new PersonR("DEFAULT Person R"));

        System.out.println(personR);
    }

    @Ignore
    @Test
    public void shouldCreateFileWithSpecificContent() throws IOException {
        // Given
        final String filePath = "d:\\xFile.txt";
        final String filePath2 = "d:\\xFile2.txt";
        final PrintStream stream = new PrintStream(new FileOutputStream(filePath));

        // When
        stream.write("The Truth Is Out There".getBytes());
        stream.close();

        // Then
        final File xFile = new File(filePath);
        final File xFile2 = new File(filePath2);
        assertThat(xFile).exists().isFile().isAbsolute();
        assertThat(contentOf(xFile)).startsWith("The Truth").contains("Is Out").endsWith("There");
        assertThat(xFile).hasContentEqualTo(xFile2);
    }

    @Test
    public void shouldThrowExceptionWhenGetElementOutOfBounds() {
        try {
            // Given
            final List<String> people = new ArrayList<String>();
            people.add("Lolek");
            people.add("Bolek");

            // When
            people.get(10);
            failBecauseExceptionWasNotThrown(IndexOutOfBoundsException.class);
            // or
            // fail("IndexOutOfBoundsException expected because people has only 2 elements");
        } catch (final IndexOutOfBoundsException indexOutOfBoundsException) {
            // Then
            assertThat(indexOutOfBoundsException).hasMessage("Index: 10, Size: 2");
        }
    }

    @Test
    public void festPreconditions() {
        final String text = "text";
        Preconditions.checkNotNullOrEmpty(text);
    }
}
