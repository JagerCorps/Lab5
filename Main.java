import Data.Organisation;
import Services.*;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Главный класс
 */
public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String path = System.getenv("Lab5");
        path = "C:\\Users\\mrgle\\Desktop\\Программирование\\Лаба 5\\Jager.xml";
        PriorityQueue<Organisation> organisations;
        organisations = Parser.read(path, scanner);
        Interpretor interpretor = new Interpretor(organisations,scanner);
        interpretor.setFile(new File(path));
        System.out.println("Здравствуйте!");
        System.out.println("Данные из файла успешно загружены. \nНачало работы.");
        while (true){
            System.out.println(">>>>>>");
            try {
                interpretor.readLine(scanner.nextLine().trim(), false);
            }
            catch(NullPointerException e){
                System.out.println("Скорее всего, возникла проблема при получении скрипта");
            }
            catch(StackOverflowError e){
                System.out.println("Скрипт призывает сам себя и зацикливается.\nИсправьте скрипт.");
            }
            finally {
                System.out.println("Программа продолжает работу");
            }
        }
    }
}
