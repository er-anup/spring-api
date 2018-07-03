//package org.upgrad.controllers;
//
//import org.hamcrest.Matchers;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.List;
//
//import static java.util.Collections.singletonList;
//import static org.hamcrest.CoreMatchers.containsString;
//import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(AdminController.class)
//public class AdminControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    protected MockHttpSession session;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private CategoryService categoryService;
//
//    @MockBean
//    private UserProfileService userProfileService;
//
//    @Test
//    public void createCategoryWithNoAuthentication() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String categoryTitle = "Music";
//        String description = "This is the genre of Music";
//        String url = "/api/admin/category";
//        mvc.perform(post(url).session(session)
//                .param("categoryTitle", categoryTitle).param("description", description))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(containsString("Please Login first to access this endpoint!")));
//    }
//
//    @Test
//    public void createCategoryWithNoAuthorization() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        String categoryTitle = "Music";
//        String description = "This is the genre of Music";
//        String url = "/api/admin/category";
//        mvc.perform(post(url).session(session)
//                .param("categoryTitle", categoryTitle).param("description", description))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(containsString("You do not have rights to add categories.")));
//    }
//
//    @Test
//    public void createCategoryWithAdminAuthorization() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("admin");
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        String categoryTitle = "Music";
//        String description = "This is the genre of Music";
//        String url = "/api/admin/category";
//        mvc.perform(post(url).session(session)
//                .param("categoryTitle", categoryTitle).param("description", description))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(containsString("Music category added successfully.")));
//    }
//
//    @Test
//    public void getAllUsersWithNoAuthentication() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String url = "/api/admin/users/all";
//        mvc.perform(get(url).session(session))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(containsString("Please Login first to access this endpoint!")));
//    }
//
//    @Test
//    public void getAllUsersWithNoAuthorization() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        String url = "/api/admin/users/all";
//        mvc.perform(get(url).session(session))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(containsString("You do not have rights to access all users!")));
//    }
//
//    @Test
//    public void getAllUsersWithAuthorization() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("admin");
//        List<User> allUsers= singletonList(user);
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        given(userService.getAllUsers()).willReturn(allUsers);
//        String url = "/api/admin/users/all";
//        mvc.perform(get(url).session(session)
//                .contentType(MediaType.asMediaType(APPLICATION_JSON)))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].userName", Matchers.is(user.getUserName())));
//    }
//
//    @Test
//    public void deleteUserWithoutLogin() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String userId ="1";
//        String url = "/api/admin/user/1";
//        mvc.perform(delete(url)
//                .param("userId",userId)
//                .session(session))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("Please Login first to access this endpoint!")));
//    }
//
//    @Test
//    public void deleteUserWithoutAuthorization() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        String userId ="1";
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        String url = "/api/admin/user/1";
//        mvc.perform(delete(url).session(session)
//                .param("userId",userId))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("You do not have rights to delete a user!")));
//    }
//
//    @Test
//    public void deleteUserWithAuthorization() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("admin");
//        String userId ="1";
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        String url = "/api/admin/user/1";
//        mvc.perform(delete(url).session(session)
//                .param("userId",userId))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(Matchers.containsString("User with userId 1 deleted successfully!")));
//    }
//
//}
