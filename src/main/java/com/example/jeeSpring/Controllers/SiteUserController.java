package com.example.jeeSpring.Controllers;

import com.example.jeeSpring.Entities.SiteUser;
import com.example.jeeSpring.Services.SiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/SiteUser")
public class SiteUserController {
    private final SiteUserService siteUserService;


    public SiteUserController(SiteUserService siteUserService) {
        this.siteUserService = new SiteUserService();
    }

/*    @GetMapping
    public List<SiteUser> getSiteUsers();*/
}
