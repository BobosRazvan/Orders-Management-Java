package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Controllers;

import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.BusinessLogic.BillBLL;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.BusinessLogic.ClientBLL;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.BusinessLogic.ProductBLL;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.Bill;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.Client;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.Product;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.OrdersApplication;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

public class LogTableWindowController {

    @FXML
    private Button backButton;
    @FXML
    private TableView<Bill> table;
    private final BillBLL billBLL = new BillBLL();

    @FXML
    private TableColumn<Bill, Integer> idColumn;
    @FXML
    private TableColumn<Bill, Integer> orderIdColumn;
    @FXML
    private TableColumn<Bill, BigDecimal> amountColumn;

    public void initialize() {

        idColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().id()));
        orderIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().order_id()));
        amountColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().amount()));

        loadBills();
    }

    public void loadBills() {
        List<Bill> bills = billBLL.getAllBills();
        table.setItems(FXCollections.observableArrayList(bills));
    }

    @FXML
    void onBackClicked(MouseEvent event) {
        OrdersApplication app = new OrdersApplication();
        try {
            app.changeScene("Menu.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}