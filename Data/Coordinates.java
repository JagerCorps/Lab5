package Data;

import java.util.Locale;

/**
 * Класс координат
 */
public class Coordinates {

    /**
     * Поле координаты x, его значение должно превышать -422
     */
    private double x; //Значение поля должно быть больше -422

    /**
     * Поле координаты y
     */
    private double y;

    /**
     * Конструктор для задания координат x и y
     * @param x - координата x
     * @param y - координата y
     */
    public Coordinates(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Метод для получения координаты y
     * @return возвращает значение координаты y
     */
    public double getY() {
        return y;
    }

    /**
     * Метод для получения координаты x
     * @return возвращает значение координаты x
     */
    public double getX() {
        return x;
    }

    /**
     * Метод для получения координат организации в строчной форме в формате "X = A Y = B"
     * @return возвращает строку координат в формате "X = A Y = B"
     */
    @Override
    public String toString(){
        return "X = " +  x + " Y = " + y;
    }

}
