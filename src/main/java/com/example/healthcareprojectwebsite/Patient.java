package com.example.healthcareprojectwebsite;



public class Patient {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String medicalHistorySummary;

    public Patient() {} // Empty constructor for Firebase

    public Patient(String firstName, String lastName, String email, String history) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.medicalHistorySummary = history;
    }

    // Getters and Setters are required for Firebase to map data automatically
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMedicalHistorySummary() { return medicalHistorySummary; }
    public void setMedicalHistorySummary(String medicalHistorySummary) { this.medicalHistorySummary = medicalHistorySummary; }
}