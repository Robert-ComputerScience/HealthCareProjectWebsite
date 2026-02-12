package com.example.healthcareprojectwebsite;



import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;
import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseService {

    private static Firestore db;

    // Initialize Firebase Connection
    public static void initialize() {
        try {
            // You must download your 'serviceAccountKey.json' from Firebase Console -> Project Settings -> Service Accounts
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
            db = FirestoreClient.getFirestore();
            System.out.println("Firebase Connected Successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Firestore getFirestore() {
        return db;
    }
}