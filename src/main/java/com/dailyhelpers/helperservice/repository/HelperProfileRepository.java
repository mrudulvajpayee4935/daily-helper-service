package com.dailyhelpers.helperservice.repository;

import com.dailyhelpers.helperservice.model.HelperProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HelperProfileRepository extends JpaRepository<HelperProfile, Long> {
    List<HelperProfile> findByLocation(String location);
    Optional<HelperProfile> findById(Long id);
}
