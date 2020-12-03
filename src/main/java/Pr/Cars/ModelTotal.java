package Pr.Cars;

public class ModelTotal {
    String Period;
    String Amount;
    String Consumption;

    public ModelTotal(String period, String amount) {
        Period = period;
        Amount = amount;
    }

    public ModelTotal(String period, String amount, String consumption) {
        Period = period;
        Amount = amount;
        Consumption = consumption;
    }

    public String getConsumption() {
        return Consumption;
    }

    public void setConsumption(String consumption) {
        Consumption = consumption;
    }

    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String period) {
        Period = period;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
