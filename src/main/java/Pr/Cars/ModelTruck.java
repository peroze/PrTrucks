package Pr.Cars;


/**
 * This class represents a Car
 * @author peroze
 * @version 1.0 Alpha
 */
public class ModelTruck {
    private String id,LiscPlate,Manufactor,Model,Date,Type,Location,Kilometers,Plaisio,Data,ServiceInkm,GasIn,KteoIn,FireExtinguiser,SpeedWriter;

    /**
     * The constructor of th car
     * @param id  The id pf the car
     * @param liscPlate The Liscence plate number of the car
     * @param manufactor The Manufactor of the car
     * @param model The Model of the car
     * @param date  The 1st Registration date of the car
     * @param plaisio The Plaisio of the car
     * @param type The type of the car
     * @param location The Location of the car
     * @param kilometers The kilometers of the car
     * @param data The Special Characteristics of the car
     */
    public ModelTruck(String id, String liscPlate, String manufactor, String model, String date, String plaisio, String type, String location,String kilometers,String data,String serviceInKm,String gasIn,String kteoIn,String fireExtinguiser,String speedWriter ) {
        this.id = id;
        LiscPlate = liscPlate;
        Manufactor = manufactor;
        Model = model;
        Date = date;
        Plaisio = plaisio;
        Type = type;
        Location = location;
        Kilometers=kilometers;
        Data=data;
        ServiceInkm=serviceInKm;
        GasIn=gasIn;
        KteoIn=kteoIn;
        FireExtinguiser=fireExtinguiser;
        SpeedWriter=speedWriter;
    }


    /**
     * Getter for Id of the car
     * @return The Id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for id of the car
     * @param id The new id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for Liscance plate of the car
     * @return The Liscense Plate number
     */
    public String getLiscPlate() {
        return LiscPlate;
    }

    /**
     * Setter for Liscensce plate of the car
     * @param liscPlate The new Liscensce Plate Number
     */
    public void setLiscPlate(String liscPlate) {
        LiscPlate = liscPlate;
    }

    /**
     * Getter for Manufactor of the car
     * @return The Manufactor
     */
    public String getManufactor() {
        return Manufactor;
    }

    /**
     * Setter for Manufactor of the car
     * @param manufactor The new Manufactor
     */
    public void setManufactor(String manufactor) {
        Manufactor = manufactor;
    }

    /**
     * Getter for the model of the car
     * @return The Model
     */
    public String getModel() {
        return Model;
    }

    /**
     * Setter for the Model of the car
     * @param model The new model of the car
     */
    public void setModel(String model) {
        Model = model;
    }

    /**
     * Getter for Date of the first Registration of the car
     * @return The 1st Registration Date
     */
    public String getDate() {
        return Date;
    }

    /**
     * Setter for Date of the first Registration of the car
     * @param date The new First Registration Date
     */
    public void setDate(String date) {
        Date = date;
    }

    /**
     * Getter for the Plaisio of the car
     * @return The plaisio of the car
     */
    public String getPlaisio() {
        return Plaisio;
    }

    /**
     * Setter for the Plaisio of the car
     * @param plaisio The new Plaisio fo the car
     */
    public void setPlaisio(String plaisio) {
        Plaisio = plaisio;
    }

    /**
     * Getter for the Type of the car
     * @return The new Type
     */
    public String getType() {
        return Type;
    }

    /**
     * Setter for the Type of the car
     * @param type The new Type
     */
    public void setType(String type) {
        Type = type;
    }


    /**
     * Getter for the Location of the car
     * @return The Location
     */
    public String getLocation() {
        return Location;
    }


    /**
     * Setter for the Location of the car
     * @param location The new Location
     */
    public void setLocation(String location) {
        Location = location;
    }


    /**
     * Getter for the Kilometers of the car
     * @return The Kilometers of the car
     */
    public String getKilometers() {
        return Kilometers;
    }


    /**
     * Setter for the Kilometers of the car
     * @param kilometers The new Kilometers
     */
    public void setKilometers(String kilometers) {
        Kilometers = kilometers;
    }

    /**
     * Getter for the Characteristics of the Car
     * @return Data The Characteristics of the car
     */
    public String getData() {
        return Data;
    }

    /**
     * Setter for the characteristics of the car
     * @param data The new Characteristics of the car
     */
    public void setData(String data) {
        Data = data;
    }


    public String getServiceInkm() {
        return ServiceInkm;
    }

    public void setServiceInkm(String serviceInkm) {
        ServiceInkm = serviceInkm;
    }

    public String getGasIn() {
        return GasIn;
    }

    public void setGasIn(String gasIn) {
        GasIn = gasIn;
    }

    public String getKteoIn() {
        return KteoIn;
    }

    public void setKteoIn(String kteoIn) {
        KteoIn = kteoIn;
    }

    public String getFireExtinguiser() {
        return FireExtinguiser;
    }

    public void setFireExtinguiser(String fireExtinguiser) {
        FireExtinguiser = fireExtinguiser;
    }

    public String getSpeedWriter() {
        return SpeedWriter;
    }

    public void setSpeedWriter(String speedWriter) {
        SpeedWriter = speedWriter;
    }
}


