
public class Vehicle {

    private int regNumber;

    public Vehicle(int regNumber) {
        this.regNumber = regNumber;
    }

    public int getRegNumber() {
        return this.regNumber;
    }

    public void setRegNumber(int regNumber) {
        this.regNumber = regNumber;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "regNumber=" + regNumber +
                '}';
    }
}
