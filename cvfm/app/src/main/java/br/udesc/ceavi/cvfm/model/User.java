package br.udesc.ceavi.cvfm.model;

import android.content.Context;

import java.util.List;

import br.udesc.ceavi.cvfm.dao.core.Factory;
import br.udesc.ceavi.cvfm.dao.user.UserDAO;

public class User  extends Model{

    private int id;
    private String name;
    private String user;
    private String password;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void save(Context context){
        UserDAO dao = Factory.getInstance(context).getUserDAO();
        dao.insert(this);
    }

    public static List<User> seekAll(Context context){
        UserDAO dao = Factory.getInstance(context).getUserDAO();
        return dao.seekAll();
    }

    @Override
    public String toString() {
        return "Researcher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user1 = (User) o;

        if (id != user1.id) return false;
        if (name != null ? !name.equals(user1.name) : user1.name != null) return false;
        if (user != null ? !user.equals(user1.user) : user1.user != null) return false;
        return !(password != null ? !password.equals(user1.password) : user1.password != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public void update(Context context) {
        UserDAO dao = Factory.getInstance(context).getUserDAO();
        dao.update(this);
    }

    public static User seekResearcher(Context context, String user, String password){
        UserDAO dao = Factory.getInstance(context).getUserDAO();
        return dao.seekResearcher(user, password);
    }
}
