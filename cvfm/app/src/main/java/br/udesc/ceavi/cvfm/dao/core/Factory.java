package br.udesc.ceavi.cvfm.dao.core;

import android.content.Context;

import br.udesc.ceavi.cvfm.dao.control.ControlDAO;
import br.udesc.ceavi.cvfm.dao.source.SourceDAO;
import br.udesc.ceavi.cvfm.dao.user.UserDAO;

public abstract class Factory {

    protected static Context context;

    public static Factory getInstance(Context context){
        Factory.context = context;
        return new br.udesc.ceavi.cvfm.dao.sqlite.Factory();
    }

    public abstract UserDAO getUserDAO();

    public abstract ControlDAO getControlDAO();

    public abstract SourceDAO getSourceDAO();

}
