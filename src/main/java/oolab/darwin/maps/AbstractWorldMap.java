package oolab.darwin.maps;

import oolab.darwin.Config;
import oolab.darwin.Utils;
import oolab.darwin.Vector2d;
import oolab.darwin.elements.Animal;
import oolab.darwin.elements.Plant;
import oolab.darwin.interfaces.*;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap {

    //// OBSERVABLES ////

    protected final ArrayList<Animal> animals = new ArrayList<>();

    protected final Map<Vector2d, TreeSet<Animal>> animalMap = new HashMap<>();
    protected final Map<Vector2d, Plant> plantMap = new HashMap<>();

    protected final Map<Vector2d, IMapElement> objects = new HashMap<>();


    //// GLOBALS ////

    protected Config config;
    protected IMapBoundary mapBoundary;

    public AbstractWorldMap(
        Config config,
        IMapBoundary mapBoundary
    ) {
        this.config = config;
        this.mapBoundary = mapBoundary;
    }

    @Override
    public void spawnPlants(boolean isInitial) {
        ///// PREPARE AVAILABLE SPACE ////

        Set <Vector2d> nonGreenArea = getNonGreenArea();
        Set <Vector2d> greenArea = getGreenArea();

        Set <Vector2d> availableInside = new HashSet<>(greenArea);
        Set <Vector2d> availableOutside = new HashSet<>(nonGreenArea);

        //// REMOVE ALREADY USED POSITIONS ////

        /// TODO: move this part to placePlant/unplacePlant method

        for (Map.Entry<Vector2d, Plant> entry : plantMap.entrySet() ) {
            Vector2d position = entry.getKey();
            if ( greenArea.contains(position) )
                availableInside.remove(position);
            else
                availableOutside.remove(position);
        }

        int plantQuantity = isInitial
                ? config.initialPlantQuantity
                : config.plantsPerDay;

        for (int i = 0; i < plantQuantity ; i++ )
            spawnPlant(availableInside, availableOutside);

    }

    private void spawnPlant(
            Set<Vector2d> availableInside,
            Set<Vector2d> availableOutside
    ) {

        boolean includeInside = availableInside.size() > 0;
        boolean includeOutside = availableOutside.size() > 0;

        Vector2d position = null;

        if ( !includeInside && !includeOutside )
            return;

        if ( includeInside ^ includeOutside ) {
            if (includeInside) {
                position = Utils.getRandomElement( availableInside );
                availableInside.remove(position);
            } else {
                position = Utils.getRandomElement( availableOutside );
                availableOutside.remove(position);
            }
        }

        if ( includeInside && includeOutside ) {
            if (Utils.drawResult( 80 )) {
                position = Utils.getRandomElement( availableInside );
                availableInside.remove(position);
            } else {
                position = Utils.getRandomElement( availableOutside );
                availableOutside.remove(position);
            }
        }

        Plant plant = new Plant(config, position);

        placePlant(plant);
    }

    @Override
    public void consume(Animal animal, Plant plant) {
        animal.eat(plant);

        unplacePlant(plant);
    }

    @Override
    public void multiplyAt(Vector2d position, int birthdate) {
        Set <Animal> animalSet = animalMap.get(position);

        if (animalSet == null)
            return;

        ArrayList <Animal> animals = new ArrayList<>(animalSet);
        ArrayList <Animal> children = new ArrayList<>();

        for ( int i = 1; i < animals.size(); i += 2 ) {
            Animal father = animals.get(i - 1);
            Animal mother = animals.get(i);

            if (father.isFertile() && mother.isFertile()) {
                children.add(mother.multiply(father, birthdate));
            }

        }

        for ( Animal child : children ){
            place(child, null);
        }
    }

    protected void placePlant(Plant plant ) {
        plantMap.put(plant.position, plant);
    };

    protected void unplacePlant(Plant plant) {
        plantMap.remove(plant.position);
    };

    protected void placeAnimal( Animal animal, Vector2d position ) {
        if (!animalMap.containsKey(position))
            animalMap.put(position, new TreeSet<Animal>(
                (a1, a2) -> {
                    if (a1.energy != a2.energy)
                        return a1.energy - a2.energy;
                    if ( a1.birthDate != a2.birthDate )
                        return a1.birthDate - a2.birthDate;
                    if (a1.getChildren() != a2.getChildren())
                        return a1.getChildren() - a2.getChildren();

                    return a1 == a2 ? 0 : Utils.getRandomInt(0,1) * 2 - 1;
                }
            ));

        animalMap.get(position).add(animal);

    }

    protected void unplaceAnimal( Animal animal, Vector2d position ) {
        if (!animalMap.containsKey(position))
            return;

        TreeSet<Animal> animalSet = animalMap.get(position);
        animalSet.remove(animal);

        if (animalSet.size() == 0 ){
            animalMap.remove(position);
        }
    }


    @Override
    public void place(IMapElement mapElement, Vector2d prevPosition) {

        if ( mapElement instanceof Animal ) {
            Animal animal = (Animal) mapElement;

            if ( prevPosition == null )
                animals.add(animal);
            else {
                unplaceAnimal(animal, prevPosition);
            }

            mapBoundary.checkPosition(animal);
            placeAnimal(animal, animal.position);

        }


        if ( mapElement instanceof Plant ) {
            Plant plant = (Plant) mapElement;
        }
    }

    @Override
    public void kill(Animal animal, int deathDate ) {

        animal.kill(deathDate);

        unplaceAnimal(animal, animal.position);
    }

    @Override
    public ArrayList<IMapElement> objectsAt(Vector2d position) {
        ArrayList<IMapElement> mapElements = new ArrayList<>();

        if ( animalMap.containsKey(position) ) {
            TreeSet<Animal> animalSet = animalMap.get(position);

            mapElements.addAll(animalSet);
        }

        if (plantMap.containsKey(position))
            mapElements.add(plantMap.get(position));

        return mapElements;
    }

    @Override
    public void move() {
        for ( Animal animal : animals )
            animal.move();
    }

    //// GETTERS ////

    @Override
    public IMapBoundary getMapBoundary() {
        return mapBoundary;
    }

    @Override
    public ArrayList<IObservable> getObservables() {
        return null;
    }

    @Override
    public Map<Vector2d, IMapElement> getObjects() {
        return objects;
    }

    @Override
    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    @Override
    public ArrayList<Plant> getPlants() {
        return new ArrayList<>( plantMap.values() );
    }

    @Override
    public Map<Vector2d, TreeSet<Animal>> getAnimalMap() {
        return animalMap;
    }
    @Override
    public Map<Vector2d, Plant> getPlantMap() {
        return plantMap;
    }
}
