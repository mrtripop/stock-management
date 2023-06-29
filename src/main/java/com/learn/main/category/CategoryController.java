package com.learn.main.category;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/inventory/category")
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public ResponseEntity<List<Category>> getCategory(
      @RequestParam Integer page, @RequestParam Integer size, @RequestParam String orderBy) {
    try {
      List<Category> categories = categoryService.getCategory(page, size, orderBy);
      return new ResponseEntity<>(categories, HttpStatus.OK);
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      log.error(error);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<Category> getCategoryById(
      @PathVariable(name = "categoryId") Long categoryId) {
    try {
      Category categories = categoryService.getCategoryById(categoryId);
      if (categories != null) {
        return new ResponseEntity<>(categories, HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      log.error(error);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping
  public ResponseEntity<Category> createCategory(
      @RequestBody Category newCategory, @RequestParam Long categoryId) {
    try {
      Category category = categoryService.createNewCategory(newCategory, categoryId);
      return new ResponseEntity<>(category, HttpStatus.OK);
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      log.error(error);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{categoryId}")
  public ResponseEntity<Category> updateCategory(
      @PathVariable(name = "categoryId") Long categoryId, @RequestBody Category category) {
    try {
      Category result = categoryService.updateCategory(categoryId, category);
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      log.error(error);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{categoryId}")
  public ResponseEntity<?> deleteCategory(@PathVariable(name = "categoryId") Long categoryId) {
    try {
      boolean result = categoryService.deleteCategory(categoryId);
      if (result) {
        return new ResponseEntity<>(HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      log.error(error);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
