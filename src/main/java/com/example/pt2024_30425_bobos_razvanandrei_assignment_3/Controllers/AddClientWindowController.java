package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Controllers;

import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.BusinessLogic.ClientBLL;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.BusinessLogic.ProductBLL;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.Client;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.Product;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.OrdersApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.math.BigDecimal;

public class AddClientWindowController {

    @FXML
    private Button backButton;

    @FXML
    private TextField nameTextField;


    @FXML
    private Button submitButton;
    private final ClientBLL clientBLL = new ClientBLL();

    @FXML
    void onBackClicked(MouseEvent event) {
        OrdersApplication app = new OrdersApplication();
        try {
            app.changeScene("ClientsWindow.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void onSubmitClicked(MouseEvent event) {
        Client client = new Client();
        client.setName(nameTextField.getText());
        clientBLL.addClient(client);
        OrdersApplication app = new OrdersApplication();
        try {
            app.changeScene("ClientsWindow.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}