package com.example.healthcareprojectwebsite;



import com.example.healthcare.models.Patient;
import com.example.healthcare.services.FirebaseService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DashboardController {

    @FXML private TextField fNameField;
    @FXML private TextField lNameField;
    @FXML private TextField emailField;
    @FXML private TableView<Patient> patientTable;
    @FXML private TableColumn<Patient, String> colFirstName;
    @FXML private TableColumn<Patient, String> colLastName;
    @FXML private TableColumn<Patient, String> colEmail;

    @FXML
    public void initialize() {
        // Initialize columns
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Initialize Firebase Connection (Do this in main app usually, but here for demo)
        FirebaseService.initialize();
    }

    @FXML
    protected void handleAddPatient() {
        String fName = fNameField.getText();
        String lName = lNameField.getText();
        String email = emailField.getText();

        if (fName.isEmpty() || lName.isEmpty() || email.isEmpty()) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        // 1. Update UI immediately (Optimistic UI update)
        Patient newPatient = new Patient(fName, lName, email, "No history");
        patientTable.getItems().add(newPatient);

        // 2. Send to Firebase in Background
        addDataToFirebase(newPatient);

        // 3. Clear fields
        fNameField.clear();
        lNameField.clear();
        emailField.clear();
    }

    private void addDataToFirebase(Patient patient) {
        // Create a Map for Firestore
        Map<String, Object> data = new HashMap<>();
        data.put("firstName", patient.getFirstName());
        data.put("lastName", patient.getLastName());
        data.put("email", patient.getEmail());
        data.put("medicalHistory", "New Patient Entry");

        // Generate a random ID for the document
        String docId = UUID.randomUUID().toString();

        // Async write to Firestore "patients" collection
        ApiFuture<WriteResult> result = FirebaseService.getFirestore().collection("patients").document(docId).set(data);

        System.out.println("Patient sent to database. Update time: " + result.toString());
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}