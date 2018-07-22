package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.upgrad.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="INSERT INTO CATEGORY VALUES (DEFAULT,?1,?2)")
    void updateCategory(String title,String description);
}
