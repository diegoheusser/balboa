package br.udesc.ceavi.cvfm.dao.control;

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
import br.udesc.ceavi.cvfm.model.User;
import br.udesc.ceavi.cvfm.model.Source;

public class SQLiteControlDAO extends SQLiteStandardDAO<Control> implements ControlDAO {

    public SQLiteControlDAO(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return DatabaseDefinitions.TABLE_NAME_CONTROL;
    }

    @Override
    protected String[] getColumnsNames() {
        return DatabaseDefinitions.COLUMNS_NAMES_CONTROL;
    }

    @Override
    protected List<Control> getList(Cursor c) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Control> list = new ArrayList<>();
        if (c != null && c.moveToFirst()){
            do{
                Control control = new Control();
                control.setId(c.getInt(0));
                try {
                    String emissionDate = c.getString(1);
                    String deliveryDate = c.getString(2);
                    control.setEmissionDate(emissionDate.equals("NULL")?null:sdf.parse(emissionDate));
                    control.setDeliveryDate(deliveryDate.equals("NULL")?null:sdf.parse(deliveryDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                control.setPercentage(c.getInt(3));
                control.setWeek(c.getInt(4));
                control.setMonth(c.getInt(5));
                control.setYear(c.getInt(6));
                Source s = new Source();
                s.setId(c.getInt(7));
                s.setDescription(c.getString(c.getColumnIndex("description")));
                s.setLocalization(c.getString(c.getColumnIndex("localization")));
                control.setSource(s);
                User u = new User();
                u.setId(c.getInt(8));
                control.setUser(u);
                list.add(control);
            }while(c.moveToNext());
        }
        return list;
    }

    @Override
    protected ContentValues getContentValues(Control control) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues values = new ContentValues();
        values.put(DatabaseDefinitions.COLUMNS_NAMES_CONTROL[0],control.getId());
        if(control.getEmissionDate()!=null) {
            values.put(DatabaseDefinitions.COLUMNS_NAMES_CONTROL[1], sdf.format(control.getEmissionDate()));
        } else {
            values.put(DatabaseDefinitions.COLUMNS_NAMES_CONTROL[1], "NULL");
        }
        if(control.getDeliveryDate()!=null) {
            values.put(DatabaseDefinitions.COLUMNS_NAMES_CONTROL[2], sdf.format(control.getDeliveryDate()));
        } else {
            values.put(DatabaseDefinitions.COLUMNS_NAMES_CONTROL[2], "NULL");
        }
        values.put(DatabaseDefinitions.COLUMNS_NAMES_CONTROL[3], control.getPercentage());
        values.put(DatabaseDefinitions.COLUMNS_NAMES_CONTROL[4], control.getWeek());
        values.put(DatabaseDefinitions.COLUMNS_NAMES_CONTROL[5], control.getMonth());
        values.put(DatabaseDefinitions.COLUMNS_NAMES_CONTROL[6], control.getYear());
        values.put(DatabaseDefinitions.COLUMNS_NAMES_CONTROL[7], control.getSource().getId());
        values.put(DatabaseDefinitions.COLUMNS_NAMES_CONTROL[8], control.getUser().getId());
        return values;
    }

    @Override
    protected int getId(Control control) {
        return control.getId();
    }

    @Override
    public List<Control> seekAllByResearcher(int researcherID) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String select = "SELECT * FROM control as c JOIN source as s ON c.source_id = s._id WHERE c.researcher_id = ?";

        Cursor cursor = db.rawQuery(select, new String[]{String.valueOf(researcherID)});

        List<Control> list = getList(cursor);

        cursor.close();
        db.close();
        dbHelper.close();

        return list;
    }

}
