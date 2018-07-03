//package org.upgrad.controllers;
//
//import org.hamcrest.Matchers;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.upgrad.services.*;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(LikesFollowController.class)
//public class LikesFollowControllerTest {
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
//    private LikeService likeService;
//
//    @MockBean
//    private FollowService followService;
//
//    @MockBean
//    private AnswerService answerService;
//
//    @MockBean
//    private NotificationService notificationService;
//
//    @Test
//    public void addLikesWithoutLogin() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String answerId ="1";
//        String url = "/api/like/1";
//        mvc.perform(post(url).session(session)
//                .param("answerId",answerId))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("Please Login first to access this endpoint!")));
//    }
//
//    @Test
//    public void addLikesWithLogin() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        String answerId ="1";
//        given(likeService.getLikes(user.getId(),1)).willReturn(null);
//        String url = "/api/like/1";
//        mvc.perform(post(url).session(session)
//                .param("answerId",answerId))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(Matchers.containsString(" answerId " +answerId+ " liked successfully.")));
//    }
//
//    @Test
//    public void addAlreadyLikedLikesWithLogin() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        String answerId ="1";
//        Like like = new Like();
//        given(likeService.getLikes(user.getId(),1)).willReturn(like);
//        String url = "/api/like/1";
//        mvc.perform(post(url).session(session)
//                .param("answerId",answerId))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("You have already liked this answer!")));
//    }
//
//    @Test
//    public void removeLikesWithoutLogin() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String answerId ="1";
//        String url = "/api/unlike/1";
//        mvc.perform(delete(url).session(session)
//                .param("answerId",answerId))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("Please Login first to access this endpoint!")));
//    }
//
//
//    @Test
//    public void removeLikesWithoutAuth() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        String answerId ="1";
//        user.setId(3);
//        Mockito.when(likeService.getUserId(1,user.getId())).thenReturn(null);
//        String url = "/api/unlike/1";
//        mvc.perform(delete(url).session(session)
//                .param("answerId",answerId))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("You have not liked this answer")));
//    }
//
//    @Test
//    public void removeLikesWithAuth() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        user.setId(1);
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        String answerId ="1";
//        Answer answer = new Answer();
//        answer.setUser(user);
//        answer.setId(1);
//        Mockito.when(answerService.findUserIdfromAnswer(1)).thenReturn(1);
//        String url = "/api/unlike/1";
//        mvc.perform(delete(url).session(session)
//                .param("answerId",answerId))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(Matchers.containsString(" You have unliked answer with answerId " +answerId+ " successfully.")));
//    }
//
//
//
//    @Test
//    public void addFollowWithoutLogin() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String categoryId ="1";
//        String url = "/api/follow/1";
//        mvc.perform(post(url).session(session)
//                .param("categoryId",categoryId))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("Please Login first to access this endpoint!")));
//    }
//
//    @Test
//    public void addFollowWithLogin() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        String categoryId ="1";
//        String url = "/api/follow/1";
//        mvc.perform(post(url).session(session)
//                .param("categoryId",categoryId))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(Matchers.containsString(" categoryId " +categoryId+ " followed successfully.")));
//    }
//
//    @Test
//    public void removeFollowWithoutLogin() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String categoryId ="1";
//        String url = "/api/unfollow/1";
//        mvc.perform(delete(url).session(session)
//                .param("categoryId",categoryId))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("Please Login first to access this endpoint!")));
//    }
//
//
//    @Test
//    public void removeFollowWithoutFollows() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        String categoryId ="1";
//        user.setId(3);
//        Mockito.when(followService.findUserId(1,user.getId())).thenReturn(null);
//        String url = "/api/unfollow/1";
//        mvc.perform(delete(url).session(session)
//                .param("categoryId",categoryId))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("You are currently not following this category")));
//    }
//
//    @Test
//    public void removeFollowWithAuth() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        String categoryId ="1";
//        user.setId(3);
//        Follow follow  =new Follow();
//        follow.setId(1);
//        Mockito.when(followService.findUserId(1,user.getId())).thenReturn(3);
//        String url = "/api/unfollow/1";
//        mvc.perform(delete(url).session(session)
//                .param("categoryId",categoryId))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(Matchers.containsString(" You have unfollowed the category with categoryId " +categoryId+ " successfully.")));
//    }
//
//
//}
