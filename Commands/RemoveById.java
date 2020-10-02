package Commands;

import Data.Organisation;
import Services.FieldReaders;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Класс команды remove_by_id
 */
public class RemoveById extends AbstractCommand implements Command {

    /**
     * Поле сканера
     */
    protected Scanner scanner;

    /**
     * id элемента
     */
    protected int id;

    /**
     * Конструктор класса команды remove_by_id
     * @param organisations - коллекция элементов класса {@link Organisation}
     * @param scanner - сканер
     * @param id - id элемента
     */
    public RemoveById(PriorityQueue<Organisation> organisations, Scanner scanner, int id){
        super(organisations);
        this.scanner = scanner;
        this.id = id;
    }

    /**
     * Метод, приводящий команду в исполнение
     */
    @Override
    public void execute(){
        Organisation[] helpOrgArray  = new Organisation[organisations.size()];
        Organisation[] orgArray = organisations.toArray(helpOrgArray);
        boolean isDone = false;
        for (int i=0; i < orgArray.length; i++) {
            if (orgArray[i].getId() == id) {
                organisations.remove(orgArray[i]);
                System.out.println("Элемент по заданному id успешно удалён.");
                isDone = true;
                break;
            }
        }
        if (!isDone){
            System.out.println("Элемента с данным id не существует.");
            System.out.println("Процесс завершился без ошибок. Ни один элемент не был удалён.");
        }
    }

    /**
     * Метод, приводящий команду в исполнение в скрипте
     * @param id - id элемента
     */
    public void executeScript(int id){
        Organisation[] helpOrgArray  = new Organisation[organisations.size()];
        Organisation[] orgArray = organisations.toArray(helpOrgArray);
        for (int i=0; i < orgArray.length; i++) {
            if (orgArray[i].getId() == id) {
                organisations.remove(orgArray[i]);
                break;
            }
        }
    }
}
