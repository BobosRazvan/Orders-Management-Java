package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Controllers;

import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.BusinessLogic.BillBLL;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.BusinessLogic.ClientBLL;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.BusinessLogic.OrdersBLL;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.BusinessLogic.ProductBLL;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.Bill;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.Client;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.Orders;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.Product;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.OrdersApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
/**
 * Controller class for managing orders window.
 * This class handles user interactions and order processing.
 */
public class OrdersWindowController {

    @FXML
    private Button backButton;
    @FXML
    private Label underStockText;


    @FXML
    private TextField clientIdTextField;

    @FXML
    private Button createOrderButton;

    @FXML
    private TextField productIdTextField;

    @FXML
    private TextField quantityTextField;
    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableView<Client> clientsTable;

    private OrdersBLL ordersBLL = new OrdersBLL();
    private ClientBLL clientBLL = new ClientBLL();

    private final ProductBLL productBLL = new ProductBLL();
    private final BillBLL billBLL = new BillBLL();

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
    void onCreateOrderClicked(MouseEvent event) throws Exception {
        Orders order = new Orders();
        order.setClient_id(Integer.parseInt(clientIdTextField.getText()));
        order.setProduct_id(Integer.parseInt(productIdTextField.getText()));
        order.setQuantity(Integer.parseInt(quantityTextField.getText()));
        order.setTotal_amount(ordersBLL.calculateTotalPrice(order));
        if(ordersBLL.enoughStock(productBLL.getProductById(order.getProduct_id()).getId(), order.getQuantity()) == false){
            underStockText.setText("Under stock");
        }
        else{
            ordersBLL.updateStock(order);
            ordersBLL.createOrder(order);
            int order_id=ordersBLL.getLastOrderId();
            int id=0;
            Bill bill= new Bill(id,order_id, order.getTotal_amount());
            billBLL.addBill(bill);
            OrdersApplication app = new OrdersApplication();
            try {
                app.changeScene("ConfirmOrder.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void initialize() {
        List<Product> products = productBLL.getAllProducts();
        setupProductsTable(productsTable, products);
        List<Client> clients = clientBLL.getAllClients();
        setupClientsTable(clientsTable, clients);
    }

    private void setupProductsTable(TableView<Product> table, List<Product> items) {
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


    private void setupClientsTable(TableView<Client> table, List<Client> items) {
        if (items == null || items.isEmpty()) return;
        table.getColumns().clear();

        Field[] fields = items.get(0).getClass().getDeclaredFields();

        for (Field field : fields) {
            TableColumn<Client, String> column = new TableColumn<>(field.getName());
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            table.getColumns().add(column);
        }
        table.setItems(FXCollections.observableArrayList(items));

    }

}
