package br.udesc.ceavi.cvfm.dao.item;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.udesc.ceavi.cvfm.dao.core.DatabaseDefinitions;
import br.udesc.ceavi.cvfm.dao.standard.SQLiteStandardDAO;
import br.udesc.ceavi.cvfm.model.Item;

public class SQLiteItemDAO extends SQLiteStandardDAO<Item> implements ItemDAO {

    public SQLiteItemDAO(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return DatabaseDefinitions.TABLE_NAME_ITEM;
    }

    @Override
    protected String[] getColumnsNames() {
        return DatabaseDefinitions.COLUMNS_NAMES_ITEM;
    }

    @Override
    protected List<Item> getList(Cursor c) {
        List<Item> list = new ArrayList<>();
        if(c != null && c.moveToFirst()){
            do{
                Item i = new Item();
                i.setId(c.getInt(0));
                i.setDescription(c.getString(1));
                i.setIdentifier(c.getInt(2));
                list.add(i);
            }while(c.moveToNext());
        }
        return list;
    }

    @Override
    protected ContentValues getContentValues(Item item) {
        ContentValues values = new ContentValues();
        values.put(DatabaseDefinitions.COLUMNS_NAMES_ITEM[0],item.getId());
        values.put(DatabaseDefinitions.COLUMNS_NAMES_ITEM[1], item.getDescription());
        values.put(DatabaseDefinitions.COLUMNS_NAMES_ITEM[2], item.getIdentifier());
        return values;
    }

    @Override
    protected int getId(Item item) {
        return item.getId();
    }
}
