package com.example.generationalcreations;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * MainActivity serves as the main dashboard for Generational Creations e-memo in Java.
 * Displays user greeting, search field, hero banner, pinned memos, recent items, and bottom navigation.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge drawing
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Apply system bar window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Brand Logo Click
        ImageView imgLogo = findViewById(R.id.imgLogo);
        if (imgLogo != null) {
            imgLogo.setOnClickListener(v -> 
                Toast.makeText(MainActivity.this, "Generational Creations e-memo", Toast.LENGTH_SHORT).show()
            );
        }

        // Initialize Header Actions
        ImageView btnNotifications = findViewById(R.id.btnNotifications);
        if (btnNotifications != null) {
            btnNotifications.setOnClickListener(v -> 
                Toast.makeText(MainActivity.this, "Notifications (2 unread)", Toast.LENGTH_SHORT).show()
            );
        }

        ImageView imgAvatar = findViewById(R.id.imgAvatar);
        if (imgAvatar != null) {
            imgAvatar.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            });
        }

        TextView txtUserName = findViewById(R.id.txtUserName);
        if (txtUserName != null) {
            txtUserName.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            });
        }

        // Initialize Filter Button
        FrameLayout btnFilter = findViewById(R.id.btnFilter);
        if (btnFilter != null) {
            btnFilter.setOnClickListener(v -> 
                Toast.makeText(MainActivity.this, "Filtering e-memos by date & category", Toast.LENGTH_SHORT).show()
            );
        }

        // Initialize Hero Banner "New memo" button -> CreateMemoActivity
        LinearLayout btnNewMemoBanner = findViewById(R.id.btnNewMemoBanner);
        if (btnNewMemoBanner != null) {
            btnNewMemoBanner.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, CreateMemoActivity.class);
                startActivity(intent);
            });
        }

        // Pinned Card Click Listeners
        TextView txtPin1Title = findViewById(R.id.txtPin1Title);
        if (txtPin1Title != null) {
            txtPin1Title.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, CreateMemoActivity.class);
                startActivity(intent);
            });
        }

        TextView txtPin2Title = findViewById(R.id.txtPin2Title);
        if (txtPin2Title != null) {
            txtPin2Title.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, CreateMemoActivity.class);
                startActivity(intent);
            });
        }

        // Initialize View All link -> MemosActivity
        TextView btnViewAll = findViewById(R.id.btnViewAll);
        if (btnViewAll != null) {
            btnViewAll.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, MemosActivity.class);
                startActivity(intent);
            });
        }

        // Initialize Center Floating Action Button (FAB) -> CreateMemoActivity
        FrameLayout fabNewMemo = findViewById(R.id.fabNewMemo);
        if (fabNewMemo != null) {
            fabNewMemo.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, CreateMemoActivity.class);
                startActivity(intent);
            });
        }

        // Bottom Navigation Tab Handlers
        LinearLayout tabMemos = findViewById(R.id.tabMemos);
        if (tabMemos != null) {
            tabMemos.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, MemosActivity.class);
                startActivity(intent);
            });
        }

        LinearLayout tabFolders = findViewById(R.id.tabFolders);
        if (tabFolders != null) {
            tabFolders.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, FoldersActivity.class);
                startActivity(intent);
            });
        }

        LinearLayout tabProfile = findViewById(R.id.tabProfile);
        if (tabProfile != null) {
            tabProfile.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Load saved user profile name for dashboard greeting
        SharedPreferences prefs = getSharedPreferences("UserProfilePrefs", MODE_PRIVATE);
        String name = prefs.getString("profile_name", "Jordan");
        TextView txtUserName = findViewById(R.id.txtUserName);
        if (txtUserName != null) {
            txtUserName.setText(name);
        }
    }
}