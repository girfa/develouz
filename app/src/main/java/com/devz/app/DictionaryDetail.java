package com.devz.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Afrig Aminuddin on 23/08/2017.
 */

public class DictionaryDetail extends AppCompatActivity {
    private com.develouz.view.DictionaryDetail detail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dictionary_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        detail = findViewById(R.id.dictionary_detail);
        detail.data().setIntent(getIntent());
        toolbar.setTitle(detail.data().getTitle());
        toolbar.setSubtitle(detail.data().getSubtitle());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dictionary_detail, menu);
        MenuItem mark = menu.findItem(R.id.detail_bookmark);
        mark.setIcon(detail.data().isBookmarked() ? R.drawable.ic_bookmark_on : R.drawable.ic_bookmark_off);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.detail_bookmark:
                detail.data().switchBookmark();
                supportInvalidateOptionsMenu();
                break;
            case R.id.detail_copy:
                detail.data().copy();
                break;
            default:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        detail.onBackPressed();
        super.onBackPressed();
    }
}
