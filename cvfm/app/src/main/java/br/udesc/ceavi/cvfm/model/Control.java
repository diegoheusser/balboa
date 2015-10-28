package br.udesc.ceavi.cvfm.model;

import android.content.Context;

import java.util.Date;
import java.util.List;

import br.udesc.ceavi.cvfm.dao.control.ControlDAO;
import br.udesc.ceavi.cvfm.dao.core.Factory;
import br.udesc.ceavi.cvfm.dao.search.SearchDAO;

public class Control extends Model {

    private int id;
    private Date emissionDate;
    private Date deliveryDate;
    private int status;
    private int week;
    private int month;
    private int year;
    private Source source;
    private User user;
    private List<Search> searches;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(Date emissionDate) {
        this.emissionDate = emissionDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Search> getSearches() {
        return searches;
    }

    public void setSearches(List<Search> searches) {
        this.searches = searches;
    }

    public static List<Control> seekAllByResearcher(Context context, int researcherId){
        ControlDAO dao = Factory.getInstance(context).getControlDAO();
        return dao.seekAllByResearcher(researcherId);
    }

    public void save(Context context){
        ControlDAO dao = Factory.getInstance(context).getControlDAO();
        dao.insert(this);
    }

    public void update(Context context){
        ControlDAO dao = Factory.getInstance(context).getControlDAO();
        dao.update(this);
    }

    public void delete(Context context){
        SearchDAO sdao = Factory.getInstance(context).getSearchDAO();
        sdao.deleteByControl(id);
        ControlDAO dao = Factory.getInstance(context).getControlDAO();
        dao.delete(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Control control = (Control) o;

        if (id != control.id) return false;
        if (status != control.status) return false;
        if (week != control.week) return false;
        if (month != control.month) return false;
        if (year != control.year) return false;
        if (emissionDate != null ? !emissionDate.equals(control.emissionDate) : control.emissionDate != null)
            return false;
        if (deliveryDate != null ? !deliveryDate.equals(control.deliveryDate) : control.deliveryDate != null)
            return false;
        if (source != null ? !source.equals(control.source) : control.source != null) return false;
        if (user != null ? !user.equals(control.user) : control.user != null) return false;
        return !(searches != null ? !searches.equals(control.searches) : control.searches != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (emissionDate != null ? emissionDate.hashCode() : 0);
        result = 31 * result + (deliveryDate != null ? deliveryDate.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + week;
        result = 31 * result + month;
        result = 31 * result + year;
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (searches != null ? searches.hashCode() : 0);
        return result;
    }
}
