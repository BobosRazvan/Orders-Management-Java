package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.DataAccess;

import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Connection.ConnectionFactory;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Data Access Object (DAO) for managing Bill entities in the database.
 */
public class BillDAO {

      private Connection connection;

        public BillDAO() {
            this.connection = ConnectionFactory.getConnection();
        }

        public void addBill(Bill bill) {
            try {
                String query = "INSERT INTO log (order_id, amount) VALUES (?, ?)";
                var statement = connection.prepareStatement(query);
                statement.setInt(1, bill.order_id());
                statement.setBigDecimal(2, bill.amount());
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ///la asta am cam renuntat intre timp
        public List<Bill> getAllBills() {
            List<Bill> bills = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement("SELECT id, order_id, amount FROM log");
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int orderId = resultSet.getInt("order_id");
                    var amount = resultSet.getBigDecimal("amount");
                    bills.add(new Bill(id, orderId, amount));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return bills;
        }





}
