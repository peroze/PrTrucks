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

    public ModelTruck(String id, String liscPlate, String manufactor, String model, String date) {
        this.id = id;
        LiscPlate = liscPlate;
        Manufactor = manufactor;
        Model = model;
        Date=date;
    }
}
