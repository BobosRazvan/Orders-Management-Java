package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model;

import java.math.BigDecimal;
/**
 * Represents an order entity.
 */
public class Orders {

    int id;
    int client_id;
    int product_id;
    int quantity;
    BigDecimal total_amount;

    public Orders(int id, int client_id, int product_id, int quantity, BigDecimal total_amount) {
        this.id = id;
        this.client_id = client_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.total_amount = total_amount;
    }
    public Orders() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }

    public int getId() {
        return id;
    }

    public int getClient_id() {
        return client_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotal_amount() {
        return total_amount;
    }
}
