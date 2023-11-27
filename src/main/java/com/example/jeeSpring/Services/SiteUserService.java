package com.example.jeeSpring.Services;

import com.example.jeeSpring.Entities.SiteUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class SiteUserService {
    @GetMapping
    public List<SiteUser> getSiteUsers(){
        return List.of(
                new SiteUser(1, "caca", "caca@pipi.fr",
                        LocalDate.of(2002, Month.JULY, 29)
                        ,21)
        );
    }
}
