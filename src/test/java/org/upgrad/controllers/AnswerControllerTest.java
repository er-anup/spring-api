//package org.upgrad.controllers;
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
//import java.util.*;
//
//import static java.util.Collections.singletonList;
//import static org.hamcrest.CoreMatchers.containsString;
//import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(AnswerController.class)
//public class AnswerControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    protected MockHttpSession session;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private AnswerService answerService;
//
//    @MockBean
//    private QuestionService questionService;
//
//    @MockBean
//    private NotificationService notificationService;
//
//    @Test
//    public void createAnswerWithNoAuthentication() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String questionId = "3";
//        String answer = "This is the answer";
//        String url = "/api/answer";
//        mvc.perform(post(url).session(session)
//                .param("questionId", questionId).param("answer", answer))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(containsString("Please Login first to access this endpoint!")));
//    }
//    @Test
//    public void createAnswerWithAuthentication() throws Exception {
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        session = new MockHttpSession();
//        session.setAttribute("currUser",user);
//        String answer = "This is the answer";
//        String questionId = "3";
//        String url = "/api/answer";
//        mvc.perform(post(url).session(session)
//                .param("questionId",questionId).param("answer",answer))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(containsString("Answer to questionId 3 added successfully")));
//    }
//    @Test
//    public void editAnswerWithNoAuthentication() throws Exception {
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String answerId = "3";
//        String answer = "This is the edited answer";
//        String url = "/api/answer/3";
//        mvc.perform(delete(url).session(session)
//                .param("answerId", answerId).param("answer",answer))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("Please Login first to access this endpoint!")));
//    }
//    @Test
//    public void editAnswerWithNoAuthorization() throws Exception {
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        String answer = "This is the answer for REST architecture";
//        user.setId(4);
//        Mockito.when(answerService.findUserIdfromAnswer(3)).thenReturn(3);
//        String url = "/api/answer/3";
//        mvc.perform(put(url).session(session)
//                .param("answer",answer))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("You do not have rights to edit this answer.")));
//    }
//
//    @Test
//    public void editAnswerWithAuthorization() throws Exception {
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("admin");
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        String answer ="This is the edited answer";
//        user.setId(4);
//        Mockito.when(answerService.findUserIdfromAnswer(3)).thenReturn(4);
//        String url = "/api/answer/3";
//        mvc.perform(put(url).session(session)
//                .param("answer",answer))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(Matchers.containsString(" Answer with answerId 3 edited successfully.")));
//    }
//
//    @Test
//    public void getAllAnswersToQuestionsWithNoAuthentication() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        Answer answer = new Answer();
//        answer.setAns("This is an answer");
//        List<Answer> answers = singletonList(answer);
//        given(answerService.getAllAnswersToQuestion(3)).willReturn(answers);
//        String url = "/api/answer/all/3";
//        mvc.perform(get(url).session(session))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(containsString("Please Login first to access this endpoint!")));
//    }
//
//    @Test
//    public void getAllAnswersToQuestionsWithAuthentication() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        Answer answer = new Answer();
//        answer.setAns("This is an answer");
//        List<Answer> answers = singletonList(answer);
//        given(answerService.getAllAnswersToQuestion(3)).willReturn(answers);
//        String url = "/api/answer/all/3";
//        mvc.perform(get(url).session(session)
//                .contentType(MediaType.asMediaType(APPLICATION_JSON)))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].ans", Matchers.is(answer.getAns())));
//    }
//
//    @Test
//    public void getAllAnswersByUserNoAuthentication() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String url = "/api/answer/all";
//        mvc.perform(get(url).session(session))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(containsString("Please Login first to access this endpoint!")));
//    }
//    @Test
//    public void getAllAnswersByUserWithAuthentication() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        user.setId(3);
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        Answer answer = new Answer();
//        answer.setAns("This is an answer for REST architecture");
//        List<Answer> answers = singletonList(answer);
//        Mockito.when(userService.findUserId(user.getUserName())).thenReturn(3);
//        given(answerService.getAllAnswersByUser(3)).willReturn(answers);
//        String url = "/api/answer/all";
//        mvc.perform(get(url).session(session))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].ans", Matchers.is(answer.getAns())));
//    }
//    @Test
//    public void getAllAnswersByLikesNoAuthentication() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String url = "/api/answer/all/likes/3";
//        mvc.perform(get(url).session(session))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(containsString("Please Login first to access this endpoint!")));
//    }
//
//    @Test
//    public void getAllAnswersByLikesWithoutAuthentication() throws Exception{
//
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String url = "/api/answer/all/likes/3";
//        mvc.perform(get(url).session(session))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("Please Login first to access this endpoint!")));
//    }
//
//
//    @Test
//    public void deleteAnswerWithNoAuthentication() throws Exception {
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String url = "/api/answer/3";
//        mvc.perform(delete(url).session(session))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("Please Login first to access this endpoint!")));
//    }
//    @Test
//    public void deleteAnswerWithNoAuthorization() throws Exception {
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        user.setId(2);
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        Answer answer = new Answer();
//        answer.setUser(user);
//        answer.setId(3);
//        Mockito.when(answerService.findUserIdfromAnswer(3)).thenReturn(3);
//        String url = "/api/answer/3";
//        mvc.perform(delete(url).session(session))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("You do not have rights to delete this answer.")));
//    }
//    @Test
//    public void deleteAnswerWithAuthorization() throws Exception {
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("admin");
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        user.setId(3);
//        Answer answer = new Answer();
//        answer.setUser(user);
//        answer.setId(3);
//        Mockito.when(answerService.findUserIdfromAnswer(3)).thenReturn(3);
//        String url = "/api/answer/3";
//        mvc.perform(delete(url).session(session))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(Matchers.containsString(" Answer with answerId 3 deleted successfully.")));
//    }
//}