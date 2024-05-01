package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.DataAccess;

import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.Client;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.Orders;
/**
 * Data Access Object (DAO) for managing Orders entities in the database.
 */
public class OrdersDAO extends AbstractDAO<Orders> {

    public OrdersDAO() {
        super(Orders.class);
    }



}
