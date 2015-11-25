package br.udesc.ceavi.cvfm;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.widget.ListView;

import java.util.List;

import br.udesc.ceavi.cvfm.adapter.ControlAdapter;
import br.udesc.ceavi.cvfm.base.AppContext;
import br.udesc.ceavi.cvfm.model.Control;

public class ControlActivity extends ListActivity implements UpdateListView {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ControlAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppContext.controlActivity = this;
        AppContext.listViewControl = this;
        super.onCreate(savedInstanceState);
        AppContext.CONTEXT = this;
        setContentView(br.udesc.ceavi.cvfm.R.layout.control_activity);
        final List<Control> list =
                Control.seekAllByResearcher(
                        ControlActivity.this,
                        AppContext.USER.getId()
                );
        listView = getListView();
        adapter = new ControlAdapter(ControlActivity.this, list);
        listView.setAdapter(adapter);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.control_activity_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        updateListView();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    private void updateListView(){
        new br.udesc.ceavi.cvfm.webservice.importation.Item().importItems();
        new br.udesc.ceavi.cvfm.webservice.importation.Source().importSources();
        new br.udesc.ceavi.cvfm.webservice.importation.Control()
                .importControlsByUser(AppContext.USER.getId());
        final List<Control> list =
                Control.seekAllByResearcher(
                        ControlActivity.this,
                        AppContext.USER.getId()
                );

        listView = getListView();

        adapter = new ControlAdapter(ControlActivity.this, list);


        listView.setAdapter(adapter);
   }

    @Override
    public void update() {
        final List<Control> list =
                Control.seekAllByResearcher(
                        ControlActivity.this,
                        AppContext.USER.getId()
                );
        listView = getListView();
        adapter = new ControlAdapter(ControlActivity.this, list);
        listView.setAdapter(adapter);
    }

}
