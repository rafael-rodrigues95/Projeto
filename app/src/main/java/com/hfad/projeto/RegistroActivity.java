package com.hfad.projeto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mViewHolder.et_username = findViewById(R.id.et_registro_username);
        mViewHolder.et_password = findViewById(R.id.et_registro_password);
        mViewHolder.et_password2 = findViewById(R.id.et_registro_password2);
        mViewHolder.bt_registrar = findViewById(R.id.bt_registro_registrar);

        db = new DBHelper(this);

        mViewHolder.bt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mViewHolder.et_username.getText().toString().trim();
                String password = mViewHolder.et_password.getText().toString().trim();
                String password2 = mViewHolder.et_password2.getText().toString().trim();

                if(username.isEmpty())
                    Toast.makeText(RegistroActivity.this, "Insira o username.", Toast.LENGTH_SHORT).show();
                else if (password.isEmpty() || password2.isEmpty())
                    Toast.makeText(RegistroActivity.this, "Insira e repita a senha.", Toast.LENGTH_SHORT).show();
                else if (!password.equals(password2))
                    Toast.makeText(RegistroActivity.this, "Password não coincidem.", Toast.LENGTH_SHORT).show();
                else {
                    // BD
                    long res = db.Utilizador_Insert(username, password);
                    if (true){
                        Toast.makeText(RegistroActivity.this, "Usuário registrado com sucesso.", Toast.LENGTH_SHORT).show();
                        Intent i = getIntent();
                        i.putExtra("username", username);
                        setResult(1, i);
                        finish();
                    }else {
                        Toast.makeText(RegistroActivity.this, "Erro ao registrar o usuário.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private class ViewHolder {
        EditText et_username, et_password, et_password2;
        Button bt_registrar;
    }
}