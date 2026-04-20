package hh.backend.littlefreelibrary.web;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
