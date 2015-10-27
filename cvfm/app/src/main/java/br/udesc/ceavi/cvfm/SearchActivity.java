package br.udesc.ceavi.cvfm;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class SearchActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        TabHost tabHost = getTabHost();

        TabSpec tabToDo = tabHost.newTabSpec(getString(R.string.todo));
        Intent toDoIntent = new Intent(this, SearchToDoActivity.class);
        tabToDo.setContent(toDoIntent);

        TabSpec tabDone = tabHost.newTabSpec(getString(R.string.done));
        Intent doneIntent = new Intent(this, SearchDoneActivity.class);
        tabDone.setContent(doneIntent);

        tabHost.addTab(tabToDo);
        tabHost.addTab(tabDone);
    }


}
