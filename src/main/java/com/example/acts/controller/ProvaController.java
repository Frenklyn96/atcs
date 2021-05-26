package com.example.acts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProvaController {
    @GetMapping("/ciao")
    public String sayHello (@RequestParam(value="name",defaultValue ="world") String name){
        return String.format("Hello %s", name);
    }
}
