package com.learn.main.category;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<Category> getCategory() {
    try {
      return categoryRepository.findAll();
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      throw new RuntimeException(error);
    }
  }

  public Category getCategoryById(Long categoryId) {
    try {
      Optional<Category> category = categoryRepository.findById(categoryId);
      return category.orElse(null);
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      throw new RuntimeException(error);
    }
  }

  public Category createNewCategory(Category newCategory, Long categoryId) {
    try {
      if (categoryId != null) {
        Optional<Category> existCategory = categoryRepository.findById(categoryId);
        existCategory.ifPresent(newCategory::setParent);
      } else {
        newCategory.setParent(newCategory);
      }
      return categoryRepository.save(newCategory);
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      throw new RuntimeException(error);
    }
  }
}
