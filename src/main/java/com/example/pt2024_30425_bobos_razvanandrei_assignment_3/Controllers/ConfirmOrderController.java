package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Controllers;

import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.OrdersApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ConfirmOrderController {

    @FXML
    private Button backButton;

    @FXML
    private Button shoppingButton;

    @FXML
    void onBackClicked(MouseEvent event) {
        OrdersApplication app = new OrdersApplication();
        try {
            app.changeScene("OrdersWindow.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onShoppingPressed(MouseEvent event) {
        OrdersApplication app = new OrdersApplication();
        try {
            app.changeScene("OrdersWindow.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
