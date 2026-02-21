public class CreateCommandValidator extends CommandValidator {
    // check commands for typos, checks invalid commands (common behavior)
    //child validators for each type will check if id already exists and checks range
    //child validators would be the create command and the deposit command
    private Bank bank;

    public CreateCommandValidator(Bank bank){
        this.bank = bank;
    }

    public boolean validate(String command) {
        command = command.toLowerCase();
        String[] splitCommand = command.split(" ");
        int i = 0;
        String account = "";
        for (String part: splitCommand) {
            if ( (i == 0) && (!part.equals("create")) ) return false;
            if ( (i == 1) ) {
                if ( (!part.equals("checking")) && (!part.equals("savings")) && (!part.equals("cd")) ) {
                    return false;
                } else account = part;
            }
            if (i == 2) {
                for (char n: part.toCharArray()) {
                    if (!Character.isDigit(n)) return false;
                }

                if(part.length() != 8) return false;

                if (bank.accountExistsById(part)) return false;
            }
            if(i == 3) {
                if (negativeDecimal(part)) return false;

                if(Double.parseDouble(part) > 10.0) return false;
            }
            if(i == 4) {
                if (negativeDecimal(part)) return false;

                double amount = Double.parseDouble(part);
                if(amount > 10000 || amount < 1000) return false;
            }
            i++;
        }
        if (account.equals("cd")) {
            return i == 5;
        } else {
            return i == 4; //returns true if and only if there's 4 parameters
        }
    }

    private static boolean negativeDecimal(String part) {
        for (char n: part.toCharArray()) {
            if ( (n != '.') && (!Character.isDigit(n)) ) {
                return true;
            }
        }
        return false;
    }
}
