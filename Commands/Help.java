package Commands;

/**
 * Класс команды help
 */
public class Help implements Command {
    /**
     * Метод, приводящий команду в исполнение
     */
    @Override
    public void execute(){
        System.out.println("Вызвана команда help. Справка по доступным командам:");
        System.out.println("\nhelp: вывести справку по доступным командам");
        System.out.println("info: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        System.out.println("show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.println("add {element}: добавить элемент в коллекцию");
        System.out.println("update id {element}: обновить значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_by_id id:  удалить элемент из коллекции по его id");
        System.out.println("clear: очистить коллекцию");
        System.out.println("save: сохранить коллекцию в файл");
        System.out.println("execute_script file_name: считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        System.out.println("exit: завершить программу (без сохранения в файл)");
        System.out.println("remove_first: удалить первый элемент из коллекции");
        System.out.println("add_if_max {element}: добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        System.out.println("remove_lower {element}: удалить из коллекции все элементы, меньшие, чем заданный");
        System.out.println("remove_all_by_full_name fullName: удалить из коллекции все элементы, значение поля fullName которого эквивалентно заданному");
        System.out.println("filter_less_than_annual_turnover annualTurnover: вывести элементы, значение поля annualTurnover которых меньше заданного");
        System.out.println("print_field_descending_annual_turnover: вывести значения поля annualTurnover всех элементов в порядке убывания");
    }
}
