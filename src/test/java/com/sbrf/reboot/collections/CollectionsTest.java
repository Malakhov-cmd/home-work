package com.sbrf.reboot.collections;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionsTest {


    /*
     * Задача.
     * Имеется список лучших студентов вуза.
     *
     * 1. Иванов
     * 2. Петров
     * 3. Сидоров
     *
     * В новом семестре по результатам подсчетов оценок в рейтинг на 1 место добавился новый студент - Козлов.
     * Теперь в рейтинге участвуют 4 студента.
     * (Предполагаем что в рейтинг можно попасть только получив достаточное количество балов, что бы занять 1 место).
     *
     * Вопрос.
     * Какую коллекцию из Collections framework вы предпочтете для текущего хранения и использования списка студентов?
     *
     * Проинициализируйте students, добавьте в нее 4 фамилии студентов что бы тест завершился успешно.
     */
    @Test
    public void addStudentToRating() {

        List<String> students = new ArrayList<>(Arrays.asList("Иванов", "Петров", "Сидоров"));
        students.add(0, "Козлов");

        assertEquals(4, students.size());
    }

    /*
     * Задача.
     * Вы коллекционируете уникальные монеты.
     * У вас имеется специальный бокс с секциями, куда вы складываете монеты в хаотичном порядке.
     *
     * Вопрос.
     * Какую коллекцию из Collections framework вы предпочтете использовать для хранения монет в боксе.
     *
     * Проинициализируйте moneyBox, добавьте в нее 10 монет что бы тест завершился успешно.
     */
    @Test
    public void addMoneyToBox() {
        Set<Integer> moneyBox = new HashSet<>(Arrays.asList(1,2,3,4,5,10,25,50,100,200));

        assertEquals(10, moneyBox.size());
    }

    /*
     * Задача.
     * Имеется книжная полка.
     * Периодически вы берете книгу для чтения, затем кладете ее на свое место.
     *
     * Вопрос.
     * Какую коллекцию из Collections framework вы предпочтете использовать для хранения книг.
     *
     * Проинициализируйте bookshelf, добавьте в нее 3 книги что бы тест завершился успешно.
     */
    @Test
    public void addBookToShelf() {
        class Book {
        }

        Map<Integer, Book> bookshelf = new HashMap<>();

        bookshelf.put(0, new Book());
        bookshelf.put(1, new Book());
        bookshelf.put(2, new Book());

        Book bookToRead = bookshelf.remove(1);
        /*
        Процесс чтения
         */

        //возврат книги на ее место
        bookshelf.put(1, bookToRead);

        assertEquals(3, bookshelf.size());
    }

    /*
     * Задача.
     * В банке хранятся номер счета и сведения о владельце.
     * В большенстве случаев обращения к банку требуется найти сведения клиента, реже требуется зарегестрировать новый счет.
     *
     * Вопрос.
     * Какую коллекцию из Collections framework вы предпочтете использовать для счетов и информации о его владельце.
     *
     * Проинициализируйте bankRepo, добавьте в него 5 счетов что бы тест завершился успешно.
     */
    @Test
    public void addAccount(){
        Map<Integer, String> bankRepo = new TreeMap<>();

        bankRepo.put(78, "James Gandolfini");
        bankRepo.put(13, "Steve Bushemi");
        bankRepo.put(28, "Loren Branko");
        bankRepo.put(99, "Micle Imperriolly");
        bankRepo.put(45, "Eddy Falco");

        assertEquals(5, bankRepo.size());
    }
}
