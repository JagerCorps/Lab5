package Commands;

import Data.Organisation;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Класс команды filter_less_than_annual_turnover
 */
public class FilterLessThanAnnualTurnover extends AbstractCommand implements Command{

    /**
     *
     */
    protected Float annualTurnover;
    protected Scanner scanner;

    public FilterLessThanAnnualTurnover(PriorityQueue<Organisation> organisations, Scanner scanner, Float annualTurnover){
        super(organisations);
        this.scanner = scanner;
        this.annualTurnover = annualTurnover;
    }

    @Override
    public void execute(){
        Organisation[] helpOrgArray  = new Organisation[organisations.size()];
        Organisation[] orgArray = organisations.toArray(helpOrgArray);
        if (orgArray.length > 0){
            int count = 0;
            for (int i=0; i < orgArray.length; i++) {
                if (orgArray[i].getAnnualTurnover() < annualTurnover) {
                    count++;
                    System.out.println(count + ")" + orgArray[i]);
                }
            }
            if (count > 0){
                System.out.println("Фильтрация элементов произошла успешно.");
            }
            else{
                System.out.println("Процесс завершился без ошибок.");
                System.out.println("Элементы, удовлетворяющие условию, отсутствуют.");
            }

        }
        else{
            System.out.println("Коллекция пуста.");
        }
    }
}
