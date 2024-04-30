package com.example.pt2024_30425_bobos_razvanandrei_assignment_3;

import com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class OrdersApplication extends Application {

    private static Stage stg;

    @Override
    public void start(Stage stage) throws IOException {
        this.stg=stage;

        FXMLLoader fxmlLoader = new FXMLLoader(OrdersApplication.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 652, 489);
        stage.setTitle("Orders Management Application");
        stage.setScene(scene);
        Image image = new Image("file:///C:/Users/danie/Desktop/Razvi/Altele/orderManagement.png");
        stage.getIcons().add(image);
        stage.show();

    }

    public void changeScene(String fxml) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent pane = loader.load();
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch();
    }
}