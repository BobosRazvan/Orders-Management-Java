package com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Controllers;

import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.BusinessLogic.ClientBLL;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Connection.ConnectionFactory;

import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.DataAccess.*;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model.*;
import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.OrdersApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class MenuController {
    @FXML
    private Button manageClientsButton;

    @FXML
    private Button manageProductsButton;

    @FXML
    private Button processOrdersButton;


    @FXML
    void onManageClientsClicked(MouseEvent event) {
        OrdersApplication app = new OrdersApplication();
        try {
            app.changeScene("ClientsWindow.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void onManageProductsClicked(MouseEvent event) {
        OrdersApplication app = new OrdersApplication();
        try {
            app.changeScene("ProductsWindow.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onProcessOrdersClicked(MouseEvent event) {
        OrdersApplication app = new OrdersApplication();
        try {
            app.changeScene("OrdersWindow.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}