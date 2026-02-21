public class Savings extends Account {

    public Savings(String id, double apr) {
        super(id, apr);
    }

    public boolean amountAllowed(double amount){
        return amount < 2500;
    }
}
