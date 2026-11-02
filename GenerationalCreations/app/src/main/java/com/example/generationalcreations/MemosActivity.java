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
 * MemosActivity displays the full list of saved e-memos with search and filter actions.
 */
public class MemosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge drawing
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_memos);

        // Apply window insets for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Filter button
        FrameLayout btnFilterMemos = findViewById(R.id.btnFilterMemos);
        if (btnFilterMemos != null) {
            btnFilterMemos.setOnClickListener(v -> 
                Toast.makeText(MemosActivity.this, "Filter e-memos", Toast.LENGTH_SHORT).show()
            );
        }

        // Bottom Navigation Tab Handlers
        LinearLayout tabHome = findViewById(R.id.tabHome);
        if (tabHome != null) {
            tabHome.setOnClickListener(v -> {
                Intent intent = new Intent(MemosActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            });
        }

        LinearLayout tabFolders = findViewById(R.id.tabFolders);
        if (tabFolders != null) {
            tabFolders.setOnClickListener(v -> {
                Intent intent = new Intent(MemosActivity.this, FoldersActivity.class);
                startActivity(intent);
                finish();
            });
        }

        LinearLayout tabProfile = findViewById(R.id.tabProfile);
        if (tabProfile != null) {
            tabProfile.setOnClickListener(v -> {
                Intent intent = new Intent(MemosActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            });
        }

        // Center Floating Action Button (FAB)
        FrameLayout fabNewMemo = findViewById(R.id.fabNewMemo);
        if (fabNewMemo != null) {
            fabNewMemo.setOnClickListener(v -> {
                Intent intent = new Intent(MemosActivity.this, CreateMemoActivity.class);
                startActivity(intent);
            });
        }
    }
}