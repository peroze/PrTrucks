package Pr.Cars;

public class ModelCompany {

    String id,Name,Phone;

    public ModelCompany(String id, String name, String phone) {
        this.id = id;
        Name = name;
        Phone = phone;
    }

    public ModelCompany(String name, String phone) {
        Name = name;
        Phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
