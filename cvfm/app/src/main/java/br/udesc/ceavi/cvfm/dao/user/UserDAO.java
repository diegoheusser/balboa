package br.udesc.ceavi.cvfm.dao.user;

import br.udesc.ceavi.cvfm.dao.standard.StandardDAO;
import br.udesc.ceavi.cvfm.model.User;


public interface UserDAO extends StandardDAO<User> {

    User seekResearcher(String user, String password);

}