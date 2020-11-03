package sample;

/**
 * This class represents the data of an emmision card
 */
public class ModelEmmisionCard {
    private String LiscPlate;
    private String Kilometers;
    private String Date;
    private String Next;
    private String WithKTEO;

    /**
     * This is the constarctor of the class
     * @param liscPlate The lisc plate of the car
     * @param kilometers The kilometers
     * @param date The date in which the card has been published
     * @param withKteo Is the Caard included in KTEO
     * @param next The date in which the card expires
     */
    public ModelEmmisionCard(String liscPlate,  String kilometers, String date, String withKteo, String next) {
        LiscPlate = liscPlate;
        Kilometers = kilometers;
        Date = date;
        Next = next;
        WithKTEO=withKteo;
    }

    /**
     * The getter for the Lisc Plate
     * @return The Lisc Plate
     */
    public String getLiscPlate() {
        return LiscPlate;
    }

    /**
     * The setter for the Lisc Plate
     * @param liscPlate The Lisc Plate
     */
    public void setLiscPlate(String liscPlate) {
        LiscPlate = liscPlate;
    }

    /**
     * The getter for the Kilometers
     * @return The Kilometers
     */
    public String getKilometers() {
        return Kilometers;
    }

    /**
     * The Setter for the Kilometers
     * @param kilometers
     */
    public void setKilometers(String kilometers) {
        Kilometers = kilometers;
    }


    /**
     * The getter for the Date
     * @return   The Date
     */
    public String getDate() {
        return Date;
    }

    /**
     * The getter for the Date
     * @param date The Date
     */
    public void setDate(String date) {
        Date = date;
    }

    /**
     * The getter for the  next Date
     * @return The next Date
     */
    public String getNext() {
        return Next;
    }

    /**
     * The setter for the next date
     * @param next The next Date
     */
    public void setNext(String next) {
        Next = next;
    }

    /**
     * The getter of WithKteo
     * @return The new withKTEO variable
     */
    public String getWithKTEO() {
        return WithKTEO;
    }

    /**
     * The setter of WithKteo
     * @param withKteo  The new withKTEO variable
     */
    public void setWithKTEO(String withKteo) {
        WithKTEO = withKteo;
    }
}
