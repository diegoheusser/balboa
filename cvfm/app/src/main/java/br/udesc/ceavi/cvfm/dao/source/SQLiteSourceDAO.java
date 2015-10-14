package br.udesc.ceavi.cvfm.dao.source;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.udesc.ceavi.cvfm.dao.core.DatabaseDefinitions;
import br.udesc.ceavi.cvfm.dao.standard.SQLiteStandardDAO;
import br.udesc.ceavi.cvfm.model.Source;

public class SQLiteSourceDAO extends SQLiteStandardDAO<Source> implements SourceDAO {

    public SQLiteSourceDAO(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return DatabaseDefinitions.TABLE_NAME_SOURCE;
    }

    @Override
    protected String[] getColumnsNames() {
        return DatabaseDefinitions.COLUMNS_NAMES_SOURCE;
    }

    @Override
    protected List<Source> getList(Cursor c) {
        List<Source> list = new ArrayList<>();
        if (c != null && c.moveToFirst()) {
            do{
                Source s = new Source();
                s.setId(c.getInt(0));
                s.setDescription(c.getString(1));
                s.setLocalization(c.getString(2));
                list.add(s);
            } while(c.moveToNext());
        }
        return list;
    }

    @Override
    protected ContentValues getContentValues(Source source) {
        ContentValues values = new ContentValues();
        values.put(DatabaseDefinitions.COLUMNS_NAMES_SOURCE[0], source.getId());
        values.put(DatabaseDefinitions.COLUMNS_NAMES_SOURCE[1], source.getDescription());
        values.put(DatabaseDefinitions.COLUMNS_NAMES_SOURCE[2], source.getLocalization());
        return values;
    }

    @Override
    protected int getId(Source source) {
        return source.getId();
    }
}
