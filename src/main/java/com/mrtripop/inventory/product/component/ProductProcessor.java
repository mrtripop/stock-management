package com.mrtripop.inventory.product.component;

import com.mrtripop.inventory.exception.GlobalThrowable;
import com.mrtripop.inventory.product.constant.ErrorCode;
import com.mrtripop.inventory.product.models.db.Product;
import com.mrtripop.inventory.product.models.ProductDTO;
import com.mrtripop.inventory.product.models.db.ProductHistory;
import com.mrtripop.inventory.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class ProductProcessor {

  private ProductProcessor() {}

  public static ProductHistory toProductHistory(ProductDTO productDTO) throws GlobalThrowable {
    Product product = mapToProduct(productDTO);
    return toProductHistory(product);
  }

  public static ProductHistory toProductHistory(Product product) throws GlobalThrowable {
    try {
      String productHistory = Utils.writeString(product);
      return Utils.readValue(productHistory, ProductHistory.class);
    } catch (Exception e) {
      log.error("Cannot convert the product to product history: {}", e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.PRO1008_CANNOT_CONVERT_PRODUCT_TO_PRODUCT_HISTORY,
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public static void updateProduct(ProductDTO oldProduct, ProductDTO newProduct) {
    oldProduct.setCode(newProduct.getCode());
    oldProduct.setBarcode(newProduct.getBarcode());
    oldProduct.setName(newProduct.getName());
    oldProduct.setDescription(newProduct.getDescription());
    oldProduct.setCategory(newProduct.getCategory());
    oldProduct.setReorderQuantity(newProduct.getReorderQuantity());
    oldProduct.setPackedWeight(newProduct.getPackedWeight());
    oldProduct.setPackedHeight(newProduct.getPackedHeight());
    oldProduct.setPackedWidth(newProduct.getPackedWidth());
    oldProduct.setPackedDepth(newProduct.getPackedDepth());
    oldProduct.setIsActive(newProduct.getIsActive());
  }

  public static Product mapToProduct(Object productDTO) throws GlobalThrowable {
    try {
      String product = Utils.writeString(productDTO);
      return Utils.readValue(product, Product.class);
    } catch (Exception e) {
      log.error("Cannot convert the product DTO to product object: {}", e.getMessage());
      throw new GlobalThrowable(
          ErrorCode.PRO1009_CANNOT_CONVERT_PRODUCT_TO_PRODUCT_HISTORY,
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public static ProductDTO mapToProductDTO(Object product) {
    try {
      String publicString = Utils.writeString(product);
      return Utils.readValue(publicString, ProductDTO.class);
    } catch (Exception e) {
      log.error("Cannot convert the product object to product DTO: {}", e.getMessage());
      throw new RuntimeException(
          ErrorCode.PRO1010_CANNOT_CONVERT_PRODUCT_OBJECT_TO_PRODUCT_DTO.getMessage());
    }
  }
}
