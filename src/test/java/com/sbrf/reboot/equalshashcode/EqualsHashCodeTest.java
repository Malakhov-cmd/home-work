package com.sbrf.reboot.equalshashcode;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EqualsHashCodeTest {

    class Car {
        String model;
        String color;
        Calendar releaseDate;
        int maxSpeed;

        @Override
        public boolean equals(Object o) {

            //Рефлексивность: объект должен равняться самому себе
            if (o == this)
                return true;
            //Объект должны являться представлением типа Car
            if (!(o instanceof Car))
                return false;
            //Приведение типа к нашему классу
            Car comparable = (Car) o;
            //Сравнение всех значимых полей
            return this.model.equals(comparable.model) &&
                    this.color.equals(comparable.color) &&
                    this.releaseDate.equals(comparable.releaseDate) &&
                    this.maxSpeed == comparable.maxSpeed;
        }

        @Override
        public int hashCode() {
            /*Выбираем мультипликатор для обеспечения равномерного распределния значений хэш-функции
            Причины выбора числа 31:
            1) простое (что важно для равномерного распределения хэш-функции)
            2) оптимизация вычисления, т.к. может быть заменено на побитовый сдвиг (31 * i == (i << 5) - i)
            * */
            final int multiplicator  = 31;
            //Инициализация исходного значения
            int hashCode = 0;

            /*Формирование хэш-значения с помощью мультипликатора, уже полученных значений
            и встроенных реализаций hashCode() для типов используемых в классе Car*/
            //Формуруем значение по полю model
            hashCode = hashCode * multiplicator + model.hashCode();
            //С помощью мультипликатора распределяем полученное значение (стремимся минимизировать вероятность
            // коллизии) и добавляем значение по полю color
            hashCode = hashCode * multiplicator + color.hashCode();
            //аналогично полю color
            hashCode = hashCode * multiplicator + releaseDate.hashCode();
            //С помощью мультипликатора распределяем полученное значение и добавляем значение поля maxSpeed,
            //т.к. оно простого типа
            hashCode = hashCode * multiplicator + maxSpeed;

            //возвращаем хэш-код
            return hashCode;
        }
    }

    @Test
    public void assertTrueEquals() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, 0, 25);
        car2.maxSpeed = 10;


        Assertions.assertTrue(car1.equals(car2));
    }

    @Test
    public void assertFalseEquals() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Audi";
        car2.color = "white";
        car2.releaseDate = new GregorianCalendar(2017, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertFalse(car1.equals(car2));
    }

    @Test
    public void assertFalseToNull() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Assertions.assertFalse(car1.equals(null));
    }

    @Test
    public void assertTrueToThemself() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Assertions.assertTrue(car1.equals(car1));
    }

    @Test
    public void assertFalseToSimilarFieldObject() {
        @AllArgsConstructor
        class Motorcycle {
            String model;
            String color;
            Calendar releaseDate;
            int maxSpeed;
        }

        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Motorcycle moto = new Motorcycle("Mercedes", "black",
                new GregorianCalendar(2020, 0, 25), 10);

        Assertions.assertFalse(car1.equals(moto));
    }

    @Test
    public void successEqualsHashCode() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertEquals(car1.hashCode(), car2.hashCode());

    }

    @Test
    public void failEqualsHashCode() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Audi";
        car2.color = "white";
        car2.releaseDate = new GregorianCalendar(2017, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertNotEquals(car1.hashCode(), car2.hashCode());

    }

    @Test
    public void hashCodeOnDifferentTypesAndSameValues() {
        @AllArgsConstructor
        class Motorcycle {
            String model;
            String color;
            Calendar releaseDate;
            int maxSpeed;

            @Override
            public int hashCode() {
                final int multiplicator  = 31;
                int hashCode = 0;

                hashCode = hashCode * multiplicator + model.hashCode();
                hashCode = hashCode * multiplicator + color.hashCode();
                hashCode = hashCode * multiplicator + releaseDate.hashCode();
                hashCode = hashCode * multiplicator + maxSpeed;

                return hashCode;
            }
        }

        Motorcycle moto = new Motorcycle("Mercedes", "black",
                new GregorianCalendar(2020, 0, 25), 10);

        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        //При одинаковых значениях полей и единстве реализации hashcode(), но разных типах
        //получаем одинаковые значения
        Assertions.assertEquals(moto.hashCode(), car1.hashCode());
    }

}
