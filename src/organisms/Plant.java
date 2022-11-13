package organisms;

public class Plant {
    private final int id = 15;
    private final double weight = 1;

    public String getSimpleClassName() {
        return this.getClass().getSimpleName();
    }
    public int getID(){
        return id;
    }
    public double getWeight() {
        return weight;
    }
}
