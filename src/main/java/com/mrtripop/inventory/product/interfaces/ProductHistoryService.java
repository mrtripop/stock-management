package com.mrtripop.inventory.product.interfaces;

import com.mrtripop.inventory.exception.GlobalThrowable;
import com.mrtripop.inventory.product.models.ProductHistory;
import java.util.List;

public interface ProductHistoryService {

  List<ProductHistory> getAllProductsHistory(Integer page, Integer size, String orderBy)
      throws GlobalThrowable;
}
