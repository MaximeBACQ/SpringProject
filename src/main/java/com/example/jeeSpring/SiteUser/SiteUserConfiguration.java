package com.example.jeeSpring.SiteUser;

import com.example.jeeSpring.SiteUser.SiteUser;
import com.example.jeeSpring.SiteUser.SiteUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class SiteUserConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(SiteUserRepository repository){
        return args ->{
            SiteUser admin = new SiteUser("admin","admin","admin", "admin@gmail.com",
                    LocalDate.of(2002, JULY, 29)
                    ,"Male","admin",true,true,true);
            SiteUser user = new SiteUser("user","user","user", "user@gmail.com",
                    LocalDate.of(2023, JULY, 29)
                    ,"Male","user",false,false,false);
            repository.saveAll(
                    List.of(admin,user)
            );
        };
    }
}
