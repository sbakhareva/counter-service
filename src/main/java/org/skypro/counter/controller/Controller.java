package org.skypro.counter.controller;

import org.skypro.counter.service.CounterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.time.StopWatch;

@RestController
public class Controller {
    private final CounterService counterService;

    public Controller(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping
    public String hello() {
        return "Hello, world!";
    }

    @GetMapping("/counter")
    public String count() {
        StopWatch watch = StopWatch.createStarted();
        counterService.countdown();
        watch.stop();
        String response = "Количество запросов: " + counterService.getCount();
        String time = "Время обработки запроса: " + watch.getNanoTime() + " миллисекунд.";
        return response + "\n" + time;
    }

    @GetMapping("/greetings")
    public String greetings(@RequestParam("name") String name,

                            @RequestParam("lastName") String lastName) {
        return "Hello, " + name + " " + lastName;
    }


}