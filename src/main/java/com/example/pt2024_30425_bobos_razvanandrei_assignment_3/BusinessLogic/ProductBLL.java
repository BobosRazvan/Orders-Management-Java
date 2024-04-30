package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.BusinessLogic;

import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.DataAccess.ProductDAO;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductBLL {

    private ProductDAO productDAO;

    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    public Product getProductById(int id) {
        Product product = productDAO.findById(id);
        if (product == null) {
            throw new IllegalStateException("Product with ID " + id + " not found.");
        }
        return product;
    }

    public void addProduct(Product product) {
        validateProduct(product);
        productDAO.insert(product);
    }

    public void updateProduct(Product product) {
        validateProduct(product);
        productDAO.update(product);
    }

    public void deleteProduct(int id) {
        productDAO.delete(id);
    }

    private void validateProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }

        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Product price cannot be negative.");
        }

        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Product stock cannot be negative.");
        }
    }
}
