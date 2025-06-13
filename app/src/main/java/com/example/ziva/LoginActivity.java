package com.example.ziva;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Splash Screen : Afficher le logo pendant 2 secondes avant l'écran de connexion
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Afficher le contenu principal après le délai
                setTheme(R.style.Theme_CosmeticApp); // Appliquer le thème principal
                setContentView(R.layout.activity_login);

                // Configuration de l'écran de connexion
                setupLoginScreen();
            }
        }, 2000); // Délai de 2 secondes (2000 millisecondes)
    }

    // Méthode pour configurer les champs de l'écran de connexion
    private void setupLoginScreen() {
        EditText email = findViewById(R.id.etEmail);
        EditText password = findViewById(R.id.etPassword);
        Button loginButton = findViewById(R.id.btnLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("user@example.com") &&
                        password.getText().toString().equals("1234")) {

                    Intent intent = new Intent(LoginActivity.this, com.example.ziva.MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
