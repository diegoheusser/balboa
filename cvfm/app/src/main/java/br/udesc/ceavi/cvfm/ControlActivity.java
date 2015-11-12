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

public class ControlActivity extends ListActivity {

    private ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ControlAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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


    /*private class LoadControl extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ControlActivity.this);
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                updateListView();
            } catch(Exception ex){
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            adapter.notifyDataSetChanged();
        }
    }*/
}
