package com.jeeSpring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }

    @PostMapping("/cart")
    public String getCart() {
        return "cart";
    }

    @GetMapping("/cart")
    public String postCart() {
        return "cart";
    }

    @GetMapping("/registerPage")
    public String registerPage() {
        return "registerPage";
    }

    @GetMapping("/mailVerification")
    public String mailVerification() {
        return "mailVerification";
    }

    @GetMapping("/productPage")
    public String productPage() {
        return "productPage";
    }

    @GetMapping("/accountPage")
    public String accountPage() {
        return "accountPage";
    }

    @GetMapping("/adminPage")
    public String adminPage() {
        return "adminPage";
    }

    @GetMapping("/moderatorPage")
    public String moderatorPage() {
        return "moderatorPage";
    }

    @GetMapping("/detailledProductPage")
    public String detailledProductPage() {
        return "detailledProductPage";
    }



}

