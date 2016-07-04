package com.example.thero.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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
            Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(intent);
        }
        if(id == R.id.action_map)
        {
           optGeoLocation();
        }


        return super.onOptionsItemSelected(item);
    }

    public void optGeoLocation()
    {
        SharedPreferences sharedPreferencesMap = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String location = sharedPreferencesMap.getString(getString(R.string.pref_location_key),getString(R.string.pref_location_default));
        Uri mapUri = Uri.parse("geo:0,0?q="+location);
        Intent map = new Intent(Intent.ACTION_VIEW);
        map.setData(mapUri);
        if(map.resolveActivity(getPackageManager()) != null)
        {
            startActivity(map);
        }

    }
}
