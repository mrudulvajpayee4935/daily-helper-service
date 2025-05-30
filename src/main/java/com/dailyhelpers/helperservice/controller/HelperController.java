package com.dailyhelpers.helperservice.controller;

import com.dailyhelpers.helperservice.model.HelperProfile;
import com.dailyhelpers.helperservice.repository.HelperProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/helpers")
public class HelperController {

    @Autowired
    private HelperProfileRepository helperProfileRepository;

    @GetMapping("/location/{location}")
    public ResponseEntity<List<HelperProfile>> getHelpersByLocation(@PathVariable String location) {
        return ResponseEntity.ok(helperProfileRepository.findByLocation(location));
    }
}
