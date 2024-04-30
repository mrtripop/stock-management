package com.mrtripop.inventory.product.component;

import com.mrtripop.inventory.product.models.Product;
import com.mrtripop.inventory.product.models.ProductDTO;
import com.mrtripop.inventory.product.models.ProductHistory;

public class ProductProcessor {

  private ProductProcessor() {}

  public static ProductHistory toProductHistory(ProductDTO productDTO) {
    Product product = mapToProduct(productDTO);
    return toProductHistory(product);
  }

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

  public static Product mapToProduct(ProductDTO productDTO) {
    return Product.builder()
        .id(productDTO.getId())
        .code(productDTO.getCode())
        .barcode(productDTO.getBarcode())
        .name(productDTO.getName())
        .description(productDTO.getDescription())
        .category(productDTO.getCategory())
        .reorderQuantity(productDTO.getReorderQuantity())
        .packedWeight(productDTO.getPackedWeight())
        .packedHeight(productDTO.getPackedHeight())
        .packedWidth(productDTO.getPackedWidth())
        .packedDepth(productDTO.getPackedDepth())
        .isActive(productDTO.getIsActive())
        .build();
  }

  public static ProductDTO mapToProductDTO(Product product) {
    return ProductDTO.builder()
        .id(product.getId())
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

  public static ProductDTO mapToProductDTO(ProductHistory productHistory) {
    return ProductDTO.builder()
        .id(productHistory.getId())
        .code(productHistory.getCode())
        .barcode(productHistory.getBarcode())
        .name(productHistory.getName())
        .description(productHistory.getDescription())
        .category(productHistory.getCategory())
        .reorderQuantity(productHistory.getReorderQuantity())
        .packedWeight(productHistory.getPackedWeight())
        .packedHeight(productHistory.getPackedHeight())
        .packedWidth(productHistory.getPackedWidth())
        .packedDepth(productHistory.getPackedDepth())
        .isActive(productHistory.getIsActive())
        .build();
  }
}
