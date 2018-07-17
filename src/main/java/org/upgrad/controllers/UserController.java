package org.upgrad.controllers;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @PostMapping("/signup")
    public String signup(){

        return "Hello";

    }

    @PostMapping("/login")
    public String signin(){

        return "Hello";

    }

    @PostMapping("/logout")
    public String signout(){

        return "Hello";

    }

    @GetMapping("/userprofile/{userId}")
    public String userProfile(){

        return "Hello";

    }

    @GetMapping("/notification/new")
    public String getNewNotifications(){

        return "Hello";

    }

    @GetMapping("/notification/all")
    public String getAllNotifications(){

        return "Hello";

    }

}
