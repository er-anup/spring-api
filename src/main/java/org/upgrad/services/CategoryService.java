package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Category;
import java.util.List;

@Service
public interface CategoryService {
    void updateCategory(String title,String description);
    Iterable<Category> getAllCategories();
}
