package com.example.generationalcreations;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
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
 * SignUpActivity handles new user account registration for Generational Creations e-memo in Java.
 * Enforces real email format validation, strong password requirements, progress bar feedback, and navigates to the main dashboard.
 */
public class SignUpActivity extends AppCompatActivity {

    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    /**
     * Checks if the provided email string matches a valid email address pattern.
     */
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Enforces strong password rules: at least 8 characters long, containing both letters and numbers.
     */
    private boolean isStrongPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasLetter = false;
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }
        return hasLetter && hasDigit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge drawing
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        // Apply window insets for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Form Views
        final EditText etSignUpName = findViewById(R.id.etSignUpName);
        final EditText etSignUpEmail = findViewById(R.id.etSignUpEmail);
        final EditText etSignUpPassword = findViewById(R.id.etSignUpPassword);
        final EditText etConfirmPassword = findViewById(R.id.etConfirmPassword);
        final ImageView btnTogglePassword = findViewById(R.id.btnTogglePassword);
        final ImageView btnToggleConfirmPassword = findViewById(R.id.btnToggleConfirmPassword);
        final CheckBox chkTermsAgreement = findViewById(R.id.chkTermsAgreement);
        final FrameLayout btnCreateAccount = findViewById(R.id.btnCreateAccount);
        final ProgressBar progressBarSignUp = findViewById(R.id.progressBarSignUp);
        final LinearLayout btnGoogle = findViewById(R.id.btnGoogle);
        final LinearLayout btnApple = findViewById(R.id.btnApple);
        final TextView btnSignIn = findViewById(R.id.btnSignIn);

        // Password Visibility Toggle
        if (btnTogglePassword != null) {
            btnTogglePassword.setOnClickListener(v -> {
                isPasswordVisible = !isPasswordVisible;
                if (isPasswordVisible) {
                    etSignUpPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    etSignUpPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                etSignUpPassword.setSelection(etSignUpPassword.getText().length());
            });
        }

        // Confirm Password Visibility Toggle
        if (btnToggleConfirmPassword != null) {
            btnToggleConfirmPassword.setOnClickListener(v -> {
                isConfirmPasswordVisible = !isConfirmPasswordVisible;
                if (isConfirmPasswordVisible) {
                    etConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    etConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                etConfirmPassword.setSelection(etConfirmPassword.getText().length());
            });
        }

        // Create Account Submit Button
        if (btnCreateAccount != null) {
            btnCreateAccount.setOnClickListener(v -> {
                String name = etSignUpName.getText().toString().trim();
                String email = etSignUpEmail.getText().toString().trim();
                String password = etSignUpPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();

                // Validate Full Name
                if (name.isEmpty()) {
                    etSignUpName.setError("Please enter your name");
                    etSignUpName.requestFocus();
                    return;
                }

                // Validate Email Address
                if (email.isEmpty()) {
                    etSignUpEmail.setError("Please enter your email address");
                    etSignUpEmail.requestFocus();
                    return;
                }

                if (!isValidEmail(email)) {
                    etSignUpEmail.setError("Please enter a valid email address (e.g. name@example.com)");
                    etSignUpEmail.requestFocus();
                    return;
                }

                // Validate Password
                if (password.isEmpty()) {
                    etSignUpPassword.setError("Please enter a password");
                    etSignUpPassword.requestFocus();
                    return;
                }

                if (!isStrongPassword(password)) {
                    etSignUpPassword.setError("Password must be at least 8 characters and include letters & numbers");
                    etSignUpPassword.requestFocus();
                    return;
                }

                // Validate Password Matching
                if (!password.equals(confirmPassword)) {
                    etConfirmPassword.setError("Passwords do not match");
                    etConfirmPassword.requestFocus();
                    return;
                }

                // Validate Terms Agreement
                if (chkTermsAgreement != null && !chkTermsAgreement.isChecked()) {
                    Toast.makeText(SignUpActivity.this, "Please agree to the Terms & Conditions", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Show progress bar loading feedback
                if (progressBarSignUp != null) {
                    progressBarSignUp.setVisibility(View.VISIBLE);
                }
                btnCreateAccount.setEnabled(false);

                // Simulate registration delay before launching dashboard
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    Toast.makeText(SignUpActivity.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finishAffinity(); // Clear activity stack
                }, 1200);
            });
        }

        // Social Sign In Handlers
        if (btnGoogle != null) {
            btnGoogle.setOnClickListener(v -> {
                if (progressBarSignUp != null) {
                    progressBarSignUp.setVisibility(View.VISIBLE);
                }
                Toast.makeText(SignUpActivity.this, "Signing up with Google account...", Toast.LENGTH_SHORT).show();
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finishAffinity();
                }, 1000);
            });
        }

        if (btnApple != null) {
            btnApple.setOnClickListener(v -> {
                if (progressBarSignUp != null) {
                    progressBarSignUp.setVisibility(View.VISIBLE);
                }
                Toast.makeText(SignUpActivity.this, "Signing up with Apple ID...", Toast.LENGTH_SHORT).show();
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finishAffinity();
                }, 1000);
            });
        }

        // Already have an account -> Return to LoginActivity
        View.OnClickListener openSignInListener = v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        };

        if (btnSignIn != null) {
            btnSignIn.setOnClickListener(openSignInListener);
        }

        LinearLayout containerSignIn = findViewById(R.id.containerSignIn);
        if (containerSignIn != null) {
            containerSignIn.setOnClickListener(openSignInListener);
        }
    }
}