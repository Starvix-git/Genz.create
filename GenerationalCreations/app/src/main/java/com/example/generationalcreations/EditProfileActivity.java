package com.example.generationalcreations;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * EditProfileActivity handles editing user profile details such as
 * name, role, email address, bio, and profile avatar photo in Java.
 * Persists updated values to SharedPreferences.
 */
public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge drawing
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);

        // Apply window insets for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI Elements
        ImageView btnBack = findViewById(R.id.btnBack);
        ImageView btnChangeAvatarPhoto = findViewById(R.id.btnChangeAvatarPhoto);
        final EditText etFullName = findViewById(R.id.etFullName);
        final EditText etRole = findViewById(R.id.etRole);
        final EditText etEditEmail = findViewById(R.id.etEditEmail);
        final EditText etBio = findViewById(R.id.etBio);
        FrameLayout btnSaveProfile = findViewById(R.id.btnSaveProfile);

        // Load existing saved profile values
        SharedPreferences prefs = getSharedPreferences("UserProfilePrefs", MODE_PRIVATE);
        if (etFullName != null) {
            etFullName.setText(prefs.getString("profile_name", "Jordan"));
        }
        if (etRole != null) {
            etRole.setText(prefs.getString("profile_role", "Graphics designer"));
        }
        if (etEditEmail != null) {
            etEditEmail.setText(prefs.getString("profile_email", "jordanahimbisibwe160@gmail.com"));
        }
        if (etBio != null) {
            etBio.setText(prefs.getString("profile_bio", "Imagine, Create, Inspire"));
        }

        // Back Navigation Handler
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // Change Avatar Photo Handler
        if (btnChangeAvatarPhoto != null) {
            btnChangeAvatarPhoto.setOnClickListener(v -> 
                Toast.makeText(EditProfileActivity.this, "Select profile photo coming soon", Toast.LENGTH_SHORT).show()
            );
        }

        // Save Changes Handler
        if (btnSaveProfile != null) {
            btnSaveProfile.setOnClickListener(v -> {
                String name = etFullName != null ? etFullName.getText().toString().trim() : "";
                String role = etRole != null ? etRole.getText().toString().trim() : "";
                String email = etEditEmail != null ? etEditEmail.getText().toString().trim() : "";
                String bio = etBio != null ? etBio.getText().toString().trim() : "";

                if (name.isEmpty()) {
                    if (etFullName != null) {
                        etFullName.setError("Please enter your name");
                        etFullName.requestFocus();
                    }
                    return;
                }

                if (email.isEmpty()) {
                    if (etEditEmail != null) {
                        etEditEmail.setError("Please enter your email");
                        etEditEmail.requestFocus();
                    }
                    return;
                }

                // Save profile details to SharedPreferences
                prefs.edit()
                        .putString("profile_name", name)
                        .putString("profile_role", role)
                        .putString("profile_email", email)
                        .putString("profile_bio", bio)
                        .apply();

                Toast.makeText(EditProfileActivity.this, "Profile saved successfully!", Toast.LENGTH_SHORT).show();
                finish();
            });
        }
    }
}