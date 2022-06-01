package com.example.restservice.Controllers;

import com.example.restservice.Counter.Counter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    @RequestMapping(value = "/counter", method = RequestMethod.GET)
    public int getCounter() {
        return Counter.getRequestsCount();
    }
}
