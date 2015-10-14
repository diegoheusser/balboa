package br.udesc.ceavi.cvfm.dao.sqlite;


import br.udesc.ceavi.cvfm.dao.control.ControlDAO;
import br.udesc.ceavi.cvfm.dao.control.SQLiteControlDAO;
import br.udesc.ceavi.cvfm.dao.source.SQLiteSourceDAO;
import br.udesc.ceavi.cvfm.dao.source.SourceDAO;
import br.udesc.ceavi.cvfm.dao.user.SQLiteUserDAO;
import br.udesc.ceavi.cvfm.dao.user.UserDAO;

public class Factory extends br.udesc.ceavi.cvfm.dao.core.Factory {

    @Override
    public UserDAO getUserDAO() {
        return new SQLiteUserDAO(super.context);
    }

    @Override
    public ControlDAO getControlDAO() {
        return new SQLiteControlDAO(super.context);
    }

    @Override
    public SourceDAO getSourceDAO() {
        return new SQLiteSourceDAO(super.context);
    }
}
