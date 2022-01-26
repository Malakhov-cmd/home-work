package com.sbrf.reboot.concurrentmodify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveElementWithoutErrorsTest {

    @Test
    public void successConcurrentModificationException() {

        List<Integer> list = new ArrayList() {{
            add(1);
            add(2);
            add(3);
        }};

        assertThrows(ConcurrentModificationException.class, () -> {

            for (Integer integer : list) {
                list.remove(1);
            }

        });

    }

    @Test
    public void successRemoveElementWithoutErrors() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));

        list = list.stream()
                .filter(element -> element != 2)
                .collect(Collectors.toList());

        Assertions.assertEquals(2, list.size());
    }

    //Другие варианты
    @Test
    public void successRemoveElementWithoutErrorsViaRemoveIf() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));

        list.removeIf(element -> element == 2);

        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void successRemoveElementWithoutErrorsViaIterateAndAddToAnotherCollection() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> listAnother = new ArrayList<>();

        for (Integer element :
                list) {
            if (element != 2) {
                listAnother.add(element);
            }
        }

        Assertions.assertEquals(2, listAnother.size());
    }

    @Test
    public void successRemoveElementWithoutErrorsViaOldStyle() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));

        for (Iterator<Integer> it = list.iterator();
             it.hasNext(); ) {
            if (it.next() == 2) {
                it.remove();
            }
        }

        Assertions.assertEquals(2, list.size());
    }
}
