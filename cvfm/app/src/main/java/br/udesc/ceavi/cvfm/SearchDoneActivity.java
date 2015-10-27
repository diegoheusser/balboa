package br.udesc.ceavi.cvfm;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import br.udesc.ceavi.cvfm.adapter.SearchDoneAdapter;
import br.udesc.ceavi.cvfm.base.AppContext;
import br.udesc.ceavi.cvfm.model.Search;

public class SearchDoneActivity extends ListActivity {

    private ProgressDialog progressDialog;
    private List<Search> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.done_list);
        values = new ArrayList<>();
        new LoadSearch().execute();
    }

    private class LoadSearch extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(SearchDoneActivity.this);
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    SearchDoneAdapter adapter = new SearchDoneAdapter(
                            SearchDoneActivity.this,
                            values
                    );
                    setListAdapter(adapter);
                }
            });
        }

        @Override
        protected String doInBackground(String... params) {
            values = Search.seekAllDone(
                    SearchDoneActivity.this,
                    AppContext.CONTROL.getId()
            );
            return null;
        }
    }
}
