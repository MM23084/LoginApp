package com.example.loginapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrarActivity extends AppCompatActivity {

    EditText etNombre, etEmail, etPass, etConfirmarPass;
    Button btnGuardar, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        setTitle("Nuevo usuario");

        etNombre        = findViewById(R.id.etNombre);
        etEmail         = findViewById(R.id.etEmail);
        etPass          = findViewById(R.id.etPass);
        etConfirmarPass = findViewById(R.id.etConfirmarPass);
        btnGuardar      = findViewById(R.id.btnGuardar);
        btnRegresar     = findViewById(R.id.btnRegresar);

        btnGuardar.setOnClickListener(v -> guardarUsuario());
        btnRegresar.setOnClickListener(v -> finish());
    }

    private void guardarUsuario() {
        String nombre    = etNombre.getText().toString().trim();
        String email     = etEmail.getText().toString().trim();
        String pass      = etPass.getText().toString().trim();
        String confirmar = etConfirmarPass.getText().toString().trim();

        if (nombre.length() < 3) {
            etNombre.setError("El nombre debe tener al menos 3 caracteres");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Formato de email inválido");
            return;
        }
        if (pass.length() < 5 || !pass.matches(".*[a-zA-Z].*") || !pass.matches(".*[0-9].*")) {
            etPass.setError("Password debe tener mínimo 5 caracteres alfanuméricos");
            return;
        }
        if (!pass.equals(confirmar)) {
            etConfirmarPass.setError("Los passwords no coinciden");
            return;
        }

        SharedPreferences prefs = getSharedPreferences("usuarios", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("nombre_usuario", nombre);
        editor.putString("email", email);
        editor.putString("password", pass);
        editor.apply();

        Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_LONG).show();

        etNombre.setText("");
        etEmail.setText("");
        etPass.setText("");
        etConfirmarPass.setText("");
    }
}