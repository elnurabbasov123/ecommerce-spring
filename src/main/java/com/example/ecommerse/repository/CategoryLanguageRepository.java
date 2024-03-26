package com.example.ecommerse.repository;

import com.example.ecommerse.model.entity.Category;
import com.example.ecommerse.model.entity.CategoryLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.nio.file.LinkOption;
import java.util.List;

public interface CategoryLanguageRepository extends JpaRepository<CategoryLanguage, Long> {
    @Query("select cl from category_languages cl join categories c on cl.category = c " +
            "join languages l on cl.language = l where l.value =:language and c in :categories")
    List<CategoryLanguage> findAll(@Param("categories") List<Category> categories, @Param("language") String language);


    List<CategoryLanguage> findAllByLanguage_Value(String language);
}
