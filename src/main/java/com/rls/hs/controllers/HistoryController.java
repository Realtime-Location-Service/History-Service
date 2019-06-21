package com.rls.hs.controllers;


import com.rls.hs.models.LocationData;
import com.rls.hs.repositories.LocationDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
@Validated
public class HistoryController {
    private static final Logger logger = LoggerFactory
            .getLogger(HistoryController.class);

    private final LocationDataRepository locationDataRepository;

    @Autowired
    public HistoryController(LocationDataRepository theLocationDataRepository) {
        this.locationDataRepository = theLocationDataRepository;
    }

    @GetMapping("/history")
    public ResponseEntity<List<LocationData>> getHistory(@RequestHeader("RLS-Referrer") String theDomain,
                                                      @RequestParam("userID") String userID,
                                                      @RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date startDate,
                                                      @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endDate
    ) {

        return new ResponseEntity<>(locationDataRepository.findWithDateRange(theDomain, userID, startDate, endDate), HttpStatus.OK);
    }
}

//    @RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-DD") Date startDate,
//    @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-DD") Date endDate