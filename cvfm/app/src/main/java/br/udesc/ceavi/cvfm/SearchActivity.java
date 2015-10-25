package br.udesc.ceavi.cvfm;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListView;

import br.udesc.ceavi.cvfm.adapter.SearchAdapter;
import br.udesc.ceavi.cvfm.base.AppContext;

public class SearchActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String title = AppContext.CONTROL.getSource().getDescription()
                + " - "
                + AppContext.CONTROL.getSource().getLocalization();
        this.setTitle(title);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final ListView listView = getListView();
        listView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        SearchAdapter adapter = new SearchAdapter(
                SearchActivity.this,
                AppContext.CONTROL.getId()
        );

        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
