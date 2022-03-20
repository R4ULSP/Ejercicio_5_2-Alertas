package es.travelworld.ejercicio52_alertas;

import static es.travelworld.ejercicio52_alertas.tools.References.KEY_USER;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import es.travelworld.ejercicio52_alertas.databinding.ActivityLoginBinding;
import es.travelworld.ejercicio52_alertas.tools.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityLoginBinding binding;
    private User user;
    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result ->{
        if(result.getResultCode() == Activity.RESULT_OK && result.getData() != null){
                user = (User)result.getData().getSerializableExtra(KEY_USER);
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setListeners();
        user = new User();
    }

    private void setListeners() {
        binding.loginForgotPasswordButton.setOnClickListener(this);
        binding.loginNewAccountButton.setOnClickListener(this);
        binding.loginButton.setOnClickListener(this);

        binding.loginInputUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not implemented yet
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateForm();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not implemented yet
            }
        });

        binding.loginInputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not implemented yet
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateForm();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not implemented yet
            }
        });
    }

    private void validateForm() {
        binding.loginButton.setEnabled(false);
        boolean userValidation = false;
        boolean passwordValidation = false;

        if(binding.loginInputUser.getText()!=null && !binding.loginInputUser.getText().toString().equals("")){
            userValidation = true;
        }
        if(binding.loginInputPassword.getText()!=null && !binding.loginInputPassword.getText().toString().equals("")){
            passwordValidation = true;
        }

        if(userValidation && passwordValidation){
            binding.loginButton.setEnabled(true);
        }
    }

    @Override
    public void onClick(View view) {
        if (binding.loginForgotPasswordButton.equals(view)) {
            Snackbar.make(binding.getRoot(), R.string.wip_feature, BaseTransientBottomBar.LENGTH_LONG).show();
        }
        else if (binding.loginNewAccountButton.equals(view)){
            Intent intent = new Intent(this,RegisterActivity.class);
            intent.putExtra(KEY_USER, user);
            activityResultLauncher.launch(intent);
        }
        else if (binding.loginButton.equals(view)){
            login();
        }
    }

    private void login() {
        if(binding.loginInputPassword.getText()==null || binding.loginInputUser.getText()==null){
            Snackbar.make(binding.getRoot(), R.string.login_error, BaseTransientBottomBar.LENGTH_LONG).show();
            return;
        }
        if(binding.loginInputPassword.getText().toString().equals(user.getPassword()) && binding.loginInputUser.getText().toString().equals(user.getName())){
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(KEY_USER, user);
            startActivity(intent);
        }
        else{
            Snackbar.make(binding.getRoot(), R.string.login_error, BaseTransientBottomBar.LENGTH_LONG).show();
        }
    }
}