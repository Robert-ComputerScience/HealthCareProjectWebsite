module com.example.healthcareprojectwebsite {
    // Standard JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    // Firebase and Google Cloud
    requires firebase.admin;
    requires google.cloud.firestore;
    requires com.google.auth;
    requires com.google.api.apicommon;
    requires google.cloud.core;
    requires com.google.auth.oauth2;

    // Open the package to JavaFX for FXML and Firebase for Reflection
    opens com.example.healthcareprojectwebsite to javafx.fxml, google.cloud.firestore;
    exports com.example.healthcareprojectwebsite;
}