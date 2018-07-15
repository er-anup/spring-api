package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.upgrad.models.Category;
import org.upgrad.repositories.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void updateCategory(String title, String description) {
        categoryRepository.updateCategory(title,description);
    }

/* Iterating through Category model to return all Categories.  This is open for any user.
    * Using CrudRespository class.  So don't need any changes to CategoryRepository*/
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
