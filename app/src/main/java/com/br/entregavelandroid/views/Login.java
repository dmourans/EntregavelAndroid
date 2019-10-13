package com.br.entregavelandroid.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.br.entregavelandroid.R;
import com.br.entregavelandroid.models.Usuario;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    private TextInputLayout inputEmail;
    private TextInputLayout inputPass;
    private Button btnLogin;
    private Button btnRegistrar;
    private String nome;
    private String email;
    private String pass;

    private static final String EMAIL_VALIDATOR = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    public static final String NEW_USER = "usuario";
    private Pattern pattern = Pattern.compile(EMAIL_VALIDATOR);
    private Matcher matcher;

    //Valida o e-mail digitado
    public boolean validarEmail(String email){
        matcher = pattern.matcher(email);
        return matcher.matches();

    }

    //Valida uma senha basica 6 caracteres
    public boolean validarPass (String pass){
        return pass.length() >= 6;
    }

    //Inicia as variaveis de tela
    public void initViews(){
        inputEmail = findViewById(R.id.input_login_email);
        inputPass = findViewById(R.id.input_login_password);
        btnLogin = findViewById(R.id.button_login_login);
        btnRegistrar = findViewById(R.id.button_login_registrar);
    }

    //Inteção para nova activity
    public void bundleToActivity(Usuario usuario){
        Intent intent = new Intent( Login.this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(NEW_USER, usuario);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //Valida os dados inseridos nos campos
    public void validarInsercoes(){
        email = inputEmail.getEditText().getText().toString().trim();
        pass = inputPass.getEditText().getText().toString().trim();

        if (!validarEmail(email) && !validarPass(pass)){
            inputEmail.setError("Insira um e-mail valido");
            inputPass.setError("Sua senha precisa ao menos 6 caracteres");

        } else if (!validarPass(pass)) {
            inputEmail.setErrorEnabled(false);
            inputPass.setError("Sua senha precisa ao menos 6 caracteres");
        } else if (!validarEmail(email)) {
            inputPass.setErrorEnabled(false);
            inputEmail.setError("Insira um e-mail valido");
        }
        else {
            inputPass.setErrorEnabled(false);
            inputEmail.setErrorEnabled(false);

            Usuario usuario = new Usuario(nome, email, pass);
            bundleToActivity(usuario);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        if (getIntent() != null && getIntent().getExtras() != null){
            Usuario usuario = getIntent().getExtras().getParcelable(NEW_USER);

            inputEmail.getEditText().setText(usuario.getEmail());
            inputPass.getEditText().setText(usuario.getSenha());
            nome = usuario.getNome();
        }

        //verifica o click no botão login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarInsercoes();
            }
        });

        //verifica o click no botão registrar
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Login.this, Registro.class));
            }
        });

    }

}
