package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.upgrad.services.CategoryService;
import org.upgrad.services.QuestionService;

@RestController
public class CommonController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/api/categories/all")
    public ResponseEntity<?> getAllCategories () {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/api/questions/all")
    public ResponseEntity<?> getAllQuestions () {
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }
}
