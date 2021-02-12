package com.example.appy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_form extends AppCompatActivity {

    private EditText full_name, user_name, user_email, pass_word, con_pass;
    private RadioGroup gen;

    private FirebaseAuth f_auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        setUpViews();
        this.setTitle("Sign Up");

        f_auth = FirebaseAuth.getInstance();
    }
    private void setUpViews(){
        full_name = findViewById(R.id.f_name);
        user_name = findViewById(R.id.u_name);
        user_email = findViewById(R.id.u_email);
        pass_word = findViewById(R.id.u_pass);
        con_pass = findViewById(R.id.u_conpass);
        gen = findViewById(R.id.rd_grp);
    }

    public void reg_up(View view){
        if(validate()){

            String mail = user_email.getText().toString();

            String pass = pass_word.getText().toString();

            f_auth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Signup_form.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Signup_form.this, Login_form.class));
                    }
                    else{
                        Toast.makeText(Signup_form.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private Boolean validate(){
        Boolean result = false;

        String name = full_name.getText().toString();
        String uname = user_name.getText().toString();
        String uemail = user_email.getText().toString();
        String pass = pass_word.getText().toString();
        String passconfirm = con_pass.getText().toString();

        if(name.isEmpty() || uname.isEmpty() || uemail.isEmpty() || pass.isEmpty() || passconfirm.isEmpty() || gen.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"Please fill remaining part",Toast.LENGTH_SHORT).show();
        }
        else if(!passconfirm.equals(pass)){
            Toast.makeText(this,"Correct confirm password",Toast.LENGTH_SHORT).show();
        }
        else{
            result = true;
        }

        return result;
    }
}