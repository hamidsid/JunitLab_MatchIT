import java.util.ArrayList;

public class VehicleQueue {
    ArrayList<Vehicle> vehicles;
    private int totalAmount;

    public VehicleQueue() {
        vehicles = new ArrayList<>();
        totalAmount = 0;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public boolean removeVehicle(int regNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getRegNumber() == regNumber) {
                return vehicles.remove(vehicle);
            }
        }
        return false;
    }


    public int size() {
        return vehicles.size();
    }

      /*  public void removeAllMotorCycles(int windSpeed){
        if(windSpeed > 30){
            vehicles.removeIf(vehicle -> vehicle.getRegNumber()<100);
        }
    }*/

    public void removeAllMotorCycles(int windSpeed) {
        if (windSpeed > 30) {
            for (int i = 0; i < vehicles.size(); i++) {
                Vehicle vehicle = vehicles.get(i);
                if (vehicle.getRegNumber() < 100) {
                    vehicles.remove(vehicle);
                    i--;  //decrease the counter by one
                }
            }
        }
    }

    //if it is a motorcycle charge 100kr
    //if it is a car charge 300kr
    public int priceAccordingToVehicle(Vehicle vehicle) {

        //if vehicle is car
        if (vehicle.getRegNumber() > 100) {
            return 300;
        } else {
            return 100;
        }
    }

    public void chargeVehicle(){
        //start charging the first vehicle from the list, once charged remove it
        Vehicle chargedVehicle = vehicles.remove(0);
        //add the charged price to the amount
        totalAmount += priceAccordingToVehicle(chargedVehicle);
    }

    public void chargeRoundTrip(){

        Vehicle chargedVehicle = vehicles.remove(0);

        int oneWayCharge = priceAccordingToVehicle(chargedVehicle);

        //5% discount
        int discountedPrice = 5 * oneWayCharge / 100;

        totalAmount += 2 * oneWayCharge - discountedPrice;;
    }


    public int getTotalAmount(){
        return this.totalAmount;
    }




}
