package Pr.Cars;


/**
 * This class represents a KTEO
 * We need to keep the liscence plate code of the car , the total cost of the procedure, the kilometers that the vehicle traveled up until now\n the date, the warnings that the company gaves us , the expairation date and the company which carried the procedures
 * @author peroze
 * @version 1.0 Alpha
 */
public class ModelKTEO {
    private String LiscPlate;
    private String Price;
    private String Kilometers;
    private String Date;
    private String Warnings;
    private String Next;
    private String Company;

    /**
     * The constractor of the class
     * @param liscPlate The Lisc Plate of the car
     * @param price The total cost of the KTEO
     * @param kilometers The kilometers traveled so far
     * @param date The date that KTEO took place
     * @param warnings The warnings that were noted
     * @param next The Date of the next KTEO
     * @param company The company in which KTEO took place
     */
    public ModelKTEO(String liscPlate, String price, String kilometers, String date, String warnings, String next, String company) {
        LiscPlate = liscPlate;
        Price = price;
        Kilometers = kilometers;
        Date = date;
        Warnings = warnings;
        Next = next;
        Company = company;
    }


    /**
     * This is a getter for the lisc PLate
     * @return The Lisc Plate
     */
    public String getLiscPlate() {
        return LiscPlate;
    }


    /**
     * This is the setter for the Lisc Plate
     * @param liscPlate The new Lisc PLate
     */
    public void setLiscPlate(String liscPlate) {
        LiscPlate = liscPlate;
    }


    /**
     * This is a getter for the Price
     * @return The Price
     */
    public String getPrice() {
        return Price;
    }


    /**
     * This is the setter for the Price
     * @param price The new Price
     */
    public void setPrice(String price) {
        Price = price;
    }


    /**
     * This is a getter for the Kilometers
     * @return The Kilometers
     */
    public String getKilometers() {
        return Kilometers;
    }

    /**
     * This is the setter for the traveled Kilometers
     * @param kilometers The new Traveled Kilometers
     */
    public void setKilometers(String kilometers) {
        Kilometers = kilometers;
    }


    /**
     * This is a getter for the Date
     * @return The Date
     */
    public String getDate() {
        return Date;
    }

    /**
     * This is the setter for the Date
     * @param date The new date
     */
    public void setDate(String date) {
        Date = date;
    }


    /**
     * This is a getter for the Warnings
     * @return The Warnings
     */
    public String getWarnings() {
        return Warnings;
    }


    /**
     * This is the setter for the Warnings
     * @param warnings The new Warnings
     */
    public void setWarnings(String warnings) {
        Warnings = warnings;
    }


    /**
     * This is a getter for the Date of the next
     * @return The Date of the next
     */
    public String getNext() {
        return Next;
    }


    /**
     * This is the setter for the date of the next
     * @param next The new Date of the next
     */
    public void setNext(String next) {
        Next = next;
    }


    /**
     * This is a getter for the Company
     * @return The Company
     */
    public String getCompany() {
        return Company;
    }


    /**
     * This is the setter for the company
     * @param company The new Company
     */
    public void setCompany(String company) {
        Company = company;
    }
}
