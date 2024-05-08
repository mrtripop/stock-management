package com.mrtripop.inventory.product.services;

import com.mrtripop.inventory.exception.GlobalThrowable;
import com.mrtripop.inventory.product.component.ProductProcessor;
import com.mrtripop.inventory.product.interfaces.DatabaseManagementService;
import com.mrtripop.inventory.product.interfaces.ProductHistoryService;
import com.mrtripop.inventory.product.models.ProductDTO;
import com.mrtripop.inventory.product.models.ProductHistory;
import com.mrtripop.inventory.product.repository.ProductHistoryRepository;
import com.mrtripop.inventory.product.util.ProductUtil;
import com.mrtripop.inventory.util.DatabaseHelper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductHistoryServiceImpl implements ProductHistoryService {

  private final ProductHistoryService databaseManager;
  private final ProductHistoryRepository productHistoryRepository;

  public ProductHistoryServiceImpl(
      DatabaseManagementService databaseManager,
      ProductHistoryRepository productHistoryRepository) {
    this.databaseManager = databaseManager;
    this.productHistoryRepository = productHistoryRepository;
  }

  @Override
  public List<ProductDTO> getAllProductsHistory(Integer page, Integer size, String orderBy)
      throws GlobalThrowable {
    return databaseManager.getAllProductsHistory(page, size, orderBy);
  }

  public List<ProductDTO> getProductHistoriesByCode(
      String code, Integer page, Integer size, String orderBy) {
    // create a specification for query column
    Specification<ProductHistory> specification = ProductUtil.productsHaveCode(code);
    // create a sort and pagination
    Pageable pageable = DatabaseHelper.initPageableWithSort(page, size, orderBy);
    // query data from table
    Page<ProductHistory> productHistories =
        productHistoryRepository.findAll(specification, pageable);
    // get content from pagination
    return productHistories.getContent().stream().map(ProductProcessor::mapToProductDTO).toList();
  }
}
