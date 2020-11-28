package Pr.Cars;

public class ModelExternalCars {

    String id,LiscPlate,Manufactor,Model,Company,Width,Lenght,Height,Driver,Phone;

    public ModelExternalCars(String id, String liscPlate, String manufactor, String model, String company, String width, String lenght, String height, String driver, String phone) {
        this.id = id;
        LiscPlate = liscPlate;
        Manufactor = manufactor;
        Model = model;
        Company = company;
        Width = width;
        Lenght = lenght;
        Height = height;
        Driver = driver;
        Phone = phone;
    }

    public ModelExternalCars(String liscPlate, String manufactor, String model, String company, String width, String lenght, String height, String driver, String phone) {
        LiscPlate = liscPlate;
        Manufactor = manufactor;
        Model = model;
        Company = company;
        Width = width;
        Lenght = lenght;
        Height = height;
        Driver = driver;
        Phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLiscPlate() {
        return LiscPlate;
    }

    public void setLiscPlate(String lisc_Plate) {
        LiscPlate = lisc_Plate;
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

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getWidth() {
        return Width;
    }

    public void setWidth(String width) {
        Width = width;
    }

    public String getLenght() {
        return Lenght;
    }

    public void setLenght(String lenght) {
        Lenght = lenght;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getDriver() {
        return Driver;
    }

    public void setDriver(String driver) {
        Driver = driver;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
