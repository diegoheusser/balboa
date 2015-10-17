package br.udesc.ceavi.cvfm;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.udesc.ceavi.cvfm.base.AppContext;
import br.udesc.ceavi.cvfm.util.MD5;
import br.udesc.ceavi.cvfm.model.User;

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
