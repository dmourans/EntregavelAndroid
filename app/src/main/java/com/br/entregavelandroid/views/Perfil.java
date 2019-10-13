package com.br.entregavelandroid.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.br.entregavelandroid.R;
import com.br.entregavelandroid.models.Usuario;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.br.entregavelandroid.views.Login.NEW_USER;

public class Perfil extends AppCompatActivity {

    private TextInputLayout inputNome;
    private TextInputLayout inputEmail;
    private TextInputLayout inputSenha;
    private TextInputLayout inputConfirmarSenha;
    private Button btnSalvar;

    private String nome, email, senha, confirmarSenha;
    private static final String EMAIL_VALIDATOR = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_VALIDATOR);
    private Matcher matcher;

    public void initViews() {
        inputNome = findViewById(R.id.input_profile_nome);
        inputEmail = findViewById(R.id.input_profile_email);
        inputSenha = findViewById(R.id.input_profile_senha);
        inputConfirmarSenha = findViewById(R.id.input_profile_confirmar);
        btnSalvar = findViewById(R.id.button_profile_salvar);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public boolean validatarEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatarSenha(String senha) {
        return senha.length() >= 6;
    }

    public void validateInputs() {
        nome = inputNome.getEditText().getText().toString();
        email = inputEmail.getEditText().getText().toString().trim();
        senha = inputSenha.getEditText().getText().toString().trim();
        confirmarSenha = inputConfirmarSenha.getEditText().getText().toString().trim();
        boolean eValido = true;

        if (nome.isEmpty()) {
            inputNome.setError(getString(R.string.nome_vazio));
            eValido = false;
        } else {
            inputNome.setErrorEnabled(false);
            eValido = true;
        }

        if (email.isEmpty() || !validatarEmail(email)) {
            inputEmail.setError(getString(R.string.email_nao_valid));
            eValido = false;
        } else {
            inputEmail.setErrorEnabled(false);
            eValido = true;
        }

        if (!validatarSenha(senha)) {
            inputSenha.setError(getString(R.string.senha6Digit));
            eValido = false;
        } else {
            inputSenha.setErrorEnabled(false);
            eValido = true;
        }

        if (!validatarSenha(confirmarSenha)) {
            inputConfirmarSenha.setError(getString(R.string.confirSenha6Digit));
            eValido = false;
        } else {
            inputConfirmarSenha.setErrorEnabled(false);
            eValido = true;
        }

        if (eValido) {
            if (senha.equals(confirmarSenha)) {
                startActivity(new Intent(Perfil.this, MainActivity.class));
            } else {
                inputSenha.setError(getString(R.string.senha_diferente));
                inputConfirmarSenha.setError(getString(R.string.senha_diferente));

                inputNome.setErrorEnabled(false);
                inputEmail.setErrorEnabled(false);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        initViews();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (getIntent() != null && getIntent().getExtras() != null) {
            Usuario usuario = getIntent().getExtras().getParcelable(NEW_USER);

            inputNome.getEditText().setText(usuario.getNome());
            inputEmail.getEditText().setText(usuario.getEmail());
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInputs();
            }
        });

    }
}
