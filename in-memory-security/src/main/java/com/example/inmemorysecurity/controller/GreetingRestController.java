package com.example.inmemorysecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class GreetingRestController {

    @GetMapping("/greeting")
    String greet(Principal principal) {
        return "hello " + principal.getName() + " !!";
    }
}
