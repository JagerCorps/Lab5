package Commands;

import Data.Organisation;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Класс команды add_if_max
 */
public class AddIfMax extends Add implements Command {

    /**
     * Поле максимального элемента коллекции
     */
    protected Organisation maxOrganisation;

    /**
     * Конструктор класса команды add_if_max
     * @param organisations - коллекция объектов класса {@link Organisation}
     * @param scanner - сканер
     * @param line - входная строка в случае вызова через скрипт
     */
    public AddIfMax(PriorityQueue<Organisation> organisations, Scanner scanner, String line){
        super(organisations, scanner, line);
        this.maxOrganisation = this.getMaxOrganisation();
    }

    /**
     * Метод, возвращающий максимальный элемент коллекции
     * @return возвращает максимальный элемент
     */
    protected Organisation getMaxOrganisation(){
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
     * Метод, приводящий команду в исполнение
     */
    @Override
    public void execute(){
        try {
            if (this.maxOrganisation != null) {
                Organisation addedOrganisation = this.createElement();
                addedOrganisation.setId(this.idCounter() + 1);
                if (addedOrganisation.compareTo(this.maxOrganisation) > 0) {
                    organisations.add(addedOrganisation);
                    System.out.println("Добавление элемента произошло успешно.");
                } else {
                    System.out.println("Добавляемый элемент не превышает максимальный элемент коллекции.");
                    System.out.println("Добавление нового элемента в коллекцию не произошло.");
                }
            } else {
                organisations.add(this.createElement());
                System.out.println("Добавление элемента произошло успешно.");
            }
        }
        catch (Exception e){
            System.out.println("Возникла проблема при считывания данных.");
            System.out.println("Элемент не был добавлен. Рекомендуется исправить скрипт.");
        }
    }
}
