package com.mrtripop.inventory.product.services;

import com.mrtripop.inventory.exception.GlobalThrowable;
import com.mrtripop.inventory.product.interfaces.DatabaseManagementService;
import com.mrtripop.inventory.product.interfaces.ProductHistoryService;
import com.mrtripop.inventory.product.models.ProductDTO;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductHistoryServiceImpl implements ProductHistoryService {

  private final ProductHistoryService databaseManager;

  public ProductHistoryServiceImpl(DatabaseManagementService databaseManager) {
    this.databaseManager = databaseManager;
  }

  @Override
  public List<ProductDTO> getAllProductsHistory(Integer page, Integer size, String orderBy)
      throws GlobalThrowable {
    return databaseManager.getAllProductsHistory(page, size, orderBy);
  }
}
