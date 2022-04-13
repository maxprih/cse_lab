package service;

/**
 * Abstract class responsible for command
 */
public abstract class Command {

    /**
     * Executing the command
     *
     * @param args if needed
     * @throws IllegalArgumentException
     */
    public abstract void execute(String [] args) throws IllegalArgumentException;

    /**
     * For invoking command by its name
     *
     * @return string name of command
     */
    public abstract String getCommandName();

}
