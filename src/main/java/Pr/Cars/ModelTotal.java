package Pr.Cars;

public class ModelTotal {
    String Period;
    String Amount;

    public ModelTotal(String period, String amount) {
        Period = period;
        Amount = amount;
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
