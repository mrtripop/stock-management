package com.mrtripop.product.interfaces;

import com.mrtripop.exception.GlobalThrowable;
import com.mrtripop.product.models.ProductDTO;
import java.util.List;

public interface ProductHistoryService {

  List<ProductDTO> getAllProductsHistory(Integer page, Integer size, String orderBy)
      throws GlobalThrowable;
}
