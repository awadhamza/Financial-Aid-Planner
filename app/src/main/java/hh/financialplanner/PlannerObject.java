package hh.financialplanner;

public class PlannerObject {
    private String name;
    private String initialCapital;

    public PlannerObject(String name, String initialCapital) {
        this.name = name;
        this.initialCapital = initialCapital;
    }

    @Override
    public String toString() {
        return "PlannerObject{" +
                "name='" + name + '\'' +
                ", initialCapital=" + initialCapital +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitialCapital() {
        return initialCapital;
    }

    public void setInitialCapital(String initialCapital) {
        this.initialCapital = initialCapital;
    }
}
