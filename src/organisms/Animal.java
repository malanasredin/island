package organisms;

import javax.security.auth.login.Configuration;
import java.security.Policy;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {
    private final int id;
    private final double weight;
    private final int maxCountInCell;
    private final int speedMoveToCell;
    private final double foodForSaturation;
    private double currentSaturation;

    private boolean animalIsDead;
    private boolean animalAlreadyMoved;

    public boolean isAnimalAlreadyMoved() {
        return animalAlreadyMoved;
    }
    public void setAnimalAlreadyMoved(boolean animalAlreadyMoved) {
        this.animalAlreadyMoved = animalAlreadyMoved;
    }
    public  boolean isAnimalisDead(){
        return isAnimalisDead();
    }
    public void setAnimalisDead(boolean animalIsDead) {
        this.animalIsDead = animalIsDead;
    }
    public boolean isSaturation() {
        return this.foodForSaturation - this.currentSaturation < 1.0;
    }
    public void  toSaturate(Animal otherAnimal) {

        this.currentSaturation += otherAnimal.getWeight();
        otherAnimal.setAnimalisDead(true);
        System.out.println( " Текущее животное: " + this.id + " съел жертву: " + otherAnimal.id);
    }

    public double getCurrentSaturation() {
        return currentSaturation;
    }

    public void toSaturate(Plant plant) {
        this.currentSaturation += plant.getWeight();
    }
    public String getSimpleClassName() {
        return  this.getClass().getSimpleName();
    }

    public MoveDirection chooseSideToMove() {
        int direction = ThreadLocalRandom.current().nextInt(1,5);
        MoveDirection directionEnum = null;
        switch (direction) {
            case 1:
                directionEnum = MoveDirection.UP;
                break;
            case 2:
                directionEnum = MoveDirection.DOWN;
                break;
            case 3:
                directionEnum = MoveDirection.LEFT;
                break;
            case 4:
                directionEnum = MoveDirection.RIGHT;
                break;
            case 5:
                directionEnum = MoveDirection.RIGHT;
                break;
        }
        return  directionEnum;
    }
    public int stepCount() {
        int speedMoveToCell = this.speedMoveToCell;
        int maxRandomCellPerMoveSpeedNoMoreForAllAnimal = ThreadLocalRandom.current().nextInt(0, 5);
        if (maxRandomCellPerMoveSpeedNoMoreForAllAnimal <= speedMoveToCell)
            return maxRandomCellPerMoveSpeedNoMoreForAllAnimal;
        return 0;
    }

    public Animal(int id, double weight, int maxCountInCell, int speedMoveToCell, double foodForSaturation) {
        this.id = id;
        this.weight = weight;
        this.maxCountInCell = maxCountInCell;
        this.speedMoveToCell = speedMoveToCell;
        this.foodForSaturation = foodForSaturation;
        this.currentSaturation = 0;
    }

    public Animal(int id, double weight, int maxCountInCell, int speedMoveToCell, double foodForSaturation,double currentSaturation) {
        this.id = id;
        this.weight = weight;
        this.maxCountInCell = maxCountInCell;
        this.speedMoveToCell = speedMoveToCell;
        this.foodForSaturation = foodForSaturation;
        this.currentSaturation = 0;
    }

    public String getSimpleNameClassName() {
        return this.getClass().getSimpleName();
    }

    //метод кушать животных животными
    public void eat(Animal anotherAnimal){
       // System.out.println( " Текущее животное: " + this.id + " жертва: " + anotherAnimal.id);
        if(anotherAnimal == null) return;
        if(this.equals(anotherAnimal)) return;
        if(this.canBeEaten(anotherAnimal) && !this.isSaturation()) {
        this.toSaturate(anotherAnimal);
        }
    }
    // метод кушать растения животными
    public void eat(CopyOnWriteArrayList<Plant> plants) {
        if(plants != null && this instanceof Herbivore && plants.size()> 0) {
            this.toSaturate(plants.get(0));
            plants.remove(0);
        }
    }

        // метод быть съеденным
    private boolean canBeEaten(Animal anotherAnimal){
        int probability = Utils.probability[this.getId()][anotherAnimal.getId()];
        int factProbability = ThreadLocalRandom.current().nextInt(0,100);
        return probability > factProbability;

    }
    //метод размножаться
    public CopyOnWriteArrayList<Animal> multiply(Animal anotherAnimal) {
        if (this.getId() == anotherAnimal.getId() && !this.equals(anotherAnimal)) {
            CopyOnWriteArrayList<Animal> children = new CopyOnWriteArrayList<>();
            AnimalFactory animalFactory = new AnimalFactory();
            for (int i = 0; i < Utils.childQuanity.get(id); i++) {
                children.add(animalFactory.createAnimal(this.getId()));
                System.out.println("рождено: " + getId());
            }
            return children;

        }
        return null;
    }

    public int getId(){
        return id;
    };



    public double getWeight() {
        return weight;
    }
    //иконки
    @Override
    public String toString() {
        return Utils.iconsOfAnimlsAndPlants.get(this.id);
    }
}
