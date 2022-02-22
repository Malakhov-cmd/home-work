package com.sbrf.reboot.service.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TaskExecutorService {

    private final int numberOfThreads;

    private final ExecutorService service;

    public TaskExecutorService(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
        this.service = Executors.newFixedThreadPool(recommendedThreadCount());
    }

    public void execute(Task task) {
        IntStream
                .range(0, numberOfThreads)
                .mapToObj(i -> task)
                .forEach(service::execute);
    }

    public void shutdown() {
        service.shutdown();
    }

    public int recommendedThreadCount()
    {
        int cores = Runtime.getRuntime().availableProcessors() - 1;
        return Math.min(cores, this.numberOfThreads);
    }
}
