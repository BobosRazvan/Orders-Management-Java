package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Controllers;

import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.BusinessLogic.ClientBLL;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.BusinessLogic.ProductBLL;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.*;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.OrdersApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;


public class EditClientWindowController {

    @FXML
    private Button backButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField idTextField;

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
        Client client=clientBLL.getClientById(Integer.parseInt(idTextField.getText()));
        client.setName(nameTextField.getText());
        clientBLL.updateClient(client);

        OrdersApplication app = new OrdersApplication();
        try {
            app.changeScene("ClientsWindow.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}