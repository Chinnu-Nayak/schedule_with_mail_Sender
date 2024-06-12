package com.chinu.Service;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class JobService {
    @Autowired
    private NotifcationService notifcationService;

    @Scheduled(cron = "${cron.interval}")
    public void process() throws MessagingException, IOException {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = now.format(formatter);
        System.out.println("Current Time: " + formattedTime);



        notifcationService.sendDailyReport();
    }
}
