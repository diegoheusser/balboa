package br.udesc.ceavi.cvfm.base;

import android.content.Context;

import br.udesc.ceavi.cvfm.UpdateListView;
import br.udesc.ceavi.cvfm.adapter.SearchDoneAdapter;
import br.udesc.ceavi.cvfm.model.Control;
import br.udesc.ceavi.cvfm.model.User;

public class AppContext {

    //When use emulator change localhost by 10.0.2.2
    public static final String SERVICE_URL = "http://10.1.1.175:8080/CustoVidaFlorianopolisWS";
    public static User USER = null;
    public static Control CONTROL = null;
    public static Context CONTEXT = null;
    public static UpdateListView listViewDone = null;

}
