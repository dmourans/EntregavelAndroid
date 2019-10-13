package com.br.entregavelandroid.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.br.entregavelandroid.models.Usuario;
import com.br.entregavelandroid.views.Login;

import com.br.entregavelandroid.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.br.entregavelandroid.views.Login.NEW_USER;

public class Registro extends AppCompatActivity {

    private TextInputLayout inputNome;
    private TextInputLayout inputEmail;
    private TextInputLayout inputSenha;
    private TextInputLayout inputSenhaConfirma;
    private Button btnRegistrar;

    private String nome, email, senha, confirmarSenha;
    private static final String  EMAIL_VALIDATOR = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_VALIDATOR);
    private Matcher matcher;

    //Iniciar campos de tela
    public void initViews(){
        inputNome = findViewById(R.id.input_registrar_nome);
        inputEmail = findViewById(R.id.input_registrar_email);
        inputSenha = findViewById(R.id.input_registrar_pass);
        inputSenhaConfirma = findViewById(R.id.input_registrar_confirmar);
        btnRegistrar = findViewById(R.id.button_registrar_registrar);
    }

    //Valida uma senha de tamanho 6 caracteres
    public boolean validarSenha(String senha){
        return senha.length() >= 6;
    }

    //Validar e-mail
    public boolean validarEmail(String email){
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //Validar sequencia de inputs da tela
    public void validarInputs(){
        nome = inputNome.getEditText().getText().toString();
        email = inputEmail.getEditText().getText().toString().trim();
        senha = inputSenha.getEditText().getText().toString().trim();
        confirmarSenha = inputSenhaConfirma.getEditText().getText().toString().trim();

        //validação final para gravar
        boolean eValido = true;

        //validação para nome
        if (nome.isEmpty()){
            inputNome.setError(getString(R.string.nome_vazio));
            eValido = false;
        } else {
            inputNome.setErrorEnabled(false);
        }

        //validação para email, ou vazio ou com metodo criado
        if (!validarEmail(email) || email.isEmpty()){
            inputEmail.setError(getString(R.string.email_nao_valid));
            eValido = false;
        } else {
            inputEmail.setErrorEnabled(false);
            eValido = true;
        }

        //validar senha via metodo
        if (!validarSenha(senha)){
            inputSenha.setError(getString(R.string.senha6Digit));
            eValido = false;
        } else {
            inputSenha.setErrorEnabled(false);
            eValido = true;
        }

        //validar confirmação de senha via metodo
        if (!validarSenha(confirmarSenha)){
            inputSenhaConfirma.setError(getString(R.string.confirSenha6Digit));
            eValido = false;
        } else {
            inputSenhaConfirma.setErrorEnabled(false);
            eValido = true;
        }

        //Validar se senhas digitadas são iguais
        if (eValido){
            if(senha.equals(confirmarSenha)){
                Usuario usuario = new Usuario(nome,email,senha);
                setToBundle(usuario);

            } else {
                inputSenha.setError(getString(R.string.senha_diferente));
                inputSenhaConfirma.setError(getString(R.string.senha_diferente));

                inputNome.setErrorEnabled(false);
                inputEmail.setErrorEnabled(false);
            }
        }


    }

    private void setToBundle(Usuario usuario) {
        Intent intent = new Intent(Registro.this, Login.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(NEW_USER, usuario);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        initViews();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarInputs();
            }
        });

    }
}
