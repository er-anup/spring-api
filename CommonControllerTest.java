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
//import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(CommonController.class)
//public class CommonControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    protected MockHttpSession session;
//
//    @MockBean
//    private QuestionService questionService;
//
//    @MockBean
//    private CategoryService categoryService;
//
//    @Test
//    public void allCategories() throws Exception{
//        Category category = new Category();
//        category.setTitle("Music");
//        category.setDescription("This is the genre of Music");
//        category.setId(1);
//        List<Category> allCategories = singletonList(category);
//        given(categoryService.getAllCategories()).willReturn(allCategories);
//        String url = "/api/categories/all";
//        mvc.perform(get(url)
//                .contentType(MediaType.asMediaType(APPLICATION_JSON)))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].title", Matchers.is(category.getTitle())));
//    }
//
//    @Test
//    public void allQuestions() throws Exception{
//        Question question = new Question();
//        question.setContent("What is the question?");
//        List<Question> allQuestions = singletonList(question);
//        given(questionService.getAllQuestions()).willReturn(allQuestions);
//        String url = "/api/questions/all";
//        mvc.perform(get(url)
//                .contentType(MediaType.asMediaType(APPLICATION_JSON)))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].content", Matchers.is(question.getContent())));
//    }
//
//}
