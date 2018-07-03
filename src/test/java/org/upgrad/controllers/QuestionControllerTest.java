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
//import static java.util.Collections.singletonList;
//import static org.hamcrest.CoreMatchers.containsString;
//import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.mock;
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//@RunWith(SpringRunner.class)
//@WebMvcTest(QuestionController.class)
//public class QuestionControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    protected MockHttpSession session;
//
//    @MockBean
//    private QuestionService questionService;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private FollowService followService;
//
//    @Test
//    public void addQuestionWithNoAuthentication() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String question = "what is the question";
//        String categories = "1,2";
//        String url = "/api/question";
//        mvc.perform(post(url).session(session)
//                .param("categoryId",categories)
//                .param("question",question))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(containsString("Please Login first to access this endpoint!")));
//    }
//
//    @Test
//    public void addQuestionWithAuthentication() throws Exception {
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        session = new MockHttpSession();
//        session.setAttribute("currUser",user);
//        String question = "What is REST architecture?";
//        String categories = "1,2";
//        String url = "/api/question";
//        mvc.perform(post(url).session(session)
//                .param("categoryId",categories)
//                .param("question",question))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(containsString("Question added successfully.")));
//    }
//
//    @Test
//    public void AllQuestionsByCategoryWthNoAuthentication() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String categoryId = "3";
//        String url = "/api/question/all/3";
//        mvc.perform(get(url).session(session)
//                .param("categoryId",categoryId))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(containsString("Please Login first to access this endpoint!")));
//    }
//
//    @Test
//    public void getAllQuestionsByUserNoAuthentication() throws Exception{
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String url = "/api/question/all";
//        mvc.perform(get(url).session(session))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(containsString("Please Login first to access this endpoint!")));
//    }
//    @Test
//    public void getAllQuestionsByUserWithAuthentication() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        Question question = new Question();
//        question.setContent("What is REST architecture?");
//        question.setUser(user);
//        List<Question> questions = singletonList(question);
//        user.setId(3);
//        given(questionService.getAllQuestionsByUser(3)).willReturn(questions);
//        String url = "/api/question/all";
//        mvc.perform(get(url).session(session))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].content", Matchers.is(question.getContent())));
//    }
//    @Test
//    public void AllQuestionsByCategoryWthAuthentication() throws Exception{
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        Question question = new Question();
//        question.setContent("what is the question?");
//        question.setUser(user);
//        Category category = new Category();
//        category.setId(3);
//        category.setTitle("Music");
//        category.setDescription("This is the genre of Music");
//        Set<Category> categories =new HashSet<>();
//        categories.add(category);
//        String categoryId = "3";
//        question.setCategories(categories);
//        List<Question> questions = singletonList(question);
//        given(questionService.getAllQuestionsByCategory(3)).willReturn(questions);
//        String url = "/api/question/all/3";
//        mvc.perform(get(url).session(session)
//                .param("categoryId",categoryId)
//                .contentType(MediaType.asMediaType(APPLICATION_JSON)))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].content", Matchers.is(question.getContent())));
//    }
//    @Test
//    public void deleteQuestionWithNoAuthentication() throws Exception {
//        session = new MockHttpSession();
//        session.setAttribute("currUser", null);
//        String questionId = "3";
//        String url = "/api/question/3";
//        mvc.perform(delete(url).session(session)
//                .param("questionId", questionId))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("Please Login first to access this endpoint!")));
//    }
//    @Test
//    public void deleteQuestionWithNoAuthorization() throws Exception {
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        String questionId = "3";
//        user.setId(4);
//        Mockito.when(questionService.findUserIdfromQuestion(3)).thenReturn(3);
//        String url = "/api/question/3";
//        mvc.perform(delete(url).session(session)
//                .param("questionId", questionId))
//                .andExpect(status().is4xxClientError())
//                .andExpect(content().string(Matchers.containsString("You do not have rights to delete this question!")));
//    }
//
//    @Test
//    public void deleteQuestionWithAdminAuthorization() throws Exception {
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("admin");
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        String questionId = "3";
//        user.setId(4);
//        Mockito.when(questionService.findUserIdfromQuestion(3)).thenReturn(3);
//        String url = "/api/question/3";
//        mvc.perform(delete(url).session(session)
//                .param("questionId", questionId))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(Matchers.containsString("Question with questionId 3 deleted successfully.")));
//    }
//
//    @Test
//    public void deleteQuestionWithAuthorization() throws Exception {
//        User user = new User();
//        user.setUserName("upgrad");
//        user.setRole("user");
//        user.setId(3);
//        session = new MockHttpSession();
//        session.setAttribute("currUser", user);
//        Question question =new Question();
//        question.setUser(user);
//        question.setId(3);
//        String questionId = "3";
//        Mockito.when(questionService.findUserIdfromQuestion(question.getId())).thenReturn(user.getId());
//        String url = "/api/question/3";
//        mvc.perform(delete(url).session(session)
//                .param("questionId", questionId))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(Matchers.containsString("Question with questionId 3 deleted successfully.")));
//    }
//}