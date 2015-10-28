package br.udesc.ceavi.cvfm.dao.search;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.udesc.ceavi.cvfm.dao.core.DatabaseDefinitions;
import br.udesc.ceavi.cvfm.dao.sqlite.DatabaseHelper;
import br.udesc.ceavi.cvfm.dao.standard.SQLiteStandardDAO;
import br.udesc.ceavi.cvfm.model.Control;
import br.udesc.ceavi.cvfm.model.Item;
import br.udesc.ceavi.cvfm.model.Search;

public class SQLiteSearchDAO extends SQLiteStandardDAO<Search> implements SearchDAO {

    public SQLiteSearchDAO(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return DatabaseDefinitions.TABLE_NAME_SEARCH;
    }

    @Override
    protected String[] getColumnsNames() {
        return DatabaseDefinitions.COLUMNS_NAMES_SEARCH;
    }

    @Override
    protected List<Search> getList(Cursor c) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Search> list = new ArrayList<>();
        if(c != null && c.moveToFirst()){
            do{
                Search s = new Search();
                s.setId(c.getInt(0));
                try{
                    String oldDate = c.getString(1);
                    String newDate = c.getString(5);
                    s.setOldDate(oldDate.equals("NULL")?null:sdf.parse(oldDate));
                    s.setNewDate(newDate.equals("NULL")?null:sdf.parse(newDate));
                }catch (ParseException e){
                    e.printStackTrace();
                }
                s.setOldBrand(c.getString(2));
                s.setOldSpecification(c.getString(3));
                s.setOldPrice(c.getDouble(4));
                s.setNewBrand(c.getString(6));
                s.setNewSpecification(c.getString(7));
                s.setNewPrice(c.getDouble(8));
                Item i  = new Item();
                i.setId(c.getInt(9));
                i.setDescription(c.getString(11));
                i.setIdentifier(c.getInt(12));
                Control ctr = new Control();
                ctr.setId(c.getInt(10));
                s.setItem(i);
                s.setControl(ctr);
                list.add(s);
            }while(c.moveToNext());
        }
        return list;
    }

    @Override
    protected ContentValues getContentValues(Search search) {
        ContentValues values = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(search.getOldDate() != null){
            values.put(DatabaseDefinitions.COLUMNS_NAMES_SEARCH[1],sdf.format(search.getOldDate()));
        } else {
            values.put(DatabaseDefinitions.COLUMNS_NAMES_SEARCH[1], "NULL");
        }
        values.put(DatabaseDefinitions.COLUMNS_NAMES_SEARCH[2],search.getOldBrand());
        values.put(DatabaseDefinitions.COLUMNS_NAMES_SEARCH[3],search.getOldSpecification());
        values.put(DatabaseDefinitions.COLUMNS_NAMES_SEARCH[4],search.getOldPrice());
        if(search.getNewDate() != null){
            values.put(DatabaseDefinitions.COLUMNS_NAMES_SEARCH[5],sdf.format(search.getNewDate()));
        } else {
            values.put(DatabaseDefinitions.COLUMNS_NAMES_SEARCH[5], "NULL");
        }
        values.put(DatabaseDefinitions.COLUMNS_NAMES_SEARCH[6],search.getNewBrand());
        values.put(DatabaseDefinitions.COLUMNS_NAMES_SEARCH[7],search.getNewSpecification());
        values.put(DatabaseDefinitions.COLUMNS_NAMES_SEARCH[8],search.getNewPrice());
        values.put(DatabaseDefinitions.COLUMNS_NAMES_SEARCH[9],search.getItem().getId());
        values.put(DatabaseDefinitions.COLUMNS_NAMES_SEARCH[10],search.getControl().getId());
        return values;
    }

    @Override
    protected int getId(Search search) {
        return search.getId();
    }

    @Override
    public List<Search> seekAllToDo(int controlid) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String select = "SELECT s.*, i.description, i.identifier FROM search as s JOIN item as i ON s.item_id = i._id WHERE s.control_id = ? AND new_price == 0.0 ORDER BY _id";

        Cursor cursor = db.rawQuery(select, new String[]{String.valueOf(controlid)});

        List<Search> list = getList(cursor);

        cursor.close();
        db.close();
        dbHelper.close();

        return list;
    }

    @Override
    public List<Search> seekAllDone(int controlid) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String select = "SELECT s.*, i.description, i.identifier FROM search as s JOIN item as i ON s.item_id = i._id WHERE s.control_id = ? AND new_price > 0.0 ORDER BY _id DESC";

        Cursor cursor = db.rawQuery(select, new String[]{String.valueOf(controlid)});

        List<Search> list = getList(cursor);

        cursor.close();
        db.close();
        dbHelper.close();

        return list;
    }

    @Override
    public void deleteByControl(int controlid) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.delete(getTableName(),getColumnsNames()[10]+" = ?",new String[]{controlid+""});
        db.close();
        dbHelper.close();
    }
}
