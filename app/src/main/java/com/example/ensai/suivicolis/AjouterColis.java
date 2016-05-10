package com.example.ensai.suivicolis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ensai.suivicolis.Core.Colis;

public class AjouterColis extends AppCompatActivity implements View.OnClickListener{

    Button boutonAjouterColis = null;
    EditText editTextDescription = null;
    EditText editTextReference = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_colis);

        boutonAjouterColis = (Button) findViewById(R.id.boutonAjouterColis);
        boutonAjouterColis.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        editTextDescription = (EditText) findViewById(R.id.edit_text_description);
        String description = editTextDescription.getText().toString();

        editTextReference = (EditText) findViewById(R.id.edit_text_reference);
        String reference = editTextReference.getText().toString();


        Colis newColis = new Colis();
        newColis.setDescription(description);
    }
}
