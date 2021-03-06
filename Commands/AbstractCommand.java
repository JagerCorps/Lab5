package Commands;

import Data.Organisation;

import java.util.PriorityQueue;


/**
 * Абстрактный класс-предок для всех команд
 */
public abstract class AbstractCommand implements Command{

    /**
     * Поле коллекции классов Organisation {@link Organisation}, с которыми идёт работа
     */
    protected PriorityQueue<Organisation> organisations;

    /**
     *Конструктор класса AbstractCommand
     * @param organisations - коллекция классов Organisation {@link Organisation}
     */
    protected AbstractCommand(PriorityQueue<Organisation> organisations){
        this.organisations = organisations;
    }

}
