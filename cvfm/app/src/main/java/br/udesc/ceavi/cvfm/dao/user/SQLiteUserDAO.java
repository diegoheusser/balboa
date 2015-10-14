package br.udesc.ceavi.cvfm.dao.user;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.udesc.ceavi.cvfm.dao.core.DatabaseDefinitions;
import br.udesc.ceavi.cvfm.dao.sqlite.DatabaseHelper;
import br.udesc.ceavi.cvfm.dao.standard.SQLiteStandardDAO;
import br.udesc.ceavi.cvfm.model.User;

public class SQLiteUserDAO extends SQLiteStandardDAO<User> implements UserDAO {

    public SQLiteUserDAO(Context context) {
        super(context);
    }

    @Override
    public User seekResearcher(String user, String password) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columnsNames = getColumnsNames();
        Cursor cursor = db.query(
                getTableName(),
                columnsNames,
                "user = ? AND password = ?",
                new String[]{user, password},
                null, null, null);
        User u = null;
        if (cursor != null && cursor.moveToFirst()) {
            u = new User();
            u.setId(cursor.getInt(0));
            u.setName(cursor.getString(1));
            u.setUser(cursor.getString(2));
            u.setPassword(cursor.getString(3));
        }
        cursor.close();
        db.close();
        dbHelper.close();

        return u;
    }

    @Override
    protected String getTableName() {
        return DatabaseDefinitions.TABLE_NAME_USER;
    }

    @Override
    protected String[] getColumnsNames() {
        return DatabaseDefinitions.COLUMNS_NAMES_USER;
    }

    @Override
    protected List<User> getList(Cursor c) {
        List<User> list = new ArrayList<>();
        if(c.moveToFirst()) {
            do {
                User u = new User();
                u.setId(c.getInt(0));
                u.setName(c.getString(1));
                u.setUser(c.getString(2));
                u.setPassword(c.getString(3));
                list.add(u);
            } while (c.moveToNext());
        }
        return list;
    }

    @Override
    protected ContentValues getContentValues(User u) {
        ContentValues values = new ContentValues();
        values.put(DatabaseDefinitions.COLUMNS_NAMES_USER[0], u.getId());
        values.put(DatabaseDefinitions.COLUMNS_NAMES_USER[1], u.getName());
        values.put(DatabaseDefinitions.COLUMNS_NAMES_USER[2], u.getUser());
        values.put(DatabaseDefinitions.COLUMNS_NAMES_USER[3], u.getPassword());
        return values;
    }

    @Override
    protected int getId(User user) {
        return user.getId();
    }
}
