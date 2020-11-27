package Pr.Cars;

/**
 * This Class represents a repair
 */
public class ModelRepair {
    private String LiscPlate;
    private String Price;
    private String Kilometers;
    private String Date;
    private String Discreption;
    private String Workshop;
    private String Changes;
    private String id;

    /**
     * This is the constructor without id
     * @param liscPlate The lisc Plate of the car
     * @param price The price of the repair
     * @param kilometers The kilometers that the car has traveled until the repair
     * @param date The date of the repair
     * @param discreption The basic discription
     * @param workshop The Workshop that the repair took place
     * @param changes The changes that have been made
     */
    public ModelRepair(String liscPlate, String price, String kilometers, String date, String discreption, String workshop, String changes) {
        LiscPlate = liscPlate;
        Price = price;
        Kilometers = kilometers;
        Date = date;
        Discreption = discreption;
        Workshop = workshop;
        Changes = changes;
    }

    /**
     * This is the constructor without id
     * @param liscPlate The lisc Plate of the car
     * @param price The price of the repair
     * @param kilometers The kilometers that the car has traveled until the repair
     * @param date The date of the repair
     * @param discreption The basic discription
     * @param workshop The Workshop that the repair took place
     * @param changes The changes that have been made
     * @param Id The unique ID of the repair
     */
    public ModelRepair(String liscPlate, String price, String kilometers, String date, String discreption, String workshop, String changes, String Id) {
        LiscPlate = liscPlate;
        Price = price;
        Kilometers = kilometers;
        Date = date;
        Discreption = discreption;
        Workshop = workshop;
        Changes = changes;
        id = Id;
    }

    /**
     * This is the getter for the unique id
     * @return The unique id
     */
    public String getId() {
        return id;
    }

    public void setId(String Id) {
        id = Id;
    }

    /**
     * This is the getter for the Lisc Plate
     * @return The Lisc PLate
     */
    public String getLiscPlate() {
        return LiscPlate;
    }


    /**
     * This is a setter for the Lisc Plate
     * @param liscPlate The new Lisc Plate
     */
    public void setLiscPlate(String liscPlate) {
        LiscPlate = liscPlate;
    }

    /**
     * This is the getter for the Price
     * @return The Price
     */
    public String getPrice() {
        return Price;
    }


    /**
     * This is a setter for the Price
     * @param price The new Price
     */
    public void setPrice(String price) {
        Price = price;
    }

    /**
     * This is the getter for the traveled Kilometers
     * @return The traveled Kilometers
     */
    public String getKilometers() {
        return Kilometers;
    }

    /**
     * This is a setter for the traveled Kilometers
     * @param kilometers The new traveled Kilometers
     */
    public void setKilometers(String kilometers) {
        Kilometers = kilometers;
    }


    /**
     * This is the getter for the date of the repair
     * @return The date of the repair
     */
    public String getDate() {
        return Date;
    }


    /**
     * This is a setter for the Date of the repair
     * @param date The new date
     */
    public void setDate(String date) {
        Date = date;
    }


    /**
     * This is the getter for the basic discription of the repair
     * @return The unique id
     */
    public String getDiscreption() {
        return Discreption;
    }


    /**
     * This is a setter for the basic Discription
     * @param discreption The new Discription
     */
    public void setDiscreption(String discreption) {
        Discreption = discreption;
    }


    /**
     * This is the getter for the Workshop
     * @return The Workshop
     */
    public String getWorkshop() {
        return Workshop;
    }


    /**
     * This is a setter for the Workshop
     * @param workshop The new Workshop
     */
    public void setWorkshop(String workshop) {
        Workshop = workshop;
    }


    /**
     * This is the getter for the Changes that have been made
     * @return The Changes
     */
    public String getChanges() {
        return Changes;
    }


    /**
     * This is a setter for the changes
     * @param changes The new Changes
     */
    public void setChanges(String changes) {
        Changes = changes;
    }
}
