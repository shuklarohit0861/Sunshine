package com.example.thero.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private ShareActionProvider mShareActionProvider;
    String details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        Bundle extra = getIntent().getExtras();
        if(extra == null)
        {
            return;
        }
         details = extra.getString("WEATHER");
        TextView textView = (TextView) findViewById(R.id.detail_text_view);
        textView.setText(details);
        Log.v("DETAILS",details);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.detail, menu);

        // Inflate the menu; this adds items to the action bar if it is present.
        MenuItem menuItem = menu.findItem(R.id.menu_item_shared);

        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);


        setmShareActionProvider(createIntent());


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



        return super.onOptionsItemSelected(item);
    }


    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            return rootView;
        }
    }

    public void setmShareActionProvider(Intent shareIntent)
    {
        if(mShareActionProvider != null)
        {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    public Intent createIntent()
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,details+"#sunsineapp");
        intent.setType("text/plain");
        return intent;
    }



}
