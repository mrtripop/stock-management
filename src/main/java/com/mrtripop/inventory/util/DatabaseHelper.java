package com.mrtripop.inventory.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Slf4j
public class DatabaseHelper {
  private DatabaseHelper() {}

  public static PageRequest initSortOrder(PageRequest pageRequest, String orderBy) {
    if (orderBy.equals("desc")) {
      return pageRequest.withSort(Sort.Direction.DESC, "id");
    } else {
      return pageRequest.withSort(Sort.Direction.ASC, "id");
    }
  }

  public static PageRequest initPageable(Integer page, Integer size) {
    PageRequest pageSize;
    if (page <= 0 && size < 1) {
      pageSize = PageRequest.of(0, 10);
    } else if (page <= 0) {
      pageSize = PageRequest.of(0, size);
    } else {
      pageSize = PageRequest.of(page - 1, size);
    }
    return pageSize;
  }

  public static Pageable initPageableWithSort(Integer page, Integer size, String orderBy) {
    PageRequest pageRequest = initPageable(page, size);
    return initSortOrder(pageRequest, orderBy);
  }
}
