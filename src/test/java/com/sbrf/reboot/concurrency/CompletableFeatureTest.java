package com.sbrf.reboot.concurrency;

import com.sbrf.reboot.Repository.Client;
import com.sbrf.reboot.service.ReportService;
import com.sbrf.reboot.service.SomeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.mockito.Mockito.*;

public class CompletableFeatureTest {

    @Test
    public void successCompletableFeature() throws ExecutionException, InterruptedException, TimeoutException {
        ReportService reportService = Mockito.mock(ReportService.class);

        when(reportService.sendReport("Отправляю отчет")).then(e -> {

            Thread.sleep(Duration.ofSeconds(3).toMillis());
            return "SUCCESS";
        });

        SomeService someService = new SomeService(reportService);

        someService.doSomething();

        verify(reportService, times(1)).sendReport("Отправляю отчет");
    }

    @Test
    public void printInfoAboutClient() throws InterruptedException, ExecutionException {
        Client client = new Client();

        client.setId(0L);
        client.setPassword("passwrd");
        client.setUsername("Clint Eastwood");
        client.setAmountValue(1_000_00.0);

        //Распечатывание уже известной информации
        CompletableFuture<String> completableFuturePrint = CompletableFuture.supplyAsync(() -> {
            StringBuilder userInfoToStringBuilder = new StringBuilder();

            userInfoToStringBuilder.append("user id: ").append(client.getId())
                    .append(" username: ").append(client.getUsername())
                    .append(" password: ").append(client.getPassword())
                    .append(" dollars on account: ").append(client.getAmountValue());

            try {
                Thread.sleep(Duration.ofSeconds(3).toMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return userInfoToStringBuilder.toString();
        });

        long initTime = System.currentTimeMillis();

        //Запрос к базе данных для анализа чего-то
        Thread.sleep(Duration.ofSeconds(3).toMillis());

        System.out.println(completableFuturePrint.get());

        long finalTime = System.currentTimeMillis();

        Assertions.assertTrue(6000 > finalTime - initTime);
    }
}
