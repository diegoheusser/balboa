package br.udesc.ceavi.cvfm;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(br.udesc.ceavi.cvfm.R.layout.control_activity);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.control_activity_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        final List<Control> list = Control.seekAllByResearcher(ControlActivity.this, AppContext.USER.getId());

        final ListView listView = getListView();

        ControlAdapter adapter = new ControlAdapter(ControlActivity.this, list);

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_control, menu);
        return true;
    }

}
