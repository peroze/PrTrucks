package Pr.Cars;


/**
 * This Class represents a Service
 * @author peroze
 * @version 1.0 Alpha
 */
public class ModelService {

    private String LiscPlate,Date,Kilometers,Type,Changes,Workshop,NextDate,NextKilometers,Price,id;

    /**
     * The constractor of the Service with ID
     * @param liscPlate The Lisc Plate of the car
     * @param date The Date of the Service
     * @param kilometers The Kilometers of the car
     * @param type The type of Service
     * @param changes The Cganges that have been made
     * @param workshop The WorkShop
     * @param nextDate The of the next Service
     * @param nextKilometers The total kilometers
     * @param price The total price of the Service
     */
    public ModelService(String liscPlate, String date, String kilometers, String type, String changes, String workshop, String nextDate, String nextKilometers, String price) {
        LiscPlate = liscPlate;
        Date = date;
        Kilometers = kilometers;
        Type = type;
        Changes = changes;
        Workshop = workshop;
        NextDate = nextDate;
        NextKilometers = nextKilometers;
        Price = price;
    }

    /**
     * Constarctor of the Service  with Id
     * @param liscPlate
     * @param date
     * @param kilometers
     * @param type
     * @param changes
     * @param workshop
     * @param nextDate
     * @param nextKilometers
     * @param price
     * @param id
     */
    public ModelService(String liscPlate, String date, String kilometers, String type, String changes, String workshop, String nextDate, String nextKilometers, String price, String id) {
        LiscPlate = liscPlate;
        Date = date;
        Kilometers = kilometers;
        Type = type;
        Changes = changes;
        Workshop = workshop;
        NextDate = nextDate;
        NextKilometers = nextKilometers;
        Price = price;
        this.id = id;
    }

    /**
     * Getter for the Id of
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the id
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for the Lisc Plate
     * @return The Lisc Plate
     */
    public String getLiscPlate() {
        return LiscPlate;
    }

    /**
     * Setter for the lisc Plate
     * @param liscPlate The Lisc Plate
     */
    public void setLiscPlate(String liscPlate) {
        LiscPlate = liscPlate;
    }

    /**
     * The getter for the date
     * @return The Date
     */
    public String getDate() {
        return Date;
    }

    /**
     * The setter for the date
     * @param date The date
     */
    public void setDate(String date) {
        Date = date;
    }

    /**
     * The getter for the Kilometers
     * @return The Kilometers
     */
    public String getKilometers() {
        return Kilometers;
    }

    /**
     * The setter for the Kilometers
     * @param kilometers The Kilometers
     */
    public void setKilometers(String kilometers) {
        Kilometers = kilometers;
    }

    /**
     * The getter for the Type
     * @return The type
     */
    public String getType() {
        return Type;
    }

    /**
     * The setter for the type
     * @param type The Type
     */
    public void setType(String type) {
        Type = type;
    }

    /**
     * THe getter for the changes
     * @return The setter for the change
     */
    public String getChanges() {
        return Changes;
    }

    /**
     * THe setter for the changes
     * @param changes The Changes
     */
    public void setChanges(String changes) {
        Changes = changes;
    }

    /**
     * THe getter for the workshop
     * @return The workshop
     */
    public String getWorkshop() {
        return Workshop;
    }

    /**
     * The Setter for the workshop
     * @param workshop The workshop
     */
    public void setWorkshop(String workshop) {
        Workshop = workshop;
    }

    /**
     * The getter for the Next Date of Service
     * @return The Next Date of Service
     */
    public String getNextDate() {
        return NextDate;
    }

    /**
     * The setter for the Next Date of Service
     * @param nextDate The Next Date of Service
     */
    public void setNextDate(String nextDate) {
        NextDate = nextDate;
    }

    /**
     * The getter for number of the Kilometers for the Next Service
     * @return The Kilometers
     */
    public String getNextKilometers() {
        return NextKilometers;
    }


    /**
     * The setter for number of the Kilometers for the Next Service
     * @param nextKilometers  The Kilometers
     */
    public void setNextKilometers(String nextKilometers) {
        NextKilometers = nextKilometers;
    }


    /**
     * The getter for the price
     * @return The Price
     */
    public String getPrice() {
        return Price;
    }


    /**
     * The setter for the price
     * @param price The price
     */
    public void setPrice(String price) {
        Price = price;
    }
}
