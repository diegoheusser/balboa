package br.udesc.ceavi.cvfm;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import br.udesc.ceavi.cvfm.adapter.SearchToDoAdapter;
import br.udesc.ceavi.cvfm.base.AppContext;
import br.udesc.ceavi.cvfm.model.Search;

public class SearchToDoActivity extends ListActivity {

    private ProgressDialog progressDialog;
    private List<Search> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);
        values = new ArrayList<>();
        new LoadSearch().execute();
    }

    private class LoadSearch extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(SearchToDoActivity.this);
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
                    SearchToDoAdapter adapter = new SearchToDoAdapter(
                            SearchToDoActivity.this,
                            values
                    );
                    setListAdapter(adapter);
                }
            });
        }

        @Override
        protected String doInBackground(String... params) {
            values = Search.seekAllToDo(
                    SearchToDoActivity.this,
                    AppContext.CONTROL.getId()
            );
            return null;
        }
    }
}
