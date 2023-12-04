package com.example.jeeSpring.SiteUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {

    @Query("SELECT s from users s WHERE s.email=?1")
    Optional<SiteUser> findSiteUserByEmail(String email);
    @Query("SELECT s from users s WHERE s.userId=?1")
    Optional<SiteUser> findSiteUserById(Long userId);
}
