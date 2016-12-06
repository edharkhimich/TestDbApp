package com.khimich.testdbapp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.khimich.testdbapp.R;
import com.khimich.testdbapp.adapters.MyCursorAdapter;
import com.khimich.testdbapp.db.DataBaseHelper;
import com.khimich.testdbapp.listeners.EndScrollListener;
import com.khimich.testdbapp.model.Contact;
import com.khimich.testdbapp.utils.Constants;
import com.khimich.testdbapp.utils.MyCursorLoader;

import java.util.ArrayList;
import java.util.List;

import static com.khimich.testdbapp.utils.Constants.KEY_LS_NAME;
import static com.khimich.testdbapp.utils.Constants.KEY_NAME;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private DataBaseHelper dbHelper;
    private ListView listView;
    private List<Contact> fullList;
    private ArrayList<Contact> list;
    private MyCursorAdapter listAdapter;
    private int firstVisItem;
    private int bufferLimit = 50;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = getIntent();
        if (intent.getAction().equals(Constants.SETTINGS_ACTION)) {
            bufferLimit = intent.getIntExtra(Constants.KEY, Constants.DEFAULT_VALUE);
        }
        dbHelper = new DataBaseHelper(this, bufferLimit);
        fullList = new ArrayList<>();
        list = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);

        if (dbHelper.isEmptyDb()) {
            dbHelper.addContact();
        }
        listView.setOnScrollListener(new EndScrollListener(0) {
            @Override
            public void loadMore(int totalItemsCount, int firstvisibleItem) {
                firstVisItem = firstvisibleItem;
                getSupportLoaderManager().restartLoader(0, null, MainActivity.this);
            }
        });
        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new MyCursorLoader(this, dbHelper);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Contact contact;
        try {
            while (data.moveToNext()) {
                contact = new Contact();
                contact.setName(data.getString(data.getColumnIndex(KEY_NAME)));
                contact.setSurname(data.getString(data.getColumnIndex(KEY_LS_NAME)));
                list.add(contact);
            }
        } finally {
            data.close();
        }

        fullList.addAll(list);
        list.clear();

        listAdapter = new MyCursorAdapter(fullList, getApplicationContext());
        listView.setAdapter(listAdapter);
        listView.setSelection(firstVisItem);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        return true;
    }
}

