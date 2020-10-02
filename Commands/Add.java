package Commands;

import Data.Organisation;
import Services.FieldReaders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Класс для реализации команды add
 */
public class Add extends AbstractCommand implements Command{

    /**
     * Поле объекта класса {@link Organisation}, который подлежит додавить в коллекцию
     */
    protected Organisation newOrg = new Organisation();

    /**
     * Сканер, который будет считывать значения
     */
    protected Scanner scanner;

    /**
     * Строка, которая будет подана в команду в случае исполнения через скрипт
     */
    protected String line;

    /**
     * Конструктор класса команды add
     * @param organisations - коллекция объектов класса {@link Organisation}
     * @param scanner - сканер
     * @param line - строка
     */
    public Add(PriorityQueue<Organisation> organisations, Scanner scanner, String line){
        super(organisations);
        this.scanner = scanner;
        this.line = line;
    }

    /**
     * Метод, возвращабщий максимальное значение id среди всех элементов коллекции
     * Нужно для того, чтобы каждый новый элемент имел уникальный id
     * При создании элемента, ему задаётся id, который на 1 больше максимального
     * Если коллекция пуста, новый элемент получит id = 1, так как метод вернёт 0
     * @return возвращает максимальный id среди всех элементов коллекции
     */
    protected int idCounter(){
        Organisation[] helpOrgArray  = new Organisation[organisations.size()];
        Organisation[] orgArray = organisations.toArray(helpOrgArray);
        if (orgArray.length > 0){
            int maxID = orgArray[0].getId();
            for (int i=1; i < orgArray.length; i++){
                if (orgArray[i].getId() > maxID){
                    maxID = orgArray[i].getId();
                }
            }
            return maxID;
        }
        else{
            return 0;
        }
    }


    /**
     * Метод, который создаёт новый объект класса {@link Organisation} в качестве нового элемента коллекции
     * @throws Exception - проброс возможного исключения
     * @return возвращает созданный элемент
     */
    protected Organisation createElement() throws Exception{
        if (line.equals("")) {
            newOrg.setName(FieldReaders.readString(scanner, "Введите имя:"));
            newOrg.setCoordinates(FieldReaders.readX(scanner), FieldReaders.readY(scanner));
            newOrg.setCreationDate(LocalDate.now());
            newOrg.setAnnualTurnover(FieldReaders.readTurnover(scanner));
            newOrg.setFullName(FieldReaders.readString(scanner, "Введите полное имя:"));
            newOrg.setEmployeesCount(FieldReaders.readInt(scanner, "Введите количество сотрудников:"));
            newOrg.setType(FieldReaders.readType(scanner));
            newOrg.setOfficialAddress(FieldReaders.readString(scanner, "Введите официальный адрес организации:"));
        }
        else{
            String newLine = line.replaceAll("[{}\\s]", " ");
            String[] values = newLine.split("; ");

            String name;
            double x;
            double y;
            LocalDate creationDate;
            float annualTurnover;
            String fullName;
            int employeesCount;
            String organisationType;
            String address;

            name = values[0].trim();
            if (name.isEmpty()){
                System.out.println("Возникла проблема. Имя не может быть пустым");
                name = "RandomName" + (int) (Math.random() * 200);
                System.out.println("Принудительно присвоено случайное значение.\n");
            }
            x = Double.parseDouble(values[1]);
            if (x < -422){
                System.out.println("Возникла проблема. Координата Х не может быть меньше -422");
                x = -422;
                System.out.println("Присвоено значение -422 принудительно.\n");
            }
            y = Double.parseDouble(values[2]);
            creationDate = LocalDate.parse(values[3], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            try {
                annualTurnover = Float.parseFloat(values[4]);
                if (annualTurnover < 0) {
                    System.out.println("Возникла проблема. Годовой оборот не может быть меньше 0");
                    annualTurnover = 1;
                    System.out.println("Присвоено значение 1 принудительно.\n");
                }
            }
            catch (Exception e){
                System.out.println("Возникла проблема. Годовой оборот не может быть меньше 0");
                annualTurnover = 1;
                System.out.println("Присвоено значение 1 принудительно.\n");
            }
            fullName = values[5];
            if (fullName.isEmpty()){
                System.out.println("Возникла проблема. Полное имя не может быть пустым");
                fullName = "RandomFullName" + (int) (Math.random() * 200);
                System.out.println("Принудительно присвоено случайное значение.\n");
            }
            employeesCount = Integer.parseInt(values[6]);
            if (employeesCount < 0){
                System.out.println("Возникла проблема. Количество сотрудников не может быть меньше 0");
                employeesCount = 1;
                System.out.println("Присвоено значение 1 принудительно.\n");
            }
            organisationType = values[7];
            address = values[8].trim();
            System.out.println(address);
            if (address.isEmpty()){
                System.out.println("Возникла проблема. Адрес не может быть пустым");
                address = "RandomAddress" + (int) (Math.random() * 200);
                System.out.println("Принудительно присвоено случайное значение.\n");
            }
            newOrg.setName(name);
            newOrg.setCoordinates(x, y);
            newOrg.setCreationDate(creationDate);
            newOrg.setAnnualTurnover(annualTurnover);
            newOrg.setFullName(fullName);
            newOrg.setEmployeesCount(employeesCount);
            try {
                newOrg.setType(organisationType);
            }
            catch (IllegalArgumentException e){
                System.out.println("Возникла проблема. Тип введён некорректно");
                newOrg.setType("COMMERCIAL");
                System.out.println("Принудительно введён тип COMMERCIAL\n");
            }
            newOrg.setOfficialAddress(address);
        }

        return newOrg;
    }

    /**
     * Метод, приводящий команду в исполнение
     */
    @Override
    public void execute(){
        try {
            System.out.println("Происходит добавление нового элемента.");
            Organisation organisation = this.createElement();
            organisation.setId(this.idCounter() + 1);
            organisations.add(organisation);
            System.out.println("Добавление элемента прошло успешно.");
        }
        catch (Exception e){
            System.out.println("Возникла проблема при считывания данных.");
            System.out.println("Элемент не был добавлен. Рекомендуется исправить скрипт.");
        }
    }
}