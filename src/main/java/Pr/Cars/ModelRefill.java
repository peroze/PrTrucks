package Pr.Cars;

/**
 * This class represents the data of a Refill
 */
public class ModelRefill {
    private String LiscPlate;
    private String Kilometers;
    private String Date;
    private String Amount;
    private String Id;
    private String Driver;
    private String Location;
    private String Consumption;
    private String Cost;

    /**
     * This is the constarctor of the class
     *
     * @param liscPlate   The lisc plate of the car
     * @param kilometers  The kilometers
     * @param date        The date in which the card has been published
     * @param amount      The amount of petrol
     * @param id          The unique id of each refill
     */
    public ModelRefill(String liscPlate, String kilometers, String date, String amount, String id,String driver,String location,String consumption,String cost) {
        LiscPlate = liscPlate;
        Kilometers = kilometers;
        Date = date;
        Amount = amount;
        Id = id;
        Driver=driver;
        Location=location;
        Consumption = consumption;
        Cost = cost;
    }

    public ModelRefill(String liscPlate, String kilometers, String date, String amount, String id, String driver, String location) {
        LiscPlate = liscPlate;
        Kilometers = kilometers;
        Date = date;
        Amount = amount;
        Id = id;
        Driver = driver;
        Location = location;
    }

    public ModelRefill(String liscPlate, String kilometers, String date, String amount, String driver, String location, String consumption, String cost) {
        LiscPlate = liscPlate;
        Kilometers = kilometers;
        Date = date;
        Amount = amount;
        Driver = driver;
        Location = location;
        Consumption = consumption;
        Cost = cost;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getConsumption() {
        return Consumption;
    }

    public void setConsumption(String consumption) {
        Consumption = consumption;
    }

    /**
     * This is the constarctor of the class with out id
     *
     * @param liscPlate   The lisc plate of the car
     * @param kilometers  The kilometers
     * @param date        The date in which the card has been published
     * @param amount      The amount of petrol
     */
    public ModelRefill(String liscPlate, String kilometers, String date, String amount,String driver,String location) {
        LiscPlate = liscPlate;
        Kilometers = kilometers;
        Date = date;
        Amount = amount;
        Driver=driver;
        Location=location;
    }

    /**
     * The getter for the Lisc Plate
     *
     * @return The Lisc Plate
     */
    public String getLiscPlate() {
        return LiscPlate;
    }

    /**
     * The setter for the Lisc Plate
     *
     * @param liscPlate The Lisc Plate
     */
    public void setLiscPlate(String liscPlate) {
        LiscPlate = liscPlate;
    }

    /**
     * The getter for the Kilometers
     *
     * @return The Kilometers
     */
    public String getKilometers() {
        return Kilometers;
    }

    /**
     * The Setter for the Kilometers
     *
     * @param kilometers
     */
    public void setKilometers(String kilometers) {
        Kilometers = kilometers;
    }


    /**
     * The getter for the Date
     *
     * @return The Date
     */
    public String getDate() {
        return Date;
    }

    /**
     * The getter for the Date
     *
     * @param date The Date
     */
    public void setDate(String date) {
        Date = date;
    }


    /**
     * The getter of Consumption
     *
     * @return The new amount variable
     */
    public String getAmount() {
        return Amount;
    }

    /**
     * The setter of Consumption
     *
     * @param amount The new Amount
     */
    public void setAmount(String amount) {
        Amount = amount;
    }

    /**
     * The getter for the Id
     *
     * @return
     */
    public String getId() {
        return Id;
    }

    /**
     * The setter for the id
     *
     * @param id
     */
    public void setId(String id) {
        Id = id;
    }

    public String getDriver() {
        return Driver;
    }

    public void setDriver(String driver) {
        Driver = driver;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
