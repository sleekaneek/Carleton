public class DepreciatingPolicy extends Policy {

    private float rate;

    public DepreciatingPolicy(float amt, float ra) {
        super(amt);
        rate = ra;
    }

    public float getRate() { return rate; }

    public String toString() {
        String output = "";

        output += String.format("DepreciatingPolicy: %04d amount: $%1.2f",
                getPolicyNumber(), getAmount());
        output += (" rate: " + (rate*100) + "%");
    return output;
    }

    public boolean isExpired() {
        return amount <= 0.01;
    }

    public void depreciate() {
        if (amount > 0) {
            amount -= (rate*100);
        }
    }
}
