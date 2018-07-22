package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.User;
import org.upgrad.services.CategoryService;
import org.upgrad.services.NotificationService;
import org.upgrad.services.UserService;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId, HttpSession session) {
        User user = (User) session.getAttribute("currUser");
        if (user == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }
        else if (user.getRole().equals("admin")) {
            notificationService.deleteNotification(userId);
            userService.deleteUserById(userId);
            return new ResponseEntity<>("User with userId " + userId + " deleted successfully!", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("You do not have rights to delete a user!!", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/users/all")
    public ResponseEntity<?> getAllUsers(HttpSession session) {
        User user = (User) session.getAttribute("currUser");
        if (user == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else if (user.getRole().equals("admin")) {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You do not have rights to access all users!", HttpStatus.UNAUTHORIZED);
        }
    }
 
    @PostMapping("/category")
    public ResponseEntity<?> categoriesCreation (@RequestParam("categoryTitle") String title,@RequestParam String description, HttpSession session) {
        User user = (User) session.getAttribute("currUser");
        if (user == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else if (user.getRole().equals("admin")) {
            categoryService.updateCategory(title,description);
            return new ResponseEntity<>(title+" category added successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You do not have rights to add categories.", HttpStatus.UNAUTHORIZED);
        }
    }
}
