package Commands;

import Data.Organisation;

import java.util.PriorityQueue;

/**
 * Класс команды clear
 */
public class Clear extends AbstractCommand implements Command{

    /**
     * Конструктор класса команды clear
     * @param organisations - коллекция объектов класса организации {@link Organisation}
     */
    public Clear(PriorityQueue<Organisation> organisations){
        super(organisations);
    }

    @Override
    public void execute(){
        organisations.clear();
    }
}
