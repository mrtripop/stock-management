package com.learn.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Slf4j
public class DatabaseHelper {
  public static PageRequest initSortOrder(PageRequest pageRequest, String orderBy) {
    try {
      if (orderBy.equals("desc")) {
        log.debug("order: " + orderBy);
        return pageRequest.withSort(Sort.Direction.DESC, "id");
      } else {
        log.debug("order: " + orderBy);
        return pageRequest.withSort(Sort.Direction.ASC, "id");
      }
    } catch (Exception e) {
      log.error("InitSortOrderException");
      throw new RuntimeException("InitSortOrderException", e.getCause());
    }
  }

  public static Pageable initPageable(Integer page, Integer size, String orderBy) {
    try {
      Pageable pageSize;
      if (page < 0 && size < 1) {
        pageSize = initSortOrder(PageRequest.of(0, 10), orderBy);
      } else if (page < 0) {
        pageSize = initSortOrder(PageRequest.of(0, size), orderBy);
      } else {
        pageSize = initSortOrder(PageRequest.of(page - 1, size), orderBy);
      }
      log.debug("PageSize: " + pageSize.toString());
      return pageSize;
    } catch (Exception e) {
      log.error(e.toString());
      throw new RuntimeException("GetPageSizeException", e.getCause());
    }
  }
}
