import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;

    Bank(){
        accounts = new HashMap<>();
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void addCheckingAccount(String id, double apr) {
        accounts.put(id, new Checkings(id, apr));
    }

    public void addSavingAccount(String id, double apr) {
        accounts.put(id, new Savings(id, apr));
    }

    public void addCdAccount(String id, double apr, double balance) {
        accounts.put(id, new Cd(id, apr, balance));
    }

    public void depositIntoAccount(String id, double money) {
        accounts.get(id).deposit(money);
    }

    public void withdrawFromAccount(String id, double money) {
        accounts.get(id).withdraw(money);
    }

    public boolean accountExistsById(String id) {
        return accounts.get(id) != null; //if account doesn't exist, returns false
    }

    public boolean accountSpecifiedAmount(String id, double amount) {
        Account acc = accounts.get(id);
        return acc.amountAllowed(amount);
    }
}

