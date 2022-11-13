import island.Island;
import organisms.Animal;
import organisms.Herbivores.Rabbit;
import organisms.Plant;
import organisms.Predators.Bear;
import organisms.Utils;

import java.util.concurrent.CopyOnWriteArrayList;

public class Runner {
    public static void main(String[] args) {
        Bear bear = new Bear();
        Rabbit rabbit = new Rabbit();
        Rabbit rabbit1 = new Rabbit();




        Plant plant = new Plant();
        CopyOnWriteArrayList<Plant> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add(plant);

        CopyOnWriteArrayList<Animal> animalList = new CopyOnWriteArrayList<>();
        animalList.add(rabbit);
        animalList.add(rabbit1);
        animalList.add(bear);


        Island island = new Island();
        island.populateTheIsland();

        island.animalsOnIsland.put("2|2", animalList);
        System.out.println(island.animalsOnIsland);

        CopyOnWriteArrayList<Animal> T = island.animalsOnIsland.get("0|0");

        for (int i = 0; i < animalList.size(); i++) {
            for (int j = 0; j < animalList.size(); j++) {
                Animal animal = animalList.get(i);
                animal.multiply(animalList.get(j));
                System.out.println(animalList.get(i));
            }
        }

        for (int i = 0; i < animalList.size(); i++) {
            for (int j = 0; j < animalList.size(); j++) {
                Animal animal = animalList.get(i);
                animal.eat(animalList.get(j));
                System.out.println(animalList.get(i));
            }
        }
        AnimalLifeCycle animalLifeCycle = new AnimalLifeCycle(island);


        animalLifeCycle.animalsMoveFromCell(5, 5);
        while (true) {
            for (Integer i = 0; i < Utils.islandWidth; i++) {
                for (Integer j = 0; j < Utils.islandHeight; j++) {
                    animalLifeCycle.animalsMoveFromCell(i, j);
                    animalLifeCycle.animalsEatInIslandCell(i, j);
                    animalLifeCycle.animalsMultiplyInIslandCell(i, j);


                }
            }
        }


    }
}