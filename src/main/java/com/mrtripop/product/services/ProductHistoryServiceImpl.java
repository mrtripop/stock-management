package com.mrtripop.product.services;

import com.mrtripop.exception.GlobalThrowable;
import com.mrtripop.model.QueryParams;
import com.mrtripop.product.component.ProductProcessor;
import com.mrtripop.product.constant.ErrorCode;
import com.mrtripop.product.models.ProductDTO;
import com.mrtripop.product.models.db.ProductHistory;
import com.mrtripop.product.repository.ProductHistoryRepository;
import com.mrtripop.product.util.ProductUtil;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductHistoryServiceImpl {

  private final ProductHistoryRepository productHistoryRepository;

  public ProductHistoryServiceImpl(ProductHistoryRepository productHistoryRepository) {
    this.productHistoryRepository = productHistoryRepository;
  }

  public List<ProductDTO> getProductsHistories(QueryParams queryParams) throws GlobalThrowable {
    try {
      Pageable pageable = ProductUtil.initPageableWithSort(queryParams);
      Page<ProductHistory> productHistoriesPages = productHistoryRepository.findAll(pageable);
      return productHistoriesPages.getContent().stream()
          .map(ProductProcessor::mapToProductDTO)
          .toList();
    } catch (Exception e) {
      log.error("Cannot get product histories: {}", e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.PRO1007_CANNOT_GET_ALL_PRODUCT_HISTORIES, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public List<ProductDTO> getProductHistoriesByCode(String code, QueryParams queryParams)
      throws GlobalThrowable {
    try {
      // create a specification for query column
      // create a sort and pagination
      Specification<ProductHistory> specification = ProductUtil.productsHaveCode(code);
      Pageable pageable = ProductUtil.initPageableWithSort(queryParams);
      Page<ProductHistory> productHistoriesPages =
          productHistoryRepository.findAll(specification, pageable);
      return productHistoriesPages.getContent().stream()
          .map(ProductProcessor::mapToProductDTO)
          .toList();
    } catch (Exception e) {
      log.error("Cannot get product histories by code: {}", e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.PRO1011_CANNOT_GET_PRODUCT_HISTORIES_BY_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
