package sample;

public class ModelTruck {
    String id,LiscPlate,Manufactor,Model,Date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLiscPlate() {
        return LiscPlate;
    }

    public void setLiscPlate(String liscPlate) {
        LiscPlate = liscPlate;
    }

    public String getManufactor() {
        return Manufactor;
    }

    public void setManufactor(String manufactor) {
        Manufactor = manufactor;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String Plaisio;

    public String getPlaisio() {
        return Plaisio;
    }

    public void setPlaisio(String plaisio) {
        Plaisio = plaisio;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String Type;

    public String Location;

    public ModelTruck(String id, String liscPlate, String manufactor, String model, String date, String plaisio, String type, String location) {
        this.id = id;
        LiscPlate = liscPlate;
        Manufactor = manufactor;
        Model = model;
        Date = date;
        Plaisio = plaisio;
        Type = type;
        Location = location;
    }
}
