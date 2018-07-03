//package org.upgrad.controllers;
//
//import org.hamcrest.Matchers;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.BDDMockito.given;
//
//import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//
//import java.util.List;
//
//import static java.util.Collections.singletonList;
//import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;
//
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(CommentController.class)
//public class CommentControllerTest {
//
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
//    private AnswerService answerService;
//
//    @MockBean
//    private CommentService commentService;
//
//    @MockBean
//    private NotificationService notificationService;
//
//
//    @Test
//    public void addCommentWithoutLogin() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String comment = "good";
//        String answerId ="1";
//        String url = "/api/comment";
//        mvc.perform(post(url).session(session)
//                .param("comment",comment)
//                .param("answerId",answerId))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("Please Login first to access this endpoint!")));
//    }
//
//    @Test
//    public void addCommentWithLogin() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        String comment = "good";
//        String answerId ="1";
//        String url = "/api/comment";
//        mvc.perform(post(url).session(session)
//                .param("comment",comment)
//                .param("answerId",answerId))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(Matchers.containsString(" answerId " +answerId+ " commented successfully.")));
//    }
//
//    @Test
//    public void editCommentWithoutLogin() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String comment = "good";
//        String commentId ="1";
//        String url = "/api/comment/1";
//        mvc.perform(put(url).session(session)
//                .param("commentId",commentId)
//                .param("comment",comment))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("Please Login first to access this endpoint!")));
//    }
//
//
//    @Test
//    public void editCommentWithoutAuthorization() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        user.setId(3);
//        String commentId ="1";
//        String com = "good";
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        Comment comment = new Comment();
//        comment.setUser(user);
//        comment.setId(1);
//        Mockito.when(commentService.findUserIdfromComment(1)).thenReturn(2);
//        String url = "/api/comment/1";
//        mvc.perform(put(url).session(session)
//                .param("comment",com)
//                .param("commentId",commentId))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("You do not have rights to edit this comment.")));
//    }
//
//    @Test
//    public void editCommentWithAuthorization() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("admin");
//        String commentId ="1";
//        String comment = "good";
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        Mockito.when(commentService.findUserIdfromComment(1)).thenReturn(2);
//        String url = "/api/comment/1";
//        mvc.perform(put(url).session(session)
//                .param("comment",comment)
//                .param("commentId",commentId))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(Matchers.containsString(" Comment with commentId " +commentId+ " edited successfully.")));
//    }
//
//    @Test
//    public void editCommentWithUserAuthorization() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        user.setId(2);
//        String commentId ="1";
//        String com = "good";
//        Comment comment = new Comment();
//        comment.setUser(user);
//        comment.setId(1);
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        Mockito.when(commentService.findUserIdfromComment(1)).thenReturn(user.getId());
//        String url = "/api/comment/1";
//        mvc.perform(put(url).session(session)
//                .param("comment",com)
//                .param("commentId",commentId))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(Matchers.containsString(" Comment with commentId " +commentId+ " edited successfully.")));
//    }
//
//    @Test
//    public void deleteCommentWithoutLogin() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String commentId ="1";
//        String url = "/api/comment/1";
//        mvc.perform(delete(url)
//                .param("commentId",commentId)
//                .session(session))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("Please Login first to access this endpoint!")));
//    }
//
//    @Test
//    public void deleteCommentWithoutAuthorization() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        String commentId ="1";
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        user.setId(3);
//        Mockito.when(commentService.findUserIdfromComment(1)).thenReturn(2);
//        String url = "/api/comment/1";
//        mvc.perform(delete(url).session(session)
//                .param("commentId",commentId))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("You do not have rights to delete this comment!")));
//    }
//
//    @Test
//    public void deleteCommentWithAdminAuthorization() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("admin");
//        String commentId ="1";
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        user.setId(3);
//        Mockito.when(commentService.findUserIdfromComment(1)).thenReturn(2);
//        String url = "/api/comment/1";
//        mvc.perform(delete(url).session(session)
//                .param("commentId",commentId))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(Matchers.containsString(" Comment with commentId " +commentId+ " deleted successfully.")));
//    }
//
//    @Test
//    public void deleteCommentWithUserAuthorization() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        user.setId(3);
//        String commentId ="1";
//        Comment comment = new Comment();
//        comment.setUser(user);
//        comment.setId(1);
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        Mockito.when(commentService.findUserIdfromComment(1)).thenReturn(user.getId());
//        String url = "/api/comment/1";
//        mvc.perform(delete(url).session(session)
//                .param("commentId",commentId))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(Matchers.containsString(" Comment with commentId " +commentId+ " deleted successfully.")));
//    }
//
//
//    @Test
//    public void allCommentsWithoutLogin() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String answerId ="1";
//        String url = "/api/comment/all/1";
//        mvc.perform(get(url).session(session)
//                .param("answerId",answerId))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("Please Login first to access this endpoint!")));
//    }
//
//    @Test
//    public void allCommentsWithLogin() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        Comment comment = new Comment();
//        comment.setContent("good");
//        List<Comment> allComments = singletonList(comment);
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        String answerId ="1";
//        given(commentService.getAllComments(1)).willReturn(allComments);
//        String url = "/api/comment/all/1";
//        mvc.perform(get(url).session(session)
//                .param("answerId",answerId)
//                .contentType(MediaType.asMediaType(APPLICATION_JSON)))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].content", Matchers.is(comment.getContent())));
//    }
//
//}
