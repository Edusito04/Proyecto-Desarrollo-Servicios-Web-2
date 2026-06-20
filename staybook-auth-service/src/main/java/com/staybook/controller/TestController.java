package com.staybook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "StayBook API funcionando correctamente";
    }
    @GetMapping("/privado")
    public String privado() {

        return "Endpoint protegido con JWT";
    }
}