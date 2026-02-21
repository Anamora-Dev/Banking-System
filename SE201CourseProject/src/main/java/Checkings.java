public class Checkings extends Account {

    public Checkings(String id, double apr) {
        super(id, apr);
    }

    public boolean amountAllowed(double amount) {
        return amount < 1000;
    }

}
