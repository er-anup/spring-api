package org.upgrad.controllers;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.User;
import org.upgrad.models.User_Profile;
import org.upgrad.services.UserProfileService;
import org.upgrad.services.UserService;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserProfileService userProfileService;


    @PostMapping("/api/user/signup")
    public ResponseEntity registerUser(@RequestParam("userName") String userName, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("firstName") String firstName, @RequestParam(value = "lastName",required = false) String lastName, @RequestParam(value = "aboutMe",required = false) String aboutMe, @RequestParam(value = "contactNumber",required = false) String contactNumber, @RequestParam("dob") String dob, @RequestParam("country") String country) throws Exception {

        User user = new User();
        user.setPassword(hashPassword(password));
        user.setEmail(email);
        user.setUserName(userName);
        User_Profile user_profile = new User_Profile();
        user_profile.setLastName(lastName);
        user_profile.setFirstName(firstName);
        user_profile.setCountry(country);
        user_profile.setContactNumber(contactNumber);
        try {

        } catch (ParseException e) {e.printStackTrace();}
        user_profile.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(dob));
        user_profile.setAboutMe(aboutMe);

        String message = null;
        String userPresent = String.valueOf(userService.findUserByUsername(userName));
        String userEmail = String.valueOf(userService.findUserByEmail(email));

        if (!(userPresent.equalsIgnoreCase("null"))) {
            message = "Try any other Username, this Username has already been taken.";
            return new ResponseEntity < > (message, HttpStatus.FORBIDDEN);
        } else if (!(userEmail.equalsIgnoreCase("null"))) {
            message = "This user has already been registered, try with any other emailId.";
            return new ResponseEntity < > (message, HttpStatus.FORBIDDEN);
        } else {
            userService.registerUserDetails(user, user_profile);
            message = userName + " successfully registered";
            return new ResponseEntity <> (message, HttpStatus.OK);
        }
    }

    /**
     * @param userName username for login
     * @param password password for user
     * @param session HTTP session for status
     * @return Response entity to determine login
     */
    @PostMapping("/api/user/login")
    public ResponseEntity sigin
    (@RequestParam String userName,
     @RequestParam String password, HttpSession session) throws Exception
    {
        String message = null;
        String passwordHash = hashPassword(password);
        String passwordU = String.valueOf(userService.findUserPassword(userName));
        if (!(passwordU.equalsIgnoreCase(passwordHash))) {
            message = "Invalid Credentials";
            return new ResponseEntity <> (message, HttpStatus.UNAUTHORIZED);
        } else {
            String role = String.valueOf(userService.findUserRole(userName));
            if (role.equalsIgnoreCase("admin")) {
                message = "You have logged in as admin!";
            } else if (role.equalsIgnoreCase("user")) {
                message = "You have logged in successfully!";
            }
            if(session.getAttribute("currUser")== null) {
                User user = userService.getUserByUsername(userName);
                session.setAttribute("currUser", user);
            }
            return new ResponseEntity <> (message, HttpStatus.OK);
        }
    }

    /**
     * @param session HTTP Sesion to store status
     * @return Response entity to determine logout
     */
    @PostMapping("api/user/logout")
    public ResponseEntity<String> signout(HttpSession session){
        if(session.getAttribute("currUser")== null)
            return new ResponseEntity<>("You are currently not logged in",HttpStatus.UNAUTHORIZED);
        else{
            session.removeAttribute("currUser");
            return new ResponseEntity<>("You have logged out successfully!",HttpStatus.OK);}
    }

    /**
     * @param userId user Id for which user profile is to be retreived
     * @param session HTTP Session object
     * @return Response entity userprofile details if userId exists
     */
    @GetMapping("/api/user/userprofile/{userId}")
    public ResponseEntity<?> userProfile(@PathVariable("userId") int userId, HttpSession session) {
        if (session.getAttribute("currUser") == null)
            return new ResponseEntity<>("Please Login first to access this endpoint", HttpStatus.UNAUTHORIZED);
        else {
            User_Profile userProfile = userProfileService.retrieveUserProfile(userId);
            if (userProfile!=null) {
                return new ResponseEntity<>(userProfileService.retrieveUserProfile(userId), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("User Profile not found!", HttpStatus.NOT_FOUND);
            }
        }
    }


    /**
     * SHA-256 encryption
     * @param password the plain text String that we want to encrypt
     * @return the SAH-256 encrypted version of the password
     */
    private String hashPassword(String password) {
        String passwordHash = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        return passwordHash;
    }
}
