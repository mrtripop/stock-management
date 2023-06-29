package com.learn.main.category;

import com.learn.helper.DatabaseHelper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<Category> getCategory(Integer page, Integer size, String orderBy) {
    try {
      Pageable pageSize = DatabaseHelper.initPageableWithSort(page, size, orderBy);
      Page<Category> result = categoryRepository.findAll(pageSize);
      return result.getContent();
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

  public Category updateCategory(Long categoryId, Category category) {
    try {
      Optional<Category> origin = categoryRepository.findById(categoryId);
      if (origin.isPresent()) {
        Category temp = origin.get();
        temp.setTitle(category.getTitle());
        temp.setMetaTitle(category.getMetaTitle());
        temp.setSlug(category.getSlug());
        temp.setContent(category.getContent());
        return categoryRepository.save(temp);
      } else {
        return categoryRepository.save(category);
      }
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      throw new RuntimeException(error);
    }
  }

  public boolean deleteCategory(Long categoryId) {
    try {
      Optional<Category> category = categoryRepository.findById(categoryId);
      if (category.isPresent()) {
        categoryRepository.deleteById(categoryId);
        return true;
      }
      return false;
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      throw new RuntimeException(error);
    }
  }
}
