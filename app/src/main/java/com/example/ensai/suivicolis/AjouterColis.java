package com.example.ensai.suivicolis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AjouterColis extends AppCompatActivity implements View.OnClickListener{

    Button boutonAjouterColis = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_colis);

        boutonAjouterColis = (Button) findViewById(R.id.boutonAjouterColis);
        boutonAjouterColis.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
