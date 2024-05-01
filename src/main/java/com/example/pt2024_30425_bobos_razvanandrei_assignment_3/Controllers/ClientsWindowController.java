package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Controllers;


import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.OrdersApplication;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.BusinessLogic.*;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.*;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.DataAccess.*;



import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
/**
 * Controller class for managing clients window.
 * This class handles user interactions and displays clients data.
 */
public class ClientsWindowController {

    @FXML
    private Button addClientButton;

    @FXML
    private Button backButton;

    @FXML
    private Button deleteClientButton;

    @FXML
    private Button editClientButton;

    @FXML
    private TableView<Client> table;


    private final ClientBLL clientBLL = new ClientBLL();


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
    void onAddClientClicked(MouseEvent event) {
        OrdersApplication app = new OrdersApplication();
        try {
            app.changeScene("AddClientWindow.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    @FXML
    void onDeleteClientClicked(MouseEvent event) {
            OrdersApplication app = new OrdersApplication();
            try {
                app.changeScene("DeleteClientWindow.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    @FXML
    void onEditClientClicked(MouseEvent event) {
        OrdersApplication app = new OrdersApplication();
        try {
            app.changeScene("EditClientWindow.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize() {
        List<Client> clients = clientBLL.getAllClients();
        setupTable(table, clients);
    }

    private void setupTable(TableView<Client> table, List<Client> items) {
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

