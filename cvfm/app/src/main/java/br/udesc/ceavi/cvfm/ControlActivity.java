package br.udesc.ceavi.cvfm;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.udesc.ceavi.cvfm.adapter.ControlAdapter;
import br.udesc.ceavi.cvfm.base.AppContext;
import br.udesc.ceavi.cvfm.model.Control;

public class ControlActivity extends ListActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ControlAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppContext.CONTEXT = this;
        setContentView(br.udesc.ceavi.cvfm.R.layout.control_activity);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.control_activity_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new br.udesc.ceavi.cvfm.webservice.importation.Item().importItems();
                        new br.udesc.ceavi.cvfm.webservice.importation.Source().importSources();
                        new br.udesc.ceavi.cvfm.webservice.importation.Control()
                                .importControlsByUser(AppContext.USER.getId());
                        List<Control> controls =
                                Control.seekAllByResearcher(
                                        ControlActivity.this,
                                        AppContext.USER.getId()
                                );
                        updateListView();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        updateListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_control, menu);
        return true;
    }

    private void updateListView(){
        final List<Control> list =
                Control.seekAllByResearcher(
                        ControlActivity.this,
                        AppContext.USER.getId()
                );

        listView = getListView();

        adapter = new ControlAdapter(ControlActivity.this, list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppContext.CONTROL = list.get(position);
                Intent it = new Intent(ControlActivity.this, SearchActivity.class);
                startActivity(it);
            }
        });
    }

}
