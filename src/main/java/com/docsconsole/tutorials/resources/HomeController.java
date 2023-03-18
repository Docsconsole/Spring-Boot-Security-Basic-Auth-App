package com.docsconsole.tutorials.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hello")
public class HomeController
{
    @GetMapping(path="/", produces = "application/json")
    public String getHello()
    {
        return "hello";
    }
}
