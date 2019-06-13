package com.rls.hs.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class HistoryController {
    private static final Logger logger = LoggerFactory
            .getLogger(HistoryController.class);

}
