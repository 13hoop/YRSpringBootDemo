package com.yongren.github.demos.web;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    @GetMapping("/{id}")
    public String getByID(@PathVariable Integer id) {
        System.out.println(" --> query id " + id);
        return "hello, " + id;
    }
}