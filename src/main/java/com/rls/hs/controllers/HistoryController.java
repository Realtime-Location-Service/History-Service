package com.rls.hs.controllers;


import com.rls.hs.models.DBLocationData;
import com.rls.hs.models.LocationDataResponse;
import com.rls.hs.repositories.LocationDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<LocationDataResponse> getHistory(@RequestHeader("RLS-Referrer") String theDomain,
                                                           @RequestParam("user_id") String user_id,
                                                           @RequestParam("start_date") long start_date,
                                                           @RequestParam("end_date") long end_date
    ) {
        List<DBLocationData> someResults = locationDataRepository.findWithDateRange(theDomain, user_id, new Date(start_date * 1000), new Date(end_date * 1000));
        return new ResponseEntity<>( new LocationDataResponse(someResults), HttpStatus.OK);
    }
}