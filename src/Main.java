import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Get info needed from user input to start sim:
        Scanner simController = new Scanner(System.in);
//        System.out.println("How many roads?");
//        main.setRoadSpawns(simController.nextInt());
//        System.out.println("How many cars?");
//        main.setCarSpawns(simController.nextInt());
//        System.out.println("How many traffic lights?");
//        main.setLightSpawns(simController.nextInt());

        // Create starting variables
        int roadSpawns = 0;
        int carSpawns = 0;
        int lightSpawns = 0;

        System.out.println("How many roads?");
        roadSpawns = checkValidInt(simController, roadSpawns);
        roadSpawns = checkPositiveInt(simController, roadSpawns);

        System.out.println("How many cars?");
        carSpawns = checkValidInt(simController, carSpawns);
        carSpawns = checkPositiveInt(simController, carSpawns);

        System.out.println("How many traffic lights?");
        lightSpawns = checkValidInt(simController, lightSpawns);
        lightSpawns = checkPositiveInt(simController, lightSpawns);

        while (lightSpawns > roadSpawns) {
            System.out.println("Traffic lights quantity must not be more than road quantity");
            System.out.println("Retype the traffic light:");
            lightSpawns = simController.nextInt();
        }

        //Create objects:
        System.out.println("Object Creation:\n---------------------");
        System.out.println("Roads:");
        ArrayList<Road> roads = new ArrayList<>();
        for (int i = 0; i < roadSpawns; i++) {
            System.out.println("Please input parameters for road_" + i + "...");
            System.out.print("Length:");
            int lengthInput = 0;
            lengthInput = checkValidInt(simController, lengthInput);
            lengthInput = checkPositiveInt(simController, lengthInput);
//            System.out.print("Speed limit:");
//            int speedLimitInput = simController.nextInt();
            int speedLimitInput = 1; // force speed limit to be 1 for prototype.
            roads.add(new Road(Integer.toString(i), speedLimitInput, lengthInput, new int[]{0, 0}));
        }
        System.out.println("\nRoads;");
        for (Road road : roads
        ) {
            road.printRoadInfo();
        }

        System.out.println("\nCars;");
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i < carSpawns; i++) {
            cars.add(new Car(Integer.toString(i), roads.get(0))); // all created cars will begin on road_0.
            cars.get(i).printCarStatus();
        }

        System.out.println("\nTraffic Lights;");
        ArrayList<TrafficLight> lights = new ArrayList<>();
        for (int i = 0; i < lightSpawns; i++) {
            lights.add(new TrafficLight(Integer.toString(i), roads.get(i))); // each light will be at the end of each road.
            lights.get(i).printLightStatus();
        }
        System.out.println();

        // set locations and connections:
        /* System.out.println("Settings:");
        roads.get(1).setStartLocation(new int[]{roads.get(0).getLength() + 1, 0}); // place road_1 to a position at the end of road_0.
        roads.get(1).printRoadInfo();
        roads.get(0).getConnectedRoads().add(roads.get(1)); // connect road_0 to road_1
        System.out.println(); */

        for (int i = 0; i <= roadSpawns; i++) {
            try{
                roads.get(i + 1).setStartLocation(new int[]{roads.get(i).getLength() + 1, 0}); // place next road at the end of the previous road
            }
            catch (IndexOutOfBoundsException e){
                break;
            }
            roads.get(i + 1).printRoadInfo();
            roads.get(i).getConnectedRoads().add(roads.get(i + 1)); // connect previous road to the next road
            System.out.println();
        }


        //Simulation loop:
        System.out.println("Simulation:");
        Random random = new Random();
        int time = 0;
        int speedOfSim = 0;
        System.out.print("Set time scale in milliseconds:");
        speedOfSim = checkValidInt(simController, speedOfSim);
        speedOfSim = checkPositiveInt(simController, speedOfSim);
        int carsFinished = 0;
        while (carsFinished < cars.size()) {
            for (TrafficLight light : lights) {
                light.operate(random.nextInt());
                light.printLightStatus();
            }
            for (Car car : cars) {
                car.move();
                car.printCarStatus();
                if (car.getCurrentRoad().getConnectedRoads().isEmpty() && (car.getSpeed() == 0)) {
                    carsFinished = carsFinished + 1;
                }
            }
            time = time + 1;
            System.out.println(time + " Seconds have passed.\n");
            try {
                Thread.sleep(speedOfSim); // set speed of simulation.
            } catch (InterruptedException sim) {
                Thread.currentThread().interrupt();
            }
        }


    }

    public static int checkPositiveInt(Scanner simController, int input) {
        do {
            if (input < 0) {
                System.out.println("Input cannot be lower then 0");
                input = 0;  // reset value back to 0 for checkValidInt()
                input = checkValidInt(simController, input);
            }
        } while (input < 0);
        return input;
    }

    public static int checkValidInt(Scanner simController, int input) {
        do {
            // Check if the input is int or not
            if (simController.hasNextInt()) {  //  hasNextInt() check if the Scanner's next input is an int
                input = simController.nextInt();
            }
            else {
                System.out.println("Please enter a integer");
            }
            simController.nextLine();  // Bring scanner to next line to prevent input error/infinite loop
        } while (input == 0);
        return input;
    }
}
