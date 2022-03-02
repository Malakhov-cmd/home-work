package com.sbrf.reboot.concurrency;

import com.sbrf.reboot.service.concurrency.Task;
import com.sbrf.reboot.service.concurrency.TaskExecutorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class TaskExecutorServiceTest {

    @Test
    public void successRunMultithreading() throws InterruptedException {

        Task task = Mockito.mock(Task.class);
        CountDownLatch latch = new CountDownLatch(2);

        TaskExecutorService taskExecutorService = new TaskExecutorService(2);

        doAnswer((e -> {
            latch.countDown();
            return null;
        })).when(task).run();

        taskExecutorService.execute(task);

        latch.await();

        assertEquals(0, latch.getCount());
        verify(task, times(2)).run();

        taskExecutorService.shutdown();
    }

    @Test
    public void successRunMultithreadingOnRecommendedCountOfThread() throws InterruptedException {

        Task task = Mockito.mock(Task.class);
        CountDownLatch latch = new CountDownLatch(36);

        TaskExecutorService taskExecutorService = new TaskExecutorService(36);

        doAnswer((e -> {
            latch.countDown();
            return null;
        })).when(task).run();

        taskExecutorService.execute(task);

        latch.await();

        assertEquals(0, latch.getCount());
        verify(task, times(36)).run();
        /*
            Запускаемые потоки не разделяют ресурсы и не выполняют вычисления над
            большим количеством данных, поэтому запускать количество потоков большее
            значения - (количество ядер - 1) не имеет смысла.
            Функционал получения количества ядер исполняемой машины приведен в методе
            taskExecutorService.recommendedThreadCount().
            Моя машина имеет 8 ядер.
         */
        assertEquals(7 , taskExecutorService.recommendedThreadCount());

        taskExecutorService.shutdown();
    }
}