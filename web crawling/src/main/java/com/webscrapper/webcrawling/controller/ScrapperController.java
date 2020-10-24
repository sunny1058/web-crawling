package com.webscrapper.webcrawling.controller;

import java.util.List;

import com.webscrapper.webcrawling.dto.Books;
import com.webscrapper.webcrawling.service.AcademicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("api")
public class ScrapperController {
    @Autowired
    private AcademicService jsoupConect;

    @GetMapping("/scrap/details")
    public ResponseEntity<List<Books>> getData() {

        try {
            List<Books>  response = jsoupConect.connect();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.info("e message :" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}