package service;

/**
 * Exiting program
 */
public class Exit extends Command {

    @Override
    public void execute(String[] args) {
        System.exit(0);
    }

    @Override
    public String getCommandName() {
        return "exit";
    }

}
