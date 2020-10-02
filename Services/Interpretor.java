package Services;

import Commands.*;
import Data.Organisation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Interpretor{

    /**
     * Сканер. Если команды не будут являтся прочитанными из скрипта
     * Значения полей будут считываться с его помощью
     * Подразумевается, что он будет привязан к консоли
     */
    protected Scanner scanner;

    /**
     * Коллекция элементов класса {@link Organisation}
     */
    protected PriorityQueue<Organisation> organisations;

    /**
     * Файл, в который будет происходить сохранение данных
     */
    protected File file;

    /**
     * Конструктор интерпретатора
     * @param organisations - коллекция элементов класса {@link Organisation}
     * @param scanner - сканер
     */
    public Interpretor(PriorityQueue<Organisation> organisations, Scanner scanner){
        this.organisations = organisations;
        this.scanner = scanner;
    }

    /**
     * Метод, который задаёт интерпретатору файл с данными
     * @param file - файл
     */
    public void setFile(File file){
        this.file = file;
    }

    /**
     * Метод, считывающий строку
     * @param inputCommand - строка с командой
     * @param isScript - пометка, говорящая о том, считана ли команда из консоли или скрипта
     */
    public void readLine(String inputCommand, boolean isScript){
        String[] command = inputCommand.split("[\\s]", 2);
        switch (command[0]){
            case "help":
                Help help = new Help();
                help.execute();
                break;

            case "info":
                Info info = new Info(organisations);
                info.execute();
                break;

            case "show":
                Show show = new Show(organisations);
                show.execute();
                break;

            case "add":
                if (!isScript){
                    Add add = new Add(organisations,scanner,"");
                    add.execute();
                }
                else{
                    Add add = new Add(organisations,scanner,command[1]);
                    add.execute();
                }
                break;

            case "update":
                if (!isScript){
                    Update update = new Update(organisations,scanner,"",
                            FieldReaders.readInt(scanner,"Введите id:"));
                    update.execute();
                }
                else{
                    Update update = new Update(organisations,scanner,command[1],-1);
                    update.execute();
                }
                break;

            case "remove_by_id":
                if (!isScript){
                    RemoveById removeById = new RemoveById(organisations,scanner,
                            FieldReaders.readInt(scanner,"Введите id:"));
                    removeById.execute();
                }
                else{
                    try {
                        RemoveById removeById = new RemoveById(organisations, scanner, Integer.parseInt(command[1]));
                        removeById.execute();
                    }
                    catch (Exception e){
                        System.out.println("В команде remove_by_id возникла проблема! Поправьте скрипт.");
                    }
                }
                break;

            case "clear":
                Clear clear = new Clear(organisations);
                clear.execute();
                break;

            case "save":
                Save save = new Save(organisations,file);
                save.execute();
                break;

            case "execute_script":
                ExecuteScript executeScript = new ExecuteScript(organisations, command[1], file,
                        new Interpretor(organisations, scanner));
                executeScript.execute();
                break;

            case "exit":
                Exit exit = new Exit(scanner);
                exit.execute();

            case "remove_first":
                RemoveFirst removeFirst = new RemoveFirst(organisations);
                removeFirst.execute();
                break;

            case "add_if_max":
                if (!isScript){
                    AddIfMax addIfMax = new AddIfMax(organisations,scanner,"");
                    addIfMax.execute();
                }
                else{
                    AddIfMax addIfMax = new AddIfMax(organisations,scanner,command[1]);
                    addIfMax.execute();
                }
                break;

            case "remove_lower":
                System.out.println("Сравнение происходит по количеству сотрудников");
                if (!isScript){
                    RemoveLower removeLower = new RemoveLower(organisations,scanner,FieldReaders.readInt(scanner,"Введите количество сотрудников:"));
                    removeLower.execute();
                }
                else{
                    try {
                        RemoveLower removeLower = new RemoveLower(organisations, scanner, Integer.parseInt(command[1]));
                        removeLower.execute();
                    }
                    catch (Exception e){
                        System.out.println("В команде remove_lower возникла проблема! Поправьте скрипт.");
                    }
                }
                break;

            case "remove_all_by_full_name":
                if (!isScript){
                    RemoveAllByFullName rABFN = new RemoveAllByFullName(organisations,scanner,
                            FieldReaders.readString(scanner,"Введите полное имя:"));
                    rABFN.execute();
                }
                else{
                    try {
                        RemoveAllByFullName rABFN = new RemoveAllByFullName(organisations,scanner, command[1]);
                        rABFN.execute();
                    }
                    catch (Exception e){
                        System.out.println("В команде remove_all_by_full_name возникла проблема! Поправьте скрипт.");
                    }
                }
                break;

            case "filter_less_than_annual_turnover":
                if (!isScript){
                    FilterLessThanAnnualTurnover fLTAT = new FilterLessThanAnnualTurnover(organisations,scanner,
                            FieldReaders.readTurnover(scanner));
                    fLTAT.execute();
                }
                else{
                    try {
                        FilterLessThanAnnualTurnover fLTAT = new FilterLessThanAnnualTurnover(organisations,scanner,
                                Float.parseFloat(command[1]));
                        fLTAT.execute();
                    }
                    catch (Exception e){
                        System.out.println("В команде filter_less_than_annual_turnover возникла проблема! Поправьте скрипт.");
                    }
                }
                break;

            case "print_field_descending_annual_turnover":
                PrintFieldDescendingAnnualTurnover pFDAT = new PrintFieldDescendingAnnualTurnover(organisations);
                pFDAT.execute();
                break;

            default:
                System.out.println("Введённая команда не опознана.");
                System.out.println("Попробуйте ввести команду help для вывода списка команд.");
                break;
        }
    }
}
