package com.example.healthcareprojectwebsite;

/**
 * Model class representing a Patient.
 * Designed for JavaFX TableView and Firebase Firestore integration.
 */
public class Patient {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String medicalHistorySummary;

    // 1. MUST HAVE: Empty constructor for Firebase to reconstruct objects from the cloud
    public Patient() {}

    // 2. Main Constructor: Used by your DashboardController to create new patients
    public Patient(String id, String firstName, String lastName, String email, String history) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.medicalHistorySummary = history;
    }

    // 3. GETTERS & SETTERS: Required for JavaFX PropertyValueFactory and Firebase Mapping

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMedicalHistorySummary() { return medicalHistorySummary; }
    public void setMedicalHistorySummary(String medicalHistorySummary) { this.medicalHistorySummary = medicalHistorySummary; }
}