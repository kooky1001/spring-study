package org.example.springjsp;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/test")
    public String get() {
        return "get";
    }

    @PostMapping("/test")
    public String post() {
        return "post";
    }

    @PutMapping("/test")
    public String put() {
        return "put";
    }

    @DeleteMapping("/test")
    public String delete() {
        return "delete";
    }
}
