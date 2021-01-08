package Pr.Cars;

public class ModelFast {

    private String LiscPlate,Manufactor,Kilometers,Location,ServiceDate,ServiceKm,KTEO,EmmisionCard,Speed,Fire;

    public ModelFast(String liscPlate, String manufactor, String kilometers, String location, String serviceDate, String serviceKm, String KTEO, String emmisionCard, String speed, String fire) {
        LiscPlate = liscPlate;
        Manufactor = manufactor;
        Kilometers = kilometers;
        Location = location;
        ServiceDate = serviceDate;
        ServiceKm = serviceKm;
        this.KTEO = KTEO;
        EmmisionCard = emmisionCard;
        Speed = speed;
        Fire = fire;
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

    public String getKilometers() {
        return Kilometers;
    }

    public void setKilometers(String kilometers) {
        Kilometers = kilometers;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getServiceDate() {
        return ServiceDate;
    }

    public void setServiceDate(String serviceDate) {
        ServiceDate = serviceDate;
    }

    public String getServiceKm() {
        return ServiceKm;
    }

    public void setServiceKm(String serviceKm) {
        ServiceKm = serviceKm;
    }

    public String getKTEO() {
        return KTEO;
    }

    public void setKTEO(String KTEO) {
        this.KTEO = KTEO;
    }

    public String getEmmisionCard() {
        return EmmisionCard;
    }

    public void setEmmisionCard(String emmisionCard) {
        EmmisionCard = emmisionCard;
    }

    public String getSpeed() {
        return Speed;
    }

    public void setSpeed(String speed) {
        Speed = speed;
    }

    public String getFire() {
        return Fire;
    }

    public void setFire(String fire) {
        Fire = fire;
    }
}
