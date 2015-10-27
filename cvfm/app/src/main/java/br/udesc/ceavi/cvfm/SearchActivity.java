package br.udesc.ceavi.cvfm;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import br.udesc.ceavi.cvfm.base.AppContext;

public class SearchActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle(
                AppContext.CONTROL.getSource().getDescription()
                + " - "
                + AppContext.CONTROL.getSource().getLocalization()
        );

        TabHost tabHost = getTabHost();

        TabSpec tabToDo = tabHost.newTabSpec(getString(R.string.todo));
        tabToDo.setIndicator(getString(R.string.todo));
        Intent toDoIntent = new Intent(this, SearchToDoActivity.class);
        tabToDo.setContent(toDoIntent);

        TabSpec tabDone = tabHost.newTabSpec(getString(R.string.done));
        tabDone.setIndicator(getString(R.string.done));
        Intent doneIntent = new Intent(this, SearchDoneActivity.class);
        tabDone.setContent(doneIntent);

        tabHost.addTab(tabToDo);
        tabHost.addTab(tabDone);

    }


}
