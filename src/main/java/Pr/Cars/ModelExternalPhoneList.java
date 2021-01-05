package Pr.Cars;

public class ModelExternalPhoneList {

    public String Id,Name,Phone,Address,Discreption,Notes,Email;

    public ModelExternalPhoneList(String id, String name, String phone, String address,String email, String discreption, String notes) {
        Id = id;
        Name = name;
        Phone = phone;
        Address = address;
        Discreption = discreption;
        Notes = notes;
        Email=email;
    }

    public ModelExternalPhoneList(String name, String phone, String address,String email, String discreption, String notes) {
        Name = name;
        Phone = phone;
        Address = address;
        Discreption = discreption;
        Notes = notes;
        Email=email;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDiscreption() {
        return Discreption;
    }

    public void setDiscreption(String discreption) {
        Discreption = discreption;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
