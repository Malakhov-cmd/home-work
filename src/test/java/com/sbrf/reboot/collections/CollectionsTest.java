package com.sbrf.reboot.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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

        List<String> students = new LinkedList<>(Arrays.asList("Козлов", "Иванов", "Петров", "Сидоров"));

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

        List<Book> bookshelf = new ArrayList<>(Arrays.asList(new Book(), new Book(), new Book()));

        assertEquals(3, bookshelf.size());
    }

    /*
     * Задача.
     * В аккаунте пользователя банка хранятся уникальные идентификаторы операций.
     * В большенстве случаев обращения к банковскому аккаунту требуется найти уникальный идентификатор, реже требуется зарегестрировать новую операцию.
     *
     * Вопрос.
     * Какую коллекцию из Collections framework вы предпочтете использовать данной структуры.
     *
     * Проинициализируйте operations, добавьте в него 5 идентификаторов, чтобы тест завершился успешно.
     */
    @Test
    public void addAccount(){
        Set<Integer> operations = new TreeSet<>(Arrays.asList(47,32,5,92,55));

        assertEquals(5, operations.size());
    }
}
