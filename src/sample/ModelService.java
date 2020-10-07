package sample;

public class ModelService {

    private String LiscPlate,Date,Kilometers,Type,Changes,Workshop,NextDate,NextKilometers,Price;


    public ModelService(String liscPlate, String date, String kilometers, String type, String changes, String workshop, String nextDate, String nextKilometers, String price) {
        LiscPlate = liscPlate;
        Date = date;
        Kilometers = kilometers;
        Type = type;
        Changes = changes;
        Workshop = Workshop;
        NextDate = nextDate;
        NextKilometers = nextKilometers;
        Price = price;
    }

    public String getLiscPlate() {
        return LiscPlate;
    }

    public void setLiscPlate(String liscPlate) {
        LiscPlate = liscPlate;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getKilometers() {
        return Kilometers;
    }

    public void setKilometers(String kilometers) {
        Kilometers = kilometers;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getChanges() {
        return Changes;
    }

    public void setChanges(String changes) {
        Changes = changes;
    }

    public String getWorkshop() {
        return Workshop;
    }

    public void setWorkshop(String workshop) {
        Workshop = workshop;
    }

    public String getNextDate() {
        return NextDate;
    }

    public void setNextDate(String nextDate) {
        NextDate = nextDate;
    }

    public String getNextKilometers() {
        return NextKilometers;
    }

    public void setNextKilometers(String nextKilometers) {
        NextKilometers = nextKilometers;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
