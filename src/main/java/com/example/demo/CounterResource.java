package com.example.demo;

import com.example.demo.model.Counter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CounterResource {

    private final CounterDao counterDao;

    public CounterResource(CounterDao counterDao) {
        this.counterDao = counterDao;
    }

    @GetMapping("/counter")
    public Counter getCounter() {
        return counterDao.getCounter().orElseGet(() -> new Counter(0));
    }

    @PostMapping("/counter/increment")
    public Counter incrementCounter() {
        counterDao.incrementCounter();
        return counterDao.getCounter().orElseGet(() -> new Counter(0));
    }
}
