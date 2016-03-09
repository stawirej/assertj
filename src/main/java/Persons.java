import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Persons implements Iterable<Person> {

    private final List<Person> persons;

    public Persons() {
        persons = new ArrayList<Person>();
    }

    public void addPerson(final Person person) {
        persons.add(person);
    }

    public Iterator<Person> iterator() {
        return persons.iterator();
    }
}
