public class DepositCommandValidator extends CommandValidator{
    private Bank bank;

    public DepositCommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String command) {
        String[] splitCommand = command.split(" ");
        int i = 0;
        String id = "";
        for (String part: splitCommand) {
            if (i == 0 && !(part.equals("deposit"))) return false;

            if (i == 1) {
                for (char n: part.toCharArray()) {
                    if (!Character.isDigit(n)) return false;
                }
                if (part.length() != 8) { return false; }

                if (!bank.accountExistsById(part)) return false;
                id = part;
            }

            if (i == 2 && Double.parseDouble(part) < 0) {
                return false;
            }

            if (i == 2 && !bank.accountSpecifiedAmount(id, Double.parseDouble(part))) {
                return false;
            }

            i++;
        }

        return i == 3;
    }
}
