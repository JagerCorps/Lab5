package Commands;

import Data.Organisation;

import java.util.PriorityQueue;

public class PrintFieldDescendingAnnualTurnover extends AbstractCommand implements Command {

    /**
     * Конструктор класса команды print_field_descending_annual_turnover
     * @param organisations - коллекция объектов класса организации {@link Organisation}
     */
    public PrintFieldDescendingAnnualTurnover(PriorityQueue<Organisation> organisations){
        super(organisations);
    }

    /**
     * Метод, приводящий команду в исполнение
     */
    @Override
    public void execute(){
        System.out.println("Значения полей annualTurnover в порядке убывания:");
        Organisation[] helpOrgArray  = new Organisation[organisations.size()];
        Organisation[] orgArray = organisations.toArray(helpOrgArray);
        boolean sorted = false;
        Organisation temp;
        while (!sorted){
            sorted = true;
            for (int i=0; i < orgArray.length-1; i++){
                if (orgArray[i].getAnnualTurnover() < orgArray[i+1].getAnnualTurnover()){
                    temp = orgArray[i];
                    orgArray[i] = orgArray[i+1];
                    orgArray[i+1] = temp;
                    sorted = false;
                }
            }
        }
        for (int i=0; i < orgArray.length; i++){
            System.out.println(orgArray[i].getAnnualTurnover());
        }
    }
}
