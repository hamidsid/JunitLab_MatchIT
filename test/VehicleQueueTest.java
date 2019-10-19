import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleQueueTest {

    VehicleQueue myQueue = new VehicleQueue();

    @BeforeEach
    public void setUp() {
        Vehicle v1 = new Vehicle(485);
        Vehicle v2 = new Vehicle(123);
        Vehicle v3 = new Vehicle(456);
        Vehicle v4 = new Vehicle(789);
        Vehicle v5 = new Vehicle(400);
        Vehicle m1 = new Vehicle(10);
        Vehicle m2 = new Vehicle(11);

        myQueue.addVehicle(v1);
        myQueue.addVehicle(v2);
        myQueue.addVehicle(v3);
        myQueue.addVehicle(v4);
        myQueue.addVehicle(v5);
        myQueue.addVehicle(m1);
        myQueue.addVehicle(m2);

    }


    @Test
    public void newQueueShouldBeEmpty() {
        VehicleQueue emptyQueue = new VehicleQueue();
        assertEquals(0, emptyQueue.size());
    }


    //story 2
    @Test
    public void checkIfVehicleIsNotInTheQueue() {
        //check that vehicle with regNumber 999 is not in the queue
        Vehicle vTest = new Vehicle(999);
        assertFalse(myQueue.vehicles.contains(vTest));
    }

    //story 2
    @Test
    public void checkIfVehicleIsRemoved() {
        if (myQueue.removeVehicle(123)) {
            assertEquals(6, myQueue.size());
        }
    }

    //story 3
    @Test
    public void checkIfQueuePrintsInOrder() {
        String firstVehiclePrint = "Vehicle{regNumber=485}";
        Vehicle firstVehicle = myQueue.vehicles.get(0);
        assertEquals(firstVehiclePrint, firstVehicle.toString());
    }

    //story 4
    @Test
    public void removeMotorCyclesIfWindIsHigh() {
        //check if all motorcycles are removed when wind speed is 40
        myQueue.removeAllMotorCycles(40);

        //all remaining vehicles should be cars (regNumber > 100)
        for (Vehicle vehicle : myQueue.vehicles) {
            assertTrue(vehicle.getRegNumber() > 100);
        }
    }

    //story 5
    @Test
    public void checkIfFirstVehicleIsChargedFromQueue() {

        //Hence first vehicle is a car on our case scenario, then total amount should be 300 after
        //charging first vehicle
        myQueue.chargeVehicle();
        assertEquals(300, myQueue.getTotalAmount());
    }

    //story 6
    @Test
    public void checkTotalAmount() {

        //check to see if total amount is correct once all vehicles are charged in the queue
        while (!myQueue.vehicles.isEmpty()) {
            myQueue.chargeVehicle();
        }

        //every car is charged for 300kr and motorcycle for 100kr
        int myTotalAmount = 5 * 300 + 2 * 100;
        assertEquals(myTotalAmount, myQueue.getTotalAmount());
    }

    //story 7
    @Test
    public void checkIFPriceDifferenceIsCorrect() {

        //first element in the queue is a car and last element is a motorcycle
        //first element should be charged 300kr, last should be charged 100kr

        //get first element and last element
        Vehicle firstVehicle = myQueue.vehicles.get(0);
        Vehicle lastVehicle = myQueue.vehicles.get(myQueue.vehicles.size() - 1);

        //check if they are charged correctly
        assertEquals(300, myQueue.priceAccordingToVehicle(firstVehicle));
        assertEquals(100, myQueue.priceAccordingToVehicle(lastVehicle));
    }

    //story 8
    @Test
    public void checkRoundPrice() {

        //charge first vehicle as round trip
        myQueue.chargeRoundTrip();

        //because first vehicle in the queue is a car
        assertEquals(585, myQueue.getTotalAmount());

        Vehicle lastVehicle = myQueue.vehicles.get(myQueue.vehicles.size() - 1);
        VehicleQueue newQueue = new VehicleQueue();
        newQueue.addVehicle(lastVehicle);
        newQueue.chargeRoundTrip();

        //if the vehicle is a motorcycle and round trip charge is applied
        assertEquals(195, newQueue.getTotalAmount());


    }

    //story 9

    @Test
    public void checkTotalAmountForBothRoundTripAndNormal() {

        //suppose from the given queue, 2 cars and 1 motorcycle buys a round trip ticket
        //others buy one way. Test the total amount

        //two cars are charged as round trip
        myQueue.chargeRoundTrip();
        myQueue.chargeRoundTrip();

        //charge all remaining as one way
        while (!myQueue.vehicles.isEmpty()) {
            myQueue.chargeVehicle();

        }
        int expectedTotalAmount = 2 * 585 + 3 * 300 + 200;
        assertEquals(expectedTotalAmount, myQueue.getTotalAmount());

    }


}