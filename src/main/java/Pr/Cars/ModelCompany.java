package Pr.Cars;

public class ModelCompany {

    String id,Name,Phone,Prices;

    public ModelCompany(String id, String name, String phone,String prices) {
        this.id = id;
        Name = name;
        Phone = phone;
        Prices=prices;
    }

    public String getPrices() {
        return Prices;
    }

    public void setPrices(String prices) {
        Prices = prices;
    }

    public ModelCompany(String name, String phone, String prices) {
        Name = name;
        Phone = phone;
        Prices=prices;
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
