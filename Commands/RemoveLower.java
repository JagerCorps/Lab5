package Commands;

import Data.Organisation;
import Services.FieldReaders;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Класс команды remove_lower
 */
public class RemoveLower extends AbstractCommand implements Command {

    /**
     * Сканер
     */
    protected Scanner scanner;
    /**
     * Количество сотрудников. Требуется для сравнения
     */
    protected int employees;

    /**
     * Конструктор класса команды remove_lower
     * @param organisations - коллекция элементов класса {@link Organisation}
     * @param scanner - сканер
     * @param employees - количество сотрудников
     */
    public RemoveLower(PriorityQueue<Organisation> organisations, Scanner scanner, int employees){
        super(organisations);
        this.scanner = scanner;
        this.employees = employees;
    }

    @Override
    public void execute(){
        System.out.println("Сравнение происходит по количеству сотрудников.");
        Organisation[] helpOrgArray  = new Organisation[organisations.size()];
        Organisation[] orgArray = organisations.toArray(helpOrgArray);
        if (orgArray.length > 0) {
            int count = 0;
            for (int i = 0; i < orgArray.length; i++) {
                if (orgArray[i].getEmployeesCount() < employees) {
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
