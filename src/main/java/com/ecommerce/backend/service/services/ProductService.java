package com.ecommerce.backend.service.services;

import com.ecommerce.backend.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProducts();
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
    List<ProductDTO> getProductsByCategory(Long categoryId);
    String uploadProductImage(Long productId, MultipartFile file);
    List<ProductDTO> searchByName(String name);
    List<ProductDTO> searchByCategory(String category);
}
