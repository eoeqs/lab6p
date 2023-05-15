package managers;

import workerRelated.*;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * ColMan is a class that manages the collection of Worker objects. It stores the workerMap, which is a HashMap where the
 * keys are long values and the values are Worker objects, and the creationDate, which is a String value.
 */
public class CollectionManager {
    private HashMap<Long, Worker> workerMap;
    private final String creationDate;

    /**
     * The constructor of the ColMan class. It initializes the workerMap and creationDate fields.
     *
     * @param workerMap    a HashMap where the keys are long values and the values are Worker objects.
     * @param creationDate a String value that represents the date of creation of the collection.
     */
    public CollectionManager(HashMap<Long, Worker> workerMap, String creationDate) {
        this.workerMap = workerMap;
        this.creationDate = creationDate;
    }

    /**
     * Getter method for the workerMap field.
     *
     * @return the HashMap where the keys are long values and the values are Worker objects.
     */
    public HashMap<Long, Worker> getWorkerMap() {
        return workerMap;
    }

    /**
     * Setter method for the workerMap field.
     *
     * @param workerMap a HashMap where the keys are long values and the values are Worker objects.
     */
    public void setWorkerMap(HashMap<Long, Worker> workerMap) {
        this.workerMap = workerMap;
    }

    /**
     * Getter method for the creationDate field.
     *
     * @return a String value that represents the date of creation of the collection.
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * Replaces the Worker object with the given key in the workerMap with a new Worker object created through
     * UIMan.createNewWorker method.
     *
     * @param worker the new Worker to replace the old one.
     */

    public void replace(Worker worker) {
        long key = worker.getId();
        Worker oldWorker = workerMap.get(key);
        workerMap.replace(key, worker);
        System.out.println("\nOld element under the key = " + key + ":");
        System.out.println(oldWorker);
        System.out.println("\nwas replaced by a new one:");
        System.out.println(worker);
    }

    public static HashMap<Long, Worker> tempWorkerMap() {
        HashMap<Long, Worker> map = new HashMap<>();
        Worker one = new Worker(1, "One", new Coordinates(1, 2D), LocalDate.now(), 9000,
                LocalDate.now(), Position.DEVELOPER, Status.HIRED,
                new Organization("Company", 150000, 200L,
                        new Address("Street", "1111")));
        Worker two = new Worker(2, "Two", new Coordinates(1, 2D), LocalDate.now(), 10000,
                LocalDate.now(), Position.MANAGER_OF_CLEANING, Status.RECOMMENDED_FOR_PROMOTION,
                new Organization("Company", 100000, 200L,
                        new Address("Street", "1111")));
        Worker three = new Worker(3, "Three", new Coordinates(1, 2D), LocalDate.now(), 8000,
                LocalDate.now(), Position.HEAD_OF_DEPARTMENT, Status.REGULAR,
                new Organization("Company", 130000, 200L,
                        new Address("Street", "1111")));
        Worker four = new Worker(4, "Four", new Coordinates(1, 2D), LocalDate.now(), 7000,
                LocalDate.now(), Position.DEVELOPER, Status.REGULAR,
                new Organization("Company", 100001, 200L,
                        new Address("Street", "1111")));
        Worker five = new Worker(5, "Five", new Coordinates(1, 2D), LocalDate.now(), 14000,
                LocalDate.now(), Position.MANAGER_OF_CLEANING, Status.FIRED,
                new Organization("Company", 160000, 200L,
                        new Address("Street", "1111")));
        map.put(one.getId(), one);
        map.put(two.getId(), two);
        map.put(three.getId(), three);
        map.put(four.getId(), four);
        map.put(five.getId(), five);
        return map;
    }

}
