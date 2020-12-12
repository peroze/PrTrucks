package Pr.Cars;

public class ModelInternalPhoneList {

    String Id,Name,Phone,Email,Posistion;

    public ModelInternalPhoneList(String id, String name, String phone, String email, String posistion) {
        Id = id;
        Name = name;
        Phone = phone;
        Email = email;
        Posistion = posistion;
    }

    public ModelInternalPhoneList(String name, String phone, String email, String posistion) {
        Name = name;
        Phone = phone;
        Email = email;
        Posistion = posistion;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPosistion() {
        return Posistion;
    }

    public void setPosistion(String posistion) {
        Posistion = posistion;
    }
}
