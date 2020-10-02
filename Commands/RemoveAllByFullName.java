package Commands;

import Data.Organisation;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Класс команды remove_all_by_full_name
 */
public class RemoveAllByFullName extends AbstractCommand implements Command {

    /**
     * Сканер
     */
    protected Scanner scanner;

    /**
     * Полное имя организации
     */
    protected String fullName;
    /**
     * Конструктор класса команды remove_all_by_full_name
     * @param organisations - коллекция объектов класса организации {@link Organisation}
     * @param scanner - сканер
     * @param fullName - полное имя
     */
    public RemoveAllByFullName(PriorityQueue<Organisation> organisations, Scanner scanner, String fullName){
        super(organisations);
        this.scanner = scanner;
        this.fullName = fullName;
    }

    /**
     * Метод, приводящий команду в исполнение
     */
    @Override
    public void execute(){
        Organisation[] helpOrgArray  = new Organisation[organisations.size()];
        Organisation[] orgArray = organisations.toArray(helpOrgArray);
        if (orgArray.length > 0){
            int count = 0;
            for (int i=0; i < orgArray.length; i++) {
                if (orgArray[i].getFullName().equals(fullName)) {
                    organisations.remove(orgArray[i]);
                    count++;
                }
            }
            if (count > 0){
                System.out.println("Удаление элементов произошло успешно.");
                System.out.printf("Количество удалённых элементов: %d. %n", count);
            }
            else{
                System.out.println("Процесс завершился без ошибок.");
                System.out.println("Элементы, подлежащие удалению, отсутствуют.");
            }

        }
        else{
            System.out.println("Коллекция пуста. Удаление элементов не произошло.");
        }
    }
}
