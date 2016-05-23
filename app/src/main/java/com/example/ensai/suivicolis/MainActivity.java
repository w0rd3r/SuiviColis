package com.example.ensai.suivicolis;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ensai.suivicolis.Core.Colis;
import com.example.ensai.suivicolis.Core.Transporteur;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Colis> colis = new ArrayList<Colis>();
    Transporteur UPS = new Transporteur();
    Transporteur Chronopost = new Transporteur();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, AjouterColis.class);
                startActivity(intent);
            }
        });

        //A faire : afficher la table SQL dans la listView
        //Autoriser la modification d'une livraion Bob
        //Suppression Bob

/*J'ai essayé de faire en sort que les trucs qui sont dans la base s'affiche. Mais ça marche pas trop ...*/
        SQLiteOpenHelper helper = new BaseDonnees(this);
        SQLiteDatabase readableDB = helper.getReadableDatabase();

        Cursor cursor = readableDB.rawQuery("SELECT * FROM Colis", null);

        while(cursor.moveToNext()){

            Log.e("TAG",cursor.getString(1));
            Log.e("TAG",cursor.getString(0));
            Log.e("TAG",cursor.getString(2));

            String nomTransporteur = cursor.getString(1);
            Transporteur cronopost = new Transporteur();
            Colis newColis = new Colis();

            if(nomTransporteur=="Chronopost"){
                cronopost.setNom("Chronopost");
                cronopost.setURLtransporteur("https://track.aftership.com/chronopost-france/%1");
            }
            if(nomTransporteur=="Colissimo"){
                cronopost.setNom("Colissimo");
                cronopost.setURLtransporteur("https://track.aftership.com/la-poste-colissimo/%1");
            }
            if(nomTransporteur=="Colissimo"){
                cronopost.setNom("UPS");
                cronopost.setURLtransporteur("https://track.aftership.com/ups/%");
            }

            newColis.setReference(cursor.getString(0));
            newColis.setTransporteur(cronopost);
            newColis.setDescription(cursor.getString(2));
            colis.add(newColis);


           /* Cursor cursorTransporteurURL = readableDB.rawQuery("SELECT * FROM TRANS where nomTransporteur =?", selectionArgs);
            cursorTransporteurURL.moveToNext();
            Log.e("TAG2kgjvksdfljvklc",cursorTransporteurURL.getString(0));
            String[] selectionArgs=new String[]{cursor.getString(1)};
            Colis newColis = new Colis();
            Transporteur transpoteur = new Transporteur();

            transpoteur.setNom(cursor.getString(1));
            transpoteur.setURLtransporteur(cursorTransporteurURL.getString(1));
            newColis.setReference(cursor.getString(0));

            newColis.setTransporteur(transpoteur);

            newColis.setDescription(cursor.getString(2));

            colis.add(newColis);
            cursor.close();
            cursorTransporteurURL.close();*/

        }

        cursor.close();

        UPS.setNom("UPS");
        UPS.setURLtransporteur("https://track.aftership.com/ups/%1");
        Chronopost.setNom("Chronopost");
        Chronopost.setURLtransporteur("https://track.aftership.com/chronopost-france/%1");

        Colis colis1 = new Colis();
        colis1.setDescription("Chaussures");
        colis1.setTransporteur(UPS);
        colis1.setReference("1ZAE9558YW00052224");
        colis.add(colis1);

        Colis colis2 = new Colis();
        colis2.setDescription("Carte Mémoire");
        colis2.setTransporteur(Chronopost);
        colis2.setReference("MN262767032JB");
        colis.add(colis2);

        MonAdapter adapter = new MonAdapter(this, colis);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView = (ListView) findViewById(R.id.list);
        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Colis colis = (Colis) parent.getItemAtPosition(position);
                Uri uri = Uri.parse(colis.getURL());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

    }

    /**
     * MENU lors d'un appui long sur l'élément de la liste
     */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.list) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_list, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.modifier:
                // edit stuff here
                return true;
            case R.id.supprimer:
                // remove stuff here
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
