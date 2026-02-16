package com.example.healthcareprojectwebsite;

import com.example.healthcareprojectwebsite.Patient;
import com.example.healthcareprojectwebsite.FirebaseService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        FirebaseService.initialize();
    }

    @FXML
    protected void handleAddPatient() {
        String fName = fNameField.getText();
        String lName = lNameField.getText();
        String email = emailField.getText();

        if (fName.trim().isEmpty() || lName.trim().isEmpty() || email.trim().isEmpty()) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        // 1. Generate the ID first (The 5th argument)
        String docId = UUID.randomUUID().toString();

        // 2. Pass all 5 arguments to match the Patient class constructor
        Patient newPatient = new Patient(docId, fName, lName, email, "No history");

        // Update UI
        patientTable.getItems().add(newPatient);

        // 3. Send to Firebase
        addDataToFirebase(newPatient);

        // 3. Clear fields
        fNameField.clear();
        lNameField.clear();
        emailField.clear();
    }

    private void addDataToFirebase(Patient patient) {
        // Use the ID already stored in the patient object
        ApiFuture<WriteResult> result = FirebaseService.getFirestore()
                .collection("patients")
                .document(patient.getId())
                .set(patient);

        System.out.println("Patient " + patient.getFirstName() + " pushed to Firestore.");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}