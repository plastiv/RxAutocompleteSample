package com.github.plastiv.rxautocompletesample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

public class AddressAutocompleteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_autocomplete);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_address_autocomplete, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        item.expandActionView(); // get focus

        SearchView searchView = (SearchView) item.getActionView();

        // TODO: set query listener
        return true;
    }
}
