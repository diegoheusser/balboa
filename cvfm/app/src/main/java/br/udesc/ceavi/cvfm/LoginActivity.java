package br.udesc.ceavi.cvfm;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import br.udesc.ceavi.cvfm.base.AppContext;
import br.udesc.ceavi.cvfm.model.Control;
import br.udesc.ceavi.cvfm.model.Source;
import br.udesc.ceavi.cvfm.retrofit.response.ControlResponse;
import br.udesc.ceavi.cvfm.retrofit.response.SourceResponse;
import br.udesc.ceavi.cvfm.retrofit.service.ControlService;
import br.udesc.ceavi.cvfm.retrofit.service.SourceService;
import br.udesc.ceavi.cvfm.util.MD5;
import br.udesc.ceavi.cvfm.model.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class LoginActivity extends Activity {

    private EditText editTextUser;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(br.udesc.ceavi.cvfm.R.layout.activity_login);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    private void initView() {
        editTextUser = (EditText) findViewById(br.udesc.ceavi.cvfm.R.id.login_edit_text_user);
        editTextPassword = (EditText) findViewById(br.udesc.ceavi.cvfm.R.id.login_edit_text_password);
        buttonLogin = (Button) findViewById(br.udesc.ceavi.cvfm.R.id.login_button_login);



        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = User.seekResearcher(LoginActivity.this,
                        editTextUser.getText().toString(),
                        MD5.encrypt(editTextPassword.getText().toString()));
                if(u==null) {
                    Toast.makeText(LoginActivity.this,"Usuário ou senha inválidos",Toast.LENGTH_LONG).show();
                } else {
                    AppContext.USER = u;
                    Toast.makeText(LoginActivity.this,"Logado!",Toast.LENGTH_LONG).show();
                    new ControlAsyncTask().execute();
                }
            }
        });
    }

    private class ControlAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Intent i = new Intent(LoginActivity.this,LoadActivity.class);
            startActivity(i);
        }

        @Override
        protected Void doInBackground(Void... params) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
            RestAdapter restAdapter = new RestAdapter.Builder().
                    setEndpoint(AppContext.SERVICE_URL).setConverter(new GsonConverter(gson)).build();

            SourceService sourceService = restAdapter.create(SourceService.class);
            sourceService.seekAll(new Callback<SourceResponse>() {
                @Override
                public void success(SourceResponse sourceResponse, Response response) {
                    List<Source> newSources = sourceResponse.getSourceList();
                    List<Source> oldSources = Source.seekAll(LoginActivity.this);
                    for(Source s: newSources){
                        if(!oldSources.contains(s)){
                            if(s.onTheList(oldSources)){
                                s.update(LoginActivity.this);
                            } else {
                                s.save(LoginActivity.this);
                            }
                        }
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    error.printStackTrace();
                }
            });

            ControlService service = restAdapter.create(ControlService.class);
            service.seekControlsByResearcher(AppContext.USER.getId(), new Callback<ControlResponse>() {
                @Override
                public void success(ControlResponse controlResponse, Response response) {
                    List<Control> newControls = controlResponse.getControlList();
                    List<Control> oldControls = Control.seekAllByResearcher(
                            LoginActivity.this, AppContext.USER.getId());
                    for(Control c: newControls){
                        if(!oldControls.contains(c)){
                            if(c.onTheList(oldControls)){
                                c.update(LoginActivity.this);
                            } else {
                                c.save(LoginActivity.this);
                            }
                        }
                    }

                }

                @Override
                public void failure(RetrofitError error) {
                    error.printStackTrace();
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent i = new Intent(LoginActivity.this,ControlActivity.class);
            startActivity(i);
        }
    }

}
