package br.udesc.ceavi.cvfm.dao.control;

import java.util.List;

import br.udesc.ceavi.cvfm.dao.standard.StandardDAO;
import br.udesc.ceavi.cvfm.model.Control;

public interface ControlDAO extends StandardDAO<Control> {

    List<Control> seekAllByResearcher(int userID);

}
