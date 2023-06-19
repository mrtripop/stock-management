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

  public static PageRequest initPageable(Integer page, Integer size) {
    try {
      PageRequest pageSize;
      if (page <= 0 && size < 1) {
        pageSize = PageRequest.of(0, 10);
      } else if (page <= 0) {
        pageSize = PageRequest.of(0, size);
      } else {
        pageSize = PageRequest.of(page - 1, size);
      }
      log.debug("PageSize: " + pageSize);
      return pageSize;
    } catch (Exception e) {
      log.error(e.toString());
      throw new RuntimeException("GetPageSizeException", e.getCause());
    }
  }

  public static Pageable initPageableWithSort(Integer page, Integer size, String orderBy) {
    try {
      PageRequest pageRequest = initPageable(page, size);
      log.info(pageRequest.toString());
      return initSortOrder(pageRequest, orderBy);
    } catch (Exception e) {
      log.error(e.toString());
      throw new RuntimeException("GetPageSizeWithSortException", e.getCause());
    }
  }
}
