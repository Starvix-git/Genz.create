package com.example.generationalcreations;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * LoginActivity handles user authentication for Generational Creations e-memo in Java.
 * Validates real email address formats and password inputs with loading progress events.
 */
public class LoginActivity extends AppCompatActivity {

    private boolean isPasswordVisible = false;

    /**
     * Checks if the provided email string matches a valid email address pattern.
     */
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge presentation
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Apply system bar padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI input fields and buttons
        final EditText etEmail = findViewById(R.id.etEmail);
        final EditText etPassword = findViewById(R.id.etPassword);
        final ImageView btnTogglePassword = findViewById(R.id.btnTogglePassword);
        final FrameLayout btnLogin = findViewById(R.id.btnLogin);
        final ProgressBar progressBarLogin = findViewById(R.id.progressBarLogin);
        final TextView btnForgotPassword = findViewById(R.id.btnForgotPassword);
        final LinearLayout btnGoogle = findViewById(R.id.btnGoogle);
        final LinearLayout btnApple = findViewById(R.id.btnApple);
        final TextView btnSignUp = findViewById(R.id.btnSignUp);

        // Handle Password Visibility Toggle
        if (btnTogglePassword != null) {
            btnTogglePassword.setOnClickListener(v -> {
                isPasswordVisible = !isPasswordVisible;
                if (isPasswordVisible) {
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                etPassword.setSelection(etPassword.getText().length());
            });
        }

        // Handle Login Submit Button Click with Loading Event
        if (btnLogin != null) {
            btnLogin.setOnClickListener(v -> {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Validate Real Email Address
                if (email.isEmpty()) {
                    etEmail.setError("Please enter your email address");
                    etEmail.requestFocus();
                    return;
                }

                if (!isValidEmail(email)) {
                    etEmail.setError("Please enter a valid email address (e.g. name@example.com)");
                    etEmail.requestFocus();
                    return;
                }

                // Validate Password
                if (password.isEmpty()) {
                    etPassword.setError("Please enter your password");
                    etPassword.requestFocus();
                    return;
                }

                // Show Loading Progress Event
                if (progressBarLogin != null) {
                    progressBarLogin.setVisibility(View.VISIBLE);
                }
                btnLogin.setEnabled(false);

                // Simulate authentication loading delay
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }, 1200);
            });
        }

        // Handle Forgot Password link click
        if (btnForgotPassword != null) {
            btnForgotPassword.setOnClickListener(v -> 
                Toast.makeText(LoginActivity.this, "Password recovery coming soon", Toast.LENGTH_SHORT).show()
            );
        }

        // Handle Social Sign-In Buttons with Loading Event
        if (btnGoogle != null) {
            btnGoogle.setOnClickListener(v -> {
                if (progressBarLogin != null) {
                    progressBarLogin.setVisibility(View.VISIBLE);
                }
                Toast.makeText(LoginActivity.this, "Signing in with Google account...", Toast.LENGTH_SHORT).show();
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }, 1000);
            });
        }

        if (btnApple != null) {
            btnApple.setOnClickListener(v -> {
                if (progressBarLogin != null) {
                    progressBarLogin.setVisibility(View.VISIBLE);
                }
                Toast.makeText(LoginActivity.this, "Signing in with Apple ID...", Toast.LENGTH_SHORT).show();
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }, 1000);
            });
        }

        // Handle Sign Up link click
        View.OnClickListener openSignUpListener = v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        };

        if (btnSignUp != null) {
            btnSignUp.setOnClickListener(openSignUpListener);
        }

        LinearLayout containerSignUp = findViewById(R.id.containerSignUp);
        if (containerSignUp != null) {
            containerSignUp.setOnClickListener(openSignUpListener);
        }
    }
}