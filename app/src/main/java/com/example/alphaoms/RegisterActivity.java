package com.example.alphaoms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    Button button_register;
    EditText editTextUsername, editTextEmail, editTextPassword, editTextConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        button_register = findViewById(R.id.buttonRegister);
        editTextUsername = findViewById(R.id.editTextRegUsername);
        editTextEmail = findViewById(R.id.editTextRegEmail);
        editTextPassword = findViewById(R.id.editTextRegPassword);
        editTextConfirmPassword = findViewById(R.id.editTextRegConfirmPassword);

        button_register.setOnClickListener(new View.OnClickListener() {
            final String username = editTextUsername.getText().toString();
            final String email = editTextEmail.getText().toString();
            final String password = editTextPassword.getText().toString();
            final String cnf_password = editTextConfirmPassword.getText().toString();
            final Sqldb db =  new Sqldb(getApplicationContext(),"AlphaOMS",null,1);

            @Override
            public void onClick(View view) {
                // we are unable to get any data from the username section the activity ???
                if(username.isEmpty() || email.isEmpty() || password.isEmpty() || cnf_password.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please enter all your details", Toast.LENGTH_SHORT).show();
                }else if(password.equals(cnf_password)){
                    db.register(username,email,password);
                    Toast.makeText(RegisterActivity.this, "Record Inserted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }else{
                    Toast.makeText(RegisterActivity.this, "Your password and confirm Password didn't match pls try again !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}