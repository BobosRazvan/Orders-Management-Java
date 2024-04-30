package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.BusinessLogic;

import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.DataAccess.BillDAO;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.Bill;

import java.math.BigDecimal;
import java.util.List;

public class BillBLL {

    private final BillDAO billDAO;
    public BillBLL() {
        this.billDAO = new BillDAO();
    }

    public void addBill(Bill bill) {
        billDAO.addBill(bill);
    }
    private void validateBill(Bill bill) {
        if (bill.amount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Bill amount cannot be negative.");
        }
        if (bill.order_id() <= 0) {
            throw new IllegalArgumentException("Bill must be associated with a valid order.");
        }

    }
    public List<Bill> getAllBills() {
        return billDAO.getAllBills();
    }
}
