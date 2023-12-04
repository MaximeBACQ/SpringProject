package com.example.jeeSpring.SiteUser;

import com.example.jeeSpring.SiteUser.SiteUser;
import com.example.jeeSpring.SiteUser.SiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/SiteUser")
public class SiteUserController {
    private final SiteUserService siteUserService;

    @Autowired
    public SiteUserController(SiteUserService siteUserService) {
        this.siteUserService = siteUserService;
    }

    @GetMapping("")
    public List<SiteUser> getSiteUsers(){
        return siteUserService.getSiteUsers();
    }

    @PostMapping
    public void registerNewUser(@RequestBody SiteUser user){ // we take the request body and map it into a student
        siteUserService.addNewUser(user);
    }


}
