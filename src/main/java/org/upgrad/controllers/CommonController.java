package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.upgrad.services.CategoryService;
import org.upgrad.services.QuestionService;

/*
 * Author - Mananpreet Singh
 * Date - 11 July 2018
 * Description - Rest API Controller for 'Likes' feature.
 */

@RestController
public class CommonController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private QuestionService questionService;

    /*Rest API to retrieve all cagtegories present in the DB.
        Does not require any authenticaiton OR authorization.
        Does not require any param from user.
     */
    @GetMapping("/api/categories/all")
    public ResponseEntity<?> getAllCategories () {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    /*Rest API to retrieve all Questions present in the DB.
    Does not require any authenticaiton OR authorization.
    Does not require any param from user.
 */
    @GetMapping("/api/questions/all")
    public ResponseEntity<?> getAllQuestions () {
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }
}
