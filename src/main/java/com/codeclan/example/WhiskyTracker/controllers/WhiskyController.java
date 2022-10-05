package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository repo;

    @GetMapping(path = "/whiskies")
    public ResponseEntity<List<Whisky>> getWhiskiesByYear(
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "distillery", required = false) String distilleryName,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "region", required = false) String region
    ) {
        if (year != null)
            return new ResponseEntity<>(repo.findByYear(year), HttpStatus.OK);
        if (distilleryName != null && age != null)
            return new ResponseEntity<>(repo.findByDistilleryNameAndAge(distilleryName, age), HttpStatus.OK);
        if (region != null)
            return new ResponseEntity<>(repo.findByDistilleryRegion(region), HttpStatus.OK);

        return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
    }

}
