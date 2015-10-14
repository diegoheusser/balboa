package br.udesc.ceavi.cvfm;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import br.udesc.ceavi.cvfm.base.AppContext;
import br.udesc.ceavi.cvfm.model.User;
import br.udesc.ceavi.cvfm.retrofit.response.ResearcherResponse;
import br.udesc.ceavi.cvfm.retrofit.service.ResearcherService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("SplashActivity","onCreate");
        setContentView(br.udesc.ceavi.cvfm.R.layout.activity_splash);
        new ResearchersAsyncTask().execute();
    }

    private class ResearchersAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(AppContext.SERVICE_URL).build();

            ResearcherService service = restAdapter.create(ResearcherService.class);

            service.seekResearchers( new Callback<ResearcherResponse>() {
                @Override
                public void success(ResearcherResponse obj, Response response) {
                    List<User> oldResearchers = User.seekAll(SplashActivity.this);
                    List<User> newResearchers = obj.getResearcherList();
                    System.out.println(oldResearchers.toString());
                    System.out.println(newResearchers.toString());
                    for(User u: newResearchers){
                        if(!oldResearchers.contains(u)){
                            if(u.onTheList(oldResearchers)){
                                u.update(SplashActivity.this);
                            } else {
                                u.save(SplashActivity.this);
                            }
                        }
                    }

                }

                @Override
                public void failure(RetrofitError error) {
                    System.out.println(error.toString());
                    Toast.makeText(SplashActivity.this, "Offline", Toast.LENGTH_SHORT).show();
                }
            });
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
