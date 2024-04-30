package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Controllers;

import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.OrdersApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.BusinessLogic.*;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsWindowController {

    @FXML
    private Button addProductButton;

    @FXML
    private Button backButton;

    @FXML
    private Button deleteProductButton;

    @FXML
    private Button editProductButton;
    @FXML
    private TableView<Product> table;

    private final ProductBLL productBLL = new ProductBLL();

    @FXML
    void onBackClicked(MouseEvent event) {
        OrdersApplication app = new OrdersApplication();
        try {
            app.changeScene("Menu.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void onAddProductClicked(MouseEvent event) {
        OrdersApplication app = new OrdersApplication();
        try {
            app.changeScene("AddProductWindow.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onDeleteProductClicked(MouseEvent event) {

        OrdersApplication app = new OrdersApplication();
        try {
            app.changeScene("DeleteProductWindow.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void onEditProductClicked(MouseEvent event) {
        OrdersApplication app = new OrdersApplication();
        try {
            app.changeScene("EditProductWindow.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize() {
        List<Product> products = productBLL.getAllProducts();
        setupTable(table, products);
    }

    private void setupTable(TableView<Product> table, List<Product> items) {
        if (items == null || items.isEmpty()) return;
        table.getColumns().clear();

        Field[] fields = items.get(0).getClass().getDeclaredFields();

        for (Field field : fields) {
            TableColumn<Product, String> column = new TableColumn<>(field.getName());
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            table.getColumns().add(column);
        }
        table.setItems(FXCollections.observableArrayList(items));

    }
}
