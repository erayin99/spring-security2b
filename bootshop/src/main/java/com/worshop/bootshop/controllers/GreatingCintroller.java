package com.worshop.bootshop.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/greatings")
public class GreatingCintroller {

    @GetMapping
    public ResponseEntity<String> sayHello(){

        return ResponseEntity.ok("hello from out API");
    }

    @GetMapping("/say-good-bye")
    public ResponseEntity<String> sayGoodbye(){

        return ResponseEntity.ok ( "SEE YOU LATER" );
    }

}
