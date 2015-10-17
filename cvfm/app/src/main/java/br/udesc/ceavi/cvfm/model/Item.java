package br.udesc.ceavi.cvfm.model;

import android.content.Context;

import java.util.List;

import br.udesc.ceavi.cvfm.dao.core.Factory;
import br.udesc.ceavi.cvfm.dao.item.ItemDAO;

public class Item extends Model {

    private int id;
    private String description;
    private int identifier;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public static List<Item> seekAll(Context context){
        ItemDAO dao = Factory.getInstance(context).getItemDAO();
        return dao.seekAll();
    }

    public void save(Context context){
        ItemDAO dao = Factory.getInstance(context).getItemDAO();
        dao.insert(this);
    }

    public void update(Context context){
        ItemDAO dao = Factory.getInstance(context).getItemDAO();
        dao.update(this);
    }
}
