package service;

/**
 * Printing info about all available commands
 */
public class Help extends Command{

    @Override
    public void execute(String [] args) {
        System.out.println("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, " +
                "количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся " +
                "команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "remove_first : удалить первый элемент из коллекции\n" +
                "remove_last : удалить последний элемент из коллекции\n" +
                "history : вывести последние 15 команд (без их аргументов)\n" +
                "remove_any_by_best_album bestAlbum : удалить из коллекции один элемент, значение поля bestAlbum " +
                "которого эквивалентно заданному\n" +
                "average_of_number_of_participants : вывести среднее значение поля numberOfParticipants для всех " +
                "элементов коллекции\n" +
                "count_by_genre genre : вывести количество элементов, значение поля genre которых равно заданному");
    }

    @Override
    public String getCommandName() {
        return "help";
    }
}
