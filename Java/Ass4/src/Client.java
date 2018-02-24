import java.util.*;
public abstract class Client {
    private static final int MAX_POLICIES_PER_CLIENT = 10;
    private static int NEXT_CLIENT_ID = 1;
    private String name;
    private int id;
    protected Policy[] policies;
    protected int numPolicies;

    public Client(String n) {
        name = n;
        id = NEXT_CLIENT_ID++;
        policies = new Policy[MAX_POLICIES_PER_CLIENT];
        numPolicies = 0;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Policy[] getPolicies() {
        return policies;
    }

    public int getNumPolicies() {
        return numPolicies;
    }

    public String toString() {
        return String.format("Client: %06d amount: %s", this.id, this.name);
    }

    public float totalCoverage() {
        float total = 0;
        for (int i = 0; i < MAX_POLICIES_PER_CLIENT; i++) {
            total += policies[i].getAmount();
        }
        return total;
    }

    public Policy addPolicy(Policy p) {
        if (MAX_POLICIES_PER_CLIENT < 10) {
            policies[numPolicies] = p;
            numPolicies++;
            return p;
        }
        return null;
    }

    public Policy openPolicyFor(float amt) {
        Policy newPolicy = new Policy(amt);
        addPolicy(newPolicy);
        return newPolicy;
    }

    public Policy openPolicyFor(float amt, float rate) {
        DepreciatingPolicy newDepPolicy = new DepreciatingPolicy(amt, rate);
        addPolicy(newDepPolicy);
        return newDepPolicy;
    }

    public Policy openPolicyFor(float amt, Date expire) {
        ExpiringPolicy newExpPolicy = new ExpiringPolicy(amt, expire);
        addPolicy(newExpPolicy);
        return newExpPolicy;
    }

    public Policy getPolicy(int polNum) {
        for (Policy policy : policies) {
            if (policy.getPolicyNumber() == polNum) {
                return policy;
            }
        }
        return null;
    }

    public boolean cancelPOlicy(int polNum) {
        for (int i = 0; i < policies.length; i++) {
            if (policies[i].getPolicyNumber() == polNum) {
                policies[i] = policies[i+1];
                return true;
            }
        }
        return false;
    }

    public abstract float makeClaim(int polNum);


}
