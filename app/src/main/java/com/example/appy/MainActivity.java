package com.example.appy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebase;
    private TextView ser;
    private Button logOut;
    private CardView notes, split, game, converter, prog1, prog2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTheView();
        firebase = FirebaseAuth.getInstance();
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebase.signOut();
                finish();
                startActivity(new Intent(MainActivity.this,Login_form.class));
            }
        });
    }

    public void setTheView(){
        logOut = findViewById(R.id.log_out);
        ser = findViewById(R.id.servi);
        notes = findViewById(R.id.note);
        split = findViewById(R.id.split);
        game = findViewById(R.id.tic);
        converter = findViewById(R.id.convert);
        prog1 = findViewById(R.id.think1);
        prog2 = findViewById(R.id.think2);
    }



}