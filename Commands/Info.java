package Commands;

import Data.Organisation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;

/**
 * Класс команды info
 */
public class Info extends AbstractCommand implements Command {

    /**
     * Конструктор класса команды info
     * @param organisations - коллекция объектов класса {@link Organisation}
     */
    public Info(PriorityQueue<Organisation> organisations){
        super(organisations);
    }

    /**
     * Метод, возвращающий максимальный элемент коллекции
     * @return возвращает максимальный элемент коллекции
     */
    private Organisation getMaxOrganisation(){
        Organisation[] helpOrgArray  = new Organisation[organisations.size()];
        Organisation[] orgArray = organisations.toArray(helpOrgArray);
        if (orgArray.length > 0){
            Organisation maxOrg = orgArray[0];
            for (int i=1; i < orgArray.length; i++){
                if (orgArray[i].compareTo(maxOrg) > 0){
                    maxOrg = orgArray[i];
                }
            }
            return maxOrg;
        }
        else{
            return null;
        }
    }

    /**
     * Метод, необходимый для получения даты инициализации коллекции
     * Для этого метод ищет элемент с самой ранней датой инициализацией
     * @return возвращает дату инициализации
     */
    private LocalDate getOrganisationDate(){
        Organisation[] helpOrgArray  = new Organisation[organisations.size()];
        Organisation[] orgArray = organisations.toArray(helpOrgArray);
        if (orgArray.length > 0){
            LocalDate earlierDate = orgArray[0].getCreationDate();
            for (int i=1; i < orgArray.length; i++){
                if (orgArray[i].getCreationDate().isBefore(earlierDate)){
                    earlierDate = orgArray[i].getCreationDate();
                }
            }
            return earlierDate;
        }
        else{
            return null;
        }
    }

    /**
     * Метод, приводящий команду в исполнение
     */
    @Override
    public void execute(){
        System.out.println("Вызвана команда info. Информация о коллекции:");
        System.out.println("\nТип коллекции: PriorityQueue");
        System.out.println("Коллекция содержит элементы класса: Organisation");
        System.out.printf("Количество элементов коллекции: %d\n", this.organisations.size());
        if (this.organisations.size()>0) {
            System.out.printf("Дата инициализации коллекции: %s\n",this.getOrganisationDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            System.out.printf("Максимальный элемент коллекции: \n%s\n", this.getMaxOrganisation().toString());
        }
    }
}
