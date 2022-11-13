import island.Island;
import organisms.Animal;
import organisms.MoveDirection;
import organisms.Plant;
import organisms.Utils;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class AnimalLifeCycle {




    public Island island;



    public AnimalLifeCycle(Island island) {
        this.island = island;
    }

    private HashMap<String, CopyOnWriteArrayList<Plant>> plantsOnIsland = new HashMap<>();

    // животные перемещаются на другие клетки острова
    public void animalsMoveFromCell(Integer xCoordinate, Integer yCoordinate) {
        System.out.println("начинает движение");
        CopyOnWriteArrayList<Animal> animalsInOldCell = island.getAnimalsOnIslandInCell(xCoordinate+"|"+yCoordinate);

        for (Animal animal:animalsInOldCell) {
            MoveDirection direction = animal.chooseSideToMove();
            int stepCount = animal.stepCount();
            String newCell = newCellCoordinate(direction, stepCount, xCoordinate, yCoordinate);


            if (!newCell.equals(xCoordinate+"|"+yCoordinate)) {
                System.out.println("животное пошло " + animal.getId());
                CopyOnWriteArrayList<Animal> animalsInNewCell = island.getAnimalsOnIslandInCell(newCell);
                animalsInNewCell.add(animal);
                animalsInOldCell.remove(animal);
            }
        }

    }

    private String newCellCoordinate(MoveDirection direction, int stepCount, int xCoordinate, int yCoordinate) {
        String oldCell = xCoordinate+"|"+yCoordinate;

        switch (direction) {
            case RIGHT:
                xCoordinate = xCoordinate + stepCount >= 0 ? xCoordinate + stepCount:xCoordinate;
                break;
            case UP:
                yCoordinate = yCoordinate + stepCount >= 0?yCoordinate + stepCount:yCoordinate;
                break;
            case LEFT:
                xCoordinate = xCoordinate - stepCount >=0?xCoordinate - stepCount:xCoordinate;
                break;
            case DOWN:
                yCoordinate = yCoordinate - stepCount>=0?yCoordinate - stepCount:yCoordinate;
                break;
            default:
                yCoordinate = yCoordinate - stepCount>=0?yCoordinate - stepCount:yCoordinate;
                break;
        }

        if (xCoordinate < Utils.islandWidth && xCoordinate >=0 && yCoordinate < Utils.islandHeight && yCoordinate >= 0)
            return xCoordinate + "|" + yCoordinate;
        return oldCell;
    }


    public void run() {
        for (Integer i = 0; i < Utils.islandWidth; i++) {
            for (Integer j = 0; j < Utils.islandHeight; j++) {
                animalsEatInIslandCell(i, j);
               animalsMultiplyInIslandCell(i, j);
               animalsMoveFromCell(i, j);
            }
        }
    }
    public   void animalsEatInIslandCell(Integer x, Integer y) {
        CopyOnWriteArrayList<Animal> animalList = island.animalsOnIsland.get(x + "|" + y);
        for (int i = 0; i < animalList.size(); i++) {
            for (int j = 0; j < animalList.size(); j++) {
                Animal animal = animalList.get(i);
                animal.eat(animalList.get(j));
                System.out.println(animalList.get(i));
            }
        }
    }

    public void animalsMultiplyInIslandCell (Integer x, Integer y) {
        CopyOnWriteArrayList<Animal> animalList = island.animalsOnIsland.get(x + "|" + y);
        for (int i = 0; i < animalList.size(); i++) {
            for (int j = 0; j < animalList.size(); j++) {
                Animal animal = animalList.get(i);
                animal.multiply(animalList.get(j));

            }
        }

    }


}
