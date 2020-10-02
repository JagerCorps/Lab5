package Commands;

import Data.Organisation;
import Services.Interpretor;

import java.io.File;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Класс команды execute_script
 */
public class ExecuteScript extends AbstractCommand implements Command {

    /**
     * Сканер для считывания скрипта
     */
    protected Scanner scanner;
    /**
     * Файл, в которых будут сохраняться данные
     */
    protected File file;
    /**
     * Файл, являющийся скрпитом
     */
    protected File script;
    /**
     * Интерпретатор
     */
    protected Interpretor interpretor;

    /**
     * Конструктор класса команды execute_script
     * @param organisations - коллекция элементов класса {@link Organisation}
     * @param scriptPath - путь до скрипта
     * @param file - файл для сохранения данных
     * @param interpretor - интерпретатор
     */
    public ExecuteScript(PriorityQueue<Organisation> organisations, String scriptPath, File file, Interpretor interpretor){
        super(organisations);
        this.script = new File(scriptPath);
        this.file = file;
        this.interpretor = interpretor;
        this.interpretor.setFile(file);
    }

    /**
     * Метод, приводящий команду в исполнение
     */
    @Override
    public void execute(){
        System.out.println("Вызвана команда execute_script");
        try {
            scanner = new Scanner(script);
            do {
                interpretor.readLine(scanner.nextLine(), true);
            }
            while (scanner.hasNextLine());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            scanner.close();
        }

    }
}
