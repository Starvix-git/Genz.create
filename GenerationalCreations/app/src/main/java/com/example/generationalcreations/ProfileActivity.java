package com.example.generationalcreations;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * ProfileActivity displays user account details, memo statistics,
 * account preferences (including dark mode toggle), data settings, and navigation in Java.
 * Dynamically reloads saved user profile info.
 */
public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge presentation
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        // Apply system bar padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Dark Mode Switch
        SwitchCompat switchDarkMode = findViewById(R.id.switchDarkMode);
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (switchDarkMode != null) {
            switchDarkMode.setChecked(currentNightMode == Configuration.UI_MODE_NIGHT_YES);
            switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            });
        }

        // Initialize Profile Option Buttons
        ImageView btnSettings = findViewById(R.id.btnSettings);
        if (btnSettings != null) {
            btnSettings.setOnClickListener(v -> 
                Toast.makeText(ProfileActivity.this, "Settings clicked", Toast.LENGTH_SHORT).show()
            );
        }

        LinearLayout btnEditProfile = findViewById(R.id.btnEditProfile);
        if (btnEditProfile != null) {
            btnEditProfile.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            });
        }

        LinearLayout btnPersonalInfo = findViewById(R.id.btnPersonalInfo);
        if (btnPersonalInfo != null) {
            btnPersonalInfo.setOnClickListener(v -> 
                Toast.makeText(ProfileActivity.this, "Personal info clicked", Toast.LENGTH_SHORT).show()
            );
        }

        LinearLayout btnChangePassword = findViewById(R.id.btnChangePassword);
        if (btnChangePassword != null) {
            btnChangePassword.setOnClickListener(v -> 
                Toast.makeText(ProfileActivity.this, "Change password clicked", Toast.LENGTH_SHORT).show()
            );
        }

        LinearLayout btnReminders = findViewById(R.id.btnReminders);
        if (btnReminders != null) {
            btnReminders.setOnClickListener(v -> 
                Toast.makeText(ProfileActivity.this, "Reminders settings clicked", Toast.LENGTH_SHORT).show()
            );
        }

        LinearLayout btnBackup = findViewById(R.id.btnBackup);
        if (btnBackup != null) {
            btnBackup.setOnClickListener(v -> 
                Toast.makeText(ProfileActivity.this, "Backup & Restore clicked", Toast.LENGTH_SHORT).show()
            );
        }

        LinearLayout btnExport = findViewById(R.id.btnExport);
        if (btnExport != null) {
            btnExport.setOnClickListener(v -> 
                Toast.makeText(ProfileActivity.this, "Export Memos clicked", Toast.LENGTH_SHORT).show()
            );
        }

        LinearLayout btnLogOut = findViewById(R.id.btnLogOut);
        if (btnLogOut != null) {
            btnLogOut.setOnClickListener(v -> {
                Toast.makeText(ProfileActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finishAffinity();
            });
        }

        // Bottom Navigation Tab Handlers
        LinearLayout tabHome = findViewById(R.id.tabHome);
        if (tabHome != null) {
            tabHome.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            });
        }

        LinearLayout tabMemos = findViewById(R.id.tabMemos);
        if (tabMemos != null) {
            tabMemos.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, MemosActivity.class);
                startActivity(intent);
                finish();
            });
        }

        LinearLayout tabFolders = findViewById(R.id.tabFolders);
        if (tabFolders != null) {
            tabFolders.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, FoldersActivity.class);
                startActivity(intent);
                finish();
            });
        }

        FrameLayout fabNewMemo = findViewById(R.id.fabNewMemo);
        if (fabNewMemo != null) {
            fabNewMemo.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, CreateMemoActivity.class);
                startActivity(intent);
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUserProfile();
    }

    /**
     * Reloads saved user profile values from SharedPreferences and updates UI text views.
     */
    private void loadUserProfile() {
        SharedPreferences prefs = getSharedPreferences("UserProfilePrefs", MODE_PRIVATE);
        String name = prefs.getString("profile_name", "Jordan");
        String role = prefs.getString("profile_role", "Graphics designer");
        String email = prefs.getString("profile_email", "jordanahimbisibwe160@gmail.com");
        String bio = prefs.getString("profile_bio", "Imagine, Create, Inspire");

        TextView txtProfileName = findViewById(R.id.txtProfileName);
        TextView txtProfileRole = findViewById(R.id.txtProfileRole);
        TextView txtProfileEmail = findViewById(R.id.txtProfileEmail);
        TextView txtProfileBio = findViewById(R.id.txtProfileBio);

        if (txtProfileName != null) txtProfileName.setText(name);
        if (txtProfileRole != null) txtProfileRole.setText(role);
        if (txtProfileEmail != null) txtProfileEmail.setText(email);
        if (txtProfileBio != null) txtProfileBio.setText(bio);
    }
}