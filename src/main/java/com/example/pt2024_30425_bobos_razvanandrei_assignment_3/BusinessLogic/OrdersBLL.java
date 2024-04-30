package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.BusinessLogic;

import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.DataAccess.*;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.*;

import java.math.BigDecimal;
import java.util.List;

public class OrdersBLL {

    private OrdersDAO orderDAO;
    private ProductBLL productBLL = new ProductBLL();

    public OrdersBLL() {
        orderDAO = new OrdersDAO();
    }

    public List<Orders> getAllOrders() {
        return orderDAO.findAll();
    }

    public Orders getOrderById(int id) {
        Orders order = orderDAO.findById(id);
        if (order == null) {
            throw new IllegalStateException("Order with ID " + id + " not found.");
        }
        return order;
    }

    public void createOrder(Orders order) {
        validateOrder(order);
        orderDAO.insert(order);

    }

    public void updateOrder(Orders order) {
        validateOrder(order);
        orderDAO.update(order);
    }

    public void deleteOrder(int id) {
        orderDAO.delete(id);
    }

    private void validateOrder(Orders order) {

        if (order.getQuantity() <= 0) {
            throw new IllegalArgumentException("Order quantity must be greater than zero.");
        }

    }
    public boolean enoughStock(int productId, int requestedQuantity) throws Exception {
        Product product = productBLL.getProductById(productId);
        if (product.getStock() < requestedQuantity) {
           return false;
        }
        else return true;
    }


    public BigDecimal calculateTotalPrice(Orders order) {
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findById(order.getProduct_id());
        return product.getPrice().multiply(java.math.BigDecimal.valueOf(order.getQuantity()));
    }

    public void updateStock(Orders order) {
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findById(order.getProduct_id());
        product.setStock(product.getStock() - order.getQuantity());
        productDAO.update(product);
    }

    public int getLastOrderId() {
        return orderDAO.findLatestId();
    }
}
