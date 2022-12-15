package com.example.SpringCoverAll.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TemplateController { // todo: Authorization isn't work for this api (Always forbidden)

    @GetMapping("home_page")
    public String toHomePage() {
        return "home";
    }

}
