package br.udesc.ceavi.cvfm.model;

import android.content.Context;

import java.util.List;

import br.udesc.ceavi.cvfm.dao.core.Factory;
import br.udesc.ceavi.cvfm.dao.source.SourceDAO;

public class Source extends Model {

    private int id;
    private String description;
    private String localization;

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

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public void save(Context context){
        SourceDAO dao = Factory.getInstance(context).getSourceDAO();
        dao.insert(this);
    }

    public void update(Context context){
        SourceDAO dao = Factory.getInstance(context).getSourceDAO();
        dao.update(this);
    }

    public static List<Source> seekAll(Context context){
        SourceDAO dao = Factory.getInstance(context).getSourceDAO();
        return dao.seekAll();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Source source = (Source) o;

        if (id != source.id) return false;
        if (description != null ? !description.equals(source.description) : source.description != null)
            return false;
        return !(localization != null ? !localization.equals(source.localization) : source.localization != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (localization != null ? localization.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", localization='" + localization + '\'' +
                '}';
    }
}
