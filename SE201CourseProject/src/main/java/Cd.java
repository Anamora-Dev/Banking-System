public class Cd extends Account{

    public Cd(String id, double apr, double balance) {
        super(id, apr);
        this.balance = balance;
    }

    public boolean amountAllowed(double amount) {
        return false;
    }
}
