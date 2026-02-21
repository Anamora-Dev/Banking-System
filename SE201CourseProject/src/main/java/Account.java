public abstract class Account {
    public static final int DEFAULT_BALANCE = 0;
    private double apr;
    double balance;
    String id;

    public Account(String id, double apr) {
        this.id = id;
        this.apr = apr;
        this.balance = 0;
    }

    public String getID() {
        return id;
    }

    public double getAPR() {
        return apr;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double MONEY_TO_DEPOSIT) {
        balance += MONEY_TO_DEPOSIT;
    }

    public void withdraw(double MONEY_TO_WITHDRAW) {
        balance -= MONEY_TO_WITHDRAW;

        if (balance < DEFAULT_BALANCE) {
            balance = DEFAULT_BALANCE;
        }
    }

    public abstract boolean amountAllowed(double amount);
}
