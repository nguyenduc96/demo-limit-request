package com.example.demo.task;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Log4j2
public class TaskTest {

    @Scheduled(cron = "0 0 */1 * * *")
    public void test() {
        log.info("Time = {}", new Date());
    }
}
