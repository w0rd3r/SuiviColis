package com.example.ensai.suivicolis;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ensai.suivicolis.Core.Colis;

public class AjouterColis extends AppCompatActivity implements View.OnClickListener{

    Button boutonAjouterColis = null;
    EditText editTextDescription = null;
    EditText editTextReference = null;
    Spinner spinner = null;

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

        spinner =(Spinner) findViewById(R.id.listTransporteur);
        String transporteur =spinner.getSelectedItem().toString();

        Colis newColis = new Colis();
        newColis.setDescription(description);
        newColis.setReference(reference);
        //newColis.setTransporteur(transporteur);

        SQLiteDatabase db = new BaseDonnees(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("numero",reference);
        values.put("nomTransporteur",transporteur);
        values.put("description",description);
        long row=db.insert("Colis",null,values);

        db.close();
        AjouterColis.this.finish();

        Toast.makeText(this,"Colis ajouté",Toast.LENGTH_LONG).show();

    }
}
