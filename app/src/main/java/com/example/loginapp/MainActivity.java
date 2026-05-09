package com.example.loginapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etUsuario, etPassword;
    Button btnIngresar, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login");

        etUsuario  = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnSalir    = findViewById(R.id.btnSalir);

        btnIngresar.setOnClickListener(v -> validarLogin());

        btnSalir.setOnClickListener(v -> {
            finishAffinity();
            System.exit(0);
        });
    }

    private void validarLogin() {
        String usuario  = etUsuario.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        SharedPreferences prefs = getSharedPreferences("usuarios", MODE_PRIVATE);
        String usuarioGuardado  = prefs.getString("nombre_usuario", null);
        String passwordGuardado = prefs.getString("password", null);

        if (usuarioGuardado == null || passwordGuardado == null) {
            Toast.makeText(this, "Error de usuario y clave invadidos", Toast.LENGTH_LONG).show();
            return;
        }

        if (usuario.equals(usuarioGuardado) && password.equals(passwordGuardado)) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Error de usuario y clave invadidos", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_registrar) {
            startActivity(new Intent(this, RegistrarActivity.class));
            return true;
        } else if (id == R.id.menu_salir) {
            finishAffinity();
            System.exit(0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}