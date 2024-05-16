package com.mrtripop.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Slf4j
public class DatabaseHelper {
  private DatabaseHelper() {}

  public static PageRequest initSortOrder(PageRequest pageRequest, Sort.Direction orderBy) {
    return pageRequest.withSort(orderBy, "id");
  }

  public static PageRequest initPageable(Integer page, Integer size) {
    // page is start by 0 but page number should start from 1
    return PageRequest.of(page - 1, size);
  }

  public static Pageable initPageableWithSort(Integer page, Integer size, Sort.Direction orderBy) {
    PageRequest pageRequest = initPageable(page, size);
    return initSortOrder(pageRequest, orderBy);
  }
}
