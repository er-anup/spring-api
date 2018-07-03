//package org.upgrad.controllers;
//
//import com.google.common.base.Charsets;
//import com.google.common.hash.Hashing;
//import org.hamcrest.Matchers;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static java.util.Collections.singletonList;
//import static org.hamcrest.Matchers.containsString;
//import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(UserController.class)
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private UserService userService;
//
//    protected MockHttpSession session;
//
//    @MockBean
//    private UserProfileService userProfileService;
//
//    @MockBean
//    private NotificationService notificationService;
//
//
//    @Test
//    public void registerUser() throws Exception{
//        String firstName = "software";
//        String lastName= "development";
//        String userName = "upgrad";
//        String email = "upgrad@upgrad.com";
//        String password = "12345";
//        String country = "India";
//        String aboutMe = "hello world";
//        String dob = "2001-01-01";
//        String contactNumber = "1999999999";
//
//        String url = "/api/user/signup";
//        mvc.perform(post(url)
//                .param("firstName", firstName)
//                .param("lastName", lastName)
//                .param("userName", userName)
//                .param("email", email)
//                .param("password", password)
//                .param("country", country)
//                .param("aboutMe", aboutMe)
//                .param("dob", dob)
//                .param("contactNumber", contactNumber))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(containsString("upgrad successfully registered")));
//    }
//
//    @Test
//    public void registerUserWithExistingUsername() throws Exception{
//        User user= new User();
//        user.setId(1);
//        user.setUserName("upgrad");
//        String firstName = "software";
//        String lastName= "development";
//        String userName = "upgrad";
//        String email = "upgrad@upgrad.com";
//        String password = "12345";
//        String country = "India";
//        String aboutMe = "hello world";
//        String dob = "2001-01-01";
//        String contactNumber = "1999999999";
//        Mockito.when(userService.findUserByUsername(userName)).thenReturn("upgrad");
//        String url = "/api/user/signup";
//        mvc.perform(post(url)
//                .param("firstName", firstName)
//                .param("lastName", lastName)
//                .param("userName", userName)
//                .param("email", email)
//                .param("password", password)
//                .param("country", country)
//                .param("aboutMe", aboutMe)
//                .param("dob", dob)
//                .param("contactNumber", contactNumber))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(containsString("Try any other Username, this Username has already been taken.")));
//    }
//
//    @Test
//    public void registerUserWithExistingEmail() throws Exception{
//        User user= new User();
//        user.setId(1);
//        user.setUserName("upgrad");
//        String firstName = "software";
//        String lastName= "development";
//        String userName = "upgrad";
//        String email = "upgrad@upgrad.com";
//        String password = "12345";
//        String country = "India";
//        String aboutMe = "hello world";
//        String dob = "2001-01-01";
//        String contactNumber = "1999999999";
//        Mockito.when(userService.findUserByEmail(email)).thenReturn("upgrad@upgrad.com");
//        String url = "/api/user/signup";
//        mvc.perform(post(url)
//                .param("firstName", firstName)
//                .param("lastName", lastName)
//                .param("userName", userName)
//                .param("email", email)
//                .param("password", password)
//                .param("country", country)
//                .param("aboutMe", aboutMe)
//                .param("dob", dob)
//                .param("contactNumber", contactNumber))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(containsString("This user has already been registered, try with any other emailId.")));
//    }
//
//    @Test
//    public void loginAsAdmin() throws Exception{
//        String userName = "upgrad";
//        String password = "12345";
//        String passwordHash = Hashing.sha256()
//                .hashString(password, Charsets.US_ASCII)
//                .toString();
//        String role = "admin";
//
//        Mockito.when(userService.findUserPassword(userName)).thenReturn(passwordHash);
//        Mockito.when(userService.findUserRole(userName)).thenReturn(role);
//
//        String url = "/api/user/login";
//        mvc.perform(post(url)
//                .param("userName", userName)
//                .param("password", password))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(containsString("You have logged in as admin!")));
//    }
//
//    @Test
//    public void loginAsUser() throws Exception{
//
//        String userName = "upgrad";
//        String password = "12345";
//        String passwordHash = Hashing.sha256()
//                .hashString(password, Charsets.US_ASCII)
//                .toString();
//        String role = "user";
//
//        Mockito.when(userService.findUserPassword(userName)).thenReturn(passwordHash);
//        Mockito.when(userService.findUserRole(userName)).thenReturn(role);
//
//        String url = "/api/user/login";
//        mvc.perform(post(url)
//                .param("userName", userName)
//                .param("password", password))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(containsString("You have logged in successfully!")));
//    }
//
//    @Test
//    public void loginWithBadCredentials() throws Exception{
//
//        String userName = "upgrad";
//        String password = "12345";
//        String passwordHash = Hashing.sha256()
//                .hashString(password, Charsets.US_ASCII)
//                .toString();
//        String badPassword = "";
//        String role = "user";
//
//        Mockito.when(userService.findUserPassword(userName)).thenReturn(passwordHash);
//        Mockito.when(userService.findUserRole(userName)).thenReturn(role);
//
//        String url = "/api/user/login";
//        mvc.perform(post(url)
//                .param("userName", userName)
//                .param("password", badPassword))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(containsString("Invalid Credentials")));
//    }
//
//    @Test
//    public void logoutUser() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        String url = "/api/user/logout";
//        mvc.perform(post(url).session(session))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(containsString("You have logged out successfully")));
//    }
//
//    @Test
//    public void logoutUserWithoutLogin() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String url = "/api/user/logout";
//        mvc.perform(post(url).session(session))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(containsString("You are currently not logged in")));
//    }
//
//    @Test
//    public void getNewNotificationsWithoutLogin() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String url = "/api/user/notification/new";
//        mvc.perform(get(url).session(session))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("Please Login first to access this endpoint!")));
//    }
//
//    @Test
//    public void getNewNotificationsWithLogin() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        Notification notification = new Notification();
//        notification.setMessage("A new notification has been added");
//        notification.setUser(user);
//        notification.setId(1);
//        notification.setRead(Boolean.FALSE);
//        List<Notification> newNotifications = singletonList(notification);
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        given(notificationService.getNewNotifications(user.getId())).willReturn(newNotifications);
//        String url = "/api/user/notification/new";
//        mvc.perform(get(url).session(session)
//                .contentType(MediaType.asMediaType(APPLICATION_JSON)))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].message", Matchers.is(notification.getMessage())));
//    }
//
//    @Test
//    public void getAllNotificationsWithoutLogin() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String url = "/api/user/notification/all";
//        mvc.perform(get(url).session(session))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("Please Login first to access this endpoint!")));
//    }
//
//    @Test
//    public void getAllNotificationsWithLogin() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        Notification notification = new Notification();
//        notification.setMessage("A new notification has been added");
//        notification.setUser(user);
//        notification.setId(1);
//        notification.setRead(Boolean.FALSE);
//        List<Notification> allNotifications = singletonList(notification);
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        given(notificationService.getAllNotifications(user.getId())).willReturn(allNotifications);
//        String url = "/api/user/notification/all";
//        mvc.perform(get(url).session(session)
//                .contentType(MediaType.asMediaType(APPLICATION_JSON)))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].message", Matchers.is(notification.getMessage())));
//    }
//
//
//}
