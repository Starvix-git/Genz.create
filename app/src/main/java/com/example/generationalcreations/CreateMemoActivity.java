package com.example.generationalcreations;

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
 * CreateMemoActivity presents a form to type and save a new e-memo.
 */
public class CreateMemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge drawing
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_memo);

        // Apply window insets for system bar padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI Elements
        ImageView btnBack = findViewById(R.id.btnBack);
        FrameLayout btnSaveMemo = findViewById(R.id.btnSaveMemo);
        final EditText etMemoTitle = findViewById(R.id.etMemoTitle);
        final EditText etMemoBody = findViewById(R.id.etMemoBody);

        // Back Navigation
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // Save Memo Action
        if (btnSaveMemo != null) {
            btnSaveMemo.setOnClickListener(v -> {
                String title = etMemoTitle != null ? etMemoTitle.getText().toString().trim() : "";
                String body = etMemoBody != null ? etMemoBody.getText().toString().trim() : "";

                if (title.isEmpty()) {
                    if (etMemoTitle != null) {
                        etMemoTitle.setError("Please enter a memo title");
                        etMemoTitle.requestFocus();
                    }
                    return;
                }

                if (body.isEmpty()) {
                    if (etMemoBody != null) {
                        etMemoBody.setError("Please enter memo content");
                        etMemoBody.requestFocus();
                    }
                    return;
                }

                Toast.makeText(CreateMemoActivity.this, "e-memo saved successfully!", Toast.LENGTH_SHORT).show();
                finish();
            });
        }
    }
}