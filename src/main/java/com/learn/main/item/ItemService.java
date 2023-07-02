package com.learn.main.item;

import com.learn.helper.DatabaseHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ItemService {

  private final ItemRepository itemRepository;

  public ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public List<Item> getItems(Integer page, Integer size, String orderBy) {
    try {
      Pageable pageSize = DatabaseHelper.initPageableWithSort(page, size, orderBy);
      Page<Item> result = itemRepository.findAll(pageSize);
      return result.getContent();
    } catch (Exception e) {
      String error = ExceptionUtils.getStackTrace(e);
      log.error(error);
      throw new RuntimeException("GetItemsException", e.getCause());
    }
  }
}
