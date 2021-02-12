package com.example.appy;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_form extends AppCompatActivity {

    private EditText mail, pass;

    private FirebaseAuth f_auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        setView();
        f_auth = FirebaseAuth.getInstance();
        FirebaseUser user = f_auth.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(Login_form.this, MainActivity.class));
        }
        this.setTitle("Tinder beta");
    }

    private void setView(){
        mail = findViewById(R.id.uname);
        pass = findViewById(R.id.pass);
    }

    public void on_press_login(View view){
        validate(mail.getText().toString(),pass.getText().toString());
    }

    private void validate(String ent_mail, String passo){

        f_auth.signInWithEmailAndPassword(ent_mail, passo).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(Login_form.this, MainActivity.class));
                }
                else{
                    Toast.makeText(Login_form.this,"Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void sign_up_click(View view) {
        startActivity(new Intent(Login_form.this,Signup_form.class));
    }
}