public class CommandValidator {

    public boolean valid(String command) {
        command = command.toLowerCase();
        String[] splitCommand = command.split(" ");
        int i = 0;
        for (String part: splitCommand) {
            if ( i == 0 && !(part.equals("deposit")) && !(part.equals("create"))) return false;
            i++;
        }
        return true;
    }
}
