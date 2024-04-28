package com.mrtripop.inventory.product.component;

import com.mrtripop.inventory.product.models.Product;
import com.mrtripop.inventory.product.models.ProductHistory;

public class ProductProcessor {

  private ProductProcessor() {}

  public static ProductHistory toProductHistory(Product product) {
    return ProductHistory.builder()
        .code(product.getCode())
        .barcode(product.getBarcode())
        .name(product.getName())
        .description(product.getDescription())
        .category(product.getCategory())
        .reorderQuantity(product.getReorderQuantity())
        .packedWeight(product.getPackedWeight())
        .packedHeight(product.getPackedHeight())
        .packedWidth(product.getPackedWidth())
        .packedDepth(product.getPackedDepth())
        .isActive(product.getIsActive())
        .build();
  }

  public static void updateProduct(Product oldProduct, Product newProduct) {
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
}
