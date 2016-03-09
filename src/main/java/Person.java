public class Person {

    private final String name;
    private final Integer age;
    private final Persons friends;

    public Person(final String name, final Integer age) {
        this.name = name;
        this.age = age;
        friends = new Persons();
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void addFriend(final Person person) {
        friends.addPerson(person);
    }

    public Persons getFriends() {
        return friends;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (age == null ? 0 : age.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person)obj;
        if (age == null) {
            if (other.age != null) {
                return false;
            }
        } else if (!age.equals(other.age)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }
}
