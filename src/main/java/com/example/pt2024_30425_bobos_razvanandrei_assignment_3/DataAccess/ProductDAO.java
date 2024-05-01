package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.DataAccess;

import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.Product;
/**
 * Data Access Object (DAO) for managing Product entities in the database.
 */
public class ProductDAO extends AbstractDAO<Product>{

    public ProductDAO() {
        super(Product.class);
    }
}
