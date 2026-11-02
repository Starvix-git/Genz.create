package com.example.generationalcreations;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * FoldersActivity displays organized e-memo folders and category cards.
 */
public class FoldersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge drawing
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_folders);

        // Apply window insets for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Bottom Navigation Tab Handlers
        LinearLayout tabHome = findViewById(R.id.tabHome);
        if (tabHome != null) {
            tabHome.setOnClickListener(v -> {
                Intent intent = new Intent(FoldersActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            });
        }

        LinearLayout tabMemos = findViewById(R.id.tabMemos);
        if (tabMemos != null) {
            tabMemos.setOnClickListener(v -> {
                Intent intent = new Intent(FoldersActivity.this, MemosActivity.class);
                startActivity(intent);
                finish();
            });
        }

        LinearLayout tabProfile = findViewById(R.id.tabProfile);
        if (tabProfile != null) {
            tabProfile.setOnClickListener(v -> {
                Intent intent = new Intent(FoldersActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            });
        }

        // Center Floating Action Button (FAB)
        FrameLayout fabNewMemo = findViewById(R.id.fabNewMemo);
        if (fabNewMemo != null) {
            fabNewMemo.setOnClickListener(v -> {
                Intent intent = new Intent(FoldersActivity.this, CreateMemoActivity.class);
                startActivity(intent);
            });
        }
    }
}