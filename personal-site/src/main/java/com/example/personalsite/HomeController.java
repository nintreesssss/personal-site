package com.example.personalsite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @GetMapping("/")

    public String home(Model model) {
        model.addAttribute("name","nintrees");
        model.addAttribute("bio","MUST CS本科在读");
        model.addAttribute("github", "https://github.com/nintreesssss");
        model.addAttribute("x", "https://twitter.com/nintrees"); 
        model.addAttribute("email", "nintreesssss@gmail.com");

        return "index"; //渲染src/main/resources/templates/index.html
    }
}