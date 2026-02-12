package com.example.healthcareprojectwebsite; // Keep your existing package name

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HealthCareProjectWebsiteRun extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // CHANGE THIS LINE to load the new FXML file name
        FXMLLoader fxmlLoader = new FXMLLoader(HealthCareProjectWebsiteRun.class.getResource("dashboard-view.fxml"));

        // Set the window size (Width, Height)
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);

        stage.setTitle("Healthcare Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}