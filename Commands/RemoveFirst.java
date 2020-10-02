package Commands;

import Data.Organisation;

import java.util.PriorityQueue;

/**
 * Класс команды remove_first
 */
public class RemoveFirst extends AbstractCommand implements Command {

    /**
     * Конструктор класса команды remove_first
     * @param organisations - коллекция объектов класса организации {@link Organisation}
     */
    public RemoveFirst(PriorityQueue<Organisation> organisations){
        super(organisations);
    }

    /**
     * Метод, приводящий команду в исполнение
     */
    @Override
    public void execute(){
        organisations.poll();
    }
}
