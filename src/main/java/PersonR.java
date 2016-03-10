public class PersonR {

    private static String darkSideName = "Darth";
    private final HiddenClass hiddenObject;
    private String name;

    public PersonR(final String name) {
        setName(name);
        hiddenObject = new HiddenClass();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + " darkSideName= " + darkSideName + " hidden= " + hiddenObject.getHidden() + "]";
    }

    private int foo() {
        darkSideName = "";
        return 4;
    }
}
