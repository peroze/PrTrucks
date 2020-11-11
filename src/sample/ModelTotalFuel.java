package sample;

public class ModelTotalFuel {
    String Period;
    String Amount;

    public ModelTotalFuel(String period, String amount) {
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
