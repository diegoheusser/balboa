package br.udesc.ceavi.cvfm;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import br.udesc.ceavi.cvfm.base.AppContext;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppContext.CONTEXT = this;
        Log.i("SplashActivity","onCreate");
        setContentView(br.udesc.ceavi.cvfm.R.layout.activity_splash);
        new ResearchersAsyncTask().execute();
    }

    private class ResearchersAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            new br.udesc.ceavi.cvfm.webservice.importation.User().importUsers();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(i);
        }
    }
}
