package com.learn.main.category;

import com.learn.main.address.Address;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/inventory/categories")
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public ResponseEntity<List<Category>> getCategory() {
    try {
      List<Category> categories = categoryService.getCategory();
      return new ResponseEntity<>(categories, HttpStatus.OK);
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      log.error(error);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<Category> retrieveCategoryById(
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
      @RequestBody Category newCategory,
      @RequestParam(name = "categoryId", required = false) Long categoryId) {
    try {
      Category category = categoryService.createNewCategory(newCategory, categoryId);
      return new ResponseEntity<>(category, HttpStatus.OK);
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      log.error(error);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
