package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DistilleryController {

    @Autowired
    DistilleryRepository repo;

    @GetMapping("/distilleries")
    public ResponseEntity<List<Distillery>> getDistilleries(
            @RequestParam(name = "region", required = false) String region,
            @RequestParam(name = "whiskyAge", required = false) Integer age
    ) {
        if (region != null) return new ResponseEntity<>(repo.findByRegion(region), HttpStatus.OK);
        if (age != null) return new ResponseEntity<>(repo.findByWhiskiesAge(age), HttpStatus.OK);
        return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
    }

}
