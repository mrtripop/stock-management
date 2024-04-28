package com.mrtripop.inventory.product.services;

import com.mrtripop.inventory.exception.GlobalThrowable;
import com.mrtripop.inventory.product.constant.ErrorCode;
import com.mrtripop.inventory.product.interfaces.ProductHistoryService;
import com.mrtripop.inventory.product.models.ProductHistory;
import com.mrtripop.inventory.product.repository.ProductHistoryRepository;
import com.mrtripop.inventory.util.DatabaseHelper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductHistoryServiceImpl implements ProductHistoryService {

  private final ProductHistoryRepository productHistoryRepository;

  public ProductHistoryServiceImpl(ProductHistoryRepository productHistoryRepository) {
    this.productHistoryRepository = productHistoryRepository;
  }

  @Override
  public List<ProductHistory> getAllProductsHistory(Integer page, Integer size, String orderBy)
      throws GlobalThrowable {
    try {
      Pageable pageSize = DatabaseHelper.initPageableWithSort(page, size, orderBy);
      log.debug("Pageable object: {}", pageSize);
      Page<ProductHistory> productHistories = productHistoryRepository.findAll(pageSize);
      log.info(
          "Total element: '{}', and total page: '{}'",
          productHistories.getTotalElements(),
          productHistories.getTotalPages());
      return productHistories.getContent();
    } catch (Exception e) {
      log.error("Cannot get all products: {}", e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.PRO1001_CANNOT_GET_ALL_PRODUCTS, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
