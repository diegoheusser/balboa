package br.udesc.ceavi.cvfm;


import android.app.Activity;
import android.os.Bundle;

public class LoadActivity  extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(br.udesc.ceavi.cvfm.R.layout.loading);
    }
}
