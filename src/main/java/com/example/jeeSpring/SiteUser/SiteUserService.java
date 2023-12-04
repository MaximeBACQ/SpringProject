package com.example.jeeSpring.SiteUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteUserService {

    private final SiteUserRepository siteUserRepository;

    @Autowired
    public SiteUserService(SiteUserRepository siteUserRepository) {
        this.siteUserRepository = siteUserRepository;
    }

    public List<SiteUser> getSiteUsers(){
        return siteUserRepository.findAll();
    }

    public void addNewUser(SiteUser user) {
        Optional<SiteUser> userOptional = siteUserRepository.findSiteUserByEmail(user.getEmail());
        if(userOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }

        siteUserRepository.save(user);
    }
}
