package Commands;

import Data.Organisation;
import Services.FieldReaders;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Класс команды update
 */
public class Update extends Add implements Command {

    /**
     * id элемента
     * Если на входе он равен -1, значит команда вызвана из скрипта
     */
    protected int id;
    /**
     * Конструктор класса команды update
     * @param organisations - коллекция элементов класса {@link Organisation}
     * @param scanner - сканер
     * @param line - строка с параметрами в случае призыва команды через скрипт
     * @param id - id элемента
     */
    public Update(PriorityQueue<Organisation> organisations, Scanner scanner, String line, int id){
        super(organisations, scanner, line);
        this.id = id;
        if (id==-1){
            String[] values = this.line.split("[\\s]", 2);
            this.id = Integer.parseInt(values[0]);
            this.line = values[1];
        }
    }

    /**
     * Метод, приводящий команду в исполнение
     */
    @Override
    public void execute(){
        try {
            Organisation[] helpOrgArray = new Organisation[organisations.size()];
            Organisation[] orgArray = organisations.toArray(helpOrgArray);
            boolean isDone = false;
            for (int i = 0; i < orgArray.length; i++) {
                if (orgArray[i].getId() == id) {
                    organisations.remove(orgArray[i]);
                    System.out.println("Происходит обновление элемента по заданному id.");
                    Organisation updatedOrg = this.createElement();
                    updatedOrg.setId(id);
                    organisations.add(updatedOrg);
                    System.out.println("Элемент по заданному id успешно обновлён.");
                    isDone = true;
                    break;
                }
            }
            if (!isDone) {
                System.out.println("Элемента с данным id не существует.");
                System.out.println("Процесс завершился без ошибок. Ни один элемент не был обновлён.");
            }
        }
        catch (Exception e){
            System.out.println("Возникла проблема при считывания данных.");
            System.out.println("Элемент не был обновлён. Рекомендуется исправить скрипт.");
        }
    }
}
