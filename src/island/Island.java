package island;

import organisms.Animal;
import organisms.AnimalFactory;
import organisms.Utils;

import javax.security.auth.login.Configuration;
import java.lang.reflect.Parameter;
import java.security.Policy;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class Island {
    public HashMap<String, CopyOnWriteArrayList<Animal>> animalsOnIsland = new HashMap<>();
    public CopyOnWriteArrayList<Animal> getAnimalsOnIslandInCell(String cell) {
        return animalsOnIsland.get(cell);
    }

    public Island() {
        for (int i = 0; i < Utils.islandWidth; i++) {
            for (int j = 0; j < Utils.islandHeight; j++) {
                animalsOnIsland.put(i + "|" + j, new CopyOnWriteArrayList<>());

              //  plantsOnIsland.put(i + "|" + j, new CopyOnWriteArrayList<>());
            }
        }

    }
    public void populateTheIsland() {
        AnimalFactory animalFactory = new AnimalFactory();
        int animalId = 0;
        for (int i = 0; i < Utils.islandWidth; i++) {
            for (int j = 0; j < Utils.islandHeight; j++) {
                CopyOnWriteArrayList<Animal> animals = animalsOnIsland.get(i + "|" + j);
                animalId = ThreadLocalRandom.current().nextInt(0,Utils.probability.length-1);
                animals.add(animalFactory.createAnimal(animalId));
            }
        }
    }




}
