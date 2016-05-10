package com.example.ensai.suivicolis;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

        List<Colis> colis = new ArrayList<Colis>();
        Transporteur UPS = new Transporteur();
        UPS.setNom("UPS");
        UPS.setURLtransporteur("https://track.aftership.com/ups/");
        Transporteur Chronopost = new Transporteur();
        Chronopost.setNom("Chronopost");
        Chronopost.setURLtransporteur("https://track.aftership.com/chronopost-france/");
        Colis colis1 = new Colis();
        colis1.setDescription("iPhone");
        colis1.setTransporteur("UPS");
        colis1.setReference("1ZAE9558YW00052224");
        colis.add(colis1);
        Colis colis2 = new Colis();
        colis2.setDescription("iPad");
        colis2.setTransporteur("Chronopost");
        colis2.setReference("MN262767032JB");
        colis.add(colis2);
        MonAdapter adapter = new MonAdapter(this, colis);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView = (ListView) findViewById(R.id.list);
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
