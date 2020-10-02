package Commands;

import Data.Organisation;

import java.util.PriorityQueue;

public class Show extends AbstractCommand implements Command{

    /**
     * Конструктор класса команды show
     * @param organisations - коллекция объектов класса организации {@link Organisation}
     */
    public Show(PriorityQueue<Organisation> organisations){
        super(organisations);
    }

    /**
     * Метод, приводящий команду в исполнение
     */
    @Override
    public void execute(){
        Organisation[] helpOrgArray  = new Organisation[organisations.size()];
        Organisation[] orgArray = organisations.toArray(helpOrgArray);
        if (organisations.size() == 0){
            System.out.println("Коллекция пуста.");
        }
        else{
            System.out.println("Коллекция содержит элементы:");
            for (int i=1; i < orgArray.length + 1; i++) {
                System.out.println(i+") "+orgArray[i-1]);
            }
        }
    }
}
