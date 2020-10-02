package Commands;

import Data.Organisation;
import Services.Parser;

import java.io.File;
import java.util.PriorityQueue;

/**
 * Класс команды save
 */
public class Save extends AbstractCommand implements Command{

    /**
     * Поле файла, в который будет происходить запись данных
     */
    protected File file;

    /**
     * Конструктор класса команды save
     * @param organisations - коллекция объектов класса {@link Organisation}
     * @param file - файл, в который предстоит совершить запись данных
     */
    public Save(PriorityQueue<Organisation> organisations, File file){
        super(organisations);
        this.file = file;
    }

    /**
     * Метод, приводящий команду в исполнение
     */
    @Override
    public void execute(){
        Parser.write(organisations,file);
        System.out.println("Сохранение коллекции в файл прошло успешно.");
    }
}
